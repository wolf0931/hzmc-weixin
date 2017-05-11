/**
 * Created by DELL on 2017/4/25.
 */
var foodCount,customCount;
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
    $.ajax({

    });
    if(data == false){
        myAlert('<a href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=">先关注才能参与活动</a>');
        return false;
    }
}

function redPacket(){
    $.ajax({

    });
    myAlert('恭喜获得红包，请退回聊天窗口领取');

    myAlert('好遗憾，没有获得红包哦');
}