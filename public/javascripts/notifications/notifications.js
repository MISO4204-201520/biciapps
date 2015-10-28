/**
 * Some text 
 */

define(function(require){

	var networkModule = require('network/notificationNetwork');
	return{
		queryIfNotificationsEnabled: function (onEnabled){
			networkModule.queryIfEnabled(
				function(data){
					if(data["enabled"] == true){
						notificationsEnabled = true;
						onEnabled();
					}
					else{
						console.log("Notifications not enabled");
					}
				}
			);
		}	
	}
	
});
