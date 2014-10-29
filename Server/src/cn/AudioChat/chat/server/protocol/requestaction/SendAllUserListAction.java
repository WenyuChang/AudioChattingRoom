/**
 * 
 */
package cn.AudioChat.chat.server.protocol.requestaction;

import cn.AudioChat.chat.server.domain.RequestData;
import cn.AudioChat.chat.server.protocol.Protocol;
import cn.AudioChat.chat.server.protocol.RequestAction;
import cn.AudioChat.chat.server.protocol.response.SendALlUserList;

/**
 * @author Changwy
 * 
 * 对所有在线用户发送在线用户信息
 */
public class SendAllUserListAction implements RequestAction 
{
	private static String action = "getAllUserList";
	private SendALlUserList sendAllUserList = new SendALlUserList();

	public boolean canDeal(RequestData requestData) 
	{
		return action.equals(requestData.getCAction());
	}

	public void dealReqeust(RequestData requestData, Protocol protocol) 
	{
		sendAllUserList.deal(protocol);
	}

}
