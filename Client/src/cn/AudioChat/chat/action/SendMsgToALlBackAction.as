package cn.AudioChat.chat.action
{
	/**
	 * @author Changwy
	 */
	import cn.AudioChat.chat.Action;
	import cn.AudioChat.chat.Face;

	public class SendMsgToALlBackAction extends Action 
	{
		public function SendMsgToALlBackAction(face:Face) 
		{
			super(face);
		}
		override protected function getTag():String 
		{
			return "sendMsgToAllBack";
		}
		override public function deal(str:String):void 
		{
			this.face.getMsgArea().htmlText+=str.match(this.getPattern())[1]+"<br/>";
		}
	}
}