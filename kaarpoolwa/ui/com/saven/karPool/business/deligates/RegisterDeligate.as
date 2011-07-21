//Lavanya 13.6.11
package com.saven.karPool.business.deligates {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.saven.karPool.business.services.Services;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.http.HTTPService;
	
	public class RegisterDeligate 	{
		private var _responder:IResponder;
		private var _service:HTTPService;
		
		public function RegisterDeligate(responder:IResponder)		{
			this._service = ServiceLocator.getInstance().getHTTPService(Services.REGISTERSERVICE);  
	        this._responder = responder; 
		}
		
		public function registerData(params:Object = null) :void {
			var token1:AsyncToken = _service.send(params);
				token1.addResponder( _responder);
		}

	}
}