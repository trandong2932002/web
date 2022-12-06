// input field
let inputs = document.querySelectorAll('#otp > *[id]');
for (let i = 0; i < inputs.length; i++) {
    inputs[i].addEventListener('keydown', function (e) {
        if (e.key === "Backspace") {
            inputs[i].value = '';
            if (i !== 0)
                inputs[i - 1].focus();
        } else {
            if (i === inputs.length - 1 && inputs[i].value !== '') {
                return true;
            } else if (e.keyCode > 47 && e.keyCode < 58) {
                inputs[i].value = e.key;
                if (i !== inputs.length - 1) inputs[i + 1].focus();
                e.preventDefault();
            } else if (e.keyCode > 64 && e.keyCode < 91) {
                inputs[i].value = String.fromCharCode(e.keyCode);
                if (i !== inputs.length - 1)
                    inputs[i + 1].focus();
                e.preventDefault();
            }
        }
    });
}


// button
document.getElementById("create-otp").addEventListener("click", otp_timer)
document.getElementById("create-otp").addEventListener("click", create_verification_code)
document.getElementById("create-new-otp").addEventListener("click", otp_timer)
document.getElementById("create-new-otp").addEventListener("click", create_verification_code)

document.getElementById("verify-otp").addEventListener("click", check_verification_code)

let pathname = window.location.pathname;

function otp_timer() {
    let timeleft = 4
    let timer = setInterval(function () {
        let create_new_otp_btn;
        if (timeleft <= 0) {
            clearInterval(timer)
            create_new_otp_btn = document.getElementById("create-new-otp");
            create_new_otp_btn.innerHTML = "Lấy mã mới"
            create_new_otp_btn.disabled = false
        } else {
            create_new_otp_btn = document.getElementById("create-new-otp");
            if (timeleft == 4)
                create_new_otp_btn.disabled = true
            create_new_otp_btn.innerHTML = "Còn " + timeleft + "s"
        }
        timeleft -= 1
    }, 1000);
}

function create_verification_code() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", pathname)
    xhr.responseType = "json"

    xhr.onload = function (e) {
        let x = xhr.response
        if (x != null) {
            let messaage = x.message

            let parent = document.getElementById("message")
            while (parent.firstChild) {
                parent.removeChild(parent.firstChild)
            }

            let newChild = document.createElement("div")
            newChild.classList.add(x.status ? "alert-success" : "alert-danger")
            newChild.classList.add("alert")
            newChild.innerText = messaage

            parent.appendChild(newChild)
        }
    }

    xhr.onerror = function () {
        console.log("XHR error")
    }

    document.getElementById("action").value = "create_verification_code"
    let formData = document.getElementById("main-form")

    let json = JSON.stringify(Object.fromEntries(formData));

    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
    xhr.send(json)
}

function check_verification_code() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", pathname)
    xhr.responseType = "json"

    xhr.onload = function (e) {
        let x = xhr.response
        if (x != null) {
            let messaage = x.message

            let parent = document.getElementById("message")
            while (parent.firstChild) {
                parent.removeChild(parent.firstChild)
            }

            let newChild = document.createElement("div")
            newChild.classList.add(x.status ? "alert-success" : "alert-danger")
            newChild.classList.add("alert")
            newChild.innerText = messaage

            parent.appendChild(newChild)
        }
    }

    xhr.onerror = function () {
        console.log("XHR error")
    }
    let verification_code = "";
    let flag = true;
    inputs.forEach((input) => {
        let value = input.value;
        if (value == "") {
            flag = false
            return
        }
        verification_code += value
    })
    if (!flag) {
        return
    }

    let json = JSON.stringify({
        action: "check_verification_code",
        verification_code: verification_code,
    });

    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
    xhr.send(json)
}