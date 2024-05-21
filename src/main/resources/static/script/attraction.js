window.onload = async function () {
    getAttractionById();

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
        console.log(index);
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