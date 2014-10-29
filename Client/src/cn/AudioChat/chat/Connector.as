package cn.AudioChat.chat
{
	import flash.events.DataEvent;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.XMLSocket;
	
	import mx.controls.Alert;
	/**
	 * 
	 * @author Changwy
	 * 处理链接的细节
	 */
	public class Connector 
	{
	//	private var domain:String="域名";
    //  private var domain:String="218.193.155.159";
    	private static var domain:String="localhost";
		private var socket:XMLSocket;
		private var initEvent:Function;
		private var onDataEvent:Function;
		private var ioErrorEvent:Function;
		private var httpIOErrorEvent:Function;
		private var shouldSendKeepAlive:Boolean=false;
		private static var port:int=8800;
		
		public function Connector(initEvent:Function,onDataEvent:Function,ioErrorEvent:Function,httpIOErrorEvent:Function) 
		{
			this.initEvent=initEvent;
			this.onDataEvent=onDataEvent;
			this.ioErrorEvent=ioErrorEvent;
			this.httpIOErrorEvent=httpIOErrorEvent;
			this.connect();//调用连接主函数
		}
		
		//，当服务器端没有固定端口时使用
		/*  public function connect():void 
		{
		//调用连接主函数
			var loader:URLLoader=new URLLoader();
			loader.addEventListener(IOErrorEvent.IO_ERROR,httpIOErrorEvent);
			loader.addEventListener(Event.COMPLETE,onComplete);
			loader.load(new URLRequest("http://"+domain+"/ChatServerServlet.do"));
		} 
		
		private function onComplete(event:Event):void 
		{
		//Socket连接！！！！！！！
			var loader:URLLoader=URLLoader(event.target);
			var port:Number=loader.data;
			socket=new XMLSocket();
			socket.addEventListener(Event.CONNECT,initEvent);
			socket.addEventListener(DataEvent.DATA,onDataEvent);
			socket.addEventListener(IOErrorEvent.IO_ERROR,ioErrorEvent);
			socket.connect(domain,port);
		} */
		
		public function connect():void 
		{
		//调用连接主函数
			var loader:URLLoader=new URLLoader();
			loader.addEventListener(IOErrorEvent.IO_ERROR,httpIOErrorEvent);
			onComplete();//调用链接
		}
		
		private function onComplete():void 
		{
		//Socket连接！！！！！！！
			socket=new XMLSocket();
			socket.addEventListener(Event.CONNECT,initEvent);
			socket.addEventListener(DataEvent.DATA,onDataEvent);
			socket.addEventListener(IOErrorEvent.IO_ERROR,ioErrorEvent);
			socket.connect(domain,port);
		}
		
		public function send(tag:String,value:String):void 
		{
		//数据发送！
			try 
			{
				this.setShouldSendKeepAliveFalse();
				socket.send("<"+tag+">"+value+"</"+tag+">\r\n");
			} 
			catch (er:Error) 
			{
				this.ioErrorEvent(null);
			}
		}
		//类的外部接口
		public function setShouldSendKeepAliveFalse():void
		{
			this.shouldSendKeepAlive=false;
		}
		
		public function getShouldSendKeepAlive():Boolean
		{
			return this.shouldSendKeepAlive;
		}
		
		public function setShouldSendKeepAliveTrue():void
		{
			this.shouldSendKeepAlive=true;
		}
		
		public static function getServerDomain():String
		{
			return domain;
		}
		
		public static function getServerPort():String
		{
			return port.toString();
		}
	}
}