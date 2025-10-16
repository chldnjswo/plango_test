package com.example.plango_nickname.api

import com.example.plango_nickname.name.NameRequest
import com.example.plango_nickname.name.NameResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/name")
    suspend fun sendName(@Body request : NameRequest): NameResponse
}