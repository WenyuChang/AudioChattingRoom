package cn.AudioChat.chat.daemon
{
	import cn.AudioChat.chat.Connector;
	import cn.AudioChat.chat.Protocol;
	
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	/**
	 * @author Changwy
	 * 每隔三十秒检查一次Connector的保活标志位，看是否需要发送保活信息
	 */
	public class KeepAliveDaemon
	{
		private var protocol:Protocol;
		private var connector:Connector;
		private var timer:Timer;
		public function KeepAliveDaemon(protocol:Protocol,connector:Connector)
		{
			this.protocol=protocol;
			this.connector=connector;
			timer=new Timer(30000,0);
			timer.addEventListener(TimerEvent.TIMER,timerHandler);
			timer.start();
		}

		public function timerHandler(event:TimerEvent):void
		{
			if(connector.getShouldSendKeepAlive())
			{
				protocol.sendKeepAliveSignal();
			}
			else
			{
				connector.setShouldSendKeepAliveTrue();
			}
		}
	}
}