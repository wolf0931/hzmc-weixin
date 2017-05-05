/**
 * Created by DELL on 2016/12/22.
 */
$('button.btn').click(function(){
    event.preventDefault();
    $userName=$('#userName').val();
    $userPwd=$('#userPwd').val();
    if($userName== ""||$userPwd==""){
        $('.danger span.empty').addClass('active');
    }else{
        $('.danger span.empty').removeClass('active');
        $.ajax({
        	type:'post',
        	url:"/login",
        	async:false,
        	data:{
        		"username": $userName,
        		"password": $userPwd
        	},
        	success:function(data){
        		
        		self.location='pages/packetConfirm.html';
        		
        	}
        });
    }
});
