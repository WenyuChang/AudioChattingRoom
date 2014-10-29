/**
 * 
 */
package cn.AudioChat.chat.server.domain;

import java.net.Socket;

/**
 * @author Changwy
 * 
 * 改类为用户对象类，用以处理用户的相关信息
 */
public class User 
{
	private String ip;		//客户的ip
	private Integer port;		//端口
	private String username = "client";		//客户名称、默认值为“client”
	private String nickName = null;		//昵称
	private Socket socket; 		//对应游客的客户端socket
	
	public User(Socket socket) 
	{
		this.socket = socket;
		//获取用户的ip地址
		this.ip = socket.getRemoteSocketAddress().toString().split(":")[0].replaceAll("\\||/", "");
		//获取用户的计算机端口好
		this.port = Integer.valueOf(socket.getRemoteSocketAddress().toString().split(":")[1]);
	}

	public boolean equals(Object obj) 
	{
		if (null == obj)
			return false;
		User user2 = (User) obj;
		if (null == this.ip || null == this.port || null == user2.ip
				|| null == user2.port)
			return false;
		return this.ip.equals(user2.ip) && this.port.equals(user2.port);
	}

	public String getIp() 
	{
		return ip;
	}

	public void setIp(String ip) 
	{
		this.ip = ip;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public Socket getSocket() 
	{
		return socket;
	}

	public void setSocket(Socket socket) 
	{
		this.socket = socket;
	}

	public Integer getPort() 
	{
		return port;
	}

	public void setPort(Integer port) 
	{
		this.port = port;
	}
	
	public String getDisplayName()
	{
		if(this.getNickName() == null)
		{
			return this.getUsername();
		}
		else
		{
			return this.getNickName();
		}
	}
	
	public String getUserNameDetail() 
	{
		String[] nums=this.getIp().split("\\.");
		if(this.getNickName() == null)
		{
			return this.getDisplayName() + "  *" + nums[2]+"."+nums[3] + ":" + this.getPort();
		}
		else
		{
			return this.getDisplayName();
		}
	}
	
	public String getUserNameDetailWithExcapeRegExp()
	{
		String[] nums=this.getIp().split("\\.");
		return this.getUsername() + "  \\*" + nums[2]+"\\."+nums[3] + ":" + this.getPort();

	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName) 
	{
		this.nickName = nickName;
	}
}
