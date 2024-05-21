//email validation
function verifyEmail(){
    const mailRule = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;
    let email = document.querySelector("#email");
    let emailSpan = document.querySelector("#emailSpan");
    if(!mailRule.test(email.value)){
        emailSpan.style.display="block";
        return false;
    }else{
        emailSpan.style.display="none";
    }
    return true;
}



