package org.ack.sys.base.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Validation {

	/** 获得校验结果
	 * @param result
	 * @return
	 */
	public static ResponseResult getValidationResult(BindingResult result) {
		boolean b = result.hasErrors();
		int code = 500;
		String msg = "";
		Object data = null;
		ResponseResult responseResult = null;
		if (b) {
			code = 400;
			FieldError fe = result.getFieldError();
			msg = fe.getDefaultMessage();
			responseResult = new ResponseResult(code, msg, data);
		}
		return responseResult;
	}
}
