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
 * �������û���������,ʵ�ʲ���
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
