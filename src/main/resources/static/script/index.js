window.onload = function () {
    //check if user login
    ckLogin();

    // extract token and get user ID
    const token = localStorage.getItem("token")
    if(token != null){
        const jwtParts = token.split(".");
        const userId = JSON.parse(atob(jwtParts[1])).userId;
        localStorage.setItem("id",userId);
        console.log(userId);
    }


    // load attraction information
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

    console.log(localStorage.getItem("token"))

};



