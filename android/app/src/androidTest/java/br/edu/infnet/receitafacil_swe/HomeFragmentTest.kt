package br.edu.infnet.receitafacil_swe

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.common.truth.Truth.assertThat

class HomeFragmentTest : TestCase(){
    private lateinit var scenario: FragmentScenario<HomeFragment>

    @Before
    override fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_ReceitaFacil)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testarSeTelaDeApresentacaoAparece() {
        assertThat(onView(withId(R.id.textView)).check(matches(withText("Receita Fácil"))))
    }
}