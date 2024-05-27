function ckLogin(){
    let token = localStorage.getItem("token");
    let order = document.querySelector("#order");
    let login = document.querySelector("#login");
    let register = document.querySelector("#register");
    let logout = document.querySelector("#logout");

    if(token != null){
        order.style.display="block";
        logout.style.display="block";


        login.style.display="none";
        register.style.display="none";
    }
}

async function logout(){
    localStorage.clear();
    await axios({
        method: "get",
        url:"/api/logout"
    })

    location.href="/";

}