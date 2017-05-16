/**
 * Created by DELL on 2017/4/25.
 */
var foodCount=0,customCount=0;
$('#open1').click(function(){
    foodCount+=1;
    judge();
    redPacket();
});
$('#open2').click(function(){
    customCount+=1;
    judge();
    redPacket();
});

function judge(){
	var openId=decodeURI(location.search).split('=')[1].split('&')[0];
	
//	myAlert('1');
	
	$.ajax({
		type: 'GET',
    	url: '',
    	success:function(){
    		if(data.massage == 'success'){
    			return;
    		}
    	}
	});
	
    $.ajax({
    	type: 'GET',
    	url: '/oauth/'+openId,
    	success:function(data){
    		if(data.message == 'success'){
    	        myAlert('<a href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz='+data.data.appId+'==#wechat_redirect">先关注才能参与活动</a>');
    	        
    	    }
    	}
    });
}

function redPacket(){
    $.ajax({
    	type: 'GET',
    	url: '/WxRedpackTemplet',
    	success: function(data){
    		$rate = data.winningRate;
    	}
    });
    if(Math.random() < $rate){
    	
	    myAlert('恭喜获得红包，请退回聊天窗口领取');
    }else{	
	    myAlert('好遗憾，没有获得红包哦');
    }
}