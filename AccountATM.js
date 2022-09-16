let content = document.getElementById("content");
let content2 = document.getElementById("content2");
let content3 = document.getElementById("content3");
let content4 = document.getElementById("content4");
let loadButton = document.getElementById("loadButton");
let loadButton2 = document.getElementById("loadButton2");

let usernameInput = document.getElementById("usernameInput");
let user_idInput = document.getElementById("user_idInput");
let first_nameInput = document.getElementById("first_nameInput");
let last_nameInput = document.getElementById("last_nameInput");
let user_passwordInput = document.getElementById("passwordInput");

let accountidInput = document.getElementById("accountidInput");
let balanceInput = document.getElementById("balanceInput");
let account_useridInput = document.getElementById("account_useridInput");
let account_nameInput = document.getElementById("account_nameInput");

let delete_userInput = document.getElementById("delete_userInput");
let delete_accountInput = document.getElementById("delete_accountInput");

let submitButton = document.getElementById("submitButton");
let submitButton2 = document.getElementById("submitButton2");
// let's move into using eventListener instead of onclick - eventlistener allows multiple handlers for an event as well
// as more configuration - so potentially clicking loadButton may trigger multiple actions!
loadButton.addEventListener("click", apiGetUsers);
loadButton2.addEventListener("click", apiGetAccounts);
submitButton.addEventListener("click", apiPostUser);
submitButton2.addEventListener("click", apiPostAccount);
deleteButton.addEventListener("click", apiDeleteUser);
deleteButton2.addEventListener("click", apiDeleteAccount);


async function apiGetUsers(){
    console.log("button clicked");
    let response = await fetch("http://localhost:9000/allUsers");
    response = await response.json();
    loadUsers(response);
}

async function apiGetAccounts(){
    console.log("button clicked");
    let response = await fetch("http://localhost:9000/allaccounts");
    response = await response.json();
    loadAccounts(response);
}
//load users
async function loadUsers(response){
    content.innerHTML = "";
    console.log(response)
    let userList = document.createElement("ul");
    for(let i = 0; i < response.length; i++){
        let user_username = document.createElement('li');
        let user_user_id = document.createElement('p');
        let user_first_name = document.createElement('p');
        let user_last_name = document.createElement('p');
        let user_password = document.createElement('p');
        user_username.innerText = response[i].username;
        user_user_id.innerText = response[i].user_id;
        user_first_name.innerText = response[i].first_name;
        user_last_name.innerText = response[i].last_name;
        user_password.innerText = response[i].password;

        userList.appendChild(user_username);
        userList.appendChild(user_user_id);
        userList.appendChild(user_first_name);
        userList.appendChild(user_last_name);
        userList.appendChild(user_password);
    }
    content.appendChild(userList);
    
}

async function apiPostUser(){
    let inputUser = {
        username:usernameInput.value,
        user_id:user_idInput.value,
        first_name:first_nameInput.value,
        last_name:last_nameInput.value,
        user_password:user_passwordInput.value
    }
    let response = await fetch("http://localhost:9000/addUser", {
        method:'POST',
        mode:'cors',
        headers: {
            'Content-Type': 'application/json'
          },
        body:JSON.stringify(inputUser)
    });
    apiGetUsers();
}
//load accounts
async function loadAccounts(response){
    content2.innerHTML = "";
    console.log(response)
    let accountList = document.createElement("ul");
    for(let i = 0; i < response.length; i++){
        let account_accountid = document.createElement('li');
        let account_balance = document.createElement('p');
        let account_userid = document.createElement('p');
        let account_account_name = document.createElement('p');

        account_accountid.innerText = response[i].accountid;
        account_balance.innerText = response[i].balance;
        account_userid.innerText = response.account_accountid;
        account_account_name.innerText = response[i].account_name;

        accountList.appendChild(account_accountid);
        accountList.appendChild(account_balance);
        accountList.appendChild(account_userid);
        accountList.appendChild(account_account_name);
    }
    content2.appendChild(accountList);
    
}
//
async function apiPostAccount(){
    let inputAccount = {
        accountid:accountidInput.value,
        balance:balanceInput.value,
        account_userid:account_useridInput.value,
        account_name:account_nameInput.value,
    }
    let response = await fetch("http://localhost:9000/addAccount", {
        method:'POST',
        mode:'cors',
        headers: {
            'Content-Type': 'application/json'
          },
        body:JSON.stringify(inputAccount)
    });
    apiGetAccounts();
}
//
async function apiDeleteUser(user_id){
    let inputUser = delete_userInput.value;

    let response = await fetch("http://localhost:9000/deleteUser/"+inputUser, {
        method:'Delete',
        mode:'cors',
        headers: {
            'Content-Type': 'application/json'
          },
        body:JSON.stringify(inputUser)
    });
    apiGetUsers();
}

async function apiDeleteAccount(){
    let inputAccount = delete_accountInput.value;
    
    let response = await fetch("http://localhost:9000/deleteAccount/"+inputAccount, {
        method:'Delete',
        mode:'cors',
        headers: {
            'Content-Type': 'application/json'
          },
        body:JSON.stringify(inputAccount)
    });
    apiGetAccounts();
}
