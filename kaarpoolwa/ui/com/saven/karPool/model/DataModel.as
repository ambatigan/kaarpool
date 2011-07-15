//Lavanya 10.6.11
package com.saven.karPool.model {
	import com.adobe.cairngorm.model.ModelLocator;
	
	[Bindable]
	public class DataModel implements  com.adobe.cairngorm.model.ModelLocator	{
		
		private static var dataModel:com.saven.karPool.model.DataModel;
		
		
		public var dataprovider : XML;
		
		public var username : String = ''; // Username
		
		public var password : String = '';
				
		public static function getInstance() : com.saven.karPool.model.DataModel {
		
			if( dataModel == null ) {
				dataModel = new com.saven.karPool.model.DataModel();
			}
			return dataModel; 
		}
	
		public function DataModel() {
			
			if( com.saven.karPool.model.DataModel.dataModel != null ) {
				throw new Error ( "Only one datamodel should exist" );
			}
		}

	}
}