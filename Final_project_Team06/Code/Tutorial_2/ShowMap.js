

function geocodeAddress() {
	
	console.log("Inside Map JavaScript");
	const geocoder = new google.maps.Geocoder();
	const address = document.getElementById("address").value;
	console.log("Inside Map JavaScript Address:"+address);
	const map = new google.maps.Map(document.getElementById("google_map"), {
	  zoom: 8,
	  center: { lat: 41.881832, lng: -87.623177 },
	});
	geocoder.geocode({ address: address }, (results, status) => {
	  if (status === "OK") {
		map.setCenter(results[0].geometry.location);
		console.log("Inside Geocoder:"+results[0].geometry.location);
		console.log(results);
		new google.maps.Marker({
		  map: map,
		  position: results[0].geometry.location,
		});
	  } else {
		alert(
		  "Geocode was not successful for the following reason: " + status
		);
	  }
	});
}