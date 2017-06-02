package com.mauricio.ifpb.pdm.arrocha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvMaior, tvMenor;
    private EditText etOpcao;
    private Button btEnviar, btReplay;
    private Arrocha jogo;

    // Ao criar a classe, cria o jogo
    public MainActivity(){
        this.jogo = new Arrocha();

    }

    // Ao carregar toda a classe...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvMaior = (TextView) findViewById(R.id.tvMaior);
        this.tvMenor = (TextView) findViewById(R.id.tvMenor);
        this.etOpcao = (EditText) findViewById(R.id.etOpcao);
        this.btReplay = (Button) findViewById(R.id.btReplay);
        this.btReplay.setOnClickListener(new onClickReplay());
        this.btEnviar = (Button) findViewById(R.id.btEscolher);
        this.btEnviar.setOnClickListener(new onClick());


    }



    private void update(){
        this.tvMaior.setText(String.valueOf(this.jogo.getMaior()));
        this.tvMenor.setText(String.valueOf(this.jogo.getMenor()));
        Log.i("ArrochaGame", jogo.toString()+" - escolhido: "+this.etOpcao.getText().toString());


    }

    private class onClickReplay implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            MainActivity.this.jogo = new Arrocha();
            MainActivity.this.etOpcao.clearFocus();
            MainActivity.this.etOpcao.setText("");
            MainActivity.this.btReplay.setVisibility(View.INVISIBLE);
            MainActivity.this.etOpcao.setFocusable(true);
            MainActivity.this.etOpcao.setEnabled(true);
            MainActivity.this.etOpcao.setFocusableInTouchMode(true);
            MainActivity.this.jogo.reset();
            MainActivity.this.update();
        }
    }

    private class onClick implements View.OnClickListener{
        int escolha;

        @Override
        public void onClick(View v) {
            this.escolha = Integer.valueOf(MainActivity.this.etOpcao.getText().toString());
            int resultado = -1;
            resultado = MainActivity.this.jogo.jogar(this.escolha);

            if(resultado == 1){ // Acertou o número secreto -> PERDEU
                Toast.makeText(getApplicationContext(), "Eita... Você acertou o número secreto, então perdeu :(", Toast.LENGTH_LONG).show();

            }else if(resultado == 0){ // isArrochado == true -> VENCEU
                Toast.makeText(getApplicationContext(), "Parabéns, você conseguiu arrochar o número! \\o/", Toast.LENGTH_LONG).show();
                MainActivity.this.etOpcao.setText( String.valueOf(MainActivity.this.jogo.getSecreto() ) );

            }else if(resultado == -2) { // É inválido. Define agora se é maior ou menor -> PERDEU

                if (MainActivity.this.jogo.isMaior(this.escolha)) {
                    Toast.makeText(getApplicationContext(), "Ops, você jogou um número maior que o limite. Você perdeu :(", Toast.LENGTH_LONG).show();

                } else if (MainActivity.this.jogo.isMenor(this.escolha)) {
                    Toast.makeText(getApplicationContext(), "Ops, você jogou um número menor que o limite. Você perdeu :(", Toast.LENGTH_LONG).show();

                }
            }
//            Todos os casos acima exigem o botão replay visível
            if(resultado != -2) {
                MainActivity.this.btReplay.setVisibility(View.VISIBLE);
                MainActivity.this.etOpcao.setFocusable(false);
                MainActivity.this.etOpcao.setEnabled(false);
            }

            MainActivity.this.update();
        }
    }


}
