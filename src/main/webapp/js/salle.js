
var i = 0;
$(document).ready(function() {
	$.ajax({
		url: "SalleController",
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

function adds() {
	var code = $('#code').val();
	var capacity = $('#capacity').val();
	var type = $('#type').val();

	if (i === 0) {
		console.log(i);
		$.ajax({
			url: "SalleController",
			data: { code: code, capacity: capacity, type: type, op: "adds" },
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
	else {
		$.ajax({
			url: "SalleController",
			data: { code: code, capacity: capacity, type: type, id: i, op: "edits" },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				fill(data);
				$('#btn').html("Ajouter");
				i = 0;
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
			}
		});

	}

}

function edits(data) {
	console.log(data);
	$('#code').val(data.code)
	$('#capacity').val(data.capacity);
	$('#type').val(data.type);
	$('#btn').html("Modifer");
	i = data.id;
}
function delc(i) {

	$.ajax({
		url: "SalleController",
		data: { id: i, op: "dels" },
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

		ligne += "<tr> <td>" + (i + 1) + "</td> <td>" + c[i].code + "</td> <td>"
			+ c[i].capacity + "</td>  <td>" + c[i].type + "</td>  <td> <button type=\"button\" onclick=\"dels("
			+ c[i].id + ")\" class=\"btn btn-link btn-rounded btn-icon \"> <i class=\"mdi text-danger mdi-delete icon-md \"></i> </button> </td>  <td> <button type=\"button\" onclick='edits(" +
			JSON.stringify(c[i]) + ")' class=\"btn btn-link btn-rounded btn-icon \"> <i class=\"mdi text-success mdi-border-color  icon-md \"></i> </button> </td>  </tr>";
	}
	$("#content").html(ligne);
}
