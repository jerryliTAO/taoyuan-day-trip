window.onload = async function () {
    ckLogin();
    let cartItem = await getCart();

    let withPlan = document.querySelector("#withPlan");
    let withoutPlan = document.querySelector("#withoutPlan");

    if (cartItem.totalPrice == 0) {
        withoutPlan.style.display = "block";
    } else {
        withPlan.style.display = "flex";
        let vue = new Vue({
            el: "#withPlan",
            data: {
                cart: cartItem
            },
            methods: {
                deleteCartItem: async function (cartItemId) {
                    let response = await axios({
                        method: "delete",
                        url: "/api/cart/" + cartItemId
                    })
                        .then(res => {
                            console.log(res.data)
                            return res.data;
                        })
                        .catch(err => {
                            console.log(err)
                        })

                    if (response.status == "success") {
                        // location.reload()
                        vue.cart = getCart();
                    } else {
                        alert("購物車刪除項目失敗")
                    }

                }
            },
            mounted: function () {
                getCart();
            }
        });
    }
}

function getCart() {
    const uid = parseInt(localStorage.getItem("userId"))
    return axios({
        method: "get",
        url: "/api/cart/" + uid
    })
        .then(res => {
            return res.data;
        })
        .catch(err => {
            console.log(err.data)
        })

}


//=======   Tappay Api ======
const APP_ID = 11327
const APP_KEY = "app_whdEWBH8e8Lzy4N6BysVRRMILYORF6UxXbiOFsICkz0J9j1C0JUlCHv1tVJC"


TPDirect.setupSDK(APP_ID, APP_KEY, 'sandbox')

let fields = {
    number: {
        element: '#cardNumber',
        placeholder: '**** **** **** ****'
    },
    expirationDate: {
        element: document.getElementById('cardExpirationDate'),
        placeholder: 'MM / YY'
    },
    ccv: {
        element: '#cardCCV',
        placeholder: 'ccv'
    }
}

TPDirect.card.setup({
    fields: fields,
    styles: {
        'input': {
            'color': 'gray'
        },
        'input.ccv': {
            'font-size': '16px'
        },
        'input.expiration-date': {
            'font-size': '16px'
        },
        'input.card-number': {
            'font-size': '16px'
        },
        '.valid': {
            'color': 'green'
        },
        '.invalid': {
            'color': 'red'
        }
    },
    // 此設定會顯示卡號輸入正確後，會顯示前六後四碼信用卡卡號
    isMaskCreditCardNumber: true,
    maskCreditCardNumberRange: {
        beginIndex: 4,
        endIndex: 11
    }
})


async function bookingTrip() {
    // 取得 TapPay Fields 的 status
    const tappayStatus = TPDirect.card.getTappayFieldsStatus()

    // 確認是否可以 getPrime
    if (tappayStatus.canGetPrime === false) {
        alert('無法取得卡片資訊')
        return
    }

    // Get prime
    TPDirect.card.getPrime(async (result) => {
        if (result.status !== 0) {
            alert('獲取卡片資訊失敗: ' + result.msg)
            return;
        }

        const price = parseInt(document.querySelector("#price").innerText);
        const contactName = document.querySelector("#contactName").value;
        const contactEmail = document.querySelector("#contactEmail").value;
        const contactPhone = document.querySelector("#contactPhone").value;
        const uid = parseInt(localStorage.getItem("userId"));
        verifyForm(contactEmail, contactPhone);

        let payResult = await axios({
            method: "post",
            url: "/api/order",
            "data": {
                "userId": uid,
                "prime": result.card.prime,
                "price": price,
                "name": contactName,
                "email": contactEmail,
                "phone_number": contactPhone
            }
        }).then(res => {
            console.log(res.data);
            return res.data;
        }).catch(err => {
            console.log(err.data);
            return err.data;
        })

        if (payResult.status == "下訂完成") {
            alert(payResult.status)
            location.href = "/order"
        } else {
            alert(payResult.status)
            return;
        }

    });
}

async function testBookingTrip() {
    let input = {
        "prime": "392f24762bd4e46605e71b389c3bab2e3af8a2cc5a9b80853d9db85870732bde",
        "partner_key": "partner_6ID1DoDlaPrfHw6HBZsULfTYtDmWs0q0ZZGKMBpp4YICWBxgK97eK3RM",
        "merchant_id": "GlobalTesting_CTBC",
        "details": "TapPay Test",
        "amount": 100,
        "cardholder": {
            "phone_number": "+886923456789",
            "name": "王小明",
            "email": "LittleMing@Wang.com"
        },
        "remember": true
    }

    let payResult = await axios({
        method: "post",
        url: "https://sandbox.tappaysdk.com/tpc/payment/pay-by-prime",
        "data": input,
        headers: {
            "Content-Type": "application/json",
            "x-api-key": "partner_6ID1DoDlaPrfHw6HBZsULfTYtDmWs0q0ZZGKMBpp4YICWBxgK97eK3RM",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
            "Access-Control-Allow-Methods": "*",
            "Access-Control-Allow-Headers": "*",
        }

    }).then(res => {
        console.log(res)
        return res;
    }).catch(err => {
        console.log(err)
    })
}


function verifyForm(email, phone) {

    //=======  Verify Email =====
    //verify email
    const emailRule = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;
    if (!emailRule.test(email)) {
        alert("Email格式錯誤");
        return;
    }


    //=======  Verify Phone Number =====
    //verify phone number :must start with 09 and have 10 digits number
    const pwdRule = /^09[0-9]{8}$/g;
    if (!pwdRule.test(phone)) {
        alert("連絡電話格式錯誤，請以09開頭，請總共為10位數");
        return;
    }
}