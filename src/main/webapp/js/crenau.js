
$(document).ready(function() {
	$.ajax({
		url: "CrenonController",
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

function addc() {
	var start = $('#debc').val();
	var end = $('#finc').val();
	$.ajax({
		url: "CrenonController",
		data: { start: start, end: end, op: "addc" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			fill(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR);
		}
	});

}
function delc(i) {

	$.ajax({
		url: "CrenonController",
		data: { id: i, op: "delc" },
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
	var start = "";
	var end = "";
	for (let i = 0; i < c.length; i++) {
		mod = c[i].id + ",\"" + c[i].start + "\"," + c[i].end + ",\"";
	start =  c[i].start.hour +" : " + c[i].start.minute +" : " + c[i].start.second;
	end =  c[i].end.hour +" : " + c[i].end.minute +" : " + c[i].end.second;

		ligne += "<tr> <td>" + (i + 1) + "</td> <td>" + start + "</td> <td>" + end + "</td> <td> <button type=\"button\" onclick=\"delc(" + c[i].id + ")\" class=\"btn btn-link btn-rounded btn-icon \"> <i class=\"mdi text-danger mdi-delete icon-md \"></i> </button> </td> </tr>";
	}
	$("#content").html(ligne);
}
