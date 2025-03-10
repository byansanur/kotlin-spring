package com.byandev.backendkt.utils

import com.byandev.backendkt.entity.UserRoles
import com.fasterxml.jackson.databind.ObjectMapper
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

fun randomStringNumber(): Long {
    return Random.nextLong(8)
}

fun generateRandomNumber() : String {
    val allowedChars = ('0'..'9')
    return (0..7).map { allowedChars.random() }.joinToString("")
}

fun formatUserRolesToJson(userRoles: UserRoles): String {
    val objMap = ObjectMapper()
    return objMap.writeValueAsString(userRoles)
//        return objectMapper.writeValueAsString(userRoles)
}

fun formatUserRolesFromString(string: String) : UserRoles {
    val objMap = ObjectMapper()
    return objMap.readValue(string, UserRoles::class.java)
}

fun formatNowTime(gmt: String? = "") : String  {
    // Format the date to GMT+7
    val zId: ZoneId = if (gmt.isNullOrBlank())
        ZoneId.of("GMT+7")
    else ZoneId.of(gmt)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    return ZonedDateTime.now(zId).format(formatter)
}