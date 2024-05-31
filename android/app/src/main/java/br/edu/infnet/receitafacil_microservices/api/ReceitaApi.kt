package br.edu.infnet.receitafacil_microservices.api

import br.edu.infnet.receitafacil_microservices.model.Receita
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


const val RECEITA_URL = "https://receitafacil-microservices-backend.azurewebsites.net"
//const val RECEITA_URL = "http://10.0.2.2:8080"

interface ReceitaApi{
    @GET("/receita")
    suspend fun getReceitas(): Response<List<Receita>>

//    @POST("/receita")
//    fun newReceita(@Body receita: Receita): Response<T>

}

object ReceitaRetrofitInstance{
    val api: ReceitaApi by lazy {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .build()
        Retrofit.Builder()
            .baseUrl(RECEITA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ReceitaApi::class.java)
    }
}