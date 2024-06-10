package br.edu.infnet.receitafacil_microservices.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Receita(
    var id: Int,
    var usuario: String = "",
    var nome: String = "",
    var preparo: String = "",
    var ingredientes: List<Ingrediente>? = null,
    var data_receita: String = "",
    var figura: String = ""
): Parcelable
