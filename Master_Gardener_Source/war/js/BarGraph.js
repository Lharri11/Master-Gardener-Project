var pollinatorString = document.getElementById("pollinatorString").valueOf();
var pollinatorLabels = init(pollinatorString);

window.alert(pollinatorString);
function init(jsonInitString) {
    var json = jsonInitString;
    var pollinatorNames = [];

    for (var i = 0; i < json.length; i++) {
        pollinatorNames.push(json[i].pollinatorName);
    }
    window.alert(pollinatorNames);
    return pollinatorNames;
}
/*
$.getJSON('chartGen.jsp', function(jsonPollinator) {

    $.each(jsonPollinator, function(index, jsonPollinator) {

        var pollinatorNames = [];

        for (var i = 0; i < jsonPollinator.length; i++) {
            pollinatorNames.push(jsonPollinator[i].pollinatorName);
        }
    });

$.getJSON("http://localhost:8081/Master-Gardener/chartGen", function(json) {
    $.each (json, function(i, json) {
        var pollinatorLabel = {"pollinatorName": json[i].pollinatorName}
        pollinatorLabels.push(pollinatorLabel);
    });
});*/

    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Nigger", "Nigger", "Nigger"],
            datasets: [{
                label: '# of Pollinators',
                data: [300, 400, 500],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
});

