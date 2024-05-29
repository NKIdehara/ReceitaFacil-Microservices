package br.edu.infnet.receitafacil_swe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import br.edu.infnet.receitafacil_swe.databinding.ActivityLoginBinding
import br.edu.infnet.receitafacil_swe.receitas.usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    // Firebase Database
    private lateinit var receitaDatabase: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicia Firebase Auth
            auth = Firebase.auth
    }

    fun Login(view: View) {
        val email = binding.txtEmail.text.toString()
        val password = binding.txtSenha.text.toString()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(applicationContext, "Campos não podem ser vazios", Toast.LENGTH_SHORT).show()
        }
        else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // login bem sucedido
                    usuario = Firebase.auth.currentUser?.uid.toString()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            // Erro
            }.addOnFailureListener { exception ->
                if (exception.message!!.contains("INVALID_LOGIN", ignoreCase = true)) {
                    Toast.makeText(applicationContext, "Usuário ou senha inválido(a)!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, exception.message, Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    fun NovoLogin(view: View) {
        val email = binding.txtEmail.text.toString()
        val password = binding.txtSenha.text.toString()

        // Verifica campos de email / senha
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(applicationContext, "Campos não podem ser vazios",Toast.LENGTH_SHORT).show()
        }
        else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Novo usuário
                    Toast.makeText(applicationContext, "Novo usuário registrado com sucesso!",Toast.LENGTH_LONG).show()
                    usuario = Firebase.auth.currentUser?.uid.toString()
                    CriaUsuario(usuario)

                    // Cria lista inicial de receitas para novo usuário
                    CriaReceitasBonus1()
                    CriaReceitasBonus2()
                    CriaReceitasBonus3()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                // Erro
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun CriaUsuario(userUID: String){
        val usuario = hashMapOf(
            "nome" to "Usuário Android",
            "endereco" to "Rua do Android, S/N",
            "tipoAcesso" to 2
        )
        receitaDatabase = FirebaseFirestore.getInstance()
        receitaDatabase.collection("Usuarios").document(userUID).set(usuario)
    }
    private fun CriaReceitasBonus1(){
        val agora = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        val receitas_bonus = hashMapOf(
            "dataReceita" to agora,
            "usuario" to usuario,
            "figura" to "https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_1.png?alt=media&token=30015b83-a563-4bc8-8e90-8f92e40f19c3",
            "nome" to "Arroz",
            "ingredientes" to "- 1 xícara de arroz\n- 1/2 colher de sal",
            "preparo" to "Lavar o arroz; colocar sal; cozinhar por 30 minutos."
        )
        receitaDatabase = FirebaseFirestore.getInstance()
        receitaDatabase.collection("Receitas").document().set(receitas_bonus)
    }
    private fun CriaReceitasBonus2(){
        val agora = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        val receitas_bonus = hashMapOf(
            "dataReceita" to agora,
            "usuario" to usuario,
            "figura" to "https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_2.png?alt=media&token=6ace9d66-e77c-420c-a85f-1d6841acb6f0",
            "nome" to "Carne",
            "ingredientes" to "- 1 bife\n- tempero a gosto",
            "preparo" to "Temperar a carne; assar até dourar."
        )
        receitaDatabase = FirebaseFirestore.getInstance()
        receitaDatabase.collection("Receitas").document().set(receitas_bonus)
    }
    private fun CriaReceitasBonus3(){
        val agora = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        val receitas_bonus = hashMapOf(
            "dataReceita" to agora,
            "usuario" to usuario,
            "figura" to "https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_3.png?alt=media&token=47038466-478b-423a-8e11-5c74f88f4d2a",
            "nome" to "Macarrão",
            "ingredientes" to "- 500g de macarrão\n- 1 lata de molho de tomate",
            "preparo" to "Cozinhar o macarrão; escorrer e colocar o molho."
        )
        receitaDatabase = FirebaseFirestore.getInstance()
        receitaDatabase.collection("Receitas").document().set(receitas_bonus)
    }
}