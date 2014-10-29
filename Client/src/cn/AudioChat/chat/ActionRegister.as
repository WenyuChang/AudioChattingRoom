package cn.AudioChat.chat
{
	/**
	 * @author Changwy
	 */
	import cn.AudioChat.chat.action.GetAllUserListBackAction;
	import cn.AudioChat.chat.action.SendMsgToALlBackAction;

	public class ActionRegister 
	{
		private var actions:Array;
		public function ActionRegister(face:Face) 
		{
			actions=[new SendMsgToALlBackAction(face),new GetAllUserListBackAction(face)];
		}
		public function getActions():Array 
		{
			return actions;
		}

	}
}