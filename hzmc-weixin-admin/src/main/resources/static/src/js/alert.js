function myAlert(str){
	 var alert='  <div class="cover"> </div><div class="add-container col-sm-4 col-sm-offset-4 no-padding">';		
	 alert += '<div class="navbar clear"><span class="right close closeDown"></span><h4 class="left">提示</h4></div>';
	 alert += '<div class="add-main">'+str+'</div>';
	 alert += '<div class="alert-footer"><div class="btn-event right closeDown">确认</div></div></div>';
	 
	 $('body').append(alert);
}

$('body').on('closeDown','click',function(){
	$('.cover').remove();
	$('.add-container').remove();
});