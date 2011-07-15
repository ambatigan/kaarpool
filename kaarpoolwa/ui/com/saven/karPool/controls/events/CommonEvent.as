//Lavanya
package com.saven.karPool.controls.events
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public class CommonEvent extends CairngormEvent
	{
		public var params : Object;
		
		public function CommonEvent(params : Object,type:String=null)		{
			super(type);	
			this.params = params;
		}
		
	}
}