$(document).ready(function () {
    console.log("Ready");
    $( "#createUserBtn" ).click(crearUsuario);
    $( "#updateUserBtn" ).click(actualizarUsuario);
    $( "#findPromotionsBtn" ).click(buscarPromociones);
});

function ponerIdUsuario(idUsuario){
    $('#idUsuario').val(idUsuario);
}

function darIdUsuario(){
    return $('#idUsuario').val();
}

/**Create User ---------------------*/
function crearUsuario(){
    var data = {};
    data.longitude = $("#lat").val();
    data.latitude = $("#lon").val();
    restPostUsuario(data);
}
function restPostUsuario(data){
    var url = "/contexto/usuario";
    $.ajax({
          contentType: 'application/json',
          type: "POST",
          url: url,
          data: JSON.stringify(data),
          success: successPostUsuario,
          failure: failureCall,
          dataType: "json",
          statusCode: {
                400:badRequestPostUsuario
            },
        });
}

function successPostUsuario( data, textStatus, jqXHR ){
    ponerIdUsuario(data.idUsuario);
}

function badRequestPostUsuario( data, textStatus, jqXHR ){
    console.log("Invalid " + data);
}


/**Update User ---------------------*/
function actualizarUsuario(){
    var data = {};
    data.longitude = $("#lat").val();
    data.latitude = $("#lon").val();
    var idUsuario = darIdUsuario();
    restPutUsuario(idUsuario, data);
}
function restPutUsuario(idUsuario, data){
    var url = "/contexto/usuario/" + idUsuario;
    $.ajax({
          contentType: 'application/json',
          type: "PUT",
          url: url,
          data: JSON.stringify(data),
          success: successPutUsuario,
          processData: false,
          failure: failureCall,
          dataType: "json",
          statusCode: {
                400:badRequestPutUsuario,
                404:notFoundPutUsuario
            },
        });
}

function successPutUsuario( data, textStatus, jqXHR ){
    console.log("userId: " + data.idUsuario);
}

function badRequestPutUsuario( data, textStatus, jqXHR ){
    console.log("Invalid " + data);
}

function notFoundPutUsuario( data, textStatus, jqXHR ){
    console.log("Not Found " + data);
}


/**Get Promotion ---------------------*/

function buscarPromociones(){
    restGetPromociones();
}

function restGetPromociones(idUsuario){
    var url = "/contexto/promocion";
    var data = {idUsuario:"1at"};
    $.ajax({
          type: "GET",
          url: url,
          data: data,
          success: successGetPromociones,
          failure: failureCall,
          statusCode: {
                404:notFoundGetPromociones
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

function notFoundGetPromociones( data, textStatus, jqXHR ){
    console.log("Not Found " + data);
}


function failureCall(errMsg){
    console.log("Err:" + errMsg);
}