/**
 *注册 
 */

var isPhoneS = 1;

function getCodeS(e) {
    checkPhoneS(); //验证手机号码
    if (isPhoneS) {
        resetCodeS(); //倒计时
        sentcode();
    } else {
        $('#telephone').focus();
    }
}

// 验证手机号码

function checkPhoneS() {
    var phone = $('#telephone').val();
    var pattern = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    isPhoneS = 1;
    if (phone == '') {
        alert('请输入手机号码');
        isPhoneS = 0;
        return false;
    } else if (!pattern.test(phone)) {
        alert('请输入正确的手机号码');
        isPhoneS = 0;
        return false;
    } else {
        return true;
    }
}

// 倒计时

function resetCodeS() {
    $('#sentcode').hide();
    $('#second').html('60');
    $('#resetCode').show();
    var second = 60;
    var timer = null;
    timerS = setInterval(function() {
        second -= 1;
        if (second > 0) {
            $('#second').html(second);
        } else {
            clearInterval(timerS);
            $('#sentcode').show();
            $('#resetCode').hide();
        }
    }, 1000);
}

// 验证码 

function sentcode() {
    var phoneNumber = $('#telephone').val();
    var code = $('#code').val();
    $.ajax({
        url: "/anon/getCode",
        type: "post",
        dataType: "json",
        data: {
            phoneNumber: phoneNumber,
        },
        success: function(data) {
            var a = data['code'];
            var b = data['msg'];
            if (a == 0) {
                alert(b);
            } else {
                alert(b);
            }
        },
        error: function() {
            alert("失败，请稍后再试");
        }
    })
}




//空值判断 注册
var ischeck = 1;

function checked() {
    var phoneNum = $('#telephone').val();
    var a = $('#code').val();
    var b = $('#nickname').val();
    var c = $('#password').val();
    var d = $('#password-two').val();
    ischeck = 1
    if (phoneNum == '') {
        alert('请输入手机号');
        ischeck = 0;
    } else if (a == '') {
        alert('请输入验证码');
        ischeck = 0;
    } else if (b == '') {
        alert('请输入昵称');
        ischeck = 0;
    } else if (c == '') {
        alert('请输入密码');
    } else if (d == '') {
        alert('请再次输入密码');
        ischeck = 0;
    } else if (c != d) {
        alert('两次密码不一致');
        ischeck = 0;
    } else {

    }
}


function accpolish() {
    checked();
    if (ischeck == 0) {
        /* $("#accept").attr("disabled", true);
         $("#accept").css("cursor", "default");*/
    }
    if (ischeck == 1) {
        var phoneNumber = $('#telephone').val();
        var code = $('#code').val();
        var password = $('#password').val();
        var username = $('#nickname').val();
        $.ajax({
            url: "/anon/insert",
            type: "post",
            dataType: "json",
            data: {
                phoneNumber: phoneNumber,
                code: code,
                password: password,
                username: username,
            },
            success: function(data) {

                var a = data['code'];
                var b = data['msg'];
                if (a == 0) {
                    alert(b);
                } else {
                    alert(b);
                }
            },
            error: function() {
                alert("失败，请稍后再试");
            }
        })
    }
}


/**
 * 密码长度判断
 */


$(function() {

    function checkStrong(val) {
        var modes = 0;
        if (val.length < 6) return 0;
        if (/\d/.test(val)) modes++; //数字
        if (/[a-z]/.test(val)) modes++; //小写
        //if (/[A-Z]/.test(val)) modes++; //大写  
        if (/\W/.test(val)) modes++; //特殊字符
        if (val.length > 12) return 3;
        return modes;
    };
    $("#password").keyup(function() {
        $("#tips").css("display", "block");
        var val = $(this).val();
        var num = checkStrong(val);
        switch (num) {
            case 0:
                $("#tips span").css('background', '#abc8d5').text('').eq(num - 1).css('background', '#abc8d5');
                break;
            case 1:
                $("#tips span").css('background', '#abc8d5').text('').eq(num - 1).css('background', 'yellow');
                break;
            case 2:
                $("#tips span").css('background', '#abc8d5').text('').eq(num - 1).css('background', 'orange');
                break;
            case 3:
                $("#tips span").css('background', '#abc8d5').text('').eq(num - 1).css('background', 'red');
                break;
            default:
                break;
        }
    })
    $(document).click(function() {
        $("#tips").css("display", "none")
    })
})