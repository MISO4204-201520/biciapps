/*
 *
 */
define(function(require){
	console.log("hello from main");	

	function notification(topic, message, userId, id){
		this.topic = topic;
		this.message = message;
		this.userId = userId;
		this.id = id;
	}

	var networkModule = require("network/notificationNetwork");
	var notificationModule = require("./notifications"); 
	
	console.log(networkModule);	
	var notifications = [];
	var notificationsEnabled = false;

	
	$(document).ready(function () {
	    notificationModule.queryIfNotificationsEnabled(onNotificationEnabled);
	});

	function onNotificationEnabled(){
		$("#findNotifictionsBtn").click(function(){
			networkModule.queryForNotifications(function(data){
				console.log("Len: " +data.length);
				
				notifications.length = 0;
				for(var i = 0 ; i< data.length; i++){
					var n = new notification(data[i]['topic'], 
						data[i]['message'], data[i]['userId'], data[i]['id'])
					notifications.push(n);
					console.log(n);
				}
				updateList(notifications);
			});	
		});

		$("#createNotifictionsBtn").click(function(){	
			var title = new notification("Topic", "message", "userId");	
			networkModule.postNewNotification(title, function(){
				console.log("Post success");
			});
		});	
		//moduleEnabled();
	}

	function queryForNotifications(){

	}	
	
	function updateList(data){
		var items = [];
		$.each(data, function(i, item) {
			var btn = '<button id="'+ item.id + 
				'" type="button" class="btn btn-default">' + 
					'<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>'+
				'</button>';
			var listItem ='<li>' + item.topic + ' '+ btn +'</li>'  
		      	items.push(listItem);
	       });  // close each()
	       $('#listaNotificaciones li').remove();
	       $('#listaNotificaciones').append( items.join('') );
		
	       $.each(data, function(i, item) {
		   $('#'+item.id).click({id: item.id},onNotificationClicked)
	       });  // close each()
       
	}

	function onNotificationClicked(item){
		//var id = $(item).attr("id");
		var id = item.data.id;
		console.log("hello: " +  id);
		networkModule.deleteNotification(id, function(data , statusText, xhr){
					if(xhr.status == 200){
						console.log("Delete success");
						//moduleEnabled();
					}
					else{
						console.log("No content")
					}
				});
	}

});
