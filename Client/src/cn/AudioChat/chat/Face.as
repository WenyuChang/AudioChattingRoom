package cn.AudioChat.chat
{
	import flash.events.MouseEvent;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	
	import mx.collections.ArrayCollection;
	import mx.containers.Panel;
	import mx.controls.Button;
	import mx.controls.LinkButton;
	import mx.controls.List;
	import mx.controls.TextArea;
	import mx.controls.TextInput;
	import mx.core.UIComponent;
	import mx.core.mx_internal;
	import mx.events.FlexEvent;
	/**
	 * 
	 * @author Changwy
	 * 聊天室的UI
	 */
	public class Face
	{
		private var msgArea:TextArea;
		private var myIn:TextInput;
		private var users:ArrayCollection=new ArrayCollection();
		private var userList:List;
		private var borderPane:Panel;
		private var inputPanel:Panel;
		private var sendButton:Button;
		private var changeNameButton:LinkButton=new LinkButton();
		public static var num:int;
		
		public function Face(myIn:TextInput,msgArea:TextArea,userList:List,borderPane:Panel,sendButton:Button,inputPanel:Panel)
		{
		//构造函数
			this.borderPane=borderPane;
			this.myIn=myIn;
			this.msgArea=msgArea;
			this.msgArea.htmlText="(System_News)：Login.....<br>";
			msgArea.addEventListener(FlexEvent.UPDATE_COMPLETE,textAreaUpdateEvent);//处理连接事件
			
			this.userList=userList;
			this.userList.contextMenu=this.getAudioChatContextMenu();
			this.userList.dataProvider=users;
			
			this.sendButton=sendButton;
			this.inputPanel=inputPanel;
			
		//设置用户昵称
			var titleBar:UIComponent=inputPanel.mx_internal::getTitleBar();
			changeNameButton.width=160;
			changeNameButton.height=20;
			changeNameButton.move(titleBar.width-170,titleBar.height-23);
			changeNameButton.label="change Nick_Name";
			changeNameButton.toolTip="Click here to change your nickname";
			titleBar.addChild(changeNameButton);
		}

		private function getAudioChatContextMenu():ContextMenu
		{
		//设置用户间的私聊
			var contextMenu:ContextMenu = new ContextMenu();
			//var contextMenuIten:ContextMenuItemPrivateChat=new ContextMenuItem("Private_Chats");
			var contextMenuItem:ContextMenuItem = new ContextMenuItem("Audio_Chat");
			contextMenu.hideBuiltInItems();
			contextMenu.customItems.push(contextMenuItem);
			return contextMenu;
		}
		
		public function addChangeNameListener(fun:Function):void
		{
		//用户昵称linkbutton的listener
			this.changeNameButton.addEventListener(MouseEvent.CLICK,fun);
		}
		
		public function textAreaUpdateEvent(event:FlexEvent):void
		{
		//Line number of the top row of characters that is currently displayed.
			this.getMsgArea().verticalScrollPosition=this.getMsgArea().maxVerticalScrollPosition;
		}
		
		//类的外部接口
		public function getMsgArea():TextArea
		{
			return this.msgArea;
		}
		public function getMyIn():TextInput
		{
			return this.myIn;
		}
		public function getUsers():ArrayCollection 
		{
			return this.users;
		}
		public function getSendButton():Button
		{
			return this.sendButton;
		}

	}
}