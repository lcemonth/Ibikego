<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			html, body, #map-canvas {
				height: 100%;
				margin: 0;
				padding: 0;
			}
		</style>
		<script type="text/javascript"
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvdipJng50BY0PWtOXH1ly8uxDpakKSgo">
		</script>
		<script type="text/javascript">
		      function initialize() {
		        var mapOptions = {
		          center: { lat: 25.070649,lng: 121.457101},
		          zoom: 12
		        };
		        var map = new google.maps.Map(document.getElementById("map-canvas"),mapOptions);
		      }
		      google.maps.event.addDomListener(window, 'load', initialize);
		</script>
	</head>
	<body onload="initialize()">
		<div id="map-canvas"></div>
	</body>
</html>