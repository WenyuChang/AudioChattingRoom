/**
 * 
 */
package cn.AudioChat.chat.server.protocol.requestaction;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Changwy
 * 
 * �����û����в����Ķ�������ע�ᣬΪ��������������׼��
 */
public class ActionRegister 
{
	private static List actions = new LinkedList();
	static 
	{
		actions.add(new SendAllUserListAction());	//�����������û����������û���Ϣ
		actions.add(new SendMsgToAllAction());		//�������û���������
		actions.add(new KeepClientAliveAction());		//ȷ���û�����
		actions.add(new ChangeNickNameAction());		//�����û��ǳ�
	}

	public static List getActions() 
	{
		return actions;
	}
}
