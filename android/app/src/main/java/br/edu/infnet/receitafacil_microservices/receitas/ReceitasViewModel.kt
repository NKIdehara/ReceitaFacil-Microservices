package br.edu.infnet.receitafacil_microservices.receitas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.receitafacil_microservices.model.Receita

class ReceitasViewModel : ViewModel() {

    val receitas: MutableLiveData<ArrayList<Receita>> by lazy{
        MutableLiveData<ArrayList<Receita>>()
    }

}
