/**
 * 
 */
package cn.AudioChat.chat.server.domain;

import cn.AudioChat.framework.view.XmlUtil;

/**
 * @author Changwy
 * 
 * �������ڶ����û��������ݵ���ش���
 */
public class RequestData 
{
	private String cAction;		//��������
	private String requestXmlString;		//�����xml����

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
	//����xml��ʽ����������
		return requestXmlString;
	}

	/**
	 * 
	 */
	public String getRequestString() 
	{
	//����ȥ������xml��ǩ������
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
