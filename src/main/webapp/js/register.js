/*$(function(){
	alert("aaa");
	$("#changeCode").mousedown(function(){
		alert("aaa");
		$("#validateCode").attr("src","Number.jsp?"+Math.random());
		return false;
	});
});*/
function    t1(){

    if($('#username').val().toString().length>6||$('#username').val().toString().length<3){
        $('#usernameerror').html("<p>用户名字符在3到6之间</p>");
        return 0;
    }else {
        $('#usernameerror').html("");
    }
    $.ajax({
        type:"post",
        url:"/user/username",
        data:{"username":$('#username').val()},
        dataType:"json",
        success:function (data) {
            if(data.u=='YES'){

                //$('#usernameerror').html("<p></p>");
            }else{

                $("#usernameerror").html("<p>用户名已存在</p>");
                return 0;
            }
        },
        error:function () {

        },
    });
    if($('#username').val()!=""&&$('#usernameerror').val()==""){
        return 1;
    }else {
        return 0;
    }
}
function t2() {
    if($('#userpassword').val().toString().length<6){
        $('#userpassworderror').html("<p>密码要求不低于6个字符</p>");
        return 0;
    }
    else {
        $('#userpassworderror').html("");
        return 1;
    }

}
function t3() {
    if($('#usercardno').val().toString().length!=18){
        $('#usercardnoerror').html("<p>身份证号18位数字</p>");
        return 0;
    }else {
        $('#usercardnoerror').html("");
        return 1;
    }

}
function t4() {
    if($('#usertel').val().toString().length!=7&&$('#usertel').val().toString().length!=8){
        $('#usertelerror').html("<p>电话号码7位或8位数字</p>");
        return 0;
    }else {
        $('#usertelerror').html("");
        return 1;
    }

}
function t5() {
    if($('#useraddress').val().toString().length==0){
        $('#useraddresserror').html("<p>地址不能为空</p>");
        return 0;
    }else {
        $('#useraddresserror').html("");
        return 1;
    }

}
function t6() {
    if($('#userpostnumber').val().toString().length!=6){
        $('#userpostnumbererror').html("<p>邮政编码为6位</p>");
        return 0;
    }else {
        $('#userpostnumbererror').html("");
        return 1;
    }

}
function su() {
    var a=t1();
    var b=t2();
    var c=t3();
    var d=t4();
    var e=t5();
    var f=t6();

    if(a==1&&b==1&&c==1&&d==1&&e==1&&f==1){
        $('#adduser').submit();
    }

}
