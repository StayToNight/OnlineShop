package com.staytonight.data.storage

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.staytonight.domain.model.User
import com.staytonight.domain.storage.DataStorage
import javax.inject.Inject
import kotlin.random.Random

class DataStorageImpl @Inject constructor(
    private val sharedPref: SharedPreferences
) : DataStorage {
    companion object {
        private const val USERS = "USERS"
        private const val DEFAULT_PASSWORD = "123"
        private const val CURRENT_USER = "CURRENT_USER"
    }

    override fun login(email: String, password: String): Boolean {
        val users = getUsers()
        val gson = Gson()

        val user = users.find { it.email == email && it.password == password }

        if (user != null)
            putCurrentUser(gson.toJson(user))

        return user != null
    }

    override fun register(firstName: String, lastName: String, email: String): Boolean {
        val usersJson = getUsers(); Log.e("TAG", "$usersJson")
        val gson = Gson()
        val users = getUsers()

        return if (users.find { it.email == email } != null)
            false
        else {
            val newUser =
                User(firstName, lastName, email, DEFAULT_PASSWORD, Random.Default.nextInt(1000))
            users.add(newUser)

            val newUsersJson = gson.toJson(users)
            putUsers(newUsersJson)

            putCurrentUser(gson.toJson(newUser))

            true
        }
    }

    override fun getCurrentUser(): User? {
        val userJson = sharedPref.getString(CURRENT_USER, "")

        return if (userJson.isNullOrEmpty())
            null
        else {
            val gson = Gson()
            val users = getUsers()
            val user: User = gson.fromJson(userJson, User::class.java)
            users.find { it.email == user.email }
        }
    }

    override fun logout() {
        sharedPref.edit().putString(CURRENT_USER, "").apply()
    }

    override fun setAvatar(avatar: String) {
        val user = getCurrentUser()
        val users = getUsers()

        users.find { it.email == user?.email }?.avatar = avatar
        putUsers(Gson().toJson(users))
    }

    private fun getUsers(): MutableList<User> {
        val usersJson = sharedPref.getString(USERS, "")
        val gson = Gson()
        val users: MutableList<User> = if (usersJson.isNullOrEmpty()) mutableListOf() else {
            val listType = object : TypeToken<List<User>>() {}.type
            gson.fromJson(usersJson, listType)
        }
        return users
    }

    private fun putUsers(users: String) =
        sharedPref.edit().putString(USERS, users).apply()

    private fun putCurrentUser(user: String) {
        sharedPref.edit().putString(CURRENT_USER, user).apply()
    }
}