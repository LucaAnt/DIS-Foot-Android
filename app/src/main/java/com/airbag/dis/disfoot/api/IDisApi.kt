package com.airbag.dis.disfoot.api

import com.airbag.dis.disfoot.model.MeasureRequest
import com.airbag.dis.disfoot.model.Shoe
import com.airbag.dis.disfoot.model.ShoeSizeResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IDisApi {

    @POST("v1/dis_models/get_models_detail")
    suspend fun getShoesModels(): Map<String, List<Shoe>>


    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("v1/dis_size_algorithm/2109")
    suspend fun getShoeSizeResponse(@Body requestBody: MeasureRequest): Map<String, ShoeSizeResponse>
    //{"gender":"M","idScan":"6ON95S","yourName":"no_name","rightMeasure":{"ballGirth":229.92500000000001,"instepGirth":242.41800000000001,"footLength":253.89099999999999},"idModel":"65","email":"asd@asd.it","scanName":"","leftMeasure":{"ballGirth":230.31700000000001,"instepGirth":238.309,"footLength":255.13300000000001}}
   }