let content = document.getElementById("content");
let usernameInput = document.getElementById("usernameInput");
let passwordInput = document.getElementById("passwordInput");
let submitButton = document.getElementById("submitButton");

submitButton.addEventListener("click", apiUserLogin);


async function apiUserLogin(){
    let response = await fetch("http://localhost:9000/allUsers");
    response = await response.json();
    validated(response);
}

async function validated(response){
    for (let i = 0; i < response.length; i++){
        if (usernameInput.value == response[i].username){
            if (passwordInput.value == response[i].user_password){
                //window.location="http://127.0.0.1:5500/AccountATM.html";
                window.location="AccountATM.html";
            }

        }
    }
}

/*
async function apiUserLogin() {
    console.log("button clicked");
    let response = await fetch("http://localhost:9000/getUserByName/"+usernameInput.value);
    response = await response.json();
    confirmUsers(response);
    if (username == usernameInput.value && password == passwordInput.value){
        //apiUserLogin();
        window.location="AccountAtm.hmtl";
    }
    //window.location="AccountAtm.hmtl"
}

async function confirmUsername(response){
    var usernameValidated = Boolean;
    usernameValidated = false;
    if (response.username == usernameInput.value){
       return true; 
    } 
    else console.log("Enter a valid username"); 
    confirmPassword();  
}

async function confirmPassword(response){
    var passwordValidated = Boolean;
    passwordValidated = false;
    if (response.password == passwordInput.value){
        console.log("logged in")
        return true;
       
    }
    apiUserLogin();

       
}


*/