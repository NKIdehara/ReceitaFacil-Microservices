package br.edu.infnet.receitafacil_swe.receitas

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Receita(
    var id: Int,
    var usuario: String = "",
    var nome: String = "",
    var preparo: String = "",
    var ingredientes: String = "",
    var data_receita: Date,
    var figura: String = ""
): Parcelable

// usuário logado
lateinit var usuario: String
