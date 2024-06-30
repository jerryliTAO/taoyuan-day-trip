window.onload = async function () {
    ckLogin();
    // const cart = await getCart();
    // console.log(cart);

    let vue = new Vue({
        el:"#withPlan",
        data:{
            cart:{}
        },
        methods:{
            getCart:function(){
                const uid = parseInt(localStorage.getItem("userId"))
                let result = axios({
                    method: "get",
                    url: "/api/cart/" + uid
                })
                    .then(res => {
                        vue.cart = res.data;
                    })
                    .catch(err => {
                        console.log(err.data)
                    })
            }
        },
        mounted:function (){
            this.getCart();
        }
    });

}


const APP_ID = 11327
const APP_KEY = "app_whdEWBH8e8Lzy4N6BysVRRMILYORF6UxXbiOFsICkz0J9j1C0JUlCHv1tVJC"


TPDirect.setupSDK(APP_ID, 'APP_KEY', 'sandbox')

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

// async function getCart() {
//     const uid = parseInt(localStorage.getItem("userId"))
//
//     let result = await axios({
//         method: "get",
//         url: "/api/cart/" + uid
//     })
//         .then(res => {
//             console.log(res.data)
//         })
//         .catch(err => {
//             console.log(err.data)
//         })
// }