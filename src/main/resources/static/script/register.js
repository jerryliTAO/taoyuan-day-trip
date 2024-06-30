window.onload = function () {
    if (localStorage.getItem("token") != null) {
        location.href = "/"
    }
}

async function register() {
    const email = document.querySelector("#email");
    const password = document.querySelector("#password");
    const account = document.querySelector("#account");

    // verify register form
    const isQualifiedForm = verifyForm(email, password, account);

    if (isQualifiedForm) {
        // register the account
        let result = await axios({
            method: "post",
            url: "/auth/register",
            "data": {
                "account": account.value,
                "email": email.value,
                "password": password.value
            }
        })
            .then(res => {
                return res.data
            })
            .catch(err => {
                return err.data
            })

        if (result.status == "ok") {
            //after register success, auto login
            let response = await axios({
                method: "post",
                url: "/auth/login",
                "data": {
                    "email": email.value,
                    "password": password.value
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
            }
        } else {
            const registerMsg = document.querySelector("#registerMsg");
            registerMsg.style.display="block";
        }


    }
}


function verifyForm(email, password, account) {

    //=======  Verify Account =====
    //verify account at least 6 digit
    const accountRule = /^[A-Za-z0-9]{6,}$/g;
    let accountSpan = document.querySelector("#accountSpan");
    if (!accountRule.test(account.value)) {
        accountSpan.style.visibility = "visible";
        return false;
    } else {
        accountSpan.style.visibility = "hidden";
    }

    //=======  Verify Password =====
    //verify password:at least 6~10 digit contain letter and number
    const passwordSpan = document.querySelector("#pwdSpan");
    const pwdRule = /^(?=.*[a-zA-Z])(?=.*\d).{6,10}$/g;
    if (!pwdRule.test(password.value)) {
        passwordSpan.style.visibility = "visible";
        return false;
    } else {
        passwordSpan.style.visibility = "hidden";
    }

    //======= Confirm Password =====
    //verify password:at least 6~10 digit contain letter and number
    const confirmPassword = document.querySelector("#confirmPassword");
    const pwdCkSpan = document.querySelector("#pwdCkSpan");
    if (confirmPassword.value != password.value) {
        pwdCkSpan.style.visibility = "visible";
        return false;
    } else {
        pwdCkSpan.style.visibility = "hidden";
    }

    //=======  Verify Email =====
    //verify email
    const emailSpan = document.querySelector("#emailSpan");
    const emailRule = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;
    if (!emailRule.test(email.value)) {
        emailSpan.style.visibility = "visible";
        return false;
    } else {
        emailSpan.style.visibility = "hidden";
    }

    return true;
}