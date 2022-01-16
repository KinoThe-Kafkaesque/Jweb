
$(document).ready(function() {
	$.ajax({
		url: "ClientController",
		data: { op: "load" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			fill(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown, jqXHR);
		}
	});
});

function passu() {
	var user = $('#user').val();
	var pass = $('#pass').val();

		$.ajax({
			url: "UserController",
			data: { user: user  , pass : pass , op: "pass" },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				fill(data);
				console.log(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR);
			}
		});
}
function editu(data) {
	console.log(data);
	$('#user').val(data.username)
	$('#capacity').val(data.capacity);
	$('#type').val(data.type);
	i = data.id;
}
function delu(i) {
	$.ajax({
		url: "UserController",
		data: { id: i, op: "delu" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			fill(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR);
		}
	});

}
function fill(c) {
	var ligne = "";
	var mod = "";
	for (let i = 0; i < c.length; i++) {
		mod = c[i].id + ",\"" + c[i].code + "\"," + c[i].capacity + ",\"" + c[i].type + "\"";
		ligne += "<tr> <td>" + (i + 1) + "</td> <td>" + c[i].nom + "</td> <td>"
			+ c[i].prenom + "</td>  <td>" + c[i].username + "</td>  <td>" + c[i].role + "</td>  <td> <button type=\"button\" onclick=\"delu("
			+ c[i].id + ")\" class=\"btn btn-link btn-rounded btn-icon \"> <i class=\"mdi text-danger mdi-delete icon-md \"></i> </button> </td>  <td> <button type=\"button\" onclick='editu(" +
			JSON.stringify(c[i]) + ")' class=\"btn btn-link btn-rounded btn-icon \"> <i class=\"mdi text-success mdi-border-color  icon-md \"></i> </button> </td>  </tr>";
	}
	$("#content").html(ligne);
}
function search() {
    var o = $('#search').val();

    $.ajax({
		url: "ClientController",
		data: { o:o ,op: "search" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			fill(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown, jqXHR);
		}
	});
}
function banu() {
	var user = $('#user').val();
	$.ajax({
		url: "UserController",
		data: { user: user, op: "ban" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			fill(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});

}	
function unbanu() {
	var user = $('#user').val();
	$.ajax({
		url: "UserController",
		data: { user: user, op: "unban" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			fill(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});

}	