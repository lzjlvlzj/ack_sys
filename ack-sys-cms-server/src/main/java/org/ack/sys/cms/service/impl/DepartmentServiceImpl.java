package org.ack.sys.cms.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.base.util.FileUtil;
import org.ack.sys.cms.service.DictionaryService;
import org.ack.sys.cms.service.UploadService;
import org.ack.sys.persist.mapper.DepartmentMapper;
import org.ack.sys.pojo.Department;
import org.ack.sys.cms.service.DepartmentService;
import org.ack.sys.pojo.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用戶邏輯
 *
 * @author ack
 */
@Service
public class DepartmentServiceImpl extends PageServiceImpl<Department, Long> implements DepartmentService {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    private DepartmentMapper departmentMapper;
    private static final String DEPT_IMG_PATH_KEY = "dept_img";
    @Autowired
    private DictionaryService dictionaryServiceImpl;
    @Autowired
    private UploadService uploadServiceImpl;


    @Override
    protected PageDao<Department, Long> getPageDao() {
        logger.debug("mapper is： {}", departmentMapper);
        return departmentMapper;
    }

    @Override
    public ResponseResult upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        logger.debug("原始文件名称:{}", originalFilename);
        boolean b = FileUtil.checkSuffix(originalFilename, Content.DEFAULT_IMG_SUFFIX);
        if (!b) {
            return new ResponseResult(400, "格式不正确", null);
        }
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        Dictionary dictionary = dictionaryServiceImpl.findByKey(DEPT_IMG_PATH_KEY);
        String path = dictionary.getValue();
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        path = path + FileUtil.createFileNameByUUID() + suffix;
        logger.debug("部门大图的相对路径: {}", path);
        return uploadServiceImpl.upload(path, file);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insert(Department t) {
        Department dbDept = findByName(t.getName());
        if (null != dbDept) {
            logger.debug("部门:{}已经存在", t.getName());
            return -1;
        }
        t.setDeleteStatus(0);
        return super.insert(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Department findByName(String name) {
        return departmentMapper.findByName(name);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int batchDelete(List<Department> list) {
        int size = list.size();
        int r = 0;
        for (int i = 0; i < size; i++) {
            Department dept = list.get(i);
            logger.debug("部门id : {}", dept.getId());
            dept.setDeleteStatus(1);
            int rt = update(dept);
            r = r + rt;
        }
        logger.debug("需要修改的数据为{}条,实际修改{}条", size, r);
        return r;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Department> findTree() {
        List<Department> list = departmentMapper.findAll();
        List<Department> deptList = new ArrayList<Department>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Department dept = list.get(i);
            if (dept.getParentId() == 0) {
                deptList.add(dept);
            }
        }

        findChildren(deptList, list);
        return deptList;
    }

    private void findChildren(List<Department> deptList, List<Department> list) {
        for (Department dept : deptList) {
            Long id = dept.getId();
            List<Department> children = new ArrayList<Department>();
            for (Department dt : list) {
                if (dt.getParentId() == id) {
                    children.add(dt);
                }
            }
            dept.setChildren(children);
            findChildren(children, list);
        }

    }


}
