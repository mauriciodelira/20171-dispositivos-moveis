package com.mauricio.ifpb.pdm.servicosexternos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                Uri uri = Uri.parse("tel:83999184287");
                Intent it = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(it);

            }
            if(v.equals(MainActivity.this.btLigar)){
                Log.i("CLICK-BOTAO","onClick - Ligar");
                Uri uri = Uri.parse("tel:83999184287");
                Intent it = new Intent(Intent.ACTION_CALL, uri);
                int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);
                if(permission == PackageManager.PERMISSION_GRANTED){
                    startActivity(it);
                }else
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Permissão negada.\nVerifique suas configurações", Toast.LENGTH_LONG).show();

            }
            if(v.equals(MainActivity.this.btEmail)){
                Log.i("CLICK-BOTAO","onClick - Enviar Email");
                Uri uri = Uri.parse("mailto:mauriciodlira@gmail.com");
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra(Intent.EXTRA_SUBJECT, "Do que você quer falar?");
                it.putExtra(Intent.EXTRA_TEXT, "Me diga que te ouço");
                startActivity(Intent.createChooser(it, "Enviar email"));
            }
            if(v.equals(MainActivity.this.btSMS)){
                Log.i("CLICK-BOTAO","onClick - Enviar SMS");
                Intent it = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:83999184287"));
                it.putExtra("sms_body", "Quer falar comigo?");
                startActivity(it);
            }
            if(v.equals(MainActivity.this.btCompartilharTexto)){
                Log.i("CLICK-BOTAO","onClick - Compartilhar");
                Intent it = new Intent(.ACTION_SEND);
                // Intent.setType = mime tipes.
                it.setType("text/plain");
                it.putExtra(Intent.EXTRA_TEXT, "Você é lindx de todo o coração do mundo. <3");
                startActivity(Intent.createChooser(it, "Compartilhando seu texto"));

            }
            if(v.equals(MainActivity.this.btPonto)){
                Log.i("CLICK-BOTAO","onClick - Ponto no mapa");
                Uri uri = Uri.parse("gep:-8.0000000, -35.0000000");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
            if(v.equals(MainActivity.this.btRota)){
                Log.i("CLICK-BOTAO","onClick - Rota no mapa");
                String origem = "-7.0000394,-63333332"
                String destino = "-1.0000394,-53333332"
            }

            if(v.equals(MainActivity.this.btYoutube)){
                Log.i("CLICK-BOTAO","onClick - Youtube");
                Uri uri = Uri.parse("bmvd")

            }
            else{
                Log.i("CLICK-BOTAO","onClick - Foto");

            }

        }
    }

}
