/**
 * 
 */
package cn.AudioChat.chat.server;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.AudioChat.chat.server.domain.User;
import cn.AudioChat.chat.server.protocol.Protocol;
import cn.AudioChat.chat.server.protocol.response.SendALlUserList;
import cn.AudioChat.chat.server.protocol.response.SendMsgToAll;

/**
 * @author Changwy
 * 
 * 每个Processor对像处理一个User
 */
public class Processor extends Thread 
{
	private List users;
	private User user;
	private Protocol protocol;
	private Log log = LogFactory.getLog(this.getClass());

	public Processor(List users, User user) 
	{
		this.users = users;
		this.user = user;
		this.protocol = new Protocol(users, user);
	}

	public void run() 
	{
	// 不管是否要求安全验证，都发出允许链接的认证策略
		protocol.sendStringToSingleClient(protocol.getUser(),"<?xml version=\"1.0\"?><cross-domain-policy><site-control permitted-cross-domain-policies=\"all\"/><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0");
		while (true) 
		{
			try {
				protocol.deal();//server开始处理用户数据
			} catch (Exception e) {
				log.info(e.getMessage());
				users.remove(user);
				protocol.getUsers().remove(user);
				try {
					user.getSocket().close();
				} catch (IOException e1) {
				}
				new SendALlUserList().deal(protocol);
				if (this.protocol.isHasSendLoginMessage()) {
					new SendMsgToAll().sendSystemMsg("Client:" + user.getUserNameDetail() + "Offline", protocol);
					log.info("Client:" + user.getUsername() + "Offline.");
				}
				return;
			}
		}
	}
}
