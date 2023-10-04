window.onload = function () {
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/article/selectAll",
        type: "get",
        dataType: "json",
        data: {
            pageNum:1,
            pageSize:6,
        },
        success: function(data) {
            console.log(data);
            var a = data['code'];
            var b = data['msg'];
            var c = data['data'];
            var d = c['list'];
            window.localStorage.setItem("pageNum",JSON.stringify(c['navigatepageNums'].length));
            var blogContainer = document.getElementById("blogContainer");
            if (a == 0) {
                console.log(c['list']);
                for(var i = 0;i<d.length;i++){
                    var bigDiv = document.createElement("div");
                    bigDiv.setAttribute("class","col-lg-4 col-md-6 col-12 portfolio-item portfolio-filter-graphic portfolio-filter-design");
                    var article = document.createElement("article");
                    article.setAttribute("class","blog-item sticky");
                    var header = document.createElement("header");
                    header.setAttribute("class","blog-item-header");
                    var a1 = document.createElement("a");
                    a1.setAttribute("href","blog-details.html?"+"blogId="+d[i]['blogId']);
                    var h2 = document.createElement("h2");
                    h2.innerText = d[i]['title'];
                    a1.appendChild(h2);
                    header.appendChild(a1);
                    var smallDiv = document.createElement("div");
                    smallDiv.setAttribute("class","blog-item-body");
                    var h6 = document.createElement("h6");
                    h6.setAttribute("class","blog-item-title");
                    var tagA = document.createElement("a");
                    var typeA = document.createElement("a");
                    tagA.setAttribute("href","tagThing.html?"+"tagId="+d[i]['tagsId']);
                    tagA.setAttribute("class","badge badge-light");
                    tagA.innerText = d[i]['tagsMsg'];
                    typeA.setAttribute("href","typeThing.html?"+"typeId="+d[i]['typeId']);
                    typeA.setAttribute("class","badge badge-light");
                    typeA.innerText = d[i]['typeMsg'];
                    //h6.innerText = "文章类型："+d[i]['typeMsg']+" "+" "+" "+"标签："+d[i]['tagsMsg'];
                    h6.append("文章类型： ");
                    h6.appendChild(typeA);
                    h6.append(" ");
                    h6.append("标签： ");
                    h6.appendChild(tagA);
                    var p = document.createElement("p");
                    p.innerText = d[i]['digest'];
                    smallDiv.appendChild(h6);
                    smallDiv.appendChild(p);
                    var footer = document.createElement("footer");
                    footer.setAttribute("class","blog-item-footer");
                    var ul = document.createElement("ul");
                    ul.setAttribute("class","blog-item-meta");
                    var li1 = document.createElement("li");
                    var li2 = document.createElement("li");
                    var li3 = document.createElement("li");
                    var i1 = document.createElement("i");
                    i1.setAttribute("class","fa fa-user-o");
                    var i2 = document.createElement("i");
                    i2.setAttribute("class","fa fa-calendar-o");
                    var i3 = document.createElement("i");
                    i3.setAttribute("class","fa fa-comments-o");
                    var a2 = document.createElement("a");
                    a2.setAttribute("href","#");
                    a2.innerText = d[i]['author'];
                    var a3 = document.createElement("a");
                    a3.setAttribute("href","#");
                    a3.innerText = d[i]['commentSum']+" Comments";
                    li1.appendChild(i1);
                    li1.append("By ");
                    li1.appendChild(a2);
                    li2.appendChild(i2);
                    li2.append(d[i]['creatTime']);
                    li3.setAttribute("style","margin-right: 0");
                    li3.appendChild(i3);
                    li3.appendChild(a3);
                    ul.appendChild(li1);
                    ul.appendChild(li2);
                    ul.appendChild(li3);
                    footer.appendChild(ul);
                    article.appendChild(header);
                    article.appendChild(smallDiv);
                    article.appendChild(footer);
                    bigDiv.appendChild(article);
                    blogContainer.appendChild(bigDiv);
                    var clearDiv = document.createElement("div");
                    clearDiv.setAttribute("style","clear:both");
                    blogContainer.appendChild(clearDiv);
                    blogContainer.style.height = "100%";
                };
            } else {
                swal("错误提醒", b, "warning");
            }
        },
        error: function() {
            swal("错误提醒", "请确认后重试", "warning");
        }
    });
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/getMsg",
        type: "post",
        dataType: "json",
        success: function(data) {
            console.log(data);
            document.getElementById("articleP").innerHTML = data['data']['articleTotal']+"篇";
            document.getElementById("tagP").innerHTML = data['data']['tagTotal']+"个";
            document.getElementById("messageP").innerHTML = data['data']['leaveMessageTotal']+"条";
            document.getElementById("commentP").innerHTML = data['data']['commentTotal']+"条";
            document.getElementById("timeP").innerHTML = data['data']['updateTime'];

        },
        error: function(err) {
            console.log(err);
        }
    });
    siteTime();
};

function changeTime(e) {
    if(window.localStorage.getItem("role") == 2){
        var input = document.createElement("input");
        var inputBtn = document.createElement("input");
        input.setAttribute("type", "text");
        inputBtn.setAttribute("type", "button");
        input.setAttribute("style", "width:63%;height:30px");
        inputBtn.setAttribute("style", "width:30%;height:30px");
        inputBtn.setAttribute("value", "确定");
        inputBtn.addEventListener("click", confirmTime);
        input.value = e.innerHTML;
        e.parentElement.appendChild(input);
        e.parentElement.appendChild(inputBtn);
        e.remove();
    }
}

function confirmTime(e) {
    console.log(e.target.previousElementSibling.value);
    var p = document.createElement("p");
    p.setAttribute("id","timeP");
    p.setAttribute("ondblclick","changeTime(this)");
    p.innerHTML = e.target.previousElementSibling.value;
    e.target.parentElement.appendChild(p);
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/updateTime",
        type: "post",
        dataType: "json",
        data: {
            date:e.target.previousElementSibling.value,
        },
        success: function(data) {
            console.log(data);
        },
        error: function(err) {
            console.log(err)
        }
    })
    e.target.previousElementSibling.remove();
    e.target.remove();
}

function siteTime(){
    window.setTimeout("siteTime()", 1000);
    var seconds = 1000;
    var minutes = seconds * 60;
    var hours = minutes * 60;
    var days = hours * 24;
    var years = days * 365;
    var today = new Date();
    var todayYear = today.getFullYear();
    var todayMonth = today.getMonth()+1;
    var todayDate = today.getDate();
    var todayHour = today.getHours();
    var todayMinute = today.getMinutes();
    var todaySecond = today.getSeconds();
    /* Date.UTC() -- 返回date对象距世界标准时间(UTC)1970年1月1日午夜之间的毫秒数(时间戳)
    year - 作为date对象的年份，为4位年份值
    month - 0-11之间的整数，做为date对象的月份
    day - 1-31之间的整数，做为date对象的天数
    hours - 0(午夜24点)-23之间的整数，做为date对象的小时数
    minutes - 0-59之间的整数，做为date对象的分钟数
    seconds - 0-59之间的整数，做为date对象的秒数
    microseconds - 0-999之间的整数，做为date对象的毫秒数 */
    var t1 = Date.UTC(2020,1,16,15,45,10); //北京时间2016-12-1 00:00:00
    var t2 = Date.UTC(todayYear,todayMonth,todayDate,todayHour,todayMinute,todaySecond);
    var diff = t2-t1;
    var diffYears = Math.floor(diff/years);
    var diffDays = Math.floor((diff/days)-diffYears*365);
    var diffHours = Math.floor((diff-(diffYears*365+diffDays)*days)/hours);
    var diffMinutes = Math.floor((diff-(diffYears*365+diffDays)*days-diffHours*hours)/minutes);
    var diffSeconds = Math.floor((diff-(diffYears*365+diffDays)*days-diffHours*hours-diffMinutes*minutes)/seconds);
    document.getElementById("sitetime").innerHTML=diffYears+" 年 "+diffDays+" 天 "+diffHours+" 小时 "+diffMinutes+" 分钟 "+diffSeconds+" 秒";
}



$("#pagination").pagination({
    mode:'fixed',
    pageCount:window.localStorage.getItem("pageNum"),
    current:1,
    callback:function (api) {
        console.log(api.getCurrent());
        document.getElementById("blogContainer").innerHTML = '';
        $.ajax({
            headers: {
                "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
            },
            url: "/article/selectAll",
            type: "get",
            dataType: "json",
            data: {
                pageNum:api.getCurrent(),
                pageSize:6,
            },
            success: function(data) {
                console.log(data);
                var a = data['code'];
                var b = data['msg'];
                var c = data['data'];
                var d = c['list'];
                window.localStorage.setItem("pageNum",JSON.stringify(c['navigatepageNums'].length));
                var blogContainer = document.getElementById("blogContainer");
                if (a == 0) {
                    console.log(c['list']);
                    for(var i = 0;i<d.length;i++){
                        var bigDiv = document.createElement("div");
                        bigDiv.setAttribute("class","col-lg-4 col-md-6 col-12 portfolio-item portfolio-filter-graphic portfolio-filter-design");
                        var article = document.createElement("article");
                        article.setAttribute("class","blog-item sticky");
                        var header = document.createElement("header");
                        header.setAttribute("class","blog-item-header");
                        var a1 = document.createElement("a");
                        a1.setAttribute("href","blog-details.html?"+"blogId="+d[i]['blogId']);
                        var h2 = document.createElement("h2");
                        h2.innerText = d[i]['title'];
                        a1.appendChild(h2);
                        header.appendChild(a1);
                        var smallDiv = document.createElement("div");
                        smallDiv.setAttribute("class","blog-item-body");
                        var h6 = document.createElement("h6");
                        h6.setAttribute("class","blog-item-title");
                        h6.innerText = "文章类型："+d[i]['typeMsg']+" "+" "+" "+"标签："+d[i]['tagsMsg'];
                        var p = document.createElement("p");
                        p.innerText = d[i]['digest'];
                        smallDiv.appendChild(h6);
                        smallDiv.appendChild(p);
                        var footer = document.createElement("footer");
                        footer.setAttribute("class","blog-item-footer");
                        var ul = document.createElement("ul");
                        ul.setAttribute("class","blog-item-meta");
                        var li1 = document.createElement("li");
                        var li2 = document.createElement("li");
                        var li3 = document.createElement("li");
                        var i1 = document.createElement("i");
                        i1.setAttribute("class","fa fa-user-o");
                        var i2 = document.createElement("i");
                        i2.setAttribute("class","fa fa-calendar-o");
                        var i3 = document.createElement("i");
                        i3.setAttribute("class","fa fa-comments-o");
                        var a2 = document.createElement("a");
                        a2.setAttribute("href","#");
                        a2.innerText = d[i]['author'];
                        var a3 = document.createElement("a");
                        a3.setAttribute("href","#");
                        a3.innerText = d[i]['commentSum']+" Comments";
                        li1.appendChild(i1);
                        li1.append("By ");
                        li1.appendChild(a2);
                        li2.appendChild(i2);
                        li2.append(d[i]['creatTime']);
                        li3.setAttribute("style","margin-right: 0");
                        li3.appendChild(i3);
                        li3.appendChild(a3);
                        ul.appendChild(li1);
                        ul.appendChild(li2);
                        ul.appendChild(li3);
                        footer.appendChild(ul);
                        article.appendChild(header);
                        article.appendChild(smallDiv);
                        article.appendChild(footer);
                        bigDiv.appendChild(article);
                        blogContainer.appendChild(bigDiv);
                        var clearDiv = document.createElement("div");
                        clearDiv.setAttribute("style","clear:both");
                        blogContainer.appendChild(clearDiv);
                        blogContainer.style.height = "100%";
                    };
                } else {
                    swal("错误提醒", b, "warning");
                }
            },
            error: function() {
                swal("错误提醒", "请确认后重试", "warning");
            }
        });
    }
});

$(function () {
    $('[data-toggle="popover"]').popover()
})

function alertQQ() {
    document.getElementById("hhh").click();
}
