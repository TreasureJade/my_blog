$(function() {
    $("#pic").click(function() {
        $("#upimg").click(); //隐藏了input:file样式后，点击头像就可以本地上传
        $("#upimg").on("change", function() {
            var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $("#pic").attr("src", objUrl); //将图片路径存入src中，显示出图片

            }
        });
    });
});

//建立一?可存取到?file的url
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

//上传头像到服务器
function upImg() {

    eval("(" + localStorage.getItem('Authorization') + ")");
    console.log(eval("(" + localStorage.getItem('Authorization') + ")"));
    var pic = $('#upimg')[0].files[0];
    var file = new FormData();
    file.append('file', pic);
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/user/uploadHeadPic",
        type: "post",
        data: file,
        cache: false,
        contentType: false,
        processData: false,
        success: function(data) {
            alert("成功");
            console.log(data);
            var r = data['data'];
            window.localStorage.setItem("headPortrait",r);
            console.log(r);
            //$("#resimg").append("<img src='/" + res + "'>")
        },
        /*error: function() {
            alert("失败，请稍后再试");
        }*/
});
}

var ischeck = 1;

function checked() {
    var a = $('#nickname').val();
    var b = $('#per-name').val();
    var c = $('#e-mail').val();
    var d = $('#birth-day').val();
    ischeck = 1
    if (a == '') {
        alert('请输入用户名');
        ischeck = 0;
    } else if (b == '') {
        alert('请输入真实姓名');
        ischeck = 0;
    } else if (c == '') {
        alert('请输入邮箱');
        ischeck = 0;
    } else if (c == '') {
        alert('请输入生日日期');
    } else {

    }
}


function AccPolish() {
    checked();
    if (ischeck == 1) {
        var username = $('#nickname').val();
        var trueName = $('#per-name').val();
        var email = $('#e-mail').val();
        var birthday = $('#birth-day').val();
        eval("(" + localStorage.getItem('Authorization') + ")");
        console.log(eval("(" + localStorage.getItem('Authorization') + ")"));
        $.ajax({
            headers: {
                "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
            },
            url: "/user/updateUser",
            type: "post",
            dataType: "json",
            data: {
                username    : username,
                trueName    : trueName,
                email       : email,
                birthday    : birthday,
                headPortrait: window.localStorage.getItem("headPortrait"),
            },
            success: function(data) {
                var a = data['code'];
                var b = data['msg'];
                if (a == 0) {
                    alert(b);
                    window.location.href = "index.html";
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

window.onload = function () {
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/user/getOwnerMsg",
        type: "get",
        dataType: "json",
        success: function(data) {
            var a = data['data'];
            $('#nickname').val(a['username']);
            $('#per-name').val(a['trueName']);
            $('#e-mail').val(a['email']);
            $('#birth-day').val(a['birthday']);
            $('#pic').attr("src",'http://http://www.cchobo.com'+a['headPortrait']);
        },
        error: function() {
            swal({
                    title: "错误提醒",
                    text: "请登录后查看或修改个人信息",
                    type: "warning"
                },
                function (isConfirm) {
                    window.location.href="login.html";
                });
        }
    });
};
