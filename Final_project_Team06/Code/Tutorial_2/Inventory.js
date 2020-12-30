google.charts.load('current', {packages: ['corechart','bar','table']});
//google.charts.setOnLoadCallback(drawBarChart1);
//google.charts.setOnLoadCallback(drawBarChart);
// google.charts.setOnLoadCallback(drawStacked);

//Bind click event to make an ajax call to post request of Inventory. This will return
// a json object with top 3 review for each city;

/*
$("#btnGetChartData").click(function () {
     $("#btnGetChartData").hide();
    $.ajax({
        url: "Inventory",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)            
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
});
*/

google.setOnLoadCallback(function () {
		//alert('from inside ready 1');
		$.ajax({
		url: "Inventory",
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
    var parsedData = $.parseJSON(jsonData);
    var data = new Array();
	var data1 = new Array();
	var data2 = new Array();
	var data3 = new Array();
    var productNameArr = new Array();
	var productPriceArr = new Array();
	var itemCountArr = new Array();
	var saleInfoArr= new Array();
	var prodDiscountArr=new Array();
	
	
	try {
		
		//Create an array of product name, product price and item Count
		for(var i=0; i<parsedData.length; i++) {
			var prodName = parsedData[i]["prodName"];
			var prodPrice = parsedData[i]["prodPrice"];
			var itemCount= parsedData[i]["itemCount"];
			var saleInfo= parsedData[i]["saleInfo"];
			var prodDiscount=parsedData[i]["prodDiscount"];
			
			if(!productNameArr.includes(prodName)) {
				productNameArr.push(prodName);
				productPriceArr.push(prodPrice);
				itemCountArr.push(itemCount);
				saleInfoArr.push(saleInfo);
				prodDiscountArr.push(prodDiscount);
			}
			
		 }
			 
		//Create header array for google api
		var headingArray = new Array(3);
		headingArray[0] = "Product Name";
		headingArray[1] = "Product Price";
		headingArray[2] = "Item Count";
		 
		 
		data[0] = headingArray;
		var j=1;
		for(var i=0; i<=productNameArr.length-1; i++) {
			var dataArr = new Array(3);
			dataArr[0]=productNameArr[i];
			dataArr[1]=productPriceArr[i];
			dataArr[2]=itemCountArr[i];
			
			data[j]=(dataArr);
			
			j++;
		}
		 
		drawTable(data);
		
		
		
		//BarChart
		var headingArray = new Array(2);
		headingArray[0] = "Product Name";
		headingArray[1] = "Total Number of Items";
		
		data3[0] = headingArray;
		var j=1;
		for(var i=0; i<=productNameArr.length-1; i++) {
			var dataArr = new Array(2);
			dataArr[0]=productNameArr[i];
			dataArr[1]=itemCountArr[i];
			
			data3[j]=(dataArr);
			
			j++;
		}
		
		drawBarChart2(data3);
		
		
		
		
		
		
		
		//Sale product
		var headingArray1 = new Array(2);
		headingArray1[0] = "Product Name";
		headingArray1[1] = "On Sale";
	

		data1[0] = headingArray1;
		var j=1;
		for(var i=0; i<=productNameArr.length-1; i++) {
			var dataArr = new Array(2);
			if (saleInfoArr[i]=="sale"){
				dataArr[0]=productNameArr[i];
				dataArr[1]=saleInfoArr[i];
				data1[j]=(dataArr);
				j++;
				
			}
			
	
		}
		 
		drawTable1(data1);
		
		
		
		//Product Discount
		var headingArray2 = new Array(2);
		headingArray2[0] = "Product Name";
		headingArray2[1] = "Discount";
	

		data2[0] = headingArray2;
		var j=1;
		for(var i=0; i<=productNameArr.length-1; i++) {
			var dataArr = new Array(2);
			if (parseInt(prodDiscountArr[i])>0){
				dataArr[0]=productNameArr[i];
				dataArr[1]=prodDiscountArr[i];
				data2[j]=(dataArr);
				j++;
				
			}
			
			
			
			
		}
		 
		drawTable2(data2);
		
		
		

	}
	catch(err) {
		document.write("Inside Create Data Table");
		document.write(err.message);
		document.getElementById("table_div").innerHTML = err.message;
	}

}


function drawTable(data){
	
	try {
		var tableData = google.visualization.arrayToDataTable(data);
		var table = new google.visualization.Table(document.getElementById("table_div"));
		table.draw(tableData, {showRowNumber: true, width: '100%', height: '100%'});	
	}
	catch(err){
	   document.write("Inside Draw Table");
	   document.write(err.message);
	   document.getElementById("table_div").innerHTML = err.message;
	}
	
}


function drawTable1(data){
	
	try {
		var tableData = google.visualization.arrayToDataTable(data);
		var table = new google.visualization.Table(document.getElementById("table_div1"));
		table.draw(tableData, {showRowNumber: true, width: '100%', height: '100%'});	
	}
	catch(err){
	   document.write("Inside Draw Table1");
	   document.write(err.message);
	   document.getElementById("table_div1").innerHTML = err.message;
	}
	
}

function drawTable2(data){
	
	try {
		var tableData = google.visualization.arrayToDataTable(data);
		var table = new google.visualization.Table(document.getElementById("table_div2"));
		table.draw(tableData, {showRowNumber: true, width: '100%', height: '100%'});	
	}
	catch(err){
	   document.write("Inside Draw Table2");
	   document.write(err.message);
	   document.getElementById("table_div2").innerHTML = err.message;
	}
	
}


function drawBarChart2(data3){
try {
	
	var databar = google.visualization.arrayToDataTable(data3);
	var chart = new google.visualization.BarChart(document.getElementById('barchart_div'));
	chart.draw(databar, {width: 600, height: 800, is3D: true, title: 'Number of Items'});
}
catch(err){
		document.write("Inside Create bar Chart");
		document.write(err.message);
		document.getElementById("barchart_div").innerHTML = err.message;
}
}













/*

function drawBarChart1(){
try {
	
var data = google.visualization.arrayToDataTable([
['Product Name', 'Number of Items'],
['TCLTV',5],
['LG V60',50],
['GARMINFITNESSWATCHES',0],
['APPLESMARTWATCH',0],
['SONYTV',5],
['DELLLAPTOP',2],
['SAMSUNGTV',5],
['LENEVOLAPTOP',2],
['SAMSUNG GALAXY',50],
['AMAZONALEXA',10],
['APPLESIRI',10],
['ONEPLUS 8',50],
['SONYHEADPHONE',0],
['LGTV',5],
['APPLEMACBOOKLAPTOP',2],
['GOOGLELAPTOP',2],
['MICROSOFTLAPTOP',2],
['KLIPSCHSOUND',3],
['GOOGLEASSISTANT',10],
['OCULUSVR',0],
['IPHONE 11 PRO',50],
['SAMSUNGBIXBY',10],
['KLHSOUND',3],
['SVSSOUND',3],
['MICROSOFTCORTONA',10],
['VIZIOTV',5],
['SONOSSOUND',3],
['TAGGPETTRACKER',0],
['GOOGLE PIXEL',50],
['BOSSSOUND',3],
['TVLG', 3]]);

var chart = new google.visualization.BarChart(document.getElementById('barchart_div'));
chart.draw(data, {width: 600, height: 800, is3D: true, title: 'Number of Items'});
}
catch(err){
		document.write("Inside Create bar Chart");
		document.write(err.message);
		document.getElementById("barchart_div").innerHTML = err.message;
}
}

*/






