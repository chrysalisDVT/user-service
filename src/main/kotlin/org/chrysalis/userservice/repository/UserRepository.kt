package org.chrysalis.userservice.repository

import org.chrysalis.userservice.exception.UserRepositoryException
import org.chrysalis.userservice.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.lang.Exception
import java.util.*
import javax.annotation.PostConstruct


enum class UserSchema(val colName: String) {
    ID("id"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    ADDRESS("address"),
    REGISTERED("registeredOn")
}
const val GET_USERS="SELECT * FROM USERS"
const val INSERT_USER_INFO="INSERT INTO USERS VALUES(:id,:firstName,:lastName,:address,:registeredOn)"

@Repository
class UserRepository() {
    @Autowired lateinit var namedParamTemplate: NamedParameterJdbcTemplate
    val rowMapper=RowMapper<User>{rs,rowCount ->
        User(rs.getString(UserSchema.ID.colName),
                rs.getString(UserSchema.FIRST_NAME.colName),
                rs.getString(UserSchema.LAST_NAME.colName),
                rs.getString(UserSchema.ADDRESS.colName),
                Date(rs.getDate(UserSchema.REGISTERED.colName).time))
    }
    @PostConstruct
    fun initializeRepo() {
        val params = Array<BeanPropertySqlParameterSource>(5) {
            BeanPropertySqlParameterSource(User(UUID.randomUUID().toString(), "John", "Doe", "Area 51", Date()))
        }
        namedParamTemplate.batchUpdate(INSERT_USER_INFO, params)
    }
    fun persistUserInfo(userInfo:User):Int =
            namedParamTemplate.update(INSERT_USER_INFO,BeanPropertySqlParameterSource(userInfo))


    fun getUsers(): MutableList<User> = try {
        print("Getting users....")
        namedParamTemplate.query(
                GET_USERS,
                rowMapper)
    }catch (ex:Exception){
        throw UserRepositoryException("USR_PERSIST_1001","Exception occurred while retrieving user details")
    }


}