package com.byandev.backendkt.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "user_roles")
data class UserRoles(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    val id: Long? = null,
    @Column(name = "role_number")
    val roleNumber: Int?,
    @Column(name = "role_name")
    val roleName: String
)
