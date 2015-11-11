/*
 * Promotions network operations
 */

define ({

	queryPromotions: function (data, successCall, notEnabledCall){
            notEnabledCall = (typeof notEnabledCall !== 'undefined')? notEnabledCall: function(data){
                console.log("Module not enalbed");
                }; 

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
				},
                        404: notEnabledCall 

		    },
		});
	},

});
