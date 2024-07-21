package br.edu.infnet.receitafacil_microservices.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingrediente(
    var id: Int,
    var item: Item,
    var quantidade: Float,
    var medida: Medida
): Parcelable