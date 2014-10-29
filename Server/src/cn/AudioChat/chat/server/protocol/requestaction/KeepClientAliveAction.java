/**
 * 
 */
package cn.AudioChat.chat.server.protocol.requestaction;

import cn.AudioChat.chat.server.domain.RequestData;
import cn.AudioChat.chat.server.protocol.Protocol;
import cn.AudioChat.chat.server.protocol.RequestAction;

/**
 * @author Changwy
 *
 * 确认用户在线
 */
public class KeepClientAliveAction implements RequestAction 
{
	private static String action = "keepAliveSignal";
	public boolean canDeal(RequestData requestData) 
	{
		return action.equals(requestData.getCAction());
	}

	public void dealReqeust(RequestData requestData, Protocol protocol) 
	{
		// do nothing
	}

}
