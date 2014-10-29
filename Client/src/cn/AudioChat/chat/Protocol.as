package cn.AudioChat.chat
{
	/**
	 * 
	 * @author Changwy
	 * 协议部份，由于数量比较少，所以集中在一个类里面。主要由UI调用。
	 * 该class中加入了一些数据传送过程中所需要的参数更改和返回。
	 */
	public class Protocol 
	{
		private var connector:Connector;
		public function Protocol(connector:Connector) 
		{
		//简单的构造函数，把传送进来的参数赋值
			this.connector=connector;
		}
		public function sendMsg(msg:String):void 
		{
		//发送消息给全部人 
			connector.send("sendMsgToAll",msg);
		}
		public function getAllUserList():void 
		{
		//获取在线用户列表 
			connector.send("getAllUserList","");
		}
		public function sendKeepAliveSignal():void
		{
		//发送保活信息，测试是否保持连接
		    connector.send("keepAliveSignal","");
		}
		public function changeNickname(nickname:String):void
		{
		//更改昵称 
		    connector.send("changeNickname",nickname);
		}
		
		
	}
}