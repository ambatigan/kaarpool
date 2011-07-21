//Lavanya 13.6.11
package com.saven.karPool.commands {
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.saven.karPool.business.deligates.RegisterDeligate;
	import com.saven.karPool.controls.events.CommonEvent;
	
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;

	public class RegisterCommand implements Command, IResponder
	{
		public function RegisterCommand()
		{
		}

		public function execute(event:CairngormEvent):void		{
			var e : CommonEvent = event as CommonEvent ;
			 if(e.type == "REGISTERDATA") {
				var del : RegisterDeligate = new RegisterDeligate(this);
				del.registerData(e.params);
			}
			
		}
		
		public function result(data:Object):void 		{
			var token:AsyncToken = data.token;
			var xml:XML = XML( data.result );
			
			if( xml == null )
			return;
			
			if( (xml.attribute( "status" ) == "fail") ){
				Alert.show(xml.child("error"));
				return;
			} else {
				//Alert.show("REgistration Done");
				Application.application.gLogin.clearRegistrationPage();
				Application.application.gLogin.showLogin();
			}
		}
		
		public function fault(info:Object):void
		{
		}
		
	}
}