/**
 * 
 */

(function(){
	
	$(document).ready(function () {
	    $( "#findNotifictionsBtn" ).click(findNotifications);
	});

	function findNotifications(){
		console.log("Hello");
		networkModule.queryIfEnabled(
			function(data){
				if(data["enabled"] == true){
					moduleEnabled();
				}
				else{
					console.log("Notifications not enabled");
				}
			}
		);
	}
	
	function notification(title){
		this.title = title;
	}
	
	function moduleEnabled(){
		console.log("Notifications are enabled, ok");
		networkModule.queryForNotifications(function(data){
			console.log(data);
			
			var title = new notification("Some title");
			
			networkModule.postNewNotification(title, function(){
				console.log("Post success");
			});
			
			networkModule.deleteNotification(function(data , statusText, xhr){
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
				var url = "/notifications/enabled";
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
			deleteNotification: function(successCall) {
				var url = "/notifications/id";
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


 	
