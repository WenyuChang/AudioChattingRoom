/**
 * 
 */
package cn.AudioChat.chat.server.protocol.response;

import cn.AudioChat.chat.server.domain.RequestData;
import cn.AudioChat.chat.server.protocol.Protocol;
import cn.AudioChat.framework.view.XmlUtil;

/**
 * @author Changwy
 *
 * 对所有用户发送数据,实际操作
 */
public class SendMsgToAll 
{
	private static String action="sendMsgToAllBack";

	public void sendMsgToAll(RequestData requestData, Protocol protocol) 
	{
		protocol.sendStringToAllClient(XmlUtil.getStartTag(action)+"("+protocol.getUser().getUserNameDetail()+"):"+requestData.getRequestString()+XmlUtil.getEndTag(action));
	}

	public void sendSystemMsg(String msg,Protocol protocol)
	{
		protocol.sendStringToAllClient(XmlUtil.getStartTag(action)+"(System_News):"+msg+XmlUtil.getEndTag(action));
	}

}
