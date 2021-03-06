package mcc.group14.apiclientapp.data.users

import java.io.Serializable

data class UserRegistration(
    val email_id: String,
    val display_name: String,
    var password: String
) : Serializable