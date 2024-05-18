window.onload = function () {
    let vue = new Vue({
        el: ".container",
        data: {
            attractionList: {}
        },
        methods: {
            getAllAttraction: function () {
                axios({
                        "method": "GET",
                        "url": "/api/attraction"

                    }
                ).then(function (value) {
                    vue.attractionList = value.data;
                    console.log(value.data)
                })
                    .catch(function (reason) {
                            console.log(reason)
                        })
            }
        },
        mounted: function () {
            this.getAllAttraction();
        }
    });
};


// function getAllAttraction() {
//     let vue = new Vue({
//         "el": ".container",
//         "data": {},
//         "methods": {
//             "getAllAttraction": function () {
//                 axios({
//                         "method": "get",
//                         "url": "/api/attraction",
//                         "params": null
//                     }
//                 ).then(function (value) {
//                         console.log(value.data)
//                     }
//                         .catch(function (reason) {
//                                 console.log(reason)
//                             }
//                         )
//                 )
//             }
//         }
//     });
// }


