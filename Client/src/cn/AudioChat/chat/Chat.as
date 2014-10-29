package cn.AudioChat.chat
{
	import cn.AudioChat.chat.daemon.KeepAliveDaemon;
	import cn.AudioChat.chat.popup.NickName;
	
	import flash.display.DisplayObject;
	import flash.events.DataEvent;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.media.Sound;
	
	import mx.core.Application;
	import mx.managers.PopUpManager;
	/**
	 * 
	 * @author Changwy
	 * Chat为这个歌音频聊天系统的主类，协调各功能块之间的合作问题
	 */
	public class Chat 
	{
		private var connector:Connector;
		private var protocol:Protocol;
		private var actions:Array;
		private var face:Face;
		private var keepAliveDaemon:KeepAliveDaemon;
		[Embed(source="/music/msg.mp3")]//用于系统的声音处理
		[Bindable]
		private var MsgSound:Class;
		private var msgSound:Sound=new MsgSound() as Sound;
		public function Chat(face:Face)
		{
		//构造函数
			//Socket连接函数！！
			connector=new Connector(initConnectEvent,onDataEvent,socketIOErrorEvent,httpIOErrorEvent);
			//传输协议函数！！
			protocol=new Protocol(connector);
			this.face=face;
			face.addChangeNameListener(changeNameEvent);//更改昵称监听
			face.getMyIn().addEventListener(KeyboardEvent.KEY_DOWN,onInputEnter);//输入框监听
			this.actions=new ActionRegister(face).getActions();
			this.face.getSendButton().addEventListener(MouseEvent.CLICK,sendButtonEvent);
		}
		
		private function changeNameEvent(event:Event):void
		{
		    var nickName:NickName=NickName(PopUpManager.createPopUp(DisplayObject(Application.application),NickName,true));
		    PopUpManager.centerPopUp(nickName);
		    nickName.setMsgArea(face.getMsgArea());
		    nickName.setProtocol(this.protocol);
	    
		}
		
		public function httpIOErrorEvent(event:IOErrorEvent):void
		{
		    face.getMsgArea().htmlText+="(System_News):The Server can not be connected! Please try later..<br>";
		}
		
		public function socketIOErrorEvent(event:IOErrorEvent):void 
		{
		//用于处理Socket的IO异常
			//Alert.show('进入处理'); 用于测试时使用
			face.getMsgArea().htmlText+="(System_News):It`s unconnected,and trying to connect the server...<br>";
			connector.connect();
		}
		
		public function initConnectEvent(event:Event):void 
		{
		// socket链接后做的第一个件事
			protocol.getAllUserList();
			keepAliveDaemon=new KeepAliveDaemon(this.protocol,this.connector);
		}

        public function sendButtonEvent(event:Event):void
        {
        //处理“发送”按钮的发送事件 
	        this.protocol.sendMsg(this.face.getMyIn().text)
	        face.getMyIn().text="";
        }
		public function onDataEvent(event:DataEvent):void 
		{
			var text:String=event.data;
			msgSound.play();
		//	Alert.show(text);
			for each (var action:Action in actions) 
			{
				if (action.candeal(text)) 
				{
					action.deal(text);
					return;
				}
			}
			this.connector.setShouldSendKeepAliveFalse();
		}
		
		public function onInputEnter(event:KeyboardEvent):void 
		{
		//当用户对输入框按回车时，触发发送事件 
			if (event.keyCode==13) 
			{
				protocol.sendMsg(face.getMyIn().text);
				face.getMyIn().text="";
			}
		}
		public function getProtocol():Protocol 
		{
			return this.protocol;
		}
	}
}