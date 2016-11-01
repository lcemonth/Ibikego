$(function(){
if($("#add").val()=="2"){
    $('input:radio[name="item_is_added"]').filter('[value="2"]').attr('checked', true);
}else if($("#add").val()=="1"){
 $('input:radio[name="item_is_added"]').filter('[value="1"]').attr('checked', true);
}else{
	$('input:radio[name="item_is_added"]').filter('[value="0"]').attr('checked', true);}
});

function upItem(item_no){
		var item_exp=$(item_no).attr("data-item_exp");
		var item_price=$(item_no).attr("data-item_price");
		var item_name=$(item_no).attr("data-item_name");
		var mem_no=$(item_no).attr("data-mem_no");
		var item_no=$(item_no).attr("data-item_no");
		$("#item_no").val(item_no);
		$("#mem_no").val(mem_no);
		$("#item_name").val(item_name);
		$("#item_price").val(item_price);
		$("#item_exp").val(item_exp);
	
	}