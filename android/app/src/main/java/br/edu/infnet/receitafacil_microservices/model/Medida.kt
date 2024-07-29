package br.edu.infnet.receitafacil_microservices.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medida (
    var id: Long,
    var nome: String,
    var unidade: Unidade
): Parcelable