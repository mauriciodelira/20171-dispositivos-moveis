package com.mauricio.ifpb.pdm.saogastao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOverlay;
import android.widget.Button;
import android.widget.EditText;

import java.text.Normalizer;

public class FormularioActivity extends AppCompatActivity {
    EditText etDescricao, etQtde, etValorUnitario;
    Button btAdicionar, btCancelar, btExcluir, btAtualizar;
    Produto produto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        inte

        Intent it = getIntent();
        this.produto = (Produto) it.getSerializableExtra("PRODUTO");

        this.getViews();
        this.setButtonListeners();

    }

    private void getViews(){
        this.etDescricao = (EditText) findViewById(R.id.etFormularioDescricao);
        this.etQtde = (EditText) findViewById(R.id.etFormularioQtde);
        this.etValorUnitario = (EditText) findViewById(R.id.etFormularioValorUnitario);

        this.etDescricao.clearFocus();
        this.etQtde.clearFocus();
        this.etValorUnitario.clearFocus();

        this.btCancelar = (Button) findViewById(R.id.btnFormularioCancelar);
        this.btAdicionar = (Button) findViewById(R.id.btnFormularioAdicionar);
        this.btAdicionar.setVisibility(View.VISIBLE);
        this.btAtualizar = (Button) findViewById(R.id.btnFormularioAtualizar);
        this.btAtualizar.setVisibility(View.GONE);
        this.btExcluir = (Button) findViewById(R.id.btnFormularioExcluir);
        this.btExcluir.setVisibility(View.GONE);

        if(this.produto!=null){ // se os produtos existirem, vai setar os campos e exibir o botão de excluir.
            this.etDescricao.setText( this.produto.getDescricao() );
            this.etQtde.setText(String.valueOf( this.produto.getQuantidade()) );
            this.etValorUnitario.setText(String.valueOf( this.produto.getValorUnitario()) );
            this.btExcluir.setVisibility(View.VISIBLE);
            this.btAtualizar.setVisibility(View.VISIBLE);
            this.btAdicionar.setVisibility(View.GONE);
        }

    }

    private void setButtonListeners(){
        this.btAdicionar.setOnClickListener(new ButtonClick());
        this.btCancelar.setOnClickListener(new ButtonClick());
        this.btExcluir.setOnClickListener(new ButtonClick());
        this.btAtualizar.setOnClickListener(new ButtonClick());
    }

    private class ButtonClick implements View.OnClickListener{
        Intent it = new Intent(FormularioActivity.this, MainActivity.class);

        @Override
        public void onClick(View v) {
            String strDescr = "";
            strDescr = FormularioActivity.this.etDescricao.getText()!=null ? FormularioActivity.this.etDescricao.getText().toString() : "";

            float strQtde;
            strQtde = Float.valueOf( !FormularioActivity.this.etQtde.getText().toString().isEmpty() ?
                    FormularioActivity.this.etQtde.getText().toString() : "0.0");

            float strValorUnit = Float.valueOf(FormularioActivity.this.etValorUnitario.getText().toString().isEmpty() ? "0.0" : FormularioActivity.this.etValorUnitario.getText().toString());

            CrudProdutos gerenciadorProdutos = CrudProdutos.getInstance(); // Pega instância única dos produtos

            if(v == FormularioActivity.this.btAdicionar){
                Produto aux = new Produto(strDescr, strQtde, strValorUnit);
                it.putExtra("GASTO", aux);
                setResult(MainActivity.RESULT_CREATE_OBJECT, it);
                finish();
            }else
            if(v == FormularioActivity.this.btCancelar){
                setResult(RESULT_OK, it);
                finish();
            }else
            if(v == FormularioActivity.this.btExcluir){
                it.putExtra("GASTO", FormularioActivity.this.produto);

                setResult(MainActivity.RESULT_DELETE_OBJECT, it);
                finish();
            }else if(v == FormularioActivity.this.btAtualizar){
                Produto aux = new Produto();
                aux.setDescricao(strDescr);
                aux.setQuantidade(strQtde);
                aux.setValorUnitario(strValorUnit);
                it.putExtra("GASTO", FormularioActivity.this.produto);
                it.putExtra("NOVOSDADOS", aux);
                setResult(MainActivity.RESULT_UPDATE_OBJECT, it);
                finish();
            }
        }
    }
}
