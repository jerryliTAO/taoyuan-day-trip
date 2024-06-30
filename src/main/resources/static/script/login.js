window.onload = function(){
    if(localStorage.getItem("token") != null){
        location.href="/"
    }
}

//email validation
function verifyEmail() {
    const mailRule = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;
    const email = document.querySelector("#email");
    const emailSpan = document.querySelector("#emailSpan");
    if (!mailRule.test(email.value)) {
        emailSpan.style.display = "block";
        return false;
    } else {
        emailSpan.style.display = "none";
    }
    return true;
}

//login axios
async function login() {
    const isQualifiedEmail = verifyEmail();
    if(isQualifiedEmail){
        const email = document.querySelector("#email").value;
        const password = document.querySelector("#password").value;

        // login
        let response = await axios({
            method: "post",
            url: "/auth/login",
            "data": {
                "email": email,
                "password": password
            }
        }).then(function (response) {
            return response.data;
        }).catch(function (error) {
            console.log(error);
            return null;
        });

        if (response.status == "success") {
            //login success and forward to homepage
            localStorage.setItem("token", response.accessToken);
            console.log(response.accessToken);
            location.href = "/";
        } else {
            document.querySelector("#loginError").style.display = "block";
        }
    }
}

