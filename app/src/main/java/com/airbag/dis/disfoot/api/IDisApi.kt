package com.airbag.dis.disfoot.api

import com.airbag.dis.disfoot.model.Shoe
import retrofit2.http.POST

interface IDisApi {

    @POST("v1/dis_models/get_models_detail")
    suspend fun getShoesModels(): Map<String, List<Shoe>>

}