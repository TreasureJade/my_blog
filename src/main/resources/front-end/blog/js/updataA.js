window.onload = function () {
    $.ajax({                                        //获取指定博客详情
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/article/getDetail",
        type: "get",
        dataType: "json",
        data: {
            blogId:UrlParm.parm("blogId"),
        },
        success: function(data) {
            console.log(data);
            console.log(data['data']['article']);
            console.log(document.getElementsByClassName("ck-content")[0].innerHTML);
            $('#title')[0].value = data['data']['title'];
            $('#author')[0].value = data['data']['author'];
            myEditor.setData(data['data']['article']);
            $('#recipient-name')[0].value = data['data']['tagsId'];
            $('#message-text')[0].value = data['data']['typeId'];
        },
        error: function() {
            alert("失败，请稍后再试");
        }
    });
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/types/all",
        type: "get",
        dataType: "json",
        success: function(data) {
            var a = data['data'];
            var select1 = document.getElementById("recipient-name");
            for(var i = 0;i<a.length;i++){
                var b = document.createElement("option");
                b.setAttribute("value",a[i]['id']);
                b.innerText = a[i]['typeMsg'];
                select1.appendChild(b);
            }
        },
        error: function(err) {
            console.log(err);
            //alert("失败，请稍后再试");
        }
    });
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/tags/all",
        type: "get",
        dataType: "json",
        success: function(data) {
            var c = data['data'];
            var select2 = document.getElementById("message-text");
            for(var m = 0;m<c.length;m++){
                var d = document.createElement("option");
                d.setAttribute("value",c[m]['id']);
                d.innerText = c[m]['tagsMsg'];
                select2.appendChild(d);
            }
        },
        error: function(err) {
            console.log(err);
            //alert("失败，请稍后再试");
        }
    })
};


function updataBlog() {
    console.log(document.getElementsByClassName('ck-content')[0].innerHTML,document.getElementsByClassName('ck-content')[0].innerText,getString(document.getElementsByClassName('ck-content')[0].innerText,25),$('#recipient-name')[0].value,$('#message-text')[0].value);
    console.log($('#title')[0].value);
    eval("(" + localStorage.getItem('Authorization') + ")");
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/article/updateArticle",
        type: "post",
        dataType: "json",
        data: {
            digest : getString(document.getElementsByClassName('ck-content')[0].innerText,50),
            title  : $('#title')[0].value,
            tagsId : $('#recipient-name')[0].value,
            typeId : $('#message-text')[0].value,
            article: document.getElementsByClassName('ck-content')[0].innerHTML,
            author : $('#author')[0].value,
            id     : UrlParm.parm("blogId")
        },
        success: function(data) {
            console.log(data);
            var a = data['code'];
            var b = data['msg'];
            if (a == 0) {
                swal("成功提醒", b, "success");
                window.location.href = 'index.html';
            } else {
                swal("错误提醒", b, "warning");
            }
        },
        error: function() {
            swal("错误提醒", "请确认后重试", "warning");
        }
    })
}

function getString(s,n){
    s =  delHtmlTag(s);  //html替换
    if(s.length > n){
        return s.substring(0,n);
    }
    return s;
}

function delHtmlTag(str)
{
    return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
}

UrlParm = function() { // 获取url中的参数
    var data, index;
    (function init() {
        data = [];
        index = {};
        var u = window.location.search.substr(1);
        if (u != '') {
            var parms = decodeURIComponent(u).split('&');
            for (var i = 0, len = parms.length; i < len; i++) {
                if (parms[i] != '') {
                    var p = parms[i].split("=");
                    if (p.length == 1 || (p.length == 2 && p[1] == '')) {// p | p=
                        data.push(['']);
                        index[p[0]] = data.length - 1;
                    } else if (typeof(p[0]) == 'undefined' || p[0] == '') { // =c | =
                        data[0] = [p[1]];
                    } else if (typeof(index[p[0]]) == 'undefined') { // c=aaa
                        data.push([p[1]]);
                        index[p[0]] = data.length - 1;
                    } else {// c=aaa
                        data[index[p[0]]].push(p[1]);
                    }
                }
            }
        }
    })();
    return {
        // 获得参数,类似request.getParameter()
        parm : function(o) { // o: 参数名或者参数次序
            try {
                return (typeof(o) == 'number' ? data[o][0] : data[index[o]][0]);
            } catch (e) {
            }
        },
        //获得参数组, 类似request.getParameterValues()
        parmValues : function(o) { //  o: 参数名或者参数次序
            try {
                return (typeof(o) == 'number' ? data[o] : data[index[o]]);
            } catch (e) {}
        },
        //是否含有parmName参数
        hasParm : function(parmName) {
            return typeof(parmName) == 'string' ? typeof(index[parmName]) != 'undefined' : false;
        },
        // 获得参数Map ,类似request.getParameterMap()
        parmMap : function() {
            var map = {};
            try {
                for (var p in index) {  map[p] = data[index[p]];  }
            } catch (e) {}
            return map;
        }
    }
}();