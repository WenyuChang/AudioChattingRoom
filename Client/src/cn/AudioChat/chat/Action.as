package cn.AudioChat.chat
{
	/**
	 * @author Changwy
	 */
	public class Action 
	{
		protected var face:Face;

		public function Action(face:Face):void 
		{
			this.face=face;
		}
		protected function getTag():String 
		{
			return "";
		}
		public function candeal(str:String):Boolean 
		{
			var msgPattern:RegExp=this.getPattern();
			return msgPattern.test(str);
		}
		protected function getPattern():RegExp 
		{
			return new RegExp("<" + this.getTag() + ">(.*)</" + this.getTag() + ">","s");
		}
		public function deal(str:String):void 
		{//在子类中将对这个函数进行重写
		}
	}
}