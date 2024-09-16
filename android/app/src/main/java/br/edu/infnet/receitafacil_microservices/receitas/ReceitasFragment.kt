package br.edu.infnet.receitafacil_microservices.receitas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.receitafacil_microservices.api.ReceitaRetrofitInstance
import br.edu.infnet.receitafacil_microservices.databinding.FragmentReceitasBinding
import br.edu.infnet.receitafacil_microservices.model.Receita
import br.edu.infnet.receitafacil_microservices.R
import br.edu.infnet.receitafacil_microservices.model.usuario
import br.edu.infnet.receitafacil_microservices.model.Status
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.firestore.*
import retrofit2.HttpException
import java.io.IOException

class ReceitasFragment : Fragment() {
    private lateinit var receitasViewModel: ReceitasViewModel
    private lateinit var receitaList: ArrayList<Receita>
    private lateinit var receitaAdapter: ReceitaAdapter

    // Firebase Database
    private lateinit var receitaDatabase: FirebaseFirestore

    // AdMob
    private lateinit var mAdView: AdView

    private var _binding: FragmentReceitasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentReceitasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MobileAds.initialize(getActivity()!!) {}
        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val adView = AdView(getActivity()!!)
        adView.adSize = AdSize.BANNER

        // AdMob ad unit ID para versão de testes
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        // AdMob ad unit ID para versão de produção
        //adView.adUnitId = "ca-app-pub-2133589299901588/5318016492"

        binding.recyclerviewReceitas.layoutManager = LinearLayoutManager(getActivity())
        binding.recyclerviewReceitas.setHasFixedSize(true)

        /// abre ViewModel associado à Activity
        receitasViewModel = ViewModelProvider(requireActivity()).get(ReceitasViewModel::class.java)
        receitaList = arrayListOf()
        receitaAdapter = ReceitaAdapter(receitaList)
        binding.recyclerviewReceitas.adapter = receitaAdapter
        binding.progressBar.isVisible = true
        binding.errorView.isVisible = false

        // Obtém dados do Firebase
        getReceitasData()

        // float action button pressionado
        // chama tela para adicionar nova receita
        binding.fabAdicionar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_receitas_to_navigation_adicionar)
        }
    }

    private fun validString(string: String): String {
        if(string.isNullOrEmpty())
            return ""
            else return string
    }

    private fun getReceitasData() {
        lifecycleScope.launchWhenCreated {
            val response = try{
                ReceitaRetrofitInstance.api.getReceitas(usuario)
            } catch(err: IOException){
                Log.e("API Call: ", err.toString())
                Toast.makeText(getActivity(), err.toString(), Toast.LENGTH_SHORT).show()
                binding.errorView.isVisible = true
                return@launchWhenCreated
            } catch(err: HttpException){
                Log.e("API Call: ", err.toString())
                Toast.makeText(getActivity(), err.toString(), Toast.LENGTH_SHORT).show()
                binding.errorView.isVisible = true
                return@launchWhenCreated
            }
            binding.progressBar.isVisible = false
            if(response.isSuccessful && response.body() != null){
                val receitas: List<Receita>? = response.body()
                receitas?.forEach { r ->
                    receitaList.add(Receita(r.id, r.usuario, r.nome, r.preparo, r.figura, r.custo, r.ingredientes, Status.CRIADA, validString(r.createdDate), validString(r.createdBy), validString(r.lastModifiedDate), validString(r.lastModifiedBy)))
                }
            } else {
                Log.e("API Call: ", "Erro [getReceitas]")
                Toast.makeText(getActivity(), "Erro [getReceitas]", Toast.LENGTH_SHORT).show()
                binding.errorView.isVisible = true
            }
            binding.progressBar.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}