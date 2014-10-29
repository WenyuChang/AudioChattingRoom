package cn.AudioChat.chat.server.protocol;

import cn.AudioChat.chat.server.domain.RequestData;

/**
 * @author Changwy
 * 
 * 处理请求，返回反应数据,对用户操作的反应类的接口
 */
public interface RequestAction 
{
	public void dealReqeust(RequestData requestData,Protocol protocol);
	public boolean canDeal(RequestData requestData);		//是否能处理请求
}
