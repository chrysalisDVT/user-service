package org.chrysalis.userservice

import org.chrysalis.userservice.exception.UserRepositoryException
import org.chrysalis.userservice.repository.UserRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.test.util.ReflectionTestUtils


@ExtendWith(MockitoExtension::class)
@TestMethodOrder(MethodOrderer.Alphanumeric::class)
class UserServiceApplicationTests() {
    @Mock
    lateinit var namedParamTemplate: NamedParameterJdbcTemplate

    @InjectMocks
    lateinit var userRepository: UserRepository


    @BeforeEach
    fun setUp() {
        //ReflectionTestUtils.setField(userRepository, "namedParamTemplate", namedParamTemplate)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `Assert_get_users_exception`() {

        Mockito.lenient().`when`(
                namedParamTemplate
                .query(Mockito.anyString(), Mockito.any(RowMapper::class.java)))
                .thenThrow(
                        UserRepositoryException("USR_PERSIST_1001",
                                "Exception occurred while retrieving user details"))

        val exception = Assertions.assertThrows(UserRepositoryException::class.java) { userRepository.getUsers() } as UserRepositoryException
        val exceptionMsg = "Exception occurred while retrieving user details"
        val errorCode = "USR_PERSIST_1001"
        Assertions.assertEquals(exceptionMsg, exception.errorMsg)
        Assertions.assertEquals(errorCode, exception.errorCode)
    }


@Test
fun `Assert_user_by_id`(@Mock userRepository: UserRepository) {
    // BDDMockito.`when`(userRepository.getUsers()).thenCallRealMethod()
    //  userRepository.getUsers()
}

@Test
fun `Assert_post_users`() {

}

@Test
fun `Assert_update_user`() {

}


}

//@ExtendWith(MockitoExtension::class)
//@TestMethodOrder(MethodOrderer.Alphanumeric::class)
//class UserServiceTests(@Mock val userService: UserService) {
//
//    fun `assert_get_user_service`() {
//        BDDMockito.wh
//    }

