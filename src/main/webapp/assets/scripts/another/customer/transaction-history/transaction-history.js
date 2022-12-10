document.getElementById("get-transaction").addEventListener("click", get_transaction)

let pathname = window.location.pathname

function get_transaction() {
    let xhr = new XMLHttpRequest()
    xhr.open("POST", pathname)
    xhr.responseType = "json"

    xhr.onload = function (e) {
        let x = xhr.response
        let old_tbody = document.querySelector("tbody")
        let new_tbody = document.createElement("tbody")

        for (let i = 0; i < x.length; i++) {
            let row = new_tbody.insertRow()
            let cell0 = row.insertCell()
            let cell1 = row.insertCell()
            let cell2 = row.insertCell()
            let cell3 = row.insertCell()
            let cell4 = row.insertCell()
            let cell5 = row.insertCell()
            let cell6 = row.insertCell()

            cell0.innerHTML = i
            cell0.setAttribute("scope", "row")
            if (x[i].name == "transfer") cell1.innerHTML = "Chuyển tiền"
            else if (x[i].rolledOver == null) cell1.innerHTML = "Vay"
            else cell1.innerHTML = "Tiết kiệm"
            let tzoffset = (new Date()).getTimezoneOffset() * 60000;
            let time = new Date((new Date(x[i].createdTime).getTime() - tzoffset)).toISOString()
            cell2.innerHTML = time.split('T')[0]
            cell3.innerHTML = time.split('T')[1].substring(0,8)
            cell4.innerHTML = x[i].transactionAccountDestination.accountNumber
            cell5.innerHTML = x[i].amount
            cell6.innerHTML = (x[i].name == "transfer" ? x[i].content : x[i].name)
        }

        old_tbody.parentNode.replaceChild(new_tbody, old_tbody)
        // loop
        // let row
    }

    xhr.onerror = function () {
        console.log("XHR error")
    }

    let formData = new FormData(document.getElementById("main-form"))
    let json = JSON.stringify(Object.fromEntries(formData.entries()))

    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
    xhr.send(json)
}