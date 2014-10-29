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
 * �����������ڼ�����
 */
public class Server extends Thread 
{
	private Log log = LogFactory.getLog(this.getClass());
	private ServerSocket server;

	private List users = new ArrayList();//�������ߵĿͻ�

	public void init(int port) throws IOException 
	{
	//����ָ�������Ķ˿�
		server = new ServerSocket(port);
		log.info("server open��port is " + server.getLocalPort());
		this.start();
	}

	public int init() throws IOException 
	{
	//��������˿�
		server = new ServerSocket(0);
		this.start();
		log.info("server open��port is " + server.getLocalPort());
		return server.getLocalPort();
	}

	public void run() 
	{
	//��������
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
	//�����������������ľ���socket�Զ˿ڵ�ѭ������
		int port = 8800;//�趨�̶��˿ںţ��Ķ�ʱ�ͻ��˴�����Ҳ��Ҫ����Ӧ�ĸĶ�����Ȼ�����������
		Server server = new Server();
		server.init(port);
	}
}
