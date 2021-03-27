package com.byandev.backendkt.services.impl

import com.byandev.backendkt.entity.Users
import com.byandev.backendkt.model.CreateUsersRequest
import com.byandev.backendkt.model.UsersResponse
import com.byandev.backendkt.repository.UsersRepository
import com.byandev.backendkt.services.UsersServices
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsersServiceImpl(
        val usersRepository: UsersRepository
) : UsersServices {
    override fun create(createUsersRequest: CreateUsersRequest): UsersResponse {
        val user = Users(
                id = createUsersRequest.id,
                name = createUsersRequest.name,
                userName = createUsersRequest.userName,
                passwords = createUsersRequest.passwords,
                createdAt = Date(),
                updatedAt = null
        )
        usersRepository.save(user)

        return UsersResponse(
                id = user.id,
                name = user.name,
                userName = user.userName,
                passwords = user.passwords,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
        )
    }
}