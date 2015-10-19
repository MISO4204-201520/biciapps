/**
 * Created by Jhon Gutierrez on 14/10/2015.
 */
function metodoClick() {
    var contacto = $(this).parents('tr').children()[0].textContent   
    var fecha = $(this).parents('tr').children()[2].textContent
    
    var url = "/mensajes/marcarLeido";

    $.ajax({
        type: "POST",
        url: url,
        contentType: 'application/json',
        data: '{ "contacto" : "' + contacto + '", "fecha" : "' + fecha +'" }',
        dataType: "json",
        success: resultado,
        failure: failureCall,
        statusCode: {
            400: badRequest
        },
    });

}

function resultado(data){
   alert(data);
}

function badRequest(data, textStatus, jqXHR){
    console.log("Bad request " + data);
}

function failureCall(errMsg){
    console.log("Error:" + errMsg);
}
