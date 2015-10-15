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
	restGetPromociones(data);
}

function restGetPromociones(data){
    var url = "/contexto/promocion";
    $.ajax({
          type: "POST",
          url: url,
          contentType: 'application/json',
          data: JSON.stringify(data),
          dataType: "json",
          success: successGetPromociones,
          failure: failureCall,
          statusCode: {
                400:badRequestGetPromociones
            },
        });
}


function successGetPromociones( data, textStatus, jqXHR ){
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