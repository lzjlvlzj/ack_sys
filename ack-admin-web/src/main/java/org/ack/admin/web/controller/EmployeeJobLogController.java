package org.ack.admin.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.admin.web.controller.view.ExcelBuilderView;
import org.ack.admin.web.template.CalendarEvent;
import org.ack.admin.web.template.EmployeeLogExcel;
import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.persist.page.Page;
import org.ack.pojo.EmployeeJobLog;
import org.ack.pojo.Project;
import org.ack.pojo.User;
import org.ack.service.EmployeeJobLogService;
import org.ack.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/job")
@Controller
public class EmployeeJobLogController extends
		AckPageController<EmployeeJobLog, Long> {

	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeJobLogController.class);

	@Autowired
	private EmployeeJobLogService employeeJobServiceImpl;

	@Override
	public AckMapperService<EmployeeJobLog, Long> getService() {
		return employeeJobServiceImpl;
	}

	@RequestMapping("/statistics/ui")
	@AckPermission(value="job:statistics")
	public String employeeStatisticsUI() {
		return "employeeJob/employeeJobStatistics";
	}

	@RequestMapping("/log/statistics")
	@AckPermission(value="job:statistics")
	@ResponseBody
	public DataTableTemplate<EmployeeJobLog> findStatistics(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			EmployeeJobLog employeeJobLog,
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "1") int start,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createTime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		/**/
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getCurrentUser(request);
		boolean b = onlyDepartment(user, request);
		if (b) {
			map.put("departmentId", user.getDepartmentId());
		}
		map.put("cacheStatus", "1");
		DataTableTemplate<EmployeeJobLog> t = super.dataTable(request,
				response, model, map, employeeJobLog, start, count, draw,
				orderColumn, orderType);
		return t;
	}

	@RequestMapping("/log")
	@AckPermission(value="job:log")
	public String jobLogCalendar() {
		return "employeeJob/employeeJob";
	}

	@RequestMapping("/project")
	@AckPermission(value="job:log")
	@ResponseBody
	public List<Project> findProjectList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = getCurrentUser(request);
		return employeeJobServiceImpl.findProjectList(user);
	}
	
	/**
	 * 查询缓存日志(可以作为下周内容)
	 * <p>
	 * 默认查询7条
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employeeJobLog
	 * @param draw
	 * @param start
	 * @param count
	 * @param orderColumn
	 * @param orderType
	 * @return
	 */
	@RequestMapping("/cache/list")
	@AckPermission(value="job:log")
	@ResponseBody
	public Page<EmployeeJobLog> findCacheLogList(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			EmployeeJobLog employeeJobLog,
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "1") int start,
			@RequestParam(required = false, defaultValue = "7") int count,
			@RequestParam(required = false, defaultValue = "createTime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cacheStatus", "0");
		User user = getCurrentUser(request);
		map.put("userId", user.getId());
		map.put("flag", 1);
		// 计算当前页，构造page
		int currentPage = start / count + 1;
		Page<EmployeeJobLog> page = super.getPage(request, map, employeeJobLog,
				currentPage, count, orderColumn, orderType);
		page = super.findPage(page);
		return page;
	}


	/**
	 * 展示日志到fullCalendar
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employeeJobLog
	 * @param draw
	 * @param startPage
	 * @param count
	 * @param orderColumn
	 * @param orderType
	 * @return
	 */
	@RequestMapping("/log/list")
	@ResponseBody
	@AckPermission(value="job:log")
	public List<CalendarEvent> findLogList(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			EmployeeJobLog employeeJobLog,
			String start,
			String end,
			String timezone,/* 时区 */
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "1") int startPage,
			@RequestParam(required = false, defaultValue = "42") int count,/* 默认查询的一页的数量 */
			@RequestParam(required = false, defaultValue = "createTime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		List<CalendarEvent> list = new ArrayList<CalendarEvent>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cacheStatus", 1);
		User user = getCurrentUser(request);
		map.put("userId", user.getId());
		map.put("startTime", start);
		map.put("endTime", end);
		// 计算当前页，构造page
		int currentPage = startPage / count + 1;
		Page<EmployeeJobLog> page = super.getPage(request, map, employeeJobLog,
				currentPage, count, orderColumn, orderType);
		page = super.findPage(page);
		list = changeEmployeeJobLog2CalendarEvent(page.getResult());
		return list;
	}

	private List<CalendarEvent> changeEmployeeJobLog2CalendarEvent(
			List<EmployeeJobLog> result) {
		List<CalendarEvent> list = new ArrayList<CalendarEvent>();
		if (null == result || result.size() == 0) {
			return list;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		for (int i = 0; i < result.size(); i++) {
			EmployeeJobLog log = result.get(i);
			CalendarEvent ce = new CalendarEvent();
			ce.setId(log.getId().toString());
			ce.setBackgroundColor(log.getColor());
			ce.setBorderColor(log.getColor());
			String start = sdf.format(log.getStartTime());
			String end = sdf.format(log.getEndTime());
			ce.setStart(start);
			ce.setEnd(end);
			ce.setTitle(log.getContent());
			list.add(ce);
		}
		return list;
	}
    
	@AckPermission(value="job:log")
	@RequestMapping("/log/edit/ui")
	public String editUI() {
		return "employeeJob/employeeJobEdit";
	}
    
	@AckPermission(value="job:log")
	@RequestMapping("/log/update")
	@ResponseBody
	public Integer update(HttpServletRequest request,
			HttpServletResponse response, Model model,
			EmployeeJobLog employeeJobLog, Integer flag) {
		Long id = employeeJobLog.getId();
		if (logger.isDebugEnabled()) {
			logger.debug("修改id : {}", id);
		}
		Date startTime = null;
		Date endTime = null;
		if (null != flag) {
			if (flag == 0) {
				String t = request.getParameter("time");
				Long time = Long.parseLong(t);
				Date d = new Date();
				d.setTime(time);
				startTime = d;
				endTime = d;
				employeeJobLog.setCacheStatus(1);
			}
			if (flag == 1) {
				String start = request.getParameter("st");
				String end = request.getParameter("et");
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss");
				try {
					startTime = sdf.parse(start);
				} catch (ParseException e) {
					logger.error("日期格式化出错", e);
					return 0;
				}
				try {
					endTime = sdf.parse(end);
				} catch (ParseException e) {
					logger.error("日期格式化出错", e);
					return 0;
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("修改开始时间", startTime.toString());
				logger.debug("修改结束时间", endTime.toString());
			}
			employeeJobLog.setStartTime(startTime);
			employeeJobLog.setEndTime(endTime);
		}

		int r = employeeJobServiceImpl.update(employeeJobLog);
		return r;

	}

	@AckPermission(value="job:log")
	@RequestMapping("/log/del/{id}")
	@ResponseBody
	public Integer delete(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.debug("删除id : {}", id);
		}
		return employeeJobServiceImpl.deleteById(id);
	}

	@AckPermission(value="job:log")
	@RequestMapping("/log/del")
	@ResponseBody
	public Integer deleteByIds(HttpServletRequest request,
			HttpServletResponse response, Model model, String logIds) {
		String[] ids = logIds.split(",");
		if (logger.isDebugEnabled()) {
			for (String id : ids) {
				logger.debug("删除id : {}", id);
			}
		}
		return employeeJobServiceImpl.deleteByIds(ids);
	}

	@AckPermission(value="job:log")
	@RequestMapping("/log/cache/add")
	@ResponseBody
	public Long insertCache(HttpServletRequest request,
			HttpServletResponse response, Model model,
			EmployeeJobLog employeeJobLog) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建日志缓存");
		}
		// 设置为缓存
		employeeJobLog.setCacheStatus(0);
		// 设置创建缓存时间
		employeeJobLog.setCreateTime(new Date());
		// 设置用户
		User user = getCurrentUser(request);
		employeeJobLog.setUserId(user.getId());
		// 设置部门
		employeeJobLog.setDepartmentId(user.getDepartmentId());
		Long n = employeeJobServiceImpl.insertCache(employeeJobLog);
		if (logger.isDebugEnabled()) {
			logger.debug("插入结果", n);
		}
		return n;
	}
	
	/**
	 * 导出日志
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	@AckPermission(value="job:export")
	public ModelAndView exportExcel(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if(logger.isDebugEnabled()){
			logger.debug("导出excel");
		}
		Map<String, Object> condition = getExportExcelCondition();
		User user = getCurrentUser(request);
		boolean b = onlyDepartment(user, request);
		if (b) {
			condition.put("departmentId", user.getDepartmentId());
		}
		List<EmployeeJobLog> logList = employeeJobServiceImpl.findExportList(condition);
		List<EmployeeLogExcel> list = change2EmployeeLogExcel(logList);
		Map<String, Object> map = new HashMap<String, Object> ();
		String[] headers = new String[] { "序号", "日期", "部门名称", "员工名称", "日志内容", "评审系数" };
		String sheetName = "员工日志";
		map.put("logList", list);
		map.put("fileName", "员工日志.xlsx");
		map.put("headers", headers);
		map.put("sheetName", sheetName);
		ModelAndView mv = new ModelAndView(new ExcelBuilderView() , map);
		return mv;
	}

	private List<EmployeeLogExcel> change2EmployeeLogExcel(
			List<EmployeeJobLog> logList) {
		if(null == logList){
			logger.error("查询导出信息出错!");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeLogExcel> list = new ArrayList<EmployeeLogExcel>();
		int len = logList.size();
		EmployeeLogExcel ee = null;
		for(int i = 0; i < len; i++){
			EmployeeJobLog el = logList.get(i);
			ee = new EmployeeLogExcel();
			ee.setDepartmentName(el.getDepartmentName());
			ee.setUserName(el.getRealName());
			String dateTxt = sdf.format(el.getStartTime());
			ee.setDate(dateTxt);
			ee.setLogContent(el.getContent());
			ee.setCoefficient(0.5);
			list.add(ee);
		}
		
		return list;
	}

	private static Map<String, Object> getExportExcelCondition() {
		Map<String, Object> map = new HashMap<String, Object> ();
		map.put("cacheStatus", "1");
		//设置开始日期和结束日期
		String startTime = CalendarUtil.getLogStartDay();
		String endTime = CalendarUtil.getLogEndDay();
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		return map;
	}

}
