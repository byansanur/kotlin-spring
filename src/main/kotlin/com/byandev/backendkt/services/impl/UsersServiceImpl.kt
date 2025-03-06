package com.byandev.backendkt.services.impl

import com.byandev.backendkt.entity.UserRoles
import com.byandev.backendkt.entity.Users
import com.byandev.backendkt.model.req.CreateUsersRequest
import com.byandev.backendkt.model.res.UsersResponse
import com.byandev.backendkt.repository.URoleRepository
import com.byandev.backendkt.repository.UsersRepository
import com.byandev.backendkt.services.UsersServices
import com.byandev.backendkt.utils.formatNowTime
import com.byandev.backendkt.utils.formatUserRolesFromString
import com.byandev.backendkt.utils.formatUserRolesToJson
import com.byandev.backendkt.utils.generateRandomNumber
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsersServiceImpl(
        val usersRepository: UsersRepository,
        val roleRepository: URoleRepository,
        val passwordEncoder: PasswordEncoder
) : UsersServices {
    override fun create(createUsersRequest: CreateUsersRequest): UsersResponse? {

        println("req: $createUsersRequest")

        val userRole = roleRepository.getRoleByNumber(createUsersRequest.roleNumber)
        var jsonUserRole = ""
        if (userRole != null) {
            jsonUserRole = formatUserRolesToJson(userRole)
            println("roleId: $jsonUserRole")
        }

        if (usersRepository.existsByUsername(createUsersRequest.username.toString())) {
            return null
        }

        val hashPassword = passwordEncoder.encode(createUsersRequest.passwords)

        val name = createUsersRequest.name

        val username = createUsersRequest.username ?: "${name.replace(" ", "")}-${generateRandomNumber()}"


        val user = Users(
                id = UUID.randomUUID().toString(),
                name = name,
                username = username,
                passwords = hashPassword,
                createdAt = formatNowTime(),
                updatedAt = null,
                role = jsonUserRole
        )
        println("user: $user")
        usersRepository.save(user)

        return UsersResponse(
                id = user.id,
                name = user.name,
                username = user.username,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
                roles = formatUserRolesFromString(jsonUserRole)
        )
    }
}