$(document).ready(function () {
    console.log("Hello2");
    $( "#sendBtn" ).click(sendData);
    $( "#findBtn" ).click(findPromotion);
});

function sendData(){
    var data = {};
    data.longitude = $("#lat").val();
    data.latitude = $("#lon").val();
    postNewData(data);
}

function findPromotion(){
    getPromotions();
}

function getPromotions(){
    var url = "/context/promotion";
    var data = {id:"1at"};
    $.ajax({
          type: "GET",
          url: url,
          data: data,
          success: successCall,
          failure: failureCall,
        });
}


function successCall( data, textStatus, jqXHR ){
    for(var i = 0; i < data.length; i ++){
        console.log("title: " + data[i].title);
    }
}

function failureCall(errMsg){
    console.log("Err:" + errMsg);
}

function postNewData(data){
    var url = "/context/promotion/data" + "?id=";
    var x = {foundation: "Mozilla", model: "box", week: 45, transport: "car", month: 7};
    $.ajax({
          contentType: 'application/json',
          type: "POST",
          url: url+3,
          data: JSON.stringify(data),
          success: function(){
                       console.log("Success ");
                   },
          failure: failureCall,
          //dataType: "json"
        });
}

