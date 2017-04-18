package com.example.daniel.loginmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaInicial extends AppCompatActivity {

    TextView textViewId, textViewNome;
    String nomeUsuario, idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        textViewNome = (TextView)findViewById(R.id.textViewNome);
        textViewId = (TextView)findViewById(R.id.textViewId);

        nomeUsuario = getIntent().getExtras().getString("nome");
        idUsuario = getIntent().getExtras().getString("id");

        textViewNome.setText(nomeUsuario);
        textViewId.setText(idUsuario);

    }
}
