var file;
var flieReader;
function doFrist(){
	document.getElementById('myFile').onchange=fileChange;//跟畫面做連結發生onchange事件
}
function fileChange(){
	file=document.getElementById('myFile').files[0];//一個檔案為例 多個要跑迴圈

		fileReader=new FileReader();
		fileReader.onload=function(){
		document.getElementById('image').src=fileReader.result
		};
		fileReader.readAsDataURL(file);
}

window.addEventListener('load',doFrist,false);