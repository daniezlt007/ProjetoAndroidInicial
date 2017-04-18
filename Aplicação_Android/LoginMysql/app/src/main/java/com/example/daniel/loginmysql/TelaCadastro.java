package com.example.daniel.loginmysql;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {

    EditText editTextNome,editTextEmail,editTextSenha;
    Button btCancelar, btSalvar;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        editTextNome = (EditText)findViewById(R.id.editTextNome);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextSenha = (EditText)findViewById(R.id.editTextSenha);
        btCancelar = (Button)findViewById(R.id.btCancelar);
        btSalvar = (Button)findViewById(R.id.btSalvar);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    String nome = editTextNome.getText().toString();
                    String email = editTextEmail.getText().toString();
                    String senha = editTextSenha.getText().toString();

                    if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio!", Toast.LENGTH_LONG).show();
                    }else {
                        url = "http://seu_ip/loginAndroid/registrar.php";

                        parametros = "nome="+nome+ "&email=" + email + "&senha=" +senha;
                        ;
                        new SolicitaDados().execute(url);
                    }
                } else {
                    // display error
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String resultado) {

            if(resultado.contains("Email_erro")) {
                Toast.makeText(getApplicationContext(), "Email já está cadastrado!", Toast.LENGTH_LONG).show();
            }else if(resultado.contains("registro_ok")){
                Toast.makeText(getApplicationContext(), "Usuário salvo com sucesso!", Toast.LENGTH_LONG).show();
                Intent abreInicio = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivity(abreInicio);
            }else{
                Toast.makeText(getApplicationContext(), "Ocorreu um erro!", Toast.LENGTH_LONG).show();
            }
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
