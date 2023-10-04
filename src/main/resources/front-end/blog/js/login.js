//验证手机号码
var isPhone = 1;
function toRegister() {
    window.location.href = "register.html";
}


function checkPhone() {
    var phone = $('#phone').val();
    var passward = $('#password').val();
    var pattern = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    isPhone = 1;
    if (phone == '') {
        alert('请输入手机号码');
        isPhone = 0;
        return false;
    } else if (!pattern.test(phone)) {
        alert('请输入正确的手机号码');
        isPhone = 0;
        return false;
    } else if (passward == '') {
        alert('请输入密码');
        isPhone = 0;
        return false;
    } else {
        return true;
    }
}

function getCode(e) {
    checkPhone(); //验证手机号码
    if (isPhone) {
        var phoneNum = $('#phone').val();
        var password = $('#password').val();
        $.ajax({
            url: "/anon/login",
            type: "post",
            dataType: "json",
            data: {
                phoneNum: phoneNum,
                password: password,
            },
            success: function(data) {
                console.log(data);
                var a = data['code'];
                var b = data['msg'];

                if (a == 0) {
                    var c = data['data'];
                    var d = c['token'];
                    var e = c['role'];
                    var f = c['username'];
                    var g = c['headPortrait'];
                    var r = c['role'];
                    window.localStorage.setItem('Authorization', JSON.stringify(d));
                    window.localStorage.setItem('role', JSON.stringify(e));
                    window.localStorage.setItem('username', JSON.stringify(f));
                    window.localStorage.setItem('headPortrait', JSON.stringify(g));
                    swal({
                            title: "成功提醒",
                            text: "登陆成功",
                            type: "success"
                        },
                        function (isConfirm) {
                            window.location.href = "index.html"
                        });
                } else {
                    swal({
                            title: "错误提醒",
                            text: b,
                            type: "warning"
                        });
                }
            },
            error: function() {
                swal({
                        title: "错误提醒",
                        text: "请稍后重试",
                        type: "warning"
                    });
            }
        })
    }
}