/**
 * 
 */

define ({
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
});



