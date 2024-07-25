package br.edu.infnet.receitafacil_microservices

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.edu.infnet.receitafacil_microservices.api.ReceitaRetrofitInstance
import br.edu.infnet.receitafacil_microservices.databinding.FragmentAtualizarBinding
import br.edu.infnet.receitafacil_microservices.model.Receita
import br.edu.infnet.receitafacil_microservices.receitas.ReceitasViewModel
import br.edu.infnet.receitafacil_microservices.model.usuario
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import retrofit2.HttpException
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AtualizarFragment : Fragment() {

    private val args by navArgs<AtualizarFragmentArgs>()

    lateinit var receitasViewModel: ReceitasViewModel
    private var _binding: FragmentAtualizarBinding? = null
    private val binding get() = _binding!!

    // Firebase Database
    private lateinit var receitaDatabase: FirebaseFirestore
    private val receitaRef = Firebase.firestore.collection("Receitas")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAtualizarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.progressBar?.isVisible = false

        // *** bloqueio para edição ***
        var ingredientes: String = ""
        args.selecionado.ingredientes?.forEach { i -> ingredientes += "- " + i.quantidade + " " + i.medida.nome + " de " + i.item.descricao + "\n" }
        binding.txtIngredientesAtualizar.setText(ingredientes)
//        binding.txtNomeAtualizar.isEnabled = false
        binding.txtIngredientesAtualizar.isEnabled = false
//        binding.txtPreparoAtualizar.isEnabled = false
//        binding.btnAtualizar.isEnabled = false

        // atualiza campos com dados existentes
        // dados recebidos como argumento de ReceitaAdapter
        Picasso.get().load(args.selecionado.figura).placeholder(R.drawable.ic_wait).into(binding.imgFotoAtualizar);
        binding.txtNomeAtualizar.setText(args.selecionado.nome)
        //binding.txtIngredientesAtualizar.setText(args.selecionado.ingredientes)
        binding.txtPreparoAtualizar.setText(args.selecionado.preparo)

        // botão atualizar
        binding.btnAtualizar.setOnClickListener {
            AtualizarReceita()
            receitasViewModel = ViewModelProvider(requireActivity()).get(ReceitasViewModel::class.java)
            findNavController().navigate(R.id.action_nav_atualizar_to_nav_home)
        }

        // botão apagar
        binding.btnApagar.setOnClickListener {
            ApagarReceita()
            receitasViewModel = ViewModelProvider(requireActivity()).get(ReceitasViewModel::class.java)
            findNavController().navigate(R.id.action_nav_atualizar_to_nav_home)
        }

        return root
    }

    private fun AtualizarReceita() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val agora = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
            lifecycleScope.launchWhenCreated {
                val response = try{
                    val receita = Receita(args.selecionado.id, usuario, binding.txtNomeAtualizar.text.toString(), binding.txtPreparoAtualizar.text.toString(), args.selecionado.figura, args.selecionado.custo, null, args.selecionado.createdDate, args.selecionado.createdBy, args.selecionado.lastModifiedDate, args.selecionado.lastModifiedBy)
                    ReceitaRetrofitInstance.api.editReceita(args.selecionado.id, receita)
                    Toast.makeText(getActivity() , "Receita atualizada!", Toast.LENGTH_SHORT).show()
                } catch(err: IOException){
                    Log.e("API Call: ", err.toString())
                    Toast.makeText(getActivity(), err.toString(), Toast.LENGTH_SHORT).show()
                    return@launchWhenCreated
                } catch(err: HttpException){
                    Log.e("API Call: ", err.toString())
                    Toast.makeText(getActivity(), err.toString(), Toast.LENGTH_SHORT).show()
                    return@launchWhenCreated
                }
            }
        }
        catch (error: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(getActivity(), error.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun ApagarReceita() = CoroutineScope(Dispatchers.IO).launch {
        lifecycleScope.launchWhenCreated {
            val response = try{
                ReceitaRetrofitInstance.api.deleteReceita(args.selecionado.id)
                Toast.makeText(getActivity() , "Receita apagada!", Toast.LENGTH_SHORT).show()
            } catch(err: IOException){
                Log.e("API Call: ", err.toString())
                Toast.makeText(getActivity(), err.toString(), Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            } catch(err: HttpException){
                Log.e("API Call: ", err.toString())
                Toast.makeText(getActivity(), err.toString(), Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            }
        }
    }
}