/**
 * 
 */

(function(){
	
	var notifications = [];
	var notificationsEnabled = false;
	
	$(document).ready(function () {
	    $( "#findNotifictionsBtn" ).click(findNotifications);
	});

	function findNotifications(){
		networkModule.queryIfEnabled(
			function(data){
				if(data["enabled"] == true){
					notificationsEnabled = true;
					moduleEnabled();
				}
				else{
					console.log("Notifications not enabled");
				}
			}
		);
	}
	
	function notification(topic, message, userId, id){
		this.topic = topic;
		this.message = message;
		this.userId = userId;
		this.id = id;
	}
	
	
	function moduleEnabled(){
		console.log("Notifications are enabled, ok");
		networkModule.queryForNotifications(function(data){
			console.log("Len: " +data.length);
			
			for(var i = 0 ; i< data.length; i++){
				var n = new notification(data[i]['topic'], data[i]['message'], data[i]['userId'], data[i]['id'])
				notifications.push(n);
				console.log(n);
			}
			
			var title = new notification("Topic", "message", "userId");
			
			networkModule.postNewNotification(title, function(){
				console.log("Post success");
			});
			
			networkModule.deleteNotification(notifications[0].id, function(data , statusText, xhr){
				if(xhr.status == 200){
					console.log("Delete success");
				}
				else{
					console.log("No content")
				}
			});
			
			
		});
	}
	
	var networkModule = {
			queryIfEnabled: function(successCall) {
				var url = "/notificationsModule/enabled";
				$.ajax({
					type: "GET",
					url: url,
					contentType: 'application/json',
					dataType: "json",
					success: successCall,
					failure: function failureCall(errMsg){
						console.log("Err:" + errMsg);
					},
					statusCode: {
						400: function ( data ){
							console.log("Bad request " + data);
							} 
					},
				});
			},
			queryForNotifications: function(successCall) {
				var url = "/notifications";
				$.ajax({
					type: "GET",
					url: url,
					contentType: 'application/json',
					dataType: "json",
					success: successCall,
					failure: function failureCall(errMsg){
						console.log("Err:" + errMsg);
					},
					statusCode: {
						400: function ( data ){
							console.log("Bad request " + data);
							} 
					},
				});
			},
			postNewNotification: function(data, successCall) {
				var url = "/notifications";
				$.ajax({
					type: "POST",
					url: url,
					contentType: 'application/json',
					data: JSON.stringify(data),
					//dataType: "json",
					success: successCall,
					failure: function failureCall(errMsg){
						console.log("Err:" + errMsg);
					},
					statusCode: {
						400: function ( data ){
							console.log("Bad request " + data);
							} 
					},
				});
			},
			deleteNotification: function(id, successCall) {
				var url = "/notifications/" + id;
				$.ajax({
					type: "DELETE",
					url: url,
					contentType: 'application/json',
					//data: JSON.stringify(data),
					//dataType: "json",
					success: successCall,
					failure: function failureCall(errMsg){
						console.log("Err:" + errMsg);
					},
					statusCode: {
						400: function ( data ){
							console.log("Bad request " + data);
							} 
					},
				});
			},
			
	};
	
})();


 	
