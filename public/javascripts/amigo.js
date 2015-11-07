/**
 * Created by Omar on 14/10/2015.
 */
function metodoClick() {
    $("#resOk").hide();
    $("#resError").hide();
    var email = $(this).parents('tr').children()[2].textContent
    var url = "/account/addAmigo";

    $.ajax({
        type: "POST",
        url: url,
        contentType: 'application/json',
        data: '{ "email" : "' + email + '"}',
        dataType: "json",
        success: resultado,
        failure: failureCall,
        statusCode: {
            400: badRequest
        },
    });

}

function resultado(data) {
    console.log(data);
    if (data.data[0].res) {
        $("#resOk").text(data.data[0].mensaje);
        $("#resOk").show();
        $('#contacts_table').DataTable().ajax.reload();
        $('#addContacts_table').DataTable().ajax.reload();
        //$('#myModal').modal('hide');
    }
    else {
        $("#resError").text(data.data[0].mensaje);
        $("#resError").show();
    }
}

function badRequest(data, textStatus, jqXHR){
    console.log("Bad request " + data);
}

function failureCall(errMsg){
    console.log("Error:" + errMsg);
}


function metodoDejarAmigoClick() {
    $("#resOk").hide();
    $("#resError").hide();
    var email = $(this).parents('tr').children()[1].textContent
    var url = "/account/removeAmigo";

    $.ajax({
        type: "POST",
        url: url,
        contentType: 'application/json',
        data: '{ "email" : "' + email + '"}',
        dataType: "json",
        success: resultadoDejarAmigo,
        failure: failureCallDejarAmigo,
        statusCode: {
            400: badRequestDejarAmigo
        },
    });

}
function resultadoDejarAmigo(data) {
    console.log(data);
    if (data.data[0].res) {
        $("#resOk").text(data.data[0].mensaje)
        $("#resOk").show()
        $('#contacts_table').DataTable().ajax.reload();
        $('#addContacts_table').DataTable().ajax.reload();
    }
    else {
        $("#resError").text(data.data[0].mensaje);
        $("#resError").show();
    }
}

function badRequestDejarAmigo(data, textStatus, jqXHR){
    console.log("Bad request " + data);
}

function failureCallDejarAmigo(errMsg){
    console.log("Error:" + errMsg);
}
