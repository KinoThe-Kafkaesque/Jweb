


function login() {
	var user = $('#username').val();
	var pass = $('#pass').val();
	console.log(user + " + " + pass);
	$.ajax({
		url: "ClientController",
		data: { user: user, pass: pass, op: "login" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			//location.href = 'http://localhost:8080/Jweb/Occupation.jsp';
			location.href ='https://occupation.herokuapp.com/Occupation.jsp';
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(JSON.stringify(textStatus));
			
		}
	});
	return false;


}

