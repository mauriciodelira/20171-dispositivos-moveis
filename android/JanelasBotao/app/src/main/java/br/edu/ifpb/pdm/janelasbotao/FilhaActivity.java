package br.edu.ifpb.pdm.janelasbotao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FilhaActivity extends AppCompatActivity {
    private TextView tvFilha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filha);

        tvFilha = (TextView) findViewById(R.id.tvFilhaHello);

//        1. Vai pegar a Intent que chamou a activity
        Intent it = getIntent();

//        2. Vai setar o texto da tvFilha como o texto
//        da intent associado à chave "TEXT"
        tvFilha.setText(it.getStringExtra("TEXT"));
    }

//    3. Voltar ao tocar na tela
    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        3.1. Terminar a atividade.
//        3.2. Parâmetro = código de status, se foi normal, se foi exceção
//        3.3. Constantes RESULT:
//          (padrão) CANCELLED - quando clica no botao de voltar embaixo
//          OK - deu tudo certo
//        finishActivity(RESULT_OK);

//        4. Terminar a atividade com retorno
//        4.1. Criar novo intent para retornar dados
        Intent it = new Intent(FilhaActivity.this, MainActivity.class);
        it.putExtra("RETURN", "Un, dos, trés! Un pasito bailante, MARIA!");

//        4.2. setar o tipo de retorno e o dado (intent) a ser retornado
        setResult(RESULT_OK, it);
        finish();
        return super.onTouchEvent(event);
    }
}
