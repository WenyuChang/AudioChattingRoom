/**
 * 
 */
package cn.AudioChat.chat.server.domain;

import cn.AudioChat.framework.view.XmlUtil;

/**
 * @author Changwy
 * 
 * 改类用于对于用户传入数据的相关处理
 */
public class RequestData 
{
	private String cAction;		//请求类型
	private String requestXmlString;		//请求的xml数据

	public RequestData(String cAction, String requestXmlString) 
	{
		this.cAction = cAction;
		this.requestXmlString = requestXmlString;
	}

	public String getCAction() 
	{
		return cAction;
	}

	public String getRequestXmlString() 
	{
	//返回xml型式的请求数据
		return requestXmlString;
	}

	/**
	 * 
	 */
	public String getRequestString() 
	{
	//返回去除请求xml标签的数据
		return this.requestXmlString.replaceAll(XmlUtil.getStartTag(cAction) + "|" + XmlUtil.getEndTag(cAction), "");
	}

	public String getStartTag() 
	{
		return XmlUtil.getStartTag(cAction);
	}

	public String getEndTag() 
	{
		return XmlUtil.getEndTag(cAction);
	}
}
