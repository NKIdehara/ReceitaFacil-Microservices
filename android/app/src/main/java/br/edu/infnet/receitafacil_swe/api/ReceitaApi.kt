package br.edu.infnet.receitafacil_swe.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.edu.infnet.receitafacil_swe.receitas.Receita
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.Date
import kotlin.collections.ArrayList

const val RECEITA_URL = "http://10.0.2.2:8080/"

data class Receitas(
    var id: Long,
    var usuario: String = "",
    var nome: String = "",
    var preparo: String = "",
    var ingredientes: String = "",
    var dataReceita: String = "",
    var figura: String = ""
//    @SerializedName("id"           ) var id           : Int,
//    @SerializedName("usuario"      ) var usuario      : String,
//    @SerializedName("nome"         ) var nome         : String,
//    @SerializedName("preparo"      ) var preparo      : String,
//    @SerializedName("ingredientes" ) var ingredientes : String,
//    @SerializedName("data_receita" ) var dataReceita  : String,
//    @SerializedName("figura"       ) var figura       : String
)

data class ReceitaJson(
    @SerializedName("receita") var receita: ArrayList<Receitas> = arrayListOf()
//    @SerializedName("receita") var receita: ArrayList<ReceitaDados> = arrayListOf()
)

interface ReceitaApi{
    @GET("receita/1")
    suspend fun getResponse(): Response<ReceitaJson>
}

object ReceitaRetrofitInstance{
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RECEITA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ReceitaApi by lazy {
        retrofit.create(ReceitaApi::class.java)
    }
}

class ReceitaRepository{
    suspend fun getResponse(): Response<ReceitaJson>{
        return ReceitaRetrofitInstance.api.getResponse()
    }
}

class ReceitaApiViewModel(private val receitaRepository: ReceitaRepository): ViewModel(){
    val myResponse: MutableLiveData<Response<ReceitaJson>> = MutableLiveData()

    // Executa ação em segundo plano
    fun getResponse(){
        viewModelScope.launch {
            val response: Response<ReceitaJson> = receitaRepository.getResponse()
            myResponse.value = response
        }
    }
}

class ReceitaApiViewModelFactory(private val receitaRepository: ReceitaRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReceitaApiViewModel(receitaRepository) as T
    }
}