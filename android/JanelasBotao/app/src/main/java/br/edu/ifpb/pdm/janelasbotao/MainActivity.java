package br.edu.ifpb.pdm.janelasbotao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btMain;
    private static int qtd = 0;

    public MainActivity(){
        Log.i("ORDEM","MainActivity - Construtor");
    }

//    4.3 Criando variável global estática
    private final static int FILHA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("ORDEM","MainActivity - OnCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMain = (Button) findViewById(R.id.btMainFilha);
        btMain.setOnClickListener(new OnClickBtMain());
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ORDEM","MainActivity - OnStart()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ORDEM","MainActivity - OnRestart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ORDEM","MainActivity - OnResume()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ORDEM","MainActivity - OnStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ORDEM","MainActivity - OnDestroy()");

    }

    private class OnClickBtMain implements View.OnClickListener{
        @Override
        public void onClick(View v) {

//            1. Intent precisa de dois parâmetros: contexto atual (view atual)
//            e a View que você deseja ir (em formato de .class)

            Intent it = new Intent(MainActivity.this, FilhaActivity.class);

//            2. Acrescenta parâmetros (CHAVE, VALOR)
            MainActivity.this.qtd += 1;
            it.putExtra("CLICKS", qtd);

            it.putExtra("TEXT", "Tá funcionando");

//            3. Ao clicar, vai iniciar a intent (no caso, chamar a nova activity
//            4.1. startActivity inicia sem esperar retorno
//            startActivity(it);

//            4.2 startActivityForResult inicia ESPERANDO um retorno
            startActivityForResult(it, FILHA);
        }
    }

//    5. Recebe o retorno de uma activity
//    Parametros:
//      requestCode - se foi OK ou CANCELLED)
//      resultCode - de quem veio o resultado (static final FILHA ou outro código)
//      data - dados
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("ORDEM","MainActivity - OnActivityResult()");


        if(requestCode == RESULT_OK){
            if(resultCode == FILHA){
                Log.i("JANELA", "Retorno da Filha: "+data.getStringExtra("RETURN"));
            }else
                Log.i("JANELA","Retorno de outra atividade.");
        }else
            Log.i("JANELA", "Não teve retorno.");

        super.onActivityResult(requestCode, resultCode, data);
    }
}
