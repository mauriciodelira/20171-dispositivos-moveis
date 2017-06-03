package com.mauricio.ifpb.pdm.servicosexternos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btBrowser, btLigar, btDiscar, btEmail, btSMS, btCompartilharTexto, btPonto, btRota, btYoutube, btFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViews();
        this.setViewsClick();

    }

    private void findViews(){
        btBrowser = (Button) findViewById(R.id.btBrowser);
        btLigar = (Button) findViewById(R.id.btLigar);
        btDiscar = (Button) findViewById(R.id.btDiscar);
        btEmail = (Button) findViewById(R.id.btEmail);
        btSMS = (Button) findViewById(R.id.btSMS);
        btCompartilharTexto = (Button) findViewById(R.id.btCompartilharTexto);
        btPonto = (Button) findViewById(R.id.btPontoMapa);
        btRota = (Button) findViewById(R.id.btRota);
        btYoutube = (Button) findViewById(R.id.btYoutube);
        btFoto = (Button) findViewById(R.id.btFoto);

    }

    private void setViewsClick(){
        /*
        btBrowser.setOnClickListener(new onClickBrowser());
        btLigar.setOnClickListener(new onClickLigar());
        btDiscar.setOnClickListener(new onClickDiscar());
        btSMS.setOnClickListener(new onClickSMS());
        btEmail.setOnClickListener(new onClickEmail());
        btPonto.setOnClickListener(new onClickPonto());
        btRota.setOnClickListener(new onClickRota());
        btYoutube.setOnClickListener(new onClickYoutube());
        btFoto.setOnClickListener(new onClickFoto());
        btCompartilharTexto.setOnClickListener(new onClickTexto());
        */

        btBrowser.setOnClickListener(new onClickBotao());
        btLigar.setOnClickListener(new onClickBotao());
        btDiscar.setOnClickListener(new onClickBotao());
        btSMS.setOnClickListener(new onClickBotao());
        btEmail.setOnClickListener(new onClickBotao());
        btPonto.setOnClickListener(new onClickBotao());
        btRota.setOnClickListener(new onClickBotao());
        btYoutube.setOnClickListener(new onClickBotao());
        btFoto.setOnClickListener(new onClickBotao());
        btCompartilharTexto.setOnClickListener(new onClickBotao());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("START-INFO","MainActivity - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("START-INFO","MainActivity - onResume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("START-INFO","MainActivity - onStop");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("START-INFO","MainActivity - onPause");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("START-INFO","MainActivity - onDestroy");

    }

    private class onLongClickBotao implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    private class onClickBotao implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            if(v.equals(MainActivity.this.btBrowser)){
                Log.i("CLICK-BOTAO","onClick - Browser");
                Uri uri = Uri.parse("http://pdm.valeriacavalcanti.com.br");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);

            }

            if(v.equals(MainActivity.this.btDiscar)){
                Log.i("CLICK-BOTAO","onClick - Discar");

            }
            if(v.equals(MainActivity.this.btLigar)){
                Log.i("CLICK-BOTAO","onClick - Ligar");

            }
            if(v.equals(MainActivity.this.btEmail)){
                Log.i("CLICK-BOTAO","onClick - Enviar Email");

            }
            if(v.equals(MainActivity.this.btSMS)){
                Log.i("CLICK-BOTAO","onClick - Enviar SMS");

            }
            if(v.equals(MainActivity.this.btCompartilharTexto)){
                Log.i("CLICK-BOTAO","onClick - Compartilhar");

            }
            if(v.equals(MainActivity.this.btPonto)){
                Log.i("CLICK-BOTAO","onClick - Ponto no mapa");

            }
            if(v.equals(MainActivity.this.btRota)){
                Log.i("CLICK-BOTAO","onClick - Rota no mapa");

            }
            if(v.equals(MainActivity.this.btYoutube)){
                Log.i("CLICK-BOTAO","onClick - Youtube");

            }
            else{
                Log.i("CLICK-BOTAO","onClick - Foto");

            }

        }
    }

}
