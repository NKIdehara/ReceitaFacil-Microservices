package br.edu.infnet.receitafacil_microservices.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingrediente(
    var id: Int,
    var nome: String,
    var quantidade: Float,
    var medida: String
): Parcelable