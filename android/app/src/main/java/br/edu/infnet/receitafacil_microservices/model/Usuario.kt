package br.edu.infnet.receitafacil_microservices.model

data class Usuario(
    var UID: String,
    var nome: String
)

// usuário logado
lateinit var usuario: String