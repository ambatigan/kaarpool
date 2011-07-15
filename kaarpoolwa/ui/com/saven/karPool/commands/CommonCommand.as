//Lavanya 10.06.11
package com.saven.karPool.commands {
		
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.saven.karPool.business.deligates.CommonDeligate;
	import com.saven.karPool.business.deligates.RegisterDeligate;
	import com.saven.karPool.controls.events.CommonEvent;
	import com.saven.karPool.model.DataModel;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;

	public class CommonCommand implements Command, IResponder	{
		public function CommonCommand()		{
			super();
		}
		
		[Bindable]
		private var model : DataModel = DataModel.getInstance();

		public function execute(event:CairngormEvent):void 		{
			var e : CommonEvent = event as CommonEvent ;
			
			if(e.type == "GETHISTORYDATA") {
				var delegate : CommonDeligate = new CommonDeligate(this);		
				delegate.getHistoryData(e.params);
			}
		}
		
		public function result(data:Object):void 	{
			var token:AsyncToken = data.token;
			var xml:XML = XML( data.result );
			
			if( xml == null )
			return;
			
			if( (xml.attribute( "status" ) == "fail") ){
				Alert.show(xml.child("error"));
				return;
			} else {
				model.dataprovider = xml;
			}
			
			/* var childs:XMLList = xml.children();
			var ac:Array = new Array();
				for each(var xml:XML in  childs){
					ac.push(xml);
				} */
			
		}
		
		public function fault(info:Object):void		{
		}
		
		
		
	}
}