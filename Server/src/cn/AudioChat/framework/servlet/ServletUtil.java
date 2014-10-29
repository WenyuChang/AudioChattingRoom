package cn.AudioChat.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.AudioChat.framework.dao.PageQuery;

/**
 * @author Changwy
 * 
 * Servlet的工具类。
 */
public class ServletUtil {

	public static Log log = LogFactory.getLog(ServletUtil.class);

	public static String HTML_ENCODING = "text/html;charset=utf-8";

	public static String XML_ENCODING = "text/xml;charset=utf-8";

	/**
	 * 设置HTML header的文本类型及编码为"text/html;charset=gb2312";
	 */
	public static void setHTMLContentType(HttpServletResponse response) {
		response.setContentType(HTML_ENCODING);
	}

	/**
	 * 设置HTML header的文本类型及编码为"text/xml;charset=gb2312";
	 */
	public static void setXMLContentType(HttpServletResponse response) {
		response.setContentType(XML_ENCODING);
	}

	/**
	 * 输出流到客户端
	 * 
	 * @param response
	 * @param ems
	 * @throws IOException
	 */
	public static void outView(HttpServletResponse response, String ems) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(ems);
		out.flush();
		out.close();
	}

	/**
	 * 若request对像中不存在paramName，即返回null, 若存在，则返回已经去除两端空格的字符串
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String removeSpace(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (null == value) {
			return null;
		} else {
			return value.trim();
		}
	}
	
	public static Integer getIntParameter(HttpServletRequest request,String paramName){
		String valueStr=removeSpace(request,paramName);
		if(null==valueStr){
			return null;
		}else{
			return Integer.valueOf(valueStr);
		}
	}

	/**
	 * 从request读取多个值，例如checkBox
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String[] removeMultiSpace(HttpServletRequest request, String paramName) {
		String[] values = request.getParameterValues(paramName);
		if (null == values) {
			return null;
		}
		for (int i = 0; i < values.length; i++) {
			values[i] = values[i].trim();
		}
		return values;
	}



	/**
	 * 用javascript提示相关信息，并跳转到指定页面
	 * 
	 * @param msg提示信息
	 * @param url
	 *            跳转到的路径
	 * @return
	 * @throws IOException
	 */
	public final static void outTips(HttpServletResponse response, String msg, String url) throws IOException {
		StringBuffer tips = new StringBuffer();
		tips.append("<script language='javascript'>");
		if (msg != null) {
			tips.append("alert('" + msg.replaceAll("\\\'", "\\\\\'") + "');");
		}
		if (url != null) {
			tips.append("window.location.href='" + url + "';");
		} else {
			tips.append("window.history.back();");
		}
		tips.append("</script>");
		ServletUtil.outView(response, tips.toString());
	}

	/**
	 * 从request中取出开始页数，因为xtable中默认的参数名为page，所以这里用page
	 * 如果参数中有page，即返回page，若没有，即返回1
	 * 
	 * @param request
	 * @return
	 */
	public static PageQuery getPageQuery(HttpServletRequest request) {
		String startIndexStr = ServletUtil.removeSpace(request, "startIndex");
		int startIndex;
		try{
			startIndex=Integer.parseInt(startIndexStr);
		}catch(Exception e){
			startIndex=0;
		}
		String pageSizeStr=ServletUtil.removeSpace(request, "results");
		int pageSize;
		try{
			pageSize=Integer.parseInt(pageSizeStr);
		}catch(Exception e){
			pageSize=0;
		}
		return new PageQuery(startIndex,pageSize);
	}

	/**
	 * 设置页面不没缓存
	 */
	public static void setNoCache(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}

}
