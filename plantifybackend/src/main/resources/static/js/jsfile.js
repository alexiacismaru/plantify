

const logout = document.getElementById('logout')
logout.addEventListener('click', event => {
    location.reload()
})

const redirect = document.getElementById('redirect')
redirect.addEventListener('click', event => {
    window.location.replace('login.html')
})

const name = document.getElementById('name')
const type = document.getElementById('type')
const createPlant = document.getElementById('createPlant')
createPlant.addEventListener('click', event => {
})

let userNameOK = true
const username = document.getElementById('name')
const nameRegex = new RegExp('^[a-zA-Z][a-zA-Z0-9]*$')

let emailOK = true
const email = document.getElementById('email')
const emailRegex = new RegExp('/[a-zA-Z0-9.!#$%&\'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*/')

const password = document.getElementById('newPassword')
const passwordError = document.getElementById('passwordError')

const createAccount = document.getElementById('createAccount')
createAccount.addEventListener('click', event => {
    //check if username respects the requirements
    let usernameText = username.value
    if (!nameRegex.test(usernameText)) {
        document.getElementById('errorUsername').style.display = ' block'
        userNameOK = false
        event.preventDefault()
    }
    else {
        userNameOK = true
    }

    //check if email is the right format
    let emailText = email.value
    if(!emailRegex.test(emailText)){
        document.getElementById('errorEmail').style.display = 'block'
        emailOK = false
        event.preventDefault()
    }else{
        emailOK = true
    }

    //check if password is strong enough
    if(password.value <= 6){
        passwordError.innerHTML = `Password is too short!`
    }

    //contains both uppercase and lowercase letters, contains at least one digit, contains punctuation
    let uppercase = false
    let lowercase = false
    let digit = false
    let punctuation = false
    for (let i = 0; i < password.value.length; i++) {
        let letter = !(password.value.charAt(i) === password.value.charAt(i).toUpperCase() && password.value.charAt(i) === password.value.charAt(i).toLowerCase())
        if (password.value.charAt(i) === password.value.charAt(i).toUpperCase() && letter) {
            uppercase = true
        }
        else if (password.value.charAt(i) === password.value.charAt(i).toLowerCase() && letter) {
            lowercase = true
        }
        else if (!isNaN(password.value.charAt(i)) && password.value.charAt(i) !== ' ') {
            digit=true
        }
        else if (password.value.charAt(i) !== ' '){
            punctuation = true
        }
    }
})

let confirmPasswordOK = true
const confirmPassword = document.getElementById('confirmPassword')
const confirmPasswordError = document.getElementById('confirmPasswordError')
confirmPassword.addEventListener("blur", event=> {
    if (!confirmPassword.value) {
        confirmPasswordOK = false
        confirmPasswordError.innerText = 'Please confirm your password'
    }
    else if (confirmPassword.value !== password.value) {
        confirmPasswordOK=false;
        confirmPasswordError.innerText = 'Input should match the password'
    }
})