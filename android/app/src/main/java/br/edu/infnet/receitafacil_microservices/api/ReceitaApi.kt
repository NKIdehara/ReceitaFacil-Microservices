package br.edu.infnet.receitafacil_microservices.api

import br.edu.infnet.receitafacil_microservices.model.Receita
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

const val RECEITA_URL = "https://receitafacil-microservices-backend.azurewebsites.net"
//const val RECEITA_URL = "http://10.0.2.2:8080"
const val TIME_OUT = 60L

interface ReceitaApi{
    @GET("/receita/total")
    suspend fun getTotalReceitas(): Int

    @GET("/receita/usuario/{uid}")
    suspend fun getReceitas(@Path("uid") uid: String): Response<List<Receita>>

    @POST("/receita")
    suspend fun newReceita(@Body receita: Receita)

    @PUT("/receita/{id}")
    suspend fun editReceita(@Path("id") id: Int, @Body receita: Receita)

    @DELETE("/receita/{id}")
    suspend fun deleteReceita(@Path("id") id: Int)
}

object ReceitaRetrofitInstance{
    val api: ReceitaApi by lazy {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
        Retrofit.Builder()
            .baseUrl(RECEITA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ReceitaApi::class.java)
    }
}