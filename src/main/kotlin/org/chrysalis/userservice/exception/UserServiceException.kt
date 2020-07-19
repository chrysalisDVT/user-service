package org.chrysalis.userservice.exception

import java.lang.RuntimeException

class UserServiceException(errorCode:String,message:String):RuntimeException()

class UserControllerException(errorCode:String,message:String):RuntimeException()

class UserRepositoryException(val errorCode:String,val errorMsg:String):RuntimeException()