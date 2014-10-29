/**
 * 
 */
package cn.AudioChat.chat.server.domain;

import java.net.Socket;

/**
 * @author Changwy
 * 
 * ����Ϊ�û������࣬���Դ����û��������Ϣ
 */
public class User 
{
	private String ip;		//�ͻ���ip
	private Integer port;		//�˿�
	private String username = "client";		//�ͻ����ơ�Ĭ��ֵΪ��client��
	private String nickName = null;		//�ǳ�
	private Socket socket; 		//��Ӧ�ο͵Ŀͻ���socket
	
	public User(Socket socket) 
	{
		this.socket = socket;
		//��ȡ�û���ip��ַ
		this.ip = socket.getRemoteSocketAddress().toString().split(":")[0].replaceAll("\\||/", "");
		//��ȡ�û��ļ�����˿ں�
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
