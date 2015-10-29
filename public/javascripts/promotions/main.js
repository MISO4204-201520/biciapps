/*
 *
 */

define(function(require){


	//var networkModule = require('network/promotionsNetwork');
	var promotionsModule = require('./promotions');

	$(document).ready(function () {
	    console.log("Ready");
	    $( "#findPromotionsBtn" ).click(buscarPromociones);
	});

	function ponerIdUsuario(idUsuario){
	    $('#idUsuario').val(idUsuario);
	}

	function darIdUsuario(){
	    return $('#idUsuario').val();
	}

	/**Get Promotion ---------------------*/

	function buscarPromociones(){
		var data = {};
	    data.longitud = $("#lon").val();
	    data.latitud = $("#lat").val();
	    data.maxDistancia = $("#maxDistancia").val();
	    promotionsModule.queryPromotions(data, successGetPromociones);	
	}


	function successGetPromociones(data){
	     var items = [];
		$.each(data, function(i, item) {
		      items.push('<li>' + '<b>' + item.titulo + '('+item.tienda+'): '+ '</b>' + item.descripcion + '</li>');
	       });  // close each()
	       $('#listaPromociones li').remove();
	       $('#listaPromociones').append( items.join('') );
	}

	function badRequestGetPromociones( data, textStatus, jqXHR ){
	    console.log("Bad request " + data);
	}


	function failureCall(errMsg){
	    console.log("Err:" + errMsg);
	}

});
