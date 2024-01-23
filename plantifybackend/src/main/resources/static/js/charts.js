/*<![CDATA[*/
let moistureArchive = [];
let brightnessArchive = [];
let humidityArchive = [];
let temperatureArchive = [];

function getDataList(dataPassedThrough, boundsPassedThrough){
    brightnessArchive.push(['Timestamp','Lower bound (Minimal optimum)','Brightness (in LUX)','Upper bound (maximal optimum)','Forecasted Brightness in LUX'])
    moistureArchive.push(['Timestamp','Lower bound (Minimal optimum)','Moisture (in %)','Upper bound (maximal optimum)','Forecasted soil moisture in %'])
    humidityArchive.push(['Timestamp','Lower bound (Minimal optimum)','Humidity (in %)','Upper bound (maximal optimum)','Forecasted Humidity in %'])
    temperatureArchive.push(['Timestamp','Lower bound (Minimal optimum)','Temperature (in °C)','Upper bound (maximal optimum)','Forecasted Temperature in °C'])

    for (let i = 0; i < dataPassedThrough.length; i++) {

        let timestamp = dataPassedThrough.at(i).refreshtime;
        timestamp = timestamp.toLocaleString().substring(0,10);
        moistureArchive.push([timestamp,boundsPassedThrough[2],dataPassedThrough.at(i).moistureavg,boundsPassedThrough[1],null]);
        brightnessArchive.push([timestamp,null,dataPassedThrough.at(i).lightavg,boundsPassedThrough[0],null]);
        humidityArchive.push([timestamp,boundsPassedThrough[5],dataPassedThrough.at(i).humidityavg,null,null]);
        temperatureArchive.push([timestamp,boundsPassedThrough[4],dataPassedThrough.at(i).temperatureavg,boundsPassedThrough[3],null]);

    }
}


function getForecastingData(forecastedData,forecastedDataBrightness,forecastedDataHumidity,forecastedTemp,boundsPassedThrough ){
    console.log(boundsPassedThrough)
    console.log("above is data /////////////////////////")
    for (let i = 0; i < forecastedData.length; i++) {
        console.log(forecastedData.at(i));
        if (i === 0){
            brightnessArchive.push(["2022-12-14",0,forecastedDataBrightness.at(0),boundsPassedThrough[0],forecastedDataBrightness.at(i)]);
            moistureArchive.push(["2022-12-14",boundsPassedThrough[2],forecastedData.at(0),boundsPassedThrough[1],forecastedData.at(i)]);
            humidityArchive.push(["2022-12-14",boundsPassedThrough[5],forecastedDataHumidity.at(0),null,forecastedDataHumidity.at(i)]);
            temperatureArchive.push(["2022-12-14",boundsPassedThrough[4],forecastedTemp.at(0),boundsPassedThrough[3],forecastedTemp.at(i)]);
        }else {
            brightnessArchive.push(["2022-12-14",null,null,boundsPassedThrough[0],forecastedDataBrightness.at(i)]);
            moistureArchive.push(["2022-12-14",boundsPassedThrough[2],null,boundsPassedThrough[1],forecastedData.at(i)]);
            humidityArchive.push(["2022-12-14",boundsPassedThrough[5],null,0,forecastedDataHumidity.at(i)]);
            temperatureArchive.push(["2022-12-14",boundsPassedThrough[4],null,boundsPassedThrough[3],forecastedTemp.at(i)]);
        }

    }

}


// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages': ['corechart']});
// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChartBrightness);

google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChartHumidity);


google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChartTemp);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {
    let data = google.visualization.arrayToDataTable(moistureArchive);

    //set chart options
    var options = {
        title: 'Average soil moisture level per day',
        curveType: 'function',
        legend: {position: 'bottom'}
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.LineChart(document.getElementById('pills-home'));
    chart.draw(data, options);
}


function drawChartBrightness() {
    let data = google.visualization.arrayToDataTable(brightnessArchive);

    //set chart options
    var options = {
        title: 'Average brightness level per day',
        curveType: 'function',
        legend: {position: 'bottom'}
        ,height: 500
        ,width: 900
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.LineChart(document.getElementById('pills-profile'));
    chart.draw(data, options);
}
function drawChartHumidity() {
    let data = google.visualization.arrayToDataTable(humidityArchive);

    //set chart options
    var options = {
        title: 'Average air humidity % level per day',
        curveType: 'function',
        legend: {position: 'bottom'}
        ,height: 500
        ,width: 900
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.LineChart(document.getElementById('pills-contact'));
    chart.draw(data, options);
}
function drawChartTemp() {
    let data = google.visualization.arrayToDataTable(temperatureArchive);

    //set chart options
    var options = {
        title: 'Average air temperature level per day',
        curveType: 'function',
        legend: {position: 'bottom'}
        ,height: 500
        ,width: 900
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.LineChart(document.getElementById('pills-temp'));
    chart.draw(data, options);
}