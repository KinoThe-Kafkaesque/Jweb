
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
});

function search() {
	var date = $('#date').val();
	var salle = $('#salle').val();

	if (date !== "" && salle !== 0) {
		$.ajax({
			url: "OcuppationController",
			data: { date: date, salle: salle, op: "search" },
			type: 'POST',
			success: function (data, textStatus, jqXHR) {
				fill(data);
				if(data=[]){
					alert("aucun resultat");
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(jqXHR);
			}
		});
	}
	else {
		alert("Veuillez selectionner une date et une salle");

	}

}


function fillc1(c) {
	var sc = "";
	for (let i = 0; i < c.length; i++) {
		sc += "<option value='" + c[i].id + "'>" + c[i].code + " : " + c[i].capacity + "</option>";
	}
	$("#salle").html(sc);

}


function fill(c) {

	var ligne = "";
	var date = "";
	var start = "";
	var end = "";


	for (let i = 0; i < c.length; i++) {
		date = c[i].date.year + "-" + c[i].date.month + "-" + c[i].date.day
		start = c[i].crenon.start.hour + " : " + c[i].crenon.start.minute + " : " + c[i].crenon.start.second;
		end = c[i].crenon.end.hour + " : " + c[i].crenon.end.minute + " : " + c[i].crenon.end.second;


			ligne += "<tr> <td>" + (i + 1) + "</td> <td>" + date + "</td> <td>"
			+ start + "</td>  <td>" + end + "</td>  <td>"
			+ c[i].salle.code + "</td>   <td>"
			+ c[i].admin.username + "</td></tr>";

	}
	$("#content").html(ligne);
}
