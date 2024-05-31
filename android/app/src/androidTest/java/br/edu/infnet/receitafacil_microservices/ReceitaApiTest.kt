package br.edu.infnet.receitafacil_microservices

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import br.edu.infnet.receitafacil_microservices.api.ReceitaApiViewModelFactory
import br.edu.infnet.receitafacil_microservices.api.ReceitaRepository
import br.edu.infnet.receitafacil_microservices.api.ReceitaApiViewModel
import br.edu.infnet.receitafacil_microservices.receitas.ReceitasFragment
import com.google.common.truth.Truth
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ReceitaApiTest : TestCase(){
    private lateinit var apiModel: ReceitaApiViewModel
    private lateinit var scenario: FragmentScenario<ReceitasFragment>


    @Before
    override fun setUp() {
        val receitaRepository = ReceitaRepository()
        val receitaModelFactory = ReceitaApiViewModelFactory(receitaRepository)
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_ReceitaFacil)
        scenario.moveToState(Lifecycle.State.STARTED)
        apiModel = ViewModelProvider(ReceitasFragment(), receitaModelFactory).get(ReceitaApiViewModel::class.java)
    }

    @Test
    fun testarRespostaDeApi() {
        apiModel.getResponse()
        Truth.assertThat(apiModel.myResponse.value).apply { isNotNull() }
    }

}
