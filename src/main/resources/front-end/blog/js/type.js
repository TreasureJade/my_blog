window.onload = function () {
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/types/all",
        type: "get",
        dataType: "json",
        success: function(data) {
            console.log(data['data']);
            document.getElementsByClassName("num")[0].innerHTML = data['data'].length;
            var typeBox = document.getElementById("typeBox");
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
                a.setAttribute("href","typeThing.html?"+"typeId="+data['data'][i]['id']);
                a.innerText = data['data'][i]['typeMsg'];
                typeBox.appendChild(a);
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

function updateNewType() {
    $.ajax({
        headers: {
            "Authorization": eval("(" + localStorage.getItem('Authorization') + ")")
        },
        url: "/types/insert",
        type: "post",
        data:{
            typeMsg:document.getElementById("newTypeName").value
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