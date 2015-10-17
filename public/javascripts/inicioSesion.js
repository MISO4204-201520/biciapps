/**
 * Created by Omar on 14/10/2015.
 */
function validarContrasena(){
    var pass = $("#pwd").val();
    var confirmarPass = $("#pwd1").val();

    var pwd1 = document.getElementById("pwd1");

    if(pass != confirmarPass) {
        pwd1.setCustomValidity("La contrase\u00F1a no coincide");
    } else {
        pwd1.setCustomValidity('');
    }
}