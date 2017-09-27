package org.ack.admin.web.template;

/**
 * 导出excel日志用
 * 
 * @author ack
 *
 */
public class EmployeeLogExcel {

	private String departmentName;         // 部门名称
	private String userName;               // 员工名称
	private String logContent;             // 日志内容
	private String date;                   // 日期
	private double coefficient;            // 评审系数

	public EmployeeLogExcel() {

	}

	public EmployeeLogExcel(String departmentName, String userName,
			String logContent, String date, double coefficient) {
		this.departmentName = departmentName;
		this.userName = userName;
		this.logContent = logContent;
		this.date = date;
		this.coefficient = coefficient;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
}
