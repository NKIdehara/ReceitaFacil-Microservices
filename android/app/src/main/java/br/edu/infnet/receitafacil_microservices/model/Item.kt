package br.edu.infnet.receitafacil_microservices.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item (
    var id: Long,
    var descricao: String,
    var preco: Float,
    var medida: Medida
): Parcelable