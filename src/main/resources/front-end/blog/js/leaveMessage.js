window.onload = function(){
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/leaveMsg/all",
        type: "get",
        dataType: "json",
        success: function(data) {
            console.log(data);
            var a = data['data']['msgVOList'];
            var commentListDiv = document.getElementById("commentListDiv");
            document.getElementById("commentSum").innerHTML = data['data']['leaveMsgNum']+" COMMENTS";
            console.log(a);
            for(var i = 0;i<a.length;i++) {          //动态生成父级评论
                var commentDiv = document.createElement("div");
                commentDiv.setAttribute("class", "single-comment");
                var fatherSonDiv = document.createElement("div");
                fatherSonDiv.setAttribute("class", "father-son");
                fatherSonDiv.setAttribute("style", "margin-bottom:25px");
                var imgDiv = document.createElement("div");
                imgDiv.setAttribute("class", "single-comment-thumb");
                var imgHead = document.createElement("img");
                imgHead.setAttribute("src", "http://www.cchobo.com" + a[i]['headPortrait']);
                imgHead.setAttribute("alt","hastech logo");
                imgDiv.appendChild(imgHead);
                var contentDiv = document.createElement("div");
                contentDiv.setAttribute("class","single-comment-content");
                contentDiv.setAttribute("style","width:100%");
                var contentTopDiv = document.createElement("div");
                contentTopDiv.setAttribute("class","single-comment-content-top");
                var contentTopDiv1 = document.createElement("div");
                contentTopDiv1.setAttribute("class","single-comment-content-top");
                contentTopDiv1.setAttribute("style","position: relative;height: 29px");
                var h6 = document.createElement("h6");
                var a1 = document.createElement("a");
                var a2 = document.createElement("a");
                var a3 = document.createElement("a");
                a3.setAttribute("style","display:none");
                var a4 = document.createElement("a");
                a4.setAttribute("style","display:none");
                a3.innerHTML = a[i]['userId'];
                a4.innerHTML = a[i]['leaveMessageId'];
                var p1 = document.createElement("p");
                a1.innerText = a[i]['userName'];
                a2.setAttribute("class","reply-button");
                a2.setAttribute("style","position: absolute;right: 0px;color:white");
                a2.addEventListener("click", insertSecCom);
                a2.innerText = "REPLY";
                h6.appendChild(a1);
                p1.innerText = " - "+a[i]['creatTime'];
                var p = document.createElement("p");
                p.innerText = a[i]['leaveMsg'];
                contentTopDiv.appendChild(h6);
                contentTopDiv.appendChild(p1);
                contentTopDiv1.appendChild(a2);
                contentTopDiv1.appendChild(a3);
                contentTopDiv1.appendChild(a4);
                contentDiv.appendChild(contentTopDiv);
                contentDiv.appendChild(p);
                contentDiv.appendChild(contentTopDiv1);
                commentDiv.appendChild(imgDiv);
                commentDiv.appendChild(contentDiv);
                fatherSonDiv.appendChild(commentDiv);
                //commentListDiv.appendChild(commentDiv);
                if(a[i]['replyVO']!==''){            //动态生成子级评论
                    for(var n = 0;n<a[i]['replyVO'].length;n++){
                        var commentRDiv = document.createElement("div");
                        commentRDiv.setAttribute("class", "single-comment single-comment-reply");
                        commentRDiv.setAttribute("style", "margin-left: 90px");
                        var imgRDiv = document.createElement("div");
                        imgRDiv.setAttribute("class", "single-comment-thumb");
                        var imgRHead = document.createElement("img");
                        imgRHead.setAttribute("src", "http://www.cchobo.com" + a[i]['replyVO'][n]['headPortrait']);
                        imgRHead.setAttribute("alt","hastech logo");
                        imgRDiv.appendChild(imgRHead);
                        var contentRDiv = document.createElement("div");
                        contentRDiv.setAttribute("class","single-comment-content");
                        contentRDiv.setAttribute("style","width:100%;padding: 10px");
                        var contentTopRDiv = document.createElement("div");
                        contentTopRDiv.setAttribute("class","single-comment-content-top");
                        contentTopRDiv.setAttribute("style","height: 33px");
                        var contentTopRDiv1 = document.createElement("div");
                        contentTopRDiv1.setAttribute("class","single-comment-content-top");
                        contentTopRDiv1.setAttribute("style","position: relative;height: 29px");
                        var Rh6 = document.createElement("h6");
                        var Ra1 = document.createElement("a");
                        var Ra2 = document.createElement("a");
                        var Ra3 = document.createElement("a");
                        var Ra4 = document.createElement("a");
                        Ra4.setAttribute("style","display:none");
                        var Ra5 = document.createElement("a");
                        Ra5.setAttribute("style","display:none");
                        Ra4.innerHTML = a[i]['replyVO'][n]['userId'];
                        Ra5.innerHTML = a[i]['replyVO'][n]['commentId'];
                        var Rp1 = document.createElement("p");
                        Rp1.innerText = " - "+a[i]['replyVO'][n]['creatTime'];
                        Ra1.innerText = a[i]['replyVO'][n]['userName'];
                        Ra3.innerText = " @ " + a[i]['replyVO'][n]['replyUserName'];
                        Ra2.setAttribute("class","reply-button");
                        Ra2.setAttribute("style","position: absolute;right: 0;color:white");
                        Ra2.addEventListener("click", insertSecCom);
                        Ra2.innerText = "REPLY";
                        Rh6.appendChild(Ra1);
                        Rh6.appendChild(Ra3);
                        var Rp = document.createElement("p");
                        Rp.innerText = a[i]['replyVO'][n]['commentMsg'];
                        contentTopRDiv.appendChild(Rh6);
                        contentTopRDiv.appendChild(Rp1);
                        contentTopRDiv1.appendChild(Ra2);
                        contentTopRDiv1.appendChild(Ra4);
                        contentTopRDiv1.appendChild(Ra5);
                        contentRDiv.appendChild(contentTopRDiv);
                        contentRDiv.appendChild(Rp);
                        contentRDiv.appendChild(contentTopRDiv1);
                        commentRDiv.appendChild(imgRDiv);
                        commentRDiv.appendChild(contentRDiv);
                        fatherSonDiv.appendChild(commentRDiv);
                        //commentListDiv.appendChild(commentRDiv);
                    }
                }
                commentListDiv.appendChild(fatherSonDiv);
            }
        },
        error: function() {
            alert("失败，请稍后再试");
        }
    });
};


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


function insertComment() {
    if(eval("(" + localStorage.getItem('Authorization') + ")") === null){
        swal({
                title: "错误提醒",
                text: "留言需要登录",
                type: "warning"
            },
            function (isConfirm) {
                window.location.href="login.html";
            });
    }
    else if(document.getElementById("new-review-textbox").value === ''){
        swal({
            title: "错误提醒",
            text: "留言不可为空",
            type: "warning"
        })
    }
    else{
        console.log(document.getElementById("new-review-textbox").value);
        $.ajax({
            headers: {
                "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
            },
            url: "/leaveMsg/insert",
            type: "post",
            dataType: "json",
            data: {
                leaveMsg    :document.getElementById("new-review-textbox").value,
                replyUserId :"",
                pid         :0,
            },
            success: function(data) {
                console.log(data);
                var commentListDiv = document.getElementById("commentListDiv");
                var commentDiv = document.createElement("div");
                commentDiv.setAttribute("class", "single-comment");
                var imgDiv = document.createElement("div");
                imgDiv.setAttribute("class", "single-comment-thumb");
                var imgHead = document.createElement("img");
                imgHead.setAttribute("src", "http://www.cchobo.com"+window.localStorage.getItem("headPortrait").replace(/^\"|\"$/g,''));
                imgHead.setAttribute("alt","hastech logo");
                imgDiv.appendChild(imgHead);
                var contentDiv = document.createElement("div");
                contentDiv.setAttribute("class","single-comment-content");
                contentDiv.setAttribute("style","width:100%");
                var contentTopDiv = document.createElement("div");
                contentTopDiv.setAttribute("class","single-comment-content-top");
                var contentTopDiv1 = document.createElement("div");
                contentTopDiv1.setAttribute("class","single-comment-content-top");
                contentTopDiv1.setAttribute("style","position: relative;height: 29px");
                var h6 = document.createElement("h6");
                var a1 = document.createElement("a");
                var a2 = document.createElement("a");
                var a3 = document.createElement("a");
                a3.setAttribute("style","display:none");
                var a4 = document.createElement("a");
                a4.setAttribute("style","display:none");
                var p1 = document.createElement("p");
                a1.innerText = eval("(" + localStorage.getItem('username') + ")").replace(/^\"|\"$/g,'');
                a2.setAttribute("class","reply-button");
                a2.setAttribute("style","position: absolute;right: 0px;color:white");
                a2.addEventListener("click", insertSecCom);
                a2.innerText = "REPLY";
                h6.appendChild(a1);
                p1.innerText = " - "+CurentTime();
                var p = document.createElement("p");
                p.innerText = document.getElementById("new-review-textbox").value;
                contentTopDiv.appendChild(h6);
                contentTopDiv.appendChild(p1);
                contentTopDiv1.appendChild(a2);
                contentTopDiv1.appendChild(a3);
                contentTopDiv1.appendChild(a4);
                contentDiv.appendChild(contentTopDiv);
                contentDiv.appendChild(p);
                contentDiv.appendChild(contentTopDiv1);
                commentDiv.appendChild(imgDiv);
                commentDiv.appendChild(contentDiv);
                commentListDiv.appendChild(commentDiv);
                document.getElementById("new-review-textbox").value = "";
                document.getElementById("commentSum").innerText = parseInt(document.getElementById("commentSum").innerText.split(" ")[0])+1+" COMMENTS";
            },
            error: function(err) {
                console.log(err)
            }
        })
    }
}


function insertSecCom(e) {
    console.log(e.target.nextElementSibling.innerText);
    var c = e.target.nextElementSibling.innerText;
    console.log(e.target.nextElementSibling.nextElementSibling.innerText);
    var d = e.target.nextElementSibling.nextElementSibling.innerText;
    var k = document.createElement("div");
    k.setAttribute("class","replyBox")
    k.innerHTML = "<div class=\"single-comment-reply\" style=\"margin-bottom: 10px\">\n" +
        "            <form action=\"#\" class=\"karigor-form\" style=\"text-align: right\">\n" +
        "                <div class=\"karigor-form-inner\">\n" +
        "                    <div class=\"karigor-form-input\" style=\"margin-top: 0\">\n" +
        "                        <textarea placeholder=\"Comment\" style=\"max-height: 50px;min-height: 50px;width: 97%;border-radius: 10px 10px 0 0\"></textarea>\n" +
        "                    </div>\n" +
        "                    <div class=\"karigor-form-input\" style=\"margin-top: 0\">\n" +
        "                        <input  type=\"button\" onclick=\"insertSecComBtn(this)\" value=\"SEND COMMENT\" style=\"font-family:Dosis, sans-serif;height: 33px;width: 97%;background-color: #555;border: 1px solid #555;color:#FFF;font-size: 14px;font-weight: 700;border-radius: 0 0 10px 10px\">\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </form>\n" +
        "            </div>";

    if(e.target.className === "reply-button") {
        e.target.parentElement.parentElement.parentElement.after(k);
        e.target.className = "reply-button hasAdd";
    }
    else{
        e.target.parentElement.parentElement.parentElement.nextElementSibling.remove();
        e.target.className = "reply-button";
    }
}

function insertSecComBtn(e) {
    //console.log(e.parentElement.previousElementSibling.childNodes[1].value);
    //添加新评论的评论块
    //console.log(e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling.parentElement);
    //获取回复用户的名字
    //console.log(e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling.lastElementChild.firstElementChild.firstElementChild.firstElementChild.innerHTML);
    //console.log(e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling.parentElement.firstElementChild.lastElementChild.lastElementChild.lastElementChild.innerHTML);
    var pid = e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling.parentElement.firstElementChild.lastElementChild.lastElementChild.lastElementChild.innerHTML;
    //console.log(e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling.lastElementChild.lastElementChild.lastElementChild.previousElementSibling.innerHTML);
    var userId = e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling.lastElementChild.lastElementChild.lastElementChild.previousElementSibling.innerHTML;

    if(eval("(" + localStorage.getItem('Authorization') + ")") === null){
        swal({
                title: "错误提醒",
                text: "留言需要登录",
                type: "warning"
            },
            function (isConfirm) {
                window.location.href="login.html";
            });
    }
    else if(e.parentElement.previousElementSibling.childNodes[1].value === '') {
        swal({
            title: "错误提醒",
            text: "留言不可为空",
            type: "warning"
        })
    }
    else{
        $.ajax({
            headers: {
                "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
            },
            url: "/leaveMsg/insert",
            type: "post",
            dataType: "json",
            data: {
                leaveMsg    :e.parentElement.previousElementSibling.childNodes[1].value,
                replyUserId :parseInt(userId),
                pid         :parseInt(pid),
            },
            success: function(data) {
                console.log(data);
                var commentRDiv = document.createElement("div");
                commentRDiv.setAttribute("class", "single-comment single-comment-reply");
                commentRDiv.setAttribute("style", "margin-left: 90px");
                var imgRDiv = document.createElement("div");
                imgRDiv.setAttribute("class", "single-comment-thumb");
                var imgRHead = document.createElement("img");
                imgRHead.setAttribute("src", "http://www.cchobo.com" + window.localStorage.getItem("headPortrait").replace(/^\"|\"$/g,''));
                imgRHead.setAttribute("alt","hastech logo");
                imgRDiv.appendChild(imgRHead);
                var contentRDiv = document.createElement("div");
                contentRDiv.setAttribute("class","single-comment-content");
                contentRDiv.setAttribute("style","width:100%;padding: 10px");
                var contentTopRDiv = document.createElement("div");
                contentTopRDiv.setAttribute("class","single-comment-content-top");
                contentTopRDiv.setAttribute("style","height: 33px");
                var contentTopRDiv1 = document.createElement("div");
                contentTopRDiv1.setAttribute("class","single-comment-content-top");
                contentTopRDiv1.setAttribute("style","position: relative;height: 29px");
                var Rh6 = document.createElement("h6");
                var Ra1 = document.createElement("a");
                var Ra2 = document.createElement("a");
                var Ra3 = document.createElement("a");
                var Rp1 = document.createElement("p");
                Rp1.innerText = " - "+CurentTime();
                Ra1.innerText = eval("(" + localStorage.getItem('username') + ")").replace(/^\"|\"$/g,'');
                Ra3.innerText = " @ " + e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling.lastElementChild.firstElementChild.firstElementChild.firstElementChild.innerHTML;
                Ra2.setAttribute("class","reply-button");
                Ra2.setAttribute("style","position: absolute;right: 0;color:white");
                Ra2.addEventListener("click", insertSecCom);
                Ra2.innerText = "REPLY";
                Rh6.appendChild(Ra1);
                Rh6.appendChild(Ra3);
                var Rp = document.createElement("p");
                Rp.innerText = e.parentElement.previousElementSibling.childNodes[1].value;
                contentTopRDiv.appendChild(Rh6);
                contentTopRDiv.appendChild(Rp1);
                contentTopRDiv1.appendChild(Ra2);
                contentRDiv.appendChild(contentTopRDiv);
                contentRDiv.appendChild(Rp);
                contentRDiv.appendChild(contentTopRDiv1);
                commentRDiv.appendChild(imgRDiv);
                commentRDiv.appendChild(contentRDiv);
                e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling.parentElement.appendChild(commentRDiv);
                e.parentElement.parentElement.parentElement.parentElement.parentElement.remove();//在添加评论后将输入框移除
                document.getElementById("commentSum").innerText = parseInt(document.getElementById("commentSum").innerText.split(" ")[0])+1+" COMMENTS";
            },
            error: function(err) {
                console.log(err)
            }
        })
    }
}

function CurentTime() {
    var now = new Date(); //获取时间
    var date_time = Array(); //定义数组
    var status = 0; //状态
    var clock;
    date_time.push(now.getFullYear()); //年
    date_time.push(now.getMonth() + 1); //月
    date_time.push(now.getDate()); //日
    date_time.push(now.getHours()); //时
    date_time.push(now.getMinutes()); //分
    do {
        if (status > 0 && status <= 2) {//处理月日
            if (date_time[status] < 10) {
                clock += "0";
            }
            if (status < 2) {
                str = '-';
            } else {
                str = ' ';
            }
            clock += date_time[status] + str;
        } else if (status > 2 && status <= 4) {//处理时分秒
            if (date_time[status] < 10) {
                clock += "0";
            }
            if (status < 4) {
                str = ':';
            } else {
                str = ' ';
            }
            clock += date_time[status] + str;
        } else {//处理年
            clock = date_time[status] + "-";
        }
        status++;
    } while (status <= 4);

    return clock;
}