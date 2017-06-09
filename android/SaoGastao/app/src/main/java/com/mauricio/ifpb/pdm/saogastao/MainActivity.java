package com.mauricio.ifpb.pdm.saogastao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int FORMULARIO = 1200;
    private ListView lvProdutos;
    private TextView tvListaVazias;
    private ProdutoAdapter adapter;
    private CrudProdutos gerenciadorProdutos = CrudProdutos.getInstance(); // vai pegar a instância de CRUD de produtos
    private List<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getViews();     // vai pegar todas as views renderizadas na main + popular a ListView
        setListViewParams();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adicionar novo item?", Snackbar.LENGTH_LONG)
                        .setAction("Sim", new SnackbarBotaoClickListener()).show();
            }
        });
    }

//    Pegar views
    private void getViews(){
        this.lvProdutos = (ListView) findViewById(R.id.lvMainProdutos);
        this.lvProdutos.setEmptyView(findViewById(R.id.tvMainEmptyItem));
        this.popularLista();
    }

//    Setar onItemClickListener da ListView
    private void setListViewParams(){
        // a ideia é setar aqui o onItemClickListener e o adapter, se tu tiver tempo
        this.lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this, FormularioActivity.class);
                it.putExtra("PRODUTO", gerenciadorProdutos.getProdutos().get(position));

                startActivityForResult(it, FORMULARIO);
            }
        });

        this.lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Produto p = gerenciadorProdutos.getProdutos().get(position);
                String text = String.format("%s: total - R$%s", p.getDescricao(), p.getTotal());
                Toast.makeText(getApplicationContext(), text,Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

//    Criar o menu lá de cima
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    Setar o "onclick" de cada item do menu lá de cima
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sobre) {
            Toast.makeText(getApplicationContext(), "Maurício de Lira @ IFPB", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.action_orcamento) {
            Log.i("PRODUTOS", "Produtos: "+gerenciadorProdutos.getProdutos());
            Toast.makeText(getApplicationContext(),
                    gerenciadorProdutos.getProdutos().isEmpty() ? "Não há produtos na lista."
                    : "Total até então: "+gerenciadorProdutos.getTotal(), Toast.LENGTH_LONG).show();
            return true;
        }
        if(id == R.id.action_zerar) {
            gerenciadorProdutos.zerar();
            this.popularLista();
            Toast.makeText(getApplicationContext(), "Itens zerados!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    Setar o onClick da snackbar que aparece ao clicar no FAB
    private class SnackbarBotaoClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent it = new Intent(MainActivity.this, FormularioActivity.class);
            startActivityForResult(it, FORMULARIO);
        }
    }

//    Povoar a ListView com um adapter customizado
    private void popularLista(){
        List<Produto> tempProdutos = gerenciadorProdutos.getProdutos();

        if(this.listaProdutos == null)
            this.listaProdutos = new ArrayList<Produto>();

        this.listaProdutos.clear();
        this.listaProdutos.addAll(tempProdutos);

        if (this.adapter == null) {
            Log.i("POPULANDO","Adapter nulo");
            this.adapter = new ProdutoAdapter(MainActivity.this, this.listaProdutos);
            this.lvProdutos.setAdapter(adapter);
        } else {
            this.adapter.refreshList(tempProdutos);
            Log.i("POPULANDO","Produtos: "+gerenciadorProdutos.getProdutos().toString());
            Log.i("POPULANDO","ListaProdutos: "+this.listaProdutos.toString());
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MILHO", "OnActivityResult. Result Code: "+resultCode+". Request Code: "+requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) this.popularLista();
    }

    @Override
    protected void onPause() {
        Log.i("MILHO","onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.i("MILHO","onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("MILHO","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.i("MILHO","onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.i("MILHO","onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("MILHO","onRestart");
        super.onRestart();
    }
}
