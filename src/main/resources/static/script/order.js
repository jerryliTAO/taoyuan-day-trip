window.onload = function(){
    ckLogin();
}

function toggleMenu(number) {
    // toggle the order detail
    let menu = document.querySelector("#testMenu_" + number);
    if (menu.style.display == "none" || menu.style.display == "") {
        menu.style.display = "block";
    } else {
        menu.style.display = "none";
    }

    let expand = document.querySelector("#expand_" + number);
    if (expand.innerHTML == "+") {
        expand.innerHTML = "-";
    } else {
        expand.innerHTML = "+";
    }
}