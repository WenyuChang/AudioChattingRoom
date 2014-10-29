package cn.AudioChat.chat.action
{
	/**
	 * @author Changwy
	 */
	import cn.AudioChat.chat.Action;
	import cn.AudioChat.chat.Face;

	public class GetAllUserListBackAction extends Action 
	{
		public function GetAllUserListBackAction(face:Face) 
		{
			super(face);
		}
		override protected function getTag():String 
		{
			return "getAllUserListBack";
		}
		override public function deal(str:String):void 
		{
			var userListXml:XMLList=new XML(str.match(this.getPattern())[0]).children();
			face.getUsers().removeAll();
			for (var i:int=0; i<userListXml.length(); i++) 
			{
				Face.num = userListXml.length();
				face.getUsers().addItem(userListXml[i].toString());
			}
		}
	}
}