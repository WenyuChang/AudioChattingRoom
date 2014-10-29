/**
 * 
 */
package cn.AudioChat.chat.server.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.AudioChat.chat.server.domain.RequestData;
import cn.AudioChat.chat.server.domain.User;
import cn.AudioChat.chat.server.protocol.requestaction.ActionRegister;
import cn.AudioChat.chat.server.protocol.response.SendMsgToAll;

/**
 * @author Changwy
 * 
 * Protocol类用于把客户端传来的字符串转化为服务端的能识别的请求:RequestData
 */
public class Protocol 
{
	private Log log = LogFactory.getLog(this.getClass());

	private List users;

	private User user;

	private static List actions = ActionRegister.getActions();
	
	private boolean hasSendLoginMessage = false;

	public Protocol(List users, User user) 
	{
		this.users = users;
		this.user = user;
	}
	
	public void deal() throws UnsupportedEncodingException, IOException 
	{
	//处理客户的请求
		RequestData requestData = this.getClientRequestData(user);
		log.info("Client：" + this.user.getUserNameDetail() + " Request：" + requestData.getRequestXmlString());
		log.info("Request from Client： " + requestData.getRequestXmlString());
		for (int i = 0; i < actions.size(); i++) 
		{
			RequestAction action = (RequestAction) actions.get(i);
			if (action.canDeal(requestData))
			{
				action.dealReqeust(requestData, this);
				if (!hasSendLoginMessage) 
				{
					new SendMsgToAll().sendSystemMsg("Client：" + user.getUserNameDetail() + " Login..",this);
					this.hasSendLoginMessage = true;
				}
				return;
			}
		}
		log.info("Service can not deal with the string from client !! " + requestData.getRequestXmlString());
	}

	private String getClientActionFromLine(String line) 
	{
	//从请求字符串中判断命令类型
		Pattern pattern=Pattern.compile("<(.*?)>");
		Matcher matcher=pattern.matcher(line);
		if(matcher.find())
		{
			return matcher.group(1);
		}
		else
		{
			return null;
		}
	}

	private RequestData getClientRequestData(User user) throws UnsupportedEncodingException, IOException 
	{
	//获取客户端数据
		BufferedReader reader = new BufferedReader(new InputStreamReader(user.getSocket().getInputStream(), "utf8"));
		String requestStr = reader.readLine();
		if (requestStr == null)
			throw new RuntimeException("不能从socket获取数据，客户可能已退出");
		return new RequestData(this.getClientActionFromLine(requestStr), requestStr);
	}

	public void sendStringToAllClient(String str) 
	{
	//向客户发送信息
		for (int i = 0; i < users.size(); i++) 
		{
			try {
				User user = (User) users.get(i);
				this.sendStringToSingleClient(user, str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void sendStringToSingleClient(User user, String str) 
	{
	//向单个客户发送信息
		try {
			IOUtils.write(str.replaceAll(user.getUserNameDetailWithExcapeRegExp(), "User") + "\0", user.getSocket()
					.getOutputStream(), "utf8");
			user.getSocket().getOutputStream().flush();
			log.info("Request from Server：" + str);
		} catch (IOException e) {
			log.info("Client:" + user.getUserNameDetail() + "was offline before informed!!");
			throw new RuntimeException(e);
		}
	}

	public List getUsers() 
	{
		return users;
	}

	public User getUser() 
	{
		return user;
	}
	public boolean isHasSendLoginMessage() 
	{
		return hasSendLoginMessage;
	}
	public void setHasSendLoginMessage(boolean hasSendLoginMessage) 
	{
		this.hasSendLoginMessage = hasSendLoginMessage;
	}
}
