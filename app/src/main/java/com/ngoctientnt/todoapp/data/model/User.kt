package com.ngoctientnt.todoapp.data.model

data class User(val fullName: String, val email: String, val uuid: String) {
    companion object {
        fun from(map: Map<String, String>) = object {
            val fullName by map
            val email by map
            val uuid by map

            val data = User(fullName, email, uuid)
        }.data
    }
}