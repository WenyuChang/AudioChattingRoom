package audio_cilent
{
	import flash.events.*;
	import flash.media.Microphone;
    
    //注视部分为连接服务器端，现在还无法实现，需要服务器端支持
    public function audio_publish()
    {
    	/* var nc:NetConnection = new NetConnection()		//定义连接服务器对象	
    	nc.onStatus = function(info)
    	{
    	//用于测试时使用
    		trace(info.code)
    	}
    	nc.connect("服务与其ip","登录名");
    	var publish_ns:NetStream = new NetStream(nc);	//申明netstream流
		var cam = Camera.get();		//获取摄像头 */
		var m:Microphone=new Microphone;	//申明microphone对象
		var mic = Microphone.get();		//获取microphone
		m.setLoopBack(true);	//声音回放，用于测试阶段
		
		//抓取摄像头影像
		/* local_video.attachVideo(cam);	
		publish_ns.attachVideo(cam);
		//抓取麦克风声音
		publish_ns.attachAudio(mic);
		//发布传输流
		publish_ns.publish("流名字");  */
    }
    
    public function audio_play()
    {
    //客户端从服务器端获取流
    	/* var nc:NetConnection = new NetConnection()
    	
		nc.onStatus = function(info)
		{
    		trace(info.code)
		}
		nc.connect("服务与其ip","登录名");
		
		var play_ns:NetStream = new NetStream(nc);
		
		play_ns.play("local")
		 */

    }


}
