package com.mauricio.ifpb.pdm.saogastao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {
    EditText etDescricao, etQtde, etValorUnitario;
    Button btAdicionar, btCancelar, btExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        this.getViews();


    }

    private void getViews(){
        this.etDescricao = (EditText) findViewById(R.id.etFormularioDescricao);
        this.etQtde = (EditText) findViewById(R.id.etFormularioQtde);
        this.etValorUnitario = (EditText) findViewById(R.id.etFormularioValorUnitario);

        this.btAdicionar = (Button) findViewById(R.id.btnFormularioAdicionar);
        this.btCancelar = (Button) findViewById(R.id.btnFormularioCancelar);
        this.btExcluir = (Button) findViewById(R.id.btnFormularioExcluir);
    }

    private void setButtonListeners(){
        this.btAdicionar.setOnClickListener(new ButtonClick());
        this.btCancelar.setOnClickListener(new ButtonClick());
        this.btExcluir.setOnClickListener(new ButtonClick());
    }

    private class ButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }
}
