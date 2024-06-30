window.onload = async function () {
    ckLogin();
    getAttractionById();
    optionChangePrice();
    // get attraction images
    const image = await images();


//==========   Image slide ============
    // image index;
    let index = 0;
    let attractionImg = document.querySelector(".attractionImg");

    // previous page
    let prevBtn = document.querySelector(".prevBtn");
    prevBtn.onclick = () => {
        index--;
        if (index < 0) {
            index = image.length - 1;
        }
        attractionImg.src = image[index];
    }
    // next page

    let nextBtn = document.querySelector(".nextBtn");
    nextBtn.onclick = () => {
        index++;
        if (index > (image.length - 1)) {
            index = 0;
        }
        attractionImg.src = image[index];
    }


};


let getAttractionById = function () {
    let vue = new Vue({
        el: ".container",
        data: {
            attraction: {}
        },
        methods: {
            getAttractionById: function () {
                axios({
                        "method": "GET",
                        "url": "/api" + location.pathname
                    }
                ).then((value) => {
                    vue.attraction = value.data;
                }).catch((reason) => {
                    console.log(reason)
                })
            }
        },
        mounted: function () {
            this.getAttractionById();
        }

    });
};

async function images() {
    const images = await axios({
            "method": "GET",
            "url": "/api" + location.pathname
        }
    ).then(function (value) {
        return value.data.image

    }).catch(function (reason) {
        console.log(reason)
    })
    return images;
}


//=======   limit order date from now on ======
let inputDate = document.querySelector("#inputDate");
let date = new Date();
let year = date.getFullYear();
let month = date.getMonth() + 1;
let day = date.getDate() + 1;

if (month < 10) {
    month = "0" + month;
}
if (day < 10) {
    day = "0" + day;
}
let time = year + "-" + month + "-" + day;
inputDate.setAttribute("min", time);


//====== Option change price between morning and afternoon =======
function optionChangePrice() {
    const morning = document.querySelector("#morning")
    const afternoon = document.querySelector("#afternoon")
    const price = document.querySelector("#price")
    morning.addEventListener("click", function () {
        price.innerText = "2000";
    })
    afternoon.addEventListener("click", function () {
        price.innerText = "2500";
    })
}

async function addCartItem() {

    //========   get the information of reservation
    const date = document.querySelector("#inputDate").value;
    if(date == ""){
        alert("請選擇日期");
        return
    }
    let period;
    const morning = document.querySelector("#morning");
    if (morning.checked) {
        period = "morning";
    } else {
        period = "afternoon";
    }
    const price = parseInt(document.querySelector("#price").innerText);
    const userId = localStorage.getItem("userId");
    let uri = location.pathname.split("/");
    const attractionId = parseInt(uri[2]);
    let test = "Bearer " + localStorage.getItem("token");


    // post data to backend server
    let response = await axios({
        method: "post",
        url: "/api/cartItem",
        "data": {
            "userId": userId,
            "attractionId": attractionId,
            "dateTime": date,
            "price": price,
            "period": period
        }
        // },
        // headers:{
        //     "Content-type":"application/json",
        //     "Authorization":"Bearer " + localStorage.getItem("token")
        // }
    }).then(res=>{
        return res.data;
    }).catch(error=>{
        alert(error);
        return;
    })

    if(response.status == "success"){
        location.href = "/cart";
    }else{
        alert("預約行程失敗失敗");
    }

}