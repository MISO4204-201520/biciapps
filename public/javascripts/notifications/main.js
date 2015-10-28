/*
 *
 */
define(function(require){
	console.log("hello from main");	


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
			notificationModule.queryForNotifications(function(notifications){	
				updateList(notifications);
			});
		});

		$("#createNotifictionsBtn").click(function(){	
			var title = new notificationModule.notification("Topic", "message", "userId");	
			networkModule.postNewNotification(title, function(){
				console.log("Post success");
			});
		});	
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
		networkModule.deleteNotification(id, function(){	
				notificationModule.queryForNotifications(
					function(notifications){	
					updateList(notifications);
				});
			});
	}

});
