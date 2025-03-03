window.onload = async function () {
    ckLogin();
    let data = await getHistoryOrder();
    if(data[0] == null){
        let withOrder = document.querySelector("#withOrder");
        let withoutOrder = document.querySelector("#withoutOrder");
        withOrder.style.display = "none";
        withoutOrder.style.display = "block";
    }else{
        let vue = new Vue({
            el: ".container",
            data: {
                historyOrder: data
            },
            methods: {
                toggleMenu: function (number) {
                    // toggle the order detail
                    let menu = document.querySelector("#testMenu_" + number);
                    let expend = document.querySelector("#expand_" + number);
                    let hide = document.querySelector("#hide_" + number);

                    if (menu.style.display == "none" || menu.style.display == "") {
                        menu.style.display = "block";
                        expend.style.display = "none";
                        hide.style.display = "block";
                    } else {
                        menu.style.display = "none";
                        expend.style.display = "block";
                        hide.style.display = "none";
                    }
                }
            }
        });
    }
}

function getHistoryOrder() {
    let userId = localStorage.getItem("userId");
    return axios({
        method: "get",
        url: "/api/order/" + userId
    }).then(res => {
        console.log(res.data)
        return res.data;
    }).catch(err => {
        console.log(err.data)
    });
}