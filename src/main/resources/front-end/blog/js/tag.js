window.onload = function () {
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/tags/all",
        type: "get",
        dataType: "json",
        success: function(data) {
            console.log(data['data']);
            document.getElementsByClassName("num")[0].innerHTML = data['data'].length;
            var tagBox = document.getElementById("tagBox");
            for(var i = 0;i<data['data'].length;i++){
                var a = document.createElement("a");
                if(data['data'][i]['id']%2==0) {
                    a.setAttribute("style", "font-size:17px");
                }
                else if(data['data'][i]['id']%2==1){
                    a.setAttribute("style", "font-size:15px");
                }
                else{
                    a.setAttribute("style", "font-size:18px");
                }
                a.setAttribute("href","tagThing.html?"+"tagId="+data['data'][i]['id']);
                a.innerText = data['data'][i]['tagsMsg'];
                tagBox.appendChild(a);
            }
        },
        error: function(err) {
            console.log(err);
            //alert("失败，请稍后再试");
        }
    })
    if(window.localStorage.getItem("role") != 2){
        document.getElementById("addBtn").style.display = 'none';
    }
};

function updateNewTag() {
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/tags/insert",
        type: "post",
        data:{
                tagsMsg:document.getElementById("newTagName").value
        },
        dataType: "json",
        success: function(data) {
            swal({
                    title: "成功提醒",
                    text: "新增分类成功",
                    type: "success"
                },
                function (isConfirm) {
                    window.location.reload();
                });
        },
        error: function(err) {
            console.log(err);
        }
    })
}