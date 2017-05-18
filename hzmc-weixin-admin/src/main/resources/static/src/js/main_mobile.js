/**
 * Created by DELL on 2017/4/25.
 */

function myAlert(str){
	 var alert='  <div class="cover"> </div><div class="add-container col-sm-4 col-sm-offset-4 no-padding">';		
	 alert += '<div class="navbar clear"><span class="right close closeDown"></span><h4 class="left">提示</h4></div>';
	 alert += '<div class="add-main">'+str+'</div>';
	 alert += '<div class="alert-footer"><div class="btn-event right closeDown">确认</div></div></div>';
	 
	 $('body').append(alert);
}


var foodCount=0,customCount=0;

$('.vote-button-su').click(function(){
    judge();
});
$('.vote-button-ch').click(function(){
    judge();
});

function judge(){
//	var openId=decodeURI(location.search).split('=')[1].split('&')[0];
	
//	myAlert('1');
	
	$.ajax({
		type: 'GET',
    	url: '/vote/1/oJvITt-VfGOTCe0dcXsZPCqn1APM',
    	success:function(data){
    		if(data.data == '活动已结束'){
    			myAlert('此活动已经结束');
    		}else if(data.data == '没有关注公众号'){
    			myAlert('<a href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz='+data.data.appId+'==#wechat_redirect">先关注才能参与活动</a>');
    		}else if(data.data == '内部人员不能发红包'){
    			myAlert('内部人员不能领取红包');
    		}else if(typeof(data.data) == object){
    			myAlert('恭喜获得红包，请退回聊天窗口领取');
    		}
    	}
	});
}


