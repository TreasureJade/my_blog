window.onload = function () {
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/article/selectArticleByTags",
        data: {
            tagId:UrlParm.parm("tagId")
        },
        type: "get",
        dataType: "json",
        success: function(data) {
            console.log(data);
            if(data['data'].length != 0) {
                document.getElementsByClassName("title")[0].innerHTML = '# ' + data['data'][0]['tagsMsg'];
                var timeline = document.getElementsByClassName("timeline")[0];
                for (var i = 0; i < data['data'].length; i++) {
                    var bigDiv = document.createElement("div");
                    bigDiv.setAttribute("class", "timeline-row-major");
                    var span = document.createElement("span");
                    span.setAttribute("class", "node am-animation-slide-top am-animation-delay-1");
                    var middleDiv = document.createElement("div");
                    middleDiv.setAttribute("class", "content am-comment-main am-animation-slide-top am-animation-delay-1");
                    var header = document.createElement("header");
                    header.setAttribute("class", "am-comment-hd");
                    header.setAttribute("style", "background: #fff");
                    var xsDiv = document.createElement("div");
                    xsDiv.setAttribute("class", "contentTitle am-comment-meta");
                    var titleA = document.createElement("a");
                    titleA.setAttribute("href", "blog-details.html?" + "blogId=" + data['data'][i]['blogId']);
                    titleA.setAttribute("style", "color: #898D92");
                    titleA.innerText = data['data'][i]['title'];
                    xsDiv.appendChild(titleA);
                    header.appendChild(xsDiv);
                    var smallDiv = document.createElement("div");
                    smallDiv.setAttribute("class", "am-comment-bd");
                    var timeI = document.createElement("i");
                    var typeI = document.createElement("i");
                    var userI = document.createElement("i");
                    timeI.setAttribute("class", "am-icon-calendar");
                    timeI.innerHTML = ' '+data['data'][i]['creatTime'];
                    typeI.setAttribute("class", "am-icon-folder");
                    var typeA = document.createElement("a");
                    typeA.setAttribute("href", "typeThing.html?" + "typeId=" + data['data'][i]['typeId']);
                    typeA.innerHTML = ' '+data['data'][i]['typeMsg'];
                    typeI.appendChild(typeA);
                    userI.setAttribute("class", "am-icon-user");
                    userI.innerHTML = ' '+data['data'][i]['author'];
                    smallDiv.appendChild(timeI);
                    smallDiv.appendChild(userI);
                    smallDiv.appendChild(typeI);
                    middleDiv.appendChild(header);
                    middleDiv.appendChild(smallDiv);
                    bigDiv.appendChild(span);
                    bigDiv.appendChild(middleDiv);
                    timeline.appendChild(bigDiv);
                }
            }
            else{
                document.getElementsByClassName("title")[0].innerHTML = "该标签暂无数据";
            }
        },
        error: function(err) {
            console.log(err);
            //alert("失败，请稍后再试");
        }
    })
    if(window.localStorage.getItem("role") != 2){
        document.getElementById("newBtn").style.display = 'none';
        document.getElementById("deleteBtn").style.display = 'none';
    }
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

function updateTagName() {
    console.log(document.getElementById("updateTagName").value);
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/tags/update",
        data: {
            id:UrlParm.parm("tagId"),
            tagsMsg:document.getElementById("updateTagName").value
        },
        type: "post",
        dataType: "json",
        success: function(data) {
            console.log(data);
            swal({
                title: "成功提醒",
                text: "更改标签成功",
                type: "success"
            },
            function (isConfirm) {
                window.location.reload();
            });
        },
        error: function(err) {
            console.log(err);
            swal({
                    title: "错误提醒",
                    text: "更改标签失败",
                    type: "warning"
                });
        }
    })
}

function deleteTag() {
    swal({
            title: "确定删除吗？",
            text: "你将无法恢复这个标签！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定删除！",
            cancelButtonText: "取消删除！",
            closeOnConfirm: false,
            closeOnCancel: false
        },
        function(isConfirm){
            if (isConfirm) {
                $.ajax({                                        //获取指定博客详情
                    headers: {
                        "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
                    },
                    url: "/tags/delete",
                    type: "get",
                    dataType: "json",
                    data: {
                        id:UrlParm.parm("tagId"),
                    },
                    success: function(data) {
                        console.log(data);
                        swal({
                                title: "成功提醒",
                                text: "删除标签成功",
                                type: "success",
                                showCancelButton: false,
                            },
                            function (isConfirm) {
                                window.location.href = "tag.html";
                            }
                        );
                    },
                    error: function() {
                        swal({
                                title: "错误提醒",
                                text: "删除标签失败",
                                type: "warning",
                                showCancelButton: false,
                            }
                        );
                    }
                });
            } else {
                swal({
                        title: "成功提醒",
                        text: "成功取消删除",
                        type: "success",
                        showCancelButton: false,
                    }
                );
            }
        });
}