google.charts.load('current', {packages: ['corechart','bar','table']});
//google.charts.setOnLoadCallback(drawBarChart1);
//google.charts.setOnLoadCallback(drawBarChart);
//google.charts.setOnLoadCallback(drawStacked);

//Bind click event to make an ajax call to post request of Inventory. This will return
// a json object with top 3 review for each city;

/*
$("#click").click(function () {
     $("#click").hide();
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
		//alert('from inside ready 1');
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
    var data = new Array();
	var data1 = new Array();
	var data4 = new Array();
    var orderNameArr = new Array();
	var orderProdPriceArr = new Array();
	var orderDateArr = new Array();
	var orderProdCount=[];
	var orderTotalSales=[];
	var totalSaleperDay=[];

	var orderDate=[];

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
			
		
		
		var headingArray = new Array(4);
		headingArray[0] = "Product and Services";
		headingArray[1] = "Price";
		headingArray[2] = "Count";
		headingArray[3] = "Total Sale";
		
		data[0] = headingArray;
		var j=1;
		for(var i=0; i<=orderNameArr.length-1; i++) {
			var dataArr = new Array(4);
			dataArr[0]=orderNameArr[i];
			dataArr[1]=parseFloat(orderProdPriceArr[i]);
			dataArr[2]=parseInt(orderProdCount[i]);
			dataArr[3]=parseFloat(orderTotalSales[i]);
			
			data[j]=(dataArr);
			
			j++;
		}
		
		//document.write(data);
		drawTable(data);

		
		
		//Date----------------------------------------------------------------------------------------------
		//drawTable2();
		console.log(orderDateArr.length);
		for(var i=0; i<=parsedData.length-1; i++) {
			
			var orderProdPrice = parsedData[i]["orderPrice"];
			console.log(orderProdPrice);
            var odate=parsedData[i]["orderDate"];
			var orderdate = new Date(odate.month+"/"+odate.day+"/"+odate.year);
			if (orderDateArr.length==0){
				orderDateArr.push(orderdate);
				totalSaleperDay.push(orderProdPrice);
			}
			
			console.log(checkDate(orderDateArr,orderdate));
			var ind=checkDate(orderDateArr,orderdate);

			if(ind<=orderDateArr.length-1){
				totalSaleperDay[ind]=totalSaleperDay[ind]+orderProdPrice;
			}
			else{
				orderDateArr.push(orderdate);
				totalSaleperDay.push(orderProdPrice);
			}

			
		 }
		console.log(orderDateArr);
		console.log(totalSaleperDay);
		drawTable2(orderDateArr,totalSaleperDay);


		//-------------------------------------------------------------------------------------------------------
		
		
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
		document.getElementById("sales_div").innerHTML = err.message;
	}

}




function checkDate(orderDateArr,orderdate){
	for(var i=0; i<=orderDateArr.length-1; i++) {
		if (orderDateArr[i].getTime() === orderdate.getTime()){
			return i;
		}
	}
	return i;
}



function getDate(odate){
	
	var date = new Date(odate.month+"/"+odate.day+"/"+odate.year);
	return date
}

function drawBarChart1(data4){
	try {
		
		var bardata = google.visualization.arrayToDataTable(data4);
		var chart = new google.visualization.BarChart(document.getElementById('barsale_div'));
		chart.draw(bardata, {width: 600, height: 800, is3D: true, title: 'Total Sales for Each Product and Services'});
	}
	catch(err){
			document.write("Inside Create bar Chart");
			document.write(err.message);
			document.getElementById("barsale_div").innerHTML = err.message;
	}

}


function drawTable(data){
	
	try {
		var tableData = google.visualization.arrayToDataTable(data);
		var table = new google.visualization.Table(document.getElementById("sales_div"));
		table.draw(tableData, {showRowNumber: true, width: '100%', height: '100%'});	
	}
	catch(err){
	   document.write("Inside Draw Table");
	   document.write(err.message);
	   document.getElementById("sales_div").innerHTML = err.message;
	}
	
}


/*
function drawTable2(){
	
	try {
		var data = new google.visualization.DataTable();
        data.addColumn('string', 'Date');
        data.addColumn('number', 'Sales per Day');
        data.addRows([
        ["Nov 11,2020",  {v: 10000, f: '$90743.7000'}],
        ["Nov 25,2020", {v: 12500, f: '$10343.91'}],
		["Nov 27,2020",  {v: 10000, f: '$90743.7000'}],
        ["Dec 11,2020",  {v: 10000, f: '$90743.7000'}],
        ["Oct 11,2020",  {v: 10000, f: '$90743.7000'}],
        ["Oct 11,2020",  {v: 10000, f: '$90743.7000'}]
        ]);
		
		
		var table1 = new google.visualization.Table(document.getElementById("sales_div2"));
		table1.draw(data, {showRowNumber: true, width: '100%', height: '100%'});	
	}
	catch(err){
	   document.write("Inside Draw Table");
	   document.write(err.message);
	   document.getElementById("sales_div").innerHTML = err.message;
	}
	
}
*/


function drawTable2(orderDateArr,totalSaleperDay){
	
	try {
		var data = new google.visualization.DataTable();
        data.addColumn('date', 'Date');
        data.addColumn('number', 'Sales per Day');
		for(i = 0; i < orderDateArr.length; i++) 
		   data.addRow([orderDateArr[i], totalSaleperDay[i]]);
		
		var table1 = new google.visualization.Table(document.getElementById("sales_div2"));
		table1.draw(data, {showRowNumber: true, width: '100%', height: '100%'});	
	}
	catch(err){
	   document.write("Inside Draw Table");
	   document.write(err.message);
	   document.getElementById("sales_div").innerHTML = err.message;
	}
	
}








