/**
 * 
 */
package cn.AudioChat.chat.server.protocol.response;

import java.util.List;

import cn.AudioChat.chat.server.domain.User;
import cn.AudioChat.chat.server.protocol.Protocol;
import cn.AudioChat.framework.view.XmlUtil;

/**
 * @author Changwy
 * 
 * 对所有在线用户发送在线用户信息,更改用户昵称的实际动作
 */
public class SendALlUserList 
{
	public static String action = "getAllUserListBack";
	public static String userXmlTag="UserList";

	private String getAllUsernameXml(List users) 
	{
		StringBuffer result = new StringBuffer();
		result.append(XmlUtil.XML_HEADER);
		result.append(XmlUtil.getStartTag(action));
		for (int i = 0; i < users.size(); i++) 
		{
			User tempUser = (User) users.get(i);
			result.append(XmlUtil.getStartTag(userXmlTag)+tempUser.getUserNameDetail()+XmlUtil.getEndTag(userXmlTag));
		}
		return result.append(XmlUtil.getEndTag(action)).toString();
	}

	public void deal(Protocol protocol) 
	{
		for (int i = 0; i < protocol.getUsers().size(); i++) 
		{
			try {
				User currentUser = (User) protocol.getUsers().get(i);
				protocol.sendStringToSingleClient(currentUser, this.getAllUsernameXml(protocol.getUsers()).replaceAll(
						currentUser.getUserNameDetailWithExcapeRegExp(), "Yourself"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
