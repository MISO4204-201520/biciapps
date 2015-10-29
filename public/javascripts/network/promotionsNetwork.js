/*
 * Promotions network operations
 */

define ({

	queryPromotions: function (data, successCall){
	    var url = "/contexto/promocion";
	    $.ajax({
		  type: "GET",
		  url: url,
		  contentType: 'application/json',
		  data: JSON.stringify(data),
		  dataType: "json",
		  success: successCall,
		  failure: function(errMsg){
				console.log("Err:" + errMsg);
			},
		  statusCode: {
			400: function(data){
					console.log("Bad request " + data);
				} 

		    },
		});
	},

});
