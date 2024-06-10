package br.edu.infnet.receitafacil_microservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import br.edu.infnet.receitafacil_microservices.databinding.ActivityLoginBinding
import br.edu.infnet.receitafacil_microservices.model.usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

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
}