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
