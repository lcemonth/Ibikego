$(function(){
if($("#add").val()=="2"){
    $('input:radio[name="item_is_added"]').filter('[value="2"]').attr('checked', true);
}else if($("#add").val()=="1"){
 $('input:radio[name="item_is_added"]').filter('[value="1"]').attr('checked', true);
}else{
	$('input:radio[name="item_is_added"]').filter('[value="0"]').attr('checked', true);}
});

