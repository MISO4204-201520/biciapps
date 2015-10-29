/*
 *
 */
define(function(require){


	var networkModule = require('network/promotionsNetwork');
	console.log("promotions " + networkModule);

	return{

		queryPromotions: function(data, onSuccess){	
		    networkModule.queryPromotions(data, function(data){
		    	var promotions = [];
		        for(var i; i < data.length; i ++){
				var p = new promotion(data['titulo'], data['descripcion'], 
					data['tienda']);
				promotions.push(p);
			}	
			onSuccess(data);
		    });	
		},

		promotion: function(titulo, descripcion, tienda, longitud, latitud, id){
			this.titulo = titulo;
			this.descripcion = descripcion; 
			this.tienda = tienda;
			this.longitud = longitud;
			this.latitud = latitud;
			this.id = id;

		}	
	}
});
