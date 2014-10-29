/**
 * 
 */
package cn.AudioChat.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.AudioChat.chat.server.domain.User;

/**
 * @author Changwy
 * 
 * 服务器，用于监听口
 */
public class Server extends Thread 
{
	private Log log = LogFactory.getLog(this.getClass());
	private ServerSocket server;

	private List users = new ArrayList();//代表在线的客户

	public void init(int port) throws IOException 
	{
	//用于指定监听的端口
		server = new ServerSocket(port);
		log.info("server open，port is " + server.getLocalPort());
		this.start();
	}

	public int init() throws IOException 
	{
	//随机监听端口
		server = new ServerSocket(0);
		this.start();
		log.info("server open，port is " + server.getLocalPort());
		return server.getLocalPort();
	}

	public void run() 
	{
	//进程启动
		while (true) 
		{
			try {
				Socket client = server.accept();
				log.info("client access:" + client.getRemoteSocketAddress());
				User user = new User(client);
				users.add(user);
				new Processor(users, user).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException 
	{
	//服务器主函数，核心就是socket对端口的循环监听
		int port = 8800;//设定固定端口号，改动时客户端代码里也需要有相应的改动，不然会造成连不上
		Server server = new Server();
		server.init(port);
	}
}
