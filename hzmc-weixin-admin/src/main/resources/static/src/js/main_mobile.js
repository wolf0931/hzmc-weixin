/**
 * Created by DELL on 2017/4/25.
 */

function myAlert(str){
	 var alert='  <div class="cover"> </div><div class="add-container no-padding">';		
	 alert += '<div class="navbar clear"><span class="right close closeDown"></span><h4 class="left">提示</h4></div>';
	 alert += '<div class="add-main">'+str+'</div>';
	 alert += '<div class="alert-footer"><div class="btn-event right closeDown"><span>确认</span></div></div></div>';
	 
	 $('body').append(alert);
}

$('body').on('click','.closeDown',function($scope){
    $('.cover').remove();
    $('.add-container').remove();
});


$(function($){  
	init();
});

function init(){
	$.ajax({
		type: 'GET',
		url: '/WxRedpackTemplet/1',
		success: function(data){
			if(data.message == 'success'){
				$('#vote-count-su').html(data.data.left);
				$('#vote-count-chi').html(data.data.right);
			}
		}
	});
}

$('.vote-button-su').click(function(){
	$('#vote-count-su').html(parseInt($('#vote-count-su').html())+1);
    judge(1);
});
$('.vote-button-chi').click(function(){
	$('#vote-count-chi').html(parseInt($('#vote-count-chi').html())+1);
    judge(2);
});

function judge(group){
//	var openId=decodeURI(location.search).split('=')[1].split('&')[0];
	var $user =jugeAction();
//	myAlert('1');

	payPacket($user,group);	
}

//判断用户是否关注公众号
function jugeAction(){
	$.ajax({
		type: 'GET',
    	url: '/oauth/oJvITt-VfGOTCe0dcXsZPCqn1APM',
    	success:function(data){
    		if(data.message == 'faild'){
    			myAlert('<a href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzA3MTUzNzcwMg==&scene=124#wechat_redirect">先关注才能参与活动</a>');
    			return ;
    		}else if(data.message == 'success'){
    			return data.data.user;
    		}else if(data.status == 500){
    			myAlert('您已经参与过活动');
    		}
    	}
	});
	
}

//发送红包
function payPacket(user,group){
	$.ajax({
		type: 'POST',
		url: '/wx/pay/1/'+group,
		data: user,
		success: function(data){
			if(data.message == 'success'){
				myAlert('恭喜获得红包，退出到聊天窗口领取');
			}else{
				myAlert(data);
			}
		}
	});
}


