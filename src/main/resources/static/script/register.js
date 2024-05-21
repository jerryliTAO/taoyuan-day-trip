function verifyForm(){

    //=======  Verify Account =====
    //verify account at least 6 digit
    const accountRule =/^[A-Za-z0-9]{6,}$/g ;
    let account = document.querySelector("#account");
    let accountSpan = document.querySelector("#accountSpan");
    if(!accountRule.test(account.value)){
        accountSpan.style.visibility="visible";
        return false;
    }else{
        accountSpan.style.visibility="hidden";
    }

    //=======  Verify Password =====
    //verify password:at least 6~10 digit contain letter and number
    let password = document.querySelector("#password");
    let passwordSpan = document.querySelector("#pwdSpan");
    const pwdRule=/^(?=.*[a-zA-Z])(?=.*\d).{6,10}$/g;
    if(!pwdRule.test(password.value)){
        passwordSpan.style.visibility="visible";
        return false;
    }else{
        passwordSpan.style.visibility="hidden";
    }

    //======= Confirm Password =====
    //verify password:at least 6~10 digit contain letter and number
    let confirmPassword = document.querySelector("#confirmPassword");
    let pwdCkSpan = document.querySelector("#pwdCkSpan");
    if(confirmPassword.value != password.value){
        pwdCkSpan.style.visibility="visible";
        return false;
    }else{
        pwdCkSpan.style.visibility="hidden";
    }

    //=======  Verify Email =====
    //verify email
    let email = document.querySelector("#email");
    let emailSpan = document.querySelector("#emailSpan");
    const emailRule = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;
    if(!emailRule.test(email.value)){
        emailSpan.style.visibility="visible";
        return false;
    }else{
        emailSpan.style.visibility="hidden";
    }

    return true;
}