package br.edu.infnet.receitafacil_swe.receitas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.receitafacil_swe.R
import br.edu.infnet.receitafacil_swe.api.ReceitaApiViewModel
import br.edu.infnet.receitafacil_swe.api.ReceitaApiViewModelFactory
import br.edu.infnet.receitafacil_swe.api.ReceitaRepository
import br.edu.infnet.receitafacil_swe.databinding.FragmentReceitasBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.firestore.*
import java.util.Date

class ReceitasFragment : Fragment() {
    private lateinit var apiModel: ReceitaApiViewModel

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

        // Obtém dados do Firebase
        getReceitasData()

        // float action button pressionado
        // chama tela para adicionar nova receita
        binding.fabAdicionar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_receitas_to_navigation_adicionar)
        }
    }

    private fun getReceitasData() {
//        lateinit var apiModel: ReceitaApiViewModel

        try {
            val receitaRepository = ReceitaRepository()
            val receitaModelFactory = ReceitaApiViewModelFactory(receitaRepository)
            apiModel = ViewModelProvider(this, receitaModelFactory).get(ReceitaApiViewModel::class.java)
            apiModel.getResponse()
        } catch (err: Exception) {
            Toast.makeText(getActivity(), err.toString(), Toast.LENGTH_SHORT).show()
        }

//        apiModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
//            // Completou comunicação com site e recebeu JSON
//            if(response.isSuccessful){
//                receitaList.add(Receita(0,"U1", "N1", "P1", "I1", Date(), "F1"))
////                for(r in response.body()!!.receita){
////                    receitaList.add(Receita(r.id,r.usuario, r.nome, r.preparo, r.ingredientes, r.data_receita, r.figura))
////                }
//                Log.d("API", response.body()?.receita.toString())
//            }
//            else{
//                Log.d("API", response.errorBody().toString())
//                Toast.makeText(getActivity(), response.errorBody().toString(), Toast.LENGTH_SHORT).show()
//            }
//        })

        receitaList.add(Receita(0,"U1", "N1", "P1", apiModel.myResponse.value.toString(), Date(), "F1"))
//        receitaList.add(Receita(0,"U2", "N2", "P2", "I2", Date(), "F2"))
//        receitaList.add(Receita(0,"U3", "N3", "P3", "I3", Date(), "F3"))
//        receitaDatabase = FirebaseFirestore.getInstance()
//        receitaDatabase.collection("Receitas").whereEqualTo("usuario", usuario).orderBy("nome")
//            .addSnapshotListener(object: EventListener<QuerySnapshot>{
//                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?){
//                    if (error != null){
//                        Toast.makeText(getActivity() , error.message, Toast.LENGTH_LONG).show()
//                    }
//                    for (dc: DocumentChange in value?.documentChanges!!){
//                        if (dc.type == DocumentChange.Type.ADDED){
//                            receitaList.add(dc.document.toObject(Receita::class.java))
//                        }
//                    }
//                    receitaAdapter.notifyDataSetChanged()
//                }
//            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}