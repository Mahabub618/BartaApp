package com.example.chat

class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null
    var password: String? = null
    constructor(){}

    constructor(name: String?, email: String?, password: String?, uid: String?){
        this.name = name;
        this.email = email
        this.password = password
        this.uid = uid
    }
}