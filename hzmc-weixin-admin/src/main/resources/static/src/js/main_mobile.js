/**
 * Created by DELL on 2017/4/25.
 */

function myAlert(str){
	 var alert='  <div class="cover"> </div><div class="add-container no-padding">';		
	 alert += '<div class="navbar clear"><h4 class="left">提示</h4></div>';
	 alert += '<div class="add-main">'+str+'</div>';
	 alert += '<div class="alert-footer"><div class="btn-event right closeDown"><span>确认</span></div></div></div>';
	 
	 $('body').append(alert);
}

$('body').on('click','.closeDown',function($scope){
    $('.cover').remove();
    $('.add-container').remove();
});

var userId;

$(function($){  
	url();
	getId();
	init();
});

function url(){
	var urlData=decodeURI(location.search).split('?')[1];
	if(urlData == undefined){
		$.ajax({
			type: 'GET',
			url: '/vote',
			success: function(data){
				window.location.href = data;
			}
		});
	}
}

function init(){
	$.ajax({
		type: 'GET',
		url: '/WxRedpackTemplet/1',
		success: function(data){
			if(data.message == 'success'){
				$('.vote-count-su').html(data.data.left);
				$('.vote-count-chi').html(data.data.right);
				var start = new Date(parseInt(data.data.wxRedpackTemplet.startTime)*1000);
				var startDate = start.getFullYear();
				startDate += '年' + (parseInt(start.getMonth())+1);
				startDate += '月' + start.getDate();
				startDate += '日 ' + start.getHours();
				startDate += ':' + start.getMinutes();
				startDate += ':' + start.getSeconds();
				var end = new Date(parseInt(data.data.wxRedpackTemplet.endTime)*1000);
				var endDate = end.getFullYear();
				endDate += '年' + (parseInt(end.getMonth())+1);
				endDate += '月' + end.getDate();
				endDate += '日 ' + end.getHours();
				endDate += ':' + end.getMinutes();
				endDate += ':' + end.getSeconds();
				$('#actTime').html(startDate+' 至  '+endDate);
			}
		}
	});
}

function getId(){
	var openId=decodeURI(location.search).split('=')[1].split('&')[0];
	$.ajax({
		type: 'GET',
		url: '/oauth/code/'+openId,
		success: function(data){
			userId = data.data;
		}
	});
}

$('.vote-button-su').click(function(){
    judge(1);
});
$('.vote-button-chi').click(function(){
    judge(2);
});

function judge(group){
	/*if (userId == undefined ){
		// getId();
		myAlert("加载中...");
	}*/
	jugeAction(userId,group);
//	myAlert('1');	
}

//判断用户是否关注公众号
function jugeAction(openId,group){
	$.ajax({
		type: 'GET',
    	url: '/oauth/'+openId,
    	async: false,
    	success:function(data){
    		if(data.message == 'failed' && data.data.status == '没有关注'){
    			myAlert('<a href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzA3MTUzNzcwMg==&scene=124#wechat_redirect">先关注才能参与活动</a>');
    			return ;
    		}else if(data.message == 'failed' && data.data == '已经投票'){
				myAlert(data.data);
			}else if(data.message == 'success'){
    			payPacket(data.data.user,group);
    		}else{
				myAlert(data.data);
			}
    	}
	});
	
}

//发送红包
function payPacket(user,group){
	var tem = 1;
	//user.openid = "oJvITt-VfGOTCe0dcXsZPCqn1APM";
	$.ajax({
		type: 'POST',
		url: '/pay/'+tem +"/"+group,
		//data: user,
		dataType: "json",
		headers:{
			Accept:"application/json",
			"Content-Type":"application/json"
		},
		data:JSON.stringify(user),
		success: function(data){
			if(data.message == 'success'){
				myAlert('恭喜获得红包，退出到聊天窗口领取');
				init();
			}else{
				myAlert(data.data);
			}
		}
	});
}


