package com.saven.karPool.controls
{
	import com.adobe.cairngorm.control.FrontController;
	import com.saven.karPool.commands.CommonCommand;
	import com.saven.karPool.commands.RegisterCommand;
	

	public class ApplicationController extends FrontController
	{
		public static var GET_HISTORY_DATA :String = "GETHISTORYDATA";
		
		public static var REGISTER_DATA :String = "REGISTERDATA";
		
		public function ApplicationController() 	{
			addCommand(ApplicationController.GET_HISTORY_DATA , CommonCommand);
			addCommand(ApplicationController.REGISTER_DATA , RegisterCommand);
		}
		
	}
}