package com.byandev.backendkt.services.impl

import com.byandev.backendkt.entity.UserRoles
import com.byandev.backendkt.model.req.CreateRoleRequest
import com.byandev.backendkt.model.res.UserRolesResponse
import com.byandev.backendkt.repository.URoleRepository
import com.byandev.backendkt.services.SUServices
import org.springframework.stereotype.Service

@Service
class SUServicesImpl(
    private val uRoleRepository: URoleRepository
) : SUServices{
    override fun createRoleBySuperUsers(request: CreateRoleRequest): String {
        val data = UserRoles(
            roleNumber = request.roleNumber, roleName = request.roleName
        )
        uRoleRepository.save(data)
        return "Success"
    }

    override fun getRoles(): MutableList<UserRolesResponse>? {
        val allRole = uRoleRepository.findAll()
        val data = mutableListOf<UserRolesResponse>()
        for (i in allRole.indices) {
            val resp = allRole[i].id?.let {
                UserRolesResponse(
                    id = it,
                    roleNumber = allRole[i].roleNumber,
                    roleName = allRole[i].roleName
                )
            }
            if (resp != null) {
                data.add(resp)
            }
        }
        return data
    }
}