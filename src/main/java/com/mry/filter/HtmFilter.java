package com.mry.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class HtmFilter extends OncePerRequestFilter {

	private static final String DEFAULT_HTM_PATTER = "/(.+)\\.htm$";

	private static final String DEFAULT_PREFIX = "/WEB-INF/jsp/";

	private static final String DeFAULT_SUFFIX = ".jsp";

	private String htmpatter = DEFAULT_HTM_PATTER;

	private Pattern pattern;

	private String perfix = DEFAULT_PREFIX;

	private String suffix = DeFAULT_SUFFIX;

	private Pattern getpattern() {
		if (pattern != null) {
			return pattern;
		}
		return pattern = Pattern.compile(this.getHtmpatter());
	}

	private String getnewpath(String path) {
		Pattern pattern = getpattern();
		Matcher matcher = pattern.matcher(path);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	public String getHtmpatter() {
		return htmpatter;
	}

	public void setHtmpatter(String htmpatter) {
		this.htmpatter = htmpatter;
	}

	public String getPerfix() {
		return perfix;
	}

	public void setPerfix(String perfix) {
		this.perfix = perfix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		String newpath = this.getnewpath(path);
		if (newpath != null) {
			String str = getPerfix() + newpath + getSuffix();
			request.getRequestDispatcher(str).forward(request, response);
			return;
		}
		filterChain.doFilter(request, response);
	}

}
