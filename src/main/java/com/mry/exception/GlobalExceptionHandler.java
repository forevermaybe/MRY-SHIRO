package com.mry.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * @ResponseBody
	 * 
	 * @ExceptionHandler(value = Exception.class) public String
	 * exceptionHandler(HttpServletRequest request, Exception e) { return
	 * getStackStrac(e); }
	 */

	@ExceptionHandler(value = Exception.class)
	public String errorHtml(HttpServletRequest request, Exception e) {
		request.setAttribute("message", getStackStrac(e));
		request.setAttribute("url", request.getRequestURL().toString());
		return "error500";
	}

	private String getStackStrac(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
