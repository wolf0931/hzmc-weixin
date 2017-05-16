/**
 * Created by DELL on 2016/12/22.
 */
function login(){
    $userName=$('#userName').val();
    $userPwd=$('#userPwd').val();
    if($userName == ""||$userPwd == ""){
        $('.danger span.empty').addClass('active');
    }else{
        $('.danger span.empty').removeClass('active');
        var userTemp = {"username": $userName,"password": $userPwd};
        $.ajax({
        	type:'post',
        	url:"/login",
        	dataType: "json",
        	headers:{  
                Accept:"application/json",  
                "Content-Type":"application/json"  
            },
        	data:JSON.stringify(userTemp),
        	success:function(data){
        		if(data.message == 'success'){
        			self.location='view_pc/pages/packetConfirm.html';
        		}else{
        			$('.danger span.wrong').addClass('active');
        		}
        	}
        });
    }
}
