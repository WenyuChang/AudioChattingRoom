/**
 * 
 */
package cn.AudioChat.framework.view;

/**
 * @author Changwy
 * 
 * 用户数据接受后的处理
 */
public class XmlUtil 
{
	public static String XML_HEADER="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	public static String getStartTag(String tagName) 
	{
		return "<" + tagName.toString() + ">";
	}

	public static String getEndTag(String tagName) 
	{
		return "</" + tagName.toString() + ">";
	}
	
	public static String buildXmlByStr(String tagName,String str)
	{
		return getStartTag(tagName)+str+getEndTag(tagName);
	}
	
	public static String getRedColor(String str) 
	{
		return "<span style=color:#FF0000>" + str + "</span>";
	}
}
