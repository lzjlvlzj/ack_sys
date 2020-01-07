package org.ack.sys.cms.web.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class GlobalExceptionHandler {
    
    /**
     * 全局异常捕获
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ModelMap handleException(Exception e) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("msg", "后台异常捕获");
        modelMap.addAttribute("Exception", e);
        return modelMap;
    }
}