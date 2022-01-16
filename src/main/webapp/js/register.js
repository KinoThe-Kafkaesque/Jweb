

    
function register(){
 var fname = $('#fname').val();
 var lname = $('#lname').val();
 var email = $('#email').val();
 var pass = $('#password').val();
 var recuperation = $('#recuperation').val();
console.log(fname);
 $.ajax({
    url: "ClientController",
    data: {fname: fname, lname: lname, email: email, pass :pass , recuperation : recuperation ,op:"register"},
    type: 'POST',
    success: function (data, textStatus, jqXHR) {
        alert("Votre registation est reussi. \n Veuillez vous authentifier ");
    },
    error: function (jqXHR, textStatus, errorThrown) {
        console.log(textStatus);
    }});


}

