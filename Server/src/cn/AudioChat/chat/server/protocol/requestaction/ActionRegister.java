/**
 * 
 */
package cn.AudioChat.chat.server.protocol.requestaction;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Changwy
 * 
 * 对于用户所有操作的动作进行注册，为后续动作处理做准备
 */
public class ActionRegister 
{
	private static List actions = new LinkedList();
	static 
	{
		actions.add(new SendAllUserListAction());	//对所有在线用户发送在线用户信息
		actions.add(new SendMsgToAllAction());		//对所有用户发送数据
		actions.add(new KeepClientAliveAction());		//确认用户在线
		actions.add(new ChangeNickNameAction());		//更改用户昵称
	}

	public static List getActions() 
	{
		return actions;
	}
}
