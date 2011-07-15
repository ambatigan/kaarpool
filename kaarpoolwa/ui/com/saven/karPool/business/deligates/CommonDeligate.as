//Lavanya
package com.saven.karPool.business.deligates
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.saven.karPool.business.services.Services;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.http.HTTPService;
	
	public class CommonDeligate
	{
		private var _responder:IResponder;
	
		private var _service:HTTPService;  // Service
		
		public function CommonDeligate(responder:IResponder)	{
			this._service = ServiceLocator.getInstance().getHTTPService(Services.HISTORYDATASERVICE);  
	        this._responder = responder;  
		}
		public function getHistoryData(params:Object = null) :void {
			var token2:AsyncToken = _service.send(params);
				token2.addResponder( _responder);
		}
	}
}