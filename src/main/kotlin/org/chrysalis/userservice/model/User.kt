package org.chrysalis.userservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.util.Date

@Table
data class  User @PersistenceConstructor constructor (@Id val id:String,
                val firstName:String,
                val lastName: String,
                val address:String,
                val registeredOn: Date?) : Serializable

data class SkillSet(val id:String,val name:String)