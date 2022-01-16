
$(document).ready(function () {
	$.ajax({
		url: "OcuppationController",
		data: { op: "load" },
		type: 'POST',
		success: function (data, textStatus, jqXHR) {
			fill(data);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown, jqXHR);
		}
	});
	$.ajax({
		url: "SalleController",
		data: { op: "load" },
		type: 'POST',
		success: function (data, textStatus, jqXHR) {
			fillc1(data);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown, jqXHR);
		}
	});
	$.ajax({
		url: "CrenonController",
		data: { op: "load" },
		type: 'POST',
		success: function (data, textStatus, jqXHR) {
			fillc2(data);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown, jqXHR);
		}
	});
});

function addo() {
	var date = $('#date').val();
	var salle = $('#salle').val();
	var creneau = $('#creneau').val();

		$.ajax({
			url: "OcuppationController",
			data: { date: date, salle: salle, creneau: creneau, op: "addo" },
			type: 'POST',
			success: function (data, textStatus, jqXHR) {
				fill(data);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(jqXHR);
			}
		});
	

}

function verify(id) {
	$.ajax({
		url: "OcuppationController",
		data: {  id: id, op: "verify" },
		type: 'POST',
		success: function (data, textStatus, jqXHR) {
			fill(data);
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		}
	});
}
function unverify(id) {
	$.ajax({
		url: "OcuppationController",
		data: {  id: id, op: "unverify" },
		type: 'POST',
		success: function (data, textStatus, jqXHR) {
			fill(data);
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		}
	});
}
function delo(i) {

	$.ajax({
		url: "OcuppationController",
		data: { id: i, op: "delo" },
		type: 'POST',
		success: function (data, textStatus, jqXHR) {
			fill(data);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(jqXHR);
		}
	});

}
function fillc1(c) {
	var sc = "";
	for (let i = 0; i < c.length; i++) {
		sc += "<option value='" + c[i].id + "'>" + c[i].code + " : " + c[i].capacity + "</option>";
	}
	$("#salle").html(sc);

}
function fillc2(c) {
	var ss = "";
	for (let i = 0; i < c.length; i++) {
		start = c[i].start.hour + " : " + c[i].start.minute + " : " + c[i].start.second;
		end = c[i].end.hour + " : " + c[i].end.minute + " : " + c[i].end.second;
		ss += "<option value='" + c[i].id + "'>" + start + " - " + end + "</option>";
		console.log(c[i].id);
	}
	$("#creneau").html(ss);

}
function fill(c) {
	var ligne = "";
	var mod = "";
	var date = "";
	var start = "";
	var end = "";


	for (let i = 0; i < c.length; i++) {
		mod = c[i].id + ",\"" + c[i].code + "\"," + c[i].capacity + ",\"" + c[i].type + "\"";
		date = c[i].date.year + "-" + c[i].date.month + "-" + c[i].date.day
		start = c[i].crenon.start.hour + " : " + c[i].crenon.start.minute + " : " + c[i].crenon.start.second;
		end = c[i].crenon.end.hour + " : " + c[i].crenon.end.minute + " : " + c[i].crenon.end.second;

		if (c[i].admin.id == 0) {
			ligne += "<tr> <td>" + (i + 1) + "</td> <td>" + date + "</td> <td>"
				+ start + "</td>  <td>" + end + "</td>  <td>"
				+ c[i].salle.code + "</td>   <td>"
				+ c[i].admin.username + "</td> <td> <button type=\"button\" onclick=\"delo("
				+ c[i].id + ")\" class=\"btn btn-link btn-rounded btn-icon \"> <i class=\"mdi text-danger mdi-delete icon-md \"></i> </button> </td>  <td> <button type=\"button\" onclick='verify(" +
				c[i].id + ")' class=\"btn btn-link btn-rounded btn-icon \"> <i class=\"mdi text-success mdi-calendar-check   icon-md \"></i> </button> </td>  </tr>";
		}
		else {
			ligne += "<tr> <td>" + (i + 1) + "</td> <td>" + date + "</td> <td>"
				+ start + "</td>  <td>" + end + "</td>  <td>"
				+ c[i].salle.code + "</td>   <td>"
				+ c[i].admin.username + " <td></td> <td> <button type=\"button\" onclick='unverify(" +
				+ c[i].id + ")' class=\"btn btn-link btn-rounded btn-icon \"> <i class=\"mdi text-danger mdi-calendar-remove  icon-md \"></i> </button> </td> </tr>";
		}

	}
	$("#content").html(ligne);
}
