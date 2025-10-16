package com.example.plango_nickname.name

import com.example.plango_nickname.api.ApiProvider

object NameRepository {
    private val api = ApiProvider.api
    suspend fun sendName(name : String): NameResponse{
        val request= NameRequest(name)
        return api.sendName(request)
    }
}