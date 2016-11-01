var file;
var fileReader;

function doFirst(){
	document.getElementById('myFile1').onchange = fileChange;
} 

function fileChange(){
	//測試觸發JQERY
	//alert($("#imgDiv").size());
    var	file = document.getElementById('myFile1').files[0];
	
	fileReader = new FileReader();
	fileReader.readAsDataURL(file);
	fileReader.onload = function(){
		//var image = document.getElementById('image1');
		var image = document.getElementById('image2');
		$("#imgDiv2").toggle();
		$("#imgDiv").hide();
		image.src = fileReader.result;
	};	
} 

window.addEventListener('load',doFirst,false);