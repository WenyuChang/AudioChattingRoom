package cn.AudioChat.chat.server.protocol;

import cn.AudioChat.chat.server.domain.RequestData;

/**
 * @author Changwy
 * 
 * �������󣬷��ط�Ӧ����,���û������ķ�Ӧ��Ľӿ�
 */
public interface RequestAction 
{
	public void dealReqeust(RequestData requestData,Protocol protocol);
	public boolean canDeal(RequestData requestData);		//�Ƿ��ܴ�������
}
