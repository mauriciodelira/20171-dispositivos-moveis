package com.mauricio.ifpb.pdm.saogastao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class FormularioActivity extends AppCompatActivity {
    public static int REQUEST_TIRAR_FOTO = 1500;
    private EditText etDescricao, etQtde, etValorUnitario;
    private Button btSalvar, btCancelar, btExcluir;
    private Produto produto = null;
    private ImageButton ibAddFoto;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MILHO", "FormularioActivity, onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        try {
            Intent it = getIntent();
            this.produto = (Produto) it.getSerializableExtra("PRODUTO");
            this.posicao = (int) it.getSerializableExtra("POSITION");
        }catch (Exception e){

        }

        this.getViews();
        this.setButtonListeners();

    }

    private void getViews(){
        this.etDescricao = (EditText) findViewById(R.id.etFormularioDescricao);
        this.etQtde = (EditText) findViewById(R.id.etFormularioQtde);
        this.etValorUnitario = (EditText) findViewById(R.id.etFormularioValorUnitario);
        this.ibAddFoto = (ImageButton) findViewById(R.id.ibtnFormularioFoto);
        this.etDescricao.clearFocus();
        this.etQtde.clearFocus();
        this.etValorUnitario.clearFocus();

        this.btCancelar = (Button) findViewById(R.id.btnFormularioCancelar);
        this.btSalvar = (Button) findViewById(R.id.btnFormularioSalvar);
        this.btExcluir = (Button) findViewById(R.id.btnFormularioExcluir);
        this.btExcluir.setVisibility(View.GONE);

        if(this.produto!=null){ // se os produtos existirem, vai setar os campos e exibir o botão de excluir.
            this.etDescricao.setText( this.produto.getDescricao() );
            this.etQtde.setText(String.valueOf( this.produto.getQuantidade()) );
            this.etValorUnitario.setText(String.valueOf( this.produto.getValorUnitario()) );
            this.btExcluir.setVisibility(View.VISIBLE);
            this.posicao = getIntent().getIntExtra("POSICAO", -1);
            if(this.produto.getImgBitmap()!=null)
                this.ibAddFoto.setImageBitmap(this.produto.getImgBitmap());
        }

    }

    private void setButtonListeners(){
        this.btSalvar.setOnClickListener(new ButtonClick());
        this.btCancelar.setOnClickListener(new ButtonClick());
        this.btExcluir.setOnClickListener(new ButtonClick());
        this.ibAddFoto.setOnClickListener(new ButtonClick());
    }

    private class ButtonClick implements View.OnClickListener{
        Intent it = new Intent(FormularioActivity.this, MainActivity.class);

        @Override
        public void onClick(View v) {
//            Se for btSalvar faça salvar, se for btCancelar faça nada, se não for nenhum é excluir (só envia posição)
            if(v.equals(FormularioActivity.this.btSalvar)){
                FormularioActivity.this.produto = this.getProduto();
                Intent it = new Intent();
                it.putExtra("PRODUTO", FormularioActivity.this.produto);
                it.putExtra("POSICAO", FormularioActivity.this.posicao);
                setResult(RESULT_OK, it);
            }else if(v.equals(FormularioActivity.this.btCancelar)){

            }else if(v.equals(FormularioActivity.this.ibAddFoto)) {
                FormularioActivity.this.tirarFotoIntent();
            }else{ // Aqui é o EXCLUIR. Será tratado no MainActivity.
                    Intent it = new Intent();
                    it.putExtra("POSICAO", FormularioActivity.this.posicao);
                    setResult(RESULT_OK, it);
            }

            finish();


//            String strDescr = "";
//            strDescr = FormularioActivity.this.etDescricao.getText()!=null ? FormularioActivity.this.etDescricao.getText().toString() : "";
//
//            float strQtde;
//            strQtde = Float.valueOf( !FormularioActivity.this.etQtde.getText().toString().isEmpty() ?
//                    FormularioActivity.this.etQtde.getText().toString() : "0.0");
//
//
//            CrudProdutos gerenciadorProdutos = CrudProdutos.getInstance(); // Pega instância única dos produtos
//
//            if(v == FormularioActivity.this.btAdicionar){
//                Produto aux = new Produto(strDescr, strQtde, strValorUnit);
//                it.putExtra("PRODUTO", aux);
//                it.putExtra("POSICAO", FormularioActivity.this.posicao);
//                finish();
//            }else
//            if(v == FormularioActivity.this.btCancelar){
//                setResult(RESULT_OK, it);
//                it.putExtra("POSICAO", FormularioActivity.this.posicao);
//                finish();
//            }else
//            if(v == FormularioActivity.this.btExcluir){
//                it.putExtra("PRODUTO", FormularioActivity.this.produto);
//                it.putExtra("POSICAO", FormularioActivity.this.posicao);
//                finish();
//            }else if(v == FormularioActivity.this.btAtualizar){
//                Produto aux = new Produto();
//                aux.setDescricao(strDescr);
//                aux.setQuantidade(strQtde);
//                aux.setValorUnitario(strValorUnit);
//                it.putExtra("PRODUTO", FormularioActivity.this.produto);
//                it.putExtra("POSICAO", FormularioActivity.this.posicao);
//                setResult(RESULT_OK, it);
//                finish();
//            }
        }

        private Produto getProduto(){
            String desc = (FormularioActivity.this.etDescricao.getText()!=null
                ? FormularioActivity.this.etDescricao.getText().toString() : "");

            float valor = Float.valueOf(FormularioActivity.this.etValorUnitario.getText().toString().isEmpty()
                    ? "0.0" : FormularioActivity.this.etValorUnitario.getText().toString());

            float qtd;
            qtd = Float.parseFloat( !FormularioActivity.this.etQtde.getText().toString().isEmpty()
                    ? FormularioActivity.this.etQtde.getText().toString() : "0.0");

            return new Produto(desc, qtd, valor);
        }
    }
    private void tirarFotoIntent(){
        Intent itFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(itFoto.resolveActivity(getPackageManager()) != null){
            Log.i("MILHO","FormularioActivity, itFoto");
            startActivityForResult(itFoto, REQUEST_TIRAR_FOTO);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("ONACTRES", "onActivRes do FormularioActivity");
        if (requestCode == REQUEST_TIRAR_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ibAddFoto.setImageBitmap(imageBitmap);
            FormularioActivity.this.produto.setImgBitmap(imageBitmap);
        }
    }


    @Override
    protected void onPause() {
        Log.i("MILHO","FormularioActivity, onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.i("MILHO","FormularioActivity, onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("MILHO","FormularioActivity, onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.i("MILHO","FormularioActivity, onDestroy");

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.i("MILHO","FormularioActivity, onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("MILHO","FormularioActivity, onRestart");
        super.onRestart();
    }

}
