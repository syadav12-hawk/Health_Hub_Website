google.charts.load('current', {packages: ['corechart','bar','table']});
//google.charts.setOnLoadCallback(createDataTable);
//google.charts.setOnLoadCallback(drawBarChart);
//google.charts.setOnLoadCallback(drawStacked);

//Bind click event to make an ajax call to post request of Inventory. This will return
// a json object with top 3 review for each city;

/*
$("#clickbar").click(function () {
     $("#clickbar").hide();
    $.ajax({
        url: "Salesreport",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)            
        },
        error: function(){
            document.write("error occurred while making ajax call;")
        }
    });    
});

*/

 google.setOnLoadCallback(function () {
		alert('from inside ready 1');
		$.ajax({
		url: "Salesreport",
		type: "POST",
		data: "{}",
		success: function (msg) {
			createDataTable(msg)            
		},
		error: function(){
			document.write("error occurred while making ajax call;")
		}
	});
 });


//This method will parse json data and build datatable required by google api to plot the bar chart.
function createDataTable(jsonData) {

	try {
		
		
	var parsedData = $.parseJSON(jsonData);
    var data4 = new Array();
	//var data1 = new Array();
    var orderNameArr = new Array();
	var orderProdPriceArr = new Array();
	var orderDateArr = new Array();
	var orderProdCount=[];
	var orderTotalSales=[];
	var perDaySaleArr= new Array();
		
		//Create an array of product name, product price and item Count
		for(var i=0; i<=parsedData.length-1; i++) {
			
			var orderName = parsedData[i]["orderName"];
			var orderProdPrice = parsedData[i]["orderPrice"];
			
			
			if(!orderNameArr.includes(orderName)){
				//var index = orderNameArr.indexOf(orderName);
				//orderProdCount[index]+=1;
				orderNameArr.push(orderName);
				orderProdPriceArr.push(orderProdPrice);
				orderProdCount.push(1);
			}
			else{
				var index = orderNameArr.indexOf(orderName);
				orderProdCount[index]+=1;	
			}

		 }
		 
		
		for(var i=0; i<=orderNameArr.length-1; i++){
			orderTotalSales.push(orderProdPriceArr[i]*orderProdCount[i]);
		}
			
		
		
		var headingArray5 = new Array(2);
		headingArray5[0] = "Product Name";
		headingArray5[1] = "Total Sale";
		
		data4[0] = headingArray5;
		var j=1;
		for(var i=0; i<=orderNameArr.length-1; i++) {
			var dataArr = new Array(2);
			dataArr[0]=orderNameArr[i];
			dataArr[1]=parseFloat(orderTotalSales[i]);
			data4[j]=(dataArr);
			
			j++;
		}
	
	    drawBarChart1(data4);


	}
	catch(err) {
		document.write("Inside Create Data Table");
		document.write(err.message);
		document.getElementById("barsale_div").innerHTML = err.message;
	}

}


function drawBarChart1(data4){
	try {
		
		var bardata = google.visualization.arrayToDataTable(data4);
		var chart = new google.visualization.BarChart(document.getElementById('barsale_div'));
		chart.draw(bardata, {width: 600, height: 800, is3D: true, title: 'Total Sales for Every Product'});
	}
	catch(err){
			document.write("Inside Create bar Chart");
			document.write(err.message);
			document.getElementById("barsale_div").innerHTML = err.message;
	}

}










