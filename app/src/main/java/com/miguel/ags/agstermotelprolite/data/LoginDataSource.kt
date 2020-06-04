package com.miguel.ags.agstermotelprolite.data

import com.miguel.ags.agstermotelprolite.data.model.LoggedInUser

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String): Result<LoggedInUser> {
        val userName = LoggedInUser("$username")
        return Result.Success(userName)
    }

    fun logout() {
        // TODO: revoke authentication
    }
}


