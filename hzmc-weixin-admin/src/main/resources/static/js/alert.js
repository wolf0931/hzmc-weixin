function myAlert(str){
	 var alert='  <div class="cover"> </div><div class="add-container col-sm-4 col-sm-offset-4 no-padding">';		
	 alert += '<div class="navbar"><span class="right close closeDown"></span><h4 class="left">设置成功</h4></div>';
	 alert += '<div class="add-main">'+str+'</div>';
	 alert += '<div><div class="btn-event right add-btn closeDown">确认</div></div>';
	 
	 $('body').append(alert);
}