

    
function fpass(){
 var email = $('#email').val();
 var pass = $('#password').val();
 var recuperation = $('#recuperation').val();
 $.ajax({
    url: "ClientController",
    data: {user: email, pass :pass , recuperation : recuperation ,op:"fpass"},
    type: 'POST',
    success: function (data, textStatus, jqXHR) {
        alert(data);
    },
    error: function (jqXHR, textStatus, errorThrown) {
        console.log(textStatus);
    }});


}

