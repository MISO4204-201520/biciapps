/*
 *
 */
define(function(require){


	var networkModule = require('network/promotionsNetwork');
	console.log("promotions " + networkModule);

        function promotion(titulo, descripcion, tienda, longitud, latitud, id){
			this.titulo = titulo;
			this.descripcion = descripcion; 
			this.tienda = tienda;
			this.longitud = longitud;
			this.latitud = latitud;
			this.id = id;

		}	

	return{

		queryPromotions: function(data, onSuccess, onNotEnabled ){	
		    networkModule.queryPromotions(data, function(data){
		    	var promotions = [];
		        for(var i = 0; i < data.length; i ++){
				var p = new promotion(data[i]['titulo'], data[i]['descripcion'], 
					data[i]['tienda']);
				promotions.push(p);
			}	
			onSuccess(promotions);
		    },
                    onNotEnabled
                    );	
		},
                promotion:promotion

	}
});
