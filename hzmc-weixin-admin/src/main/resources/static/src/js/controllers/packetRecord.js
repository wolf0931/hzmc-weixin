/**
 * Created by DELL on 2016/12/22.
 */

angular.module('myApp',[]).controller('myCtrl',function($scope){
	$.ajax({
		type: 'GET',
		url: '/WxPayRecord',
		success: function(data){
			for(var i=0;i<data.data.length;i++){
				if(data.data[i].wxPayRecord.status == 'SEDING'){
					data.data[i].wxPayRecord.status = '发放中';
				}else if(data.data[i].wxPayRecord.status == 'SENT'){
					data.data[i].wxPayRecord.status = '已发放待领取';
				}else if(data.data[i].wxPayRecord.status == 'FAILED'){
					data.data[i].wxPayRecord.status = '发放失败';
				}else if(data.data[i].wxPayRecord.status == 'RECEIVED'){
					data.data[i].wxPayRecord.status = '已领取';
				}else if(data.data[i].wxPayRecord.status == 'RFUND_ING'){
					data.data[i].wxPayRecord.status = '退款中';
				}else if(data.data[i].wxPayRecord.status == 'REFUND'){
					data.data[i].wxPayRecord.status = '已退款';
				}
				$scope.list = data;
			}
		}
	});	
});

