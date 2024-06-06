package br.edu.infnet.receitafacil_microservices

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.infnet.receitafacil_microservices.databinding.FragmentAdicionarBinding
import br.edu.infnet.receitafacil_microservices.receitas.ReceitasViewModel
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import br.edu.infnet.receitafacil_microservices.api.ReceitaRetrofitInstance
import br.edu.infnet.receitafacil_microservices.api.TheMealDBApiViewModel
import br.edu.infnet.receitafacil_microservices.api.TheMealDBApiViewModelFactory
import br.edu.infnet.receitafacil_microservices.api.TheMealDBRepository
import br.edu.infnet.receitafacil_microservices.model.Receita
import br.edu.infnet.receitafacil_microservices.model.usuario
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import retrofit2.HttpException
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AdicionarFragment : Fragment() {

    lateinit var receitasViewModel: ReceitasViewModel

    // Firebase Database
    private lateinit var receitaDatabase: FirebaseFirestore

    private var _binding: FragmentAdicionarBinding? = null
    private val binding get() = _binding!!

    // variáveis de controle para câmera
    private var TIROU_FOTO = 0
    val RESULT_OK = 1
    val REQUEST_CODE = 200
    private lateinit var local_figura: String

    // Receita API
    private lateinit var apiModel: TheMealDBApiViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdicionarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar?.isVisible = false

        binding.btnAuto.setOnClickListener{
            binding.progressBar?.isVisible = true
            FotoApi()
            binding.progressBar?.isVisible = false
        }

        // tira foto ao pressionar imagem
        binding.imgFoto.setOnClickListener{

            // Verifica se existe câmera no dispositivo
            val pm = getActivity()!!.getPackageManager()
            if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, REQUEST_CODE)
            }
            else {
                Toast.makeText(getActivity() , "Camera não disponível!", Toast.LENGTH_SHORT).show()
            }
        }

        // botão adicionar pressionado
        binding.btnAdicionar.setOnClickListener {
            if (binding.txtNome.text.toString() == "" || binding.txtPreparo.text.toString() == ""){
                Toast.makeText(getActivity() , "Receita ruim!", Toast.LENGTH_SHORT).show()
            }
            else{
                binding.btnAdicionar.isVisible = false

                // Receita com foto
                if (TIROU_FOTO == 1){
                    binding.progressBar?.isVisible = true

                    // Nome de arquivo com data e hora atual
                    val agora = LocalDateTime.now()
                    val arquivo = "${agora}.jpg"

                    // Bitmap
                    val bitmap = (binding.imgFoto.drawable as BitmapDrawable).bitmap
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

                    // Pegando a referência do serviço
                    val storageRef = FirebaseStorage.getInstance().reference
                    val arquivoRef = storageRef.child(arquivo)

                    // Upload de imagem
                    var uploadTask = arquivoRef.putBytes(baos.toByteArray())

                    uploadTask.addOnFailureListener { taskSnapshot ->
                        //Toast.makeText(getActivity() , taskSnapshot.message, Toast.LENGTH_LONG).show()
                    }.addOnSuccessListener { taskSnapshot ->
                        //Toast.makeText(getActivity() , taskSnapshot.error, Toast.LENGTH_LONG).show()
                    }

                    // Grava receita
                    val urlTask = uploadTask.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        arquivoRef.downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            // Upload de imagem bem sucedido
                            val downloadUri = task.result
                            local_figura = downloadUri.toString()
                            gravarReceita(local_figura)

                            binding.progressBar?.isVisible = false

                            /// abre ViewModel associado à Activity
                            receitasViewModel = ViewModelProvider(requireActivity()).get(ReceitasViewModel::class.java)
                            findNavController().navigate(R.id.action_nav_adicionar_to_nav_home)
                        } else {
                            binding.progressBar?.isVisible = false
                            Toast.makeText(getActivity() , "Erro ao salvar receita!", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                // Receita sem foto
                else{
                    local_figura = "https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_0.png?alt=media&token=af33f298-dbcc-40ea-aa8c-ed1430a46a57"
                    gravarReceita(local_figura)

                    /// abre ViewModel associado à Activity
                    receitasViewModel = ViewModelProvider(requireActivity()).get(ReceitasViewModel::class.java)
                    findNavController().navigate(R.id.action_nav_adicionar_to_nav_home)
                }
            }
        }
    }

    private fun gravarReceita(figura: String){
        val agora = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        var total = 0
        lifecycleScope.launchWhenCreated {
            total = try {
                ReceitaRetrofitInstance.api.getTotalReceitas()
            } catch(err: IOException){
                Log.e("API Call: ", err.toString())
                Toast.makeText(getActivity(), err.toString(), Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            } catch(err: HttpException){
                Log.e("API Call: ", err.toString())
                Toast.makeText(getActivity(), err.toString(), Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            }
            Log.d("API Total", total.toString())
        }
        lifecycleScope.launchWhenCreated {
            val response = try{
//                val total = ReceitaRetrofitInstance.api.getTotalReceitas().toString().toInt()
//                Log.d("API Total", total.toString())
                val receita = Receita(total, usuario, binding.txtNome.text.toString(), binding.txtPreparo.text.toString(), binding.txtIngredientes.text.toString(), agora, figura)
//                val receita = Receita((100..99999).random(), usuario, binding.txtNome.text.toString(), binding.txtPreparo.text.toString(), binding.txtIngredientes.text.toString(), agora, figura)
                ReceitaRetrofitInstance.api.newReceita(receita)
                Toast.makeText(getActivity() , "Receita adicionada!", Toast.LENGTH_SHORT).show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // adiciona foto tirada
        if (resultCode == -RESULT_OK && requestCode == REQUEST_CODE) {
            binding.imgFoto.setImageDrawable(bitmapToDrawable(data?.extras?.get("data") as Bitmap))
            TIROU_FOTO = 1
        }
        else{
            Toast.makeText(getActivity() , "Erro ao tirar foto!", Toast.LENGTH_SHORT).show()
        }
    }

    // converte BitMap para Drawable
    private fun bitmapToDrawable(bitmap:Bitmap):BitmapDrawable{
        return BitmapDrawable(resources,bitmap)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Obtem foto aleatória
    fun FotoApi() {
        // Receitas API
        val theMealDBRepository = TheMealDBRepository()
        val apiModelFactory = TheMealDBApiViewModelFactory(theMealDBRepository)
        apiModel = ViewModelProvider(this, apiModelFactory).get(TheMealDBApiViewModel::class.java)
        apiModel.getResponse()

        apiModel.myResponse.observe(this, Observer { response ->
            // Completou comunicação com site e recebeu JSON
            if(response.isSuccessful){
                val img = response.body()?.meal?.get(0)?.strMealThumb
                Picasso.get().load(img).placeholder(R.drawable.ic_wait).into(binding.imgFoto);
                TIROU_FOTO = 1
                Log.d("API", response.body()?.meal.toString())
            }
            else{
                Log.d("API", response.errorBody().toString())
                Toast.makeText(getActivity() , response.errorBody().toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}