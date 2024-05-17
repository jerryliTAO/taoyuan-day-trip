window.onload = function () {
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
                ).then(function (value) {
                    vue.attraction = value.data;
                    console.log(vue.attraction.name);
                }).catch(function (reason) {
                    console.log(reason)
                })
            }
        },
        mounted: function () {
            this.getAttractionById();
        }

    });
};


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