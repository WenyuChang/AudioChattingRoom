/**
 * 
 */
package cn.AudioChat.chat.server.protocol.requestaction;

import cn.AudioChat.chat.server.domain.RequestData;
import cn.AudioChat.chat.server.protocol.Protocol;
import cn.AudioChat.chat.server.protocol.RequestAction;
import cn.AudioChat.chat.server.protocol.response.SendMsgToAll;

/**
 * @author Changwy
 * 
 * 对所有用户发送数据
 */
public class SendMsgToAllAction implements RequestAction 
{
	private static String action = "sendMsgToAll";
	private SendMsgToAll sendMsgToAll = new SendMsgToAll();

	public void dealReqeust(RequestData requestData, Protocol protocol) 
	{
		sendMsgToAll.sendMsgToAll(requestData, protocol);
	}

	public boolean canDeal(RequestData requestData) 
	{
		return requestData.getCAction().indexOf(action) != -1;
	}

}
