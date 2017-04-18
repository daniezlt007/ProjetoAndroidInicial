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
import android.widget.TextView;
import android.widget.Toast;

public class TelaLogin extends AppCompatActivity {

    EditText txtEmail, txtSenha;
    Button btLogar;
    TextView txtCadastro;
    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtSenha = (EditText)findViewById(R.id.txtSenha);
        btLogar = (Button)findViewById(R.id.btnLogar);
        txtCadastro = (TextView)findViewById(R.id.txtCadastro);

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreCadastro = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(abreCadastro);
            }
        });

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    String email = txtEmail.getText().toString();
                    String senha = txtSenha.getText().toString();

                    if(email.isEmpty() || senha.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Todos os campos devem estar preenchidos!", Toast.LENGTH_LONG).show();
                    }else {
                        url = "http://seu_ip/loginAndroid/logar.php";


                        parametros = "email=" + email + "&senha=" +senha;

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
            if(resultado.contains("login_ok,")){

                String[] dados = resultado.split(",");

                //txtEmail.setText(dados[0] + " - " + dados[1] + " + " + dados[2]);


                Intent abreInicio = new Intent(TelaLogin.this, TelaInicial.class);
                abreInicio.putExtra("nome" , dados[1]);
                abreInicio.putExtra("id" , dados[2]);

                startActivity(abreInicio);
            }else{
                Toast.makeText(getApplicationContext(), "Usuário ou Senha inválidos!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
