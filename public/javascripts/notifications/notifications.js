/**
 * Some text 
 */

define(function(require){

	var networkModule = require('network/notificationNetwork');
	
	function notification(topic, message, userId, id){
		this.topic = topic;
		this.message = message;
		this.userId = userId;
		this.id = id;
	}

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
		},

		queryForNotifications: function(callBack){
			networkModule.queryForNotifications(function(data){
				console.log("Len: " +data.length);
				
				var notifications = [];
				for(var i = 0 ; i< data.length; i++){
					var n = new notification(data[i]['topic'], 
						data[i]['message'], data[i]['userId'], data[i]['id'])
					notifications.push(n);
					console.log(n);
				}
				callBack(notifications);
			});	
		},

		notification: notification
	}
	
});
