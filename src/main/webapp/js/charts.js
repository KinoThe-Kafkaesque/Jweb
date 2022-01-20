$(function () {
	$.ajax({
		url: "ChartsController",
		data: { op: "salle" },
		type: 'POST',
		success: function (data, textStatus, jqXHR) {
      fill(data);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown, jqXHR);
		}
	});
	$.ajax({
		url: "ChartsController",
		data: { op: "monthly" },
		type: 'POST',
		success: function (data, textStatus, jqXHR) {
			fill2(data);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown, jqXHR);
		}
	});
});

function fill(c) {
  var labels = [];
  var  values = [];

  for( var name in c){
    labels.push(name);
    values.push(c[name]);
  }

  const data = {
    labels: labels,
    datasets: [{
      label: 'nombres de reservation ',
      backgroundColor: 'rgb(255, 99, 132)',
      borderColor: 'rgb(255, 99, 132)',
      data: values ,
    }]
  };

  const config = {
    type: 'bar',
    data: data,
    options: {}
  };
  const myChart = new Chart(
    $('#MyChart'),
    config
  );
  }
  function fill2(c) {
    var labels = [];
    var  values = [];
  
    for( var name in c){
      var A = name.split("-");
      var month = A[1]; //months from 1-12
      var year = A[0];
      labels.push(month+"-"+year);
      values.push(c[name]);
    }
  
    const data = {
      labels: labels,
      datasets: [{
        label: 'nombres de reservation ',
        backgroundColor: 'rgb(255, 99, 132)',
        borderColor: 'rgb(255, 99, 132)',
        data: values ,
      }]
    };
  
    const config = {
      type: 'bar',
      data: data,
      options: {}
    };
    const myChart = new Chart(
      $('#Monthly'),
      config
    );
    }
