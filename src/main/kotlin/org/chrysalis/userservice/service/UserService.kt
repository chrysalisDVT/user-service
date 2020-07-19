package org.chrysalis.userservice.service

import org.chrysalis.userservice.exception.UserServiceException
import org.chrysalis.userservice.model.User
import org.chrysalis.userservice.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(@Autowired val userRepository: UserRepository) {

    fun getUserDetails(): MutableList<User> = try {
        userRepository.getUsers()
    } catch (ex: Exception) {
        throw UserServiceException("USR-SERVICE-1001", "Exception occurred while getting user details")
    }

    fun saveUser(user: User) = try {
        userRepository.persistUserInfo(user)
    }catch (ex:Exception){
        throw UserServiceException("USR-SERVICE-1002", "Exception occurred while saving user details")
    }
}