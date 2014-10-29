/**
 * 
 */
package cn.AudioChat.chat.server.protocol.requestaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.AudioChat.chat.server.domain.RequestData;
import cn.AudioChat.chat.server.protocol.Protocol;
import cn.AudioChat.chat.server.protocol.RequestAction;
import cn.AudioChat.chat.server.protocol.response.SendALlUserList;
import cn.AudioChat.chat.server.protocol.response.SendMsgToAll;

/**
 * @author Changwy
 * 
 * �����û������û��سƵ�����
 */
public class ChangeNickNameAction implements RequestAction {

	private Log log=LogFactory.getLog(this.getClass());
	
	private static String action = "changeNickname";
	
	private SendALlUserList sendAllUserList = new SendALlUserList();
	
	private SendMsgToAll sendMsgToAll=new SendMsgToAll();
	
	public boolean canDeal(RequestData requestData) {
		log.info("changeNaickName�����ã������actionΪ:"+requestData.getCAction());
		return action.equals(requestData.getCAction());
	}

	public void dealReqeust(RequestData requestData, Protocol protocol) 
	{
		protocol.getUser().setNickName(requestData.getRequestString());
		this.sendAllUserList.deal(protocol);
		this.sendMsgToAll.sendSystemMsg("Client��"+protocol.getUser().getUserNameDetail()+"��Change his nickname��", protocol);
	}

}
