package com.example.daniel.loginmysql;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by daniel on 09/12/16.
 */

public class Conexao {

    public static String postDados(String urlUsuario, String parametrosUsuarios){
        URL url;
        //conexão
        HttpURLConnection connection = null;


        try {
            //abrir conexão
            url = new URL(urlUsuario);
            connection = (HttpURLConnection)url.openConnection();

            //definindo propriedades

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Context-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(parametrosUsuarios.getBytes().length));

            connection.setRequestProperty("Content-Language" , "pt-BR");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //enviando a informação
            /*
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());

            dataOutputStream.writeBytes(parametrosUsuarios);
            dataOutputStream.flush();
            dataOutputStream.close();
            */

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            outputStreamWriter.write(parametrosUsuarios);
            outputStreamWriter.flush();


            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linha;
            StringBuffer resposta = new StringBuffer();

            while ((linha = bufferedReader.readLine()) != null){
                resposta.append(linha);
                resposta.append('\r');
            }
            bufferedReader.close();

            return resposta.toString();

        }catch (Exception e){
            return  null;
        }finally {

            if(connection != null){
                connection.disconnect();
            }

        }

    }

}
