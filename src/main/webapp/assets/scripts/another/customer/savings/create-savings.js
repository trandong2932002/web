// TODO: interest_rates, default amount, min amount, available amount must be set on server
//* LISTENER, DEFAULT VALUE
// check valid amount
const amount = document.getElementById("amount")
const available_amount = document.getElementById("available-amount")

amount.addEventListener("input", function (e) {
    let i_amount = parseInt(amount.value)
    let i_available_amount = parseInt(available_amount.value)

    if (i_amount > i_available_amount) {
        available_amount.classList.replace("text-success", "text-danger")
    } else {
        available_amount.classList.replace("text-danger", "text-success")
    }
})

// set default amount, min amount
amount.value = "1000"
amount.setAttribute("min", "1000")

// set interest rate, create date, matirity date
const term = document.getElementById("term")
const interest_rate = document.getElementById("interest-rate")
const create_date = document.getElementById("create-date")
const maturity_date = document.getElementById("maturity-date")

const interest_rates = {
    "1 tháng": "5%",
    "3 tháng": "5%",
    "6 tháng": "6%",
    "12 tháng": "6%",
    "24 tháng": "7%",
    "36 tháng": "7%"
}
const d = new Date()

term.addEventListener("change", function (e) {
    let values_interest_rates = Object.values(interest_rates)
    interest_rate.value = values_interest_rates[term.value]

    let keys_interest_rates = Object.keys(interest_rates)
    maturity_date.value = format_date(auto_add_month())
})

// set default term
Object.keys(interest_rates).forEach(function (value, i) {
    let node = document.createElement("option")
    node.value = i
    node.innerText = value
    term.appendChild(node)
})

// set default interset rate
interest_rate.value = Object.values(interest_rates)[term.value]

// set default create date, maturity date
create_date.value = format_date(d)
maturity_date.value = format_date(auto_add_month())


//* FUNCTION

function auto_add_month() {
    let new_d = new Date(d)
    new_d.setMonth(
        new_d.getMonth() + parseInt(
            term.options[term.selectedIndex].text.split(" ")[0]
        )
    )
    return new_d
}

function format_date(date) {
    let day = String(date.getDate())
    let month = String(date.getMonth() + 1)
    let year = String(date.getFullYear())

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return year + '-' + month + '-' + day
}

function validate() {
    let errors = []
    let i_amount = parseInt(amount.value)
    let i_available_amount = parseInt(available_amount.value)
    if (i_amount > i_available_amount) {
        errors.push("Số tiền trong tài khoản không đủ")
    }
    if (errors.length) {
        alert(errors.join('\n'))
        console.log('asd')
        return false
    }
    return true
}