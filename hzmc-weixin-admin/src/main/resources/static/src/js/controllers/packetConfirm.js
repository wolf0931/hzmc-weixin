/**
 * Created by DELL on 2016/12/22.
 */

var $modelId;

function config(){
	$('#sendName').val('');
    $('#activeName').val('');
    $('#sum').val('');
    $('#number').val('');
    $('#minAccount').val('');
    $('#maxAccount').val('');
    $('#startDate').datebox('setValue','');
    $('#endDate').datebox('setValue','');
    $('#rate').val('');
    $('#introduce').val('');
    $('#wishing').val('');
    $('#submit').removeClass('display');
    $('#update').addClass('display');
}


$('#submit').click(function(){
    event.preventDefault();
    if(inputJuge() == false){
    	return ;
    }
    
    var redPacketTemp = {
    		"sendName": $sendName,
    		"actName": $actName,
    		"totalAmount": $sum,
    		"totalNum": $number,
    		"minAmount": $minAccount,
    		"maxAmount": $maxAccount,
    		"startTime": $startDate,
    		"endTime": $endDate,
    		"winningRate": $rate,
    		"remark": $introduce,
    		"wishing": $wishing
    };
    $.ajax({
    	type:'post',
    	url:"/WxRedpackTemplet/add",
    	dataType: "json",
    	headers:{  
            Accept:"application/json",  
            "Content-Type":"application/json"  
        },
    	data:JSON.stringify(redPacketTemp),
    	success:function(data){
    		if(data.message == "success"){
    			myAlert('设置成功');
    		}
    	},
    	error:function(data){
    		if(data.status == 401){
    			window.location.href='../../index.html';
    		}
    	} 
    });
    
    $('body').on('click','.closeDown',function(){
        $('.cover').remove();
        $('.add-container').remove();
        $('#name').val('');
    });
});

$('#update').click(function(){
	
    event.preventDefault();
    if(inputJuge() == false){
    	return ;
    }

	var redPacketTemp = {
    		"sendName": $sendName,
    		"actName": $actName,
    		"totalAmount": $sum,
    		"totalNum": $number,
    		"minAmount": $minAccount,
    		"maxAmount": $maxAccount,
    		"startTime": $startDate,
    		"endTime": $endDate,
    		"winningRate": $rate,
    		"remark": $introduce,
    		"wishing": $wishing,
    		"id": $modelId 
    };
    $.ajax({
    	type:'post',
    	url:"/WxRedpackTemplet/update",
    	dataType: "json",
    	headers:{  
            Accept:"application/json",  
            "Content-Type":"application/json"  
        },
    	data:JSON.stringify(redPacketTemp),
    	success:function(data){
    		if(data.message == "success"){
    			myAlert('更新成功');
    		}
    	},
    	error:function(data){
    		if(data.message == "faild"){
    			myAlert('更新失败');
    		}
    		if(data.status == 401){
    			window.location.href='../../index.html';
    		}
    	} 
    });
});


function update(e){
	var id = e.id;
	$modelId = id; 
	$.ajax({
		type: 'GET',
		url: '/WxRedpackTemplet/'+id,
		success: function(data){

			var start = new Date(parseInt(data.data.startTime)*1000);
			var startDate = start.getMonth()+1;
			startDate += '/'+start.getDate();
			startDate += '/'+start.getFullYear();
			var startHour = start.getHours();
			startHour += ':' + start.getMinutes();
			startHour += ':' + start.getSeconds();
			var end = new Date(parseInt(data.data.endTime)*1000);
			var endDate = end.getMonth()+1;
			endDate += '/'+end.getDate();
			endDate += '/'+end.getFullYear();
			var endHour = end.getHours();
			endHour += ':' + end.getMinutes();
			endHour += ':' + end.getSeconds();
			$('#sendName').val(data.data.sendName);
		    $('#activeName').val(data.data.actName);
		    $('#sum').val(data.data.totalAmount);
		    $('#number').val(data.data.totalNum);
		    $('#minAccount').val(data.data.minAmount);
		    $('#maxAccount').val(data.data.maxAmount);
		    $('#startDate').datetimebox({  
		        required : false,  
		        onShowPanel:function(){  
		            $(this).datetimebox("spinner").timespinner("setValue",startHour);  
		        }  
		    }); 
		    $('#startDate').datebox('setValue',startDate);
		    $('#endDate').datetimebox({  
		        required : false,  
		        onShowPanel:function(){  
		            $(this).datetimebox("spinner").timespinner("setValue",endHour);  
		        }  
		    }); 
		    $('#endDate').datebox('setValue',endDate);
		    $('#rate').val(data.data.winningRate);
		    $('#introduce').val(data.data.remark);
		    $('#wishing').val(data.data.wishing);
		    $('#submit').addClass('display');
		    $('#update').removeClass('display');
		},
    	error:function(data){
    		if(data.status == 401){
    			window.location.href='../../index.html';
    		}
    	} 
	});
}

function inputJuge(){
	$sendName=$('#sendName').val();
	$actName=$('#activeName').val();
	$sum=$('#sum').val();
	$number=$('#number').val();
	$minAccount=$('#minAccount').val();
	$maxAccount=$('#maxAccount').val();
	$startDate=Date.parse($('#startDate').val())/1000;
	$endDate=Date.parse($('#endDate').val())/1000;
	$rate=$('#rate').val();
	$introduce=$('#introduce').val();
	$wishing=$('#wishing').val();

	if($sendName == ''){
		emptywarn($('#sendName'),'商户名');
		return false;
	}else{
		$('.errwarn').remove();
	}

	if($actName == ''){
		emptywarn($('#activeName'),'活动名');
		return false;
	}else{
		$('.errwarn').remove();
	}

	if($sum == ''){
		emptywarn($('#sum'),'总金额');
		return false;
	}else if(isNaN($sum) || $sum < 0){
		typewarn($('#sum'),'总金额');
		return false;
	}else{
		$('.errwarn').remove();
	}

	if($number == ''){
		emptywarn($('#number'),'红包总数');
		return false;
	}else if(isNaN($number) || $number < 0){
		typewarn($('#number'),'红包总数');
		return false;
	}else if($number > $sum){
		let warn='<span class="red errwarn">红包总数应小于红包总金额</sapn>';
		$('#number') .after(warn);
		return false;
	}else{
		$('.errwarn').remove();
	}

	if($minAccount == ''||$maxAccount == ''){
		emptywarn($('#maxAccount'),'金额范围');
		return false;
	}else if(isNaN($minAccount) || isNaN($maxAccount)){
		typewarn($('#maxAccount'),'金额范围');
		return false;
	}else if($minAccount < 1){
		let warn='<span class="red errwarn">单个红包最小金额应大于1元</sapn>';
		$('#maxAccount').after(warn);
		return false;
	}else if($maxAccount > $sum-$number+1){
		let warn='<span class="red errwarn">红包最大金额应保证单个红包金额大于1元</sapn>';
		$('#maxAccount').after(warn);
		return false;
	}else if($maxAccount < $minAccount){
		let warn='<span class="red errwarn">红包最大金额应大于最小金额</sapn>';
		$('#maxAccount').after(warn);
		return false;
	}else{
		$('.errwarn').remove();
	}

	if($startDate == ''||$endDate == ''){
		emptywarn($('#endDate'),'活动起止时间');
		return false;
	}else if($endDate < $startDate){
		let warn='<span class="red errwarn">活动截止时间应晚于开始时间</sapn>';
		$('#endDate').after(warn);
		return false;
	}else{
		$('.errwarn').remove();
	}

	if($rate == ''){
		emptywarn($('#rate'),'中奖几率');
		return false;
	}else if(isNaN($rate) || $rate < 0 || $rate > 1){
		typewarn($('#rate'),'中奖几率');
		return false;
	}else{
		$('.errwarn').remove();
	}

	if($introduce == ''){
		emptywarn($('#introduce'),'活动说明');
		return false;
	}else{
		$('.errwarn').remove();
	}

	if($wishing == ''){
		emptywarn($('#wishing'),'祝福语');
		return false;
	}else{
		$('.errwarn').remove();
	}
}

function emptywarn(obj,str){
	let warn='<span class="red errwarn">'+str+'不得为空</sapn>';
	obj.after(warn);
}

function typewarn(obj,str){
	let warn='<span class="red errwarn">'+str+'格式错误</sapn>';
	obj.after(warn);
}

function addZreo(str){
	if(str < 10){
		str = '0'+ str;
		return str;
	}
	return str;
}
