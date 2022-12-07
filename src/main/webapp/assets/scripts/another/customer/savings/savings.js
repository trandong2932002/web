//TODO: savings states must be set on server

const list_card_footer = document.getElementsByClassName("card-footer")

const savings_states = {
    "Đang chờ": [],
    "Đã hoàn tất": [
        "bg-success",
        "text-light"
    ],
    "Đã rút trước hạn": [
        "bg-warning"
    ]
}

for (let element of list_card_footer) {
    let state = element.children[0].children[1].innerText
    element.classList.add(...savings_states[state])
}

document.querySelectorAll("div.card").forEach((card) => {
    let maturity = card.querySelector("#maturity-date").innerText
    let date_diff = card.querySelector("#date-diff")
    let end = card.querySelector("#end-date").innerText

    if (end == "Chưa rút") {
        let maturity_date = Date.parse(maturity)
        let dates = Math.ceil((maturity_date - Date.now()) / (1000 * 60 * 60 * 24))
        date_diff.innerText = dates > 0 ? dates : "0"
    } else {
        let maturity_date = Date.parse(maturity)
        let end_date = Date.parse(end)
        let dates = Math.ceil((maturity_date - end_date) / (1000 * 60 * 60 * 24))
        date_diff.innerText = dates
    }
})