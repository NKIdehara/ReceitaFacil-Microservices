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
    var figura: String = "",
    var custo: Float = 0.0f,
    var ingredientes: List<Ingrediente>? = null,
    var status: Status = Status.CRIADA,
    var createdDate: String = "",
    var createdBy: String = "",
    var lastModifiedDate: String = "",
    var lastModifiedBy: String = ""
): Parcelable
