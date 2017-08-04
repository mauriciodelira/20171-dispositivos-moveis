package com.mauricio.ifpb.pdm.saogastao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

// Deve ter: Cadastro (crud), listar produtos, long click = valor total do prod, menu{total, sobre, zerar (crud.zerar();)
// onActivityResult: if RESULT_OK,
//      if(requestCode = FORMULARIO_INSERIR:{ crud.cadastro(obj); atualizarLista();)
//      if(requestCode = FORMULARIO_EDITAR: try{{posicao = data.getIntExtra("POSITION", -1); try="pega alterado, pega posição, set alterados"
//      }catch(Exception e){ this.crud.excluir(posicao) }
// ListaClickListener - onItemClick -> Produto p = cadastro.get(position), putExtra("POSITION", position), putExtra("PROD", prod)

/** MainActivity.java
 * construtor (this.cadastro = new Cadastro();
 * onCreate, onCreateOptionsMenu, onOptionsItemSelected
 * atualizaLista() -> this.cadastro.sort(); ((BaseAdapter)this.lvGastos.getAdapter()).notifyDataSetChanged();
 * onActivityResult(x, y, z){
 *   if(res_code==OK){
 *     if(req_code==INSERIR){ ... }
 *     else if(req_code==EDIT){
 *       try{
 *         editar (gastoOld = gastoNew)
 *       }catch(){
 *         cadastro.excluir(posicao);
 *       }
 *     }
 *   }
 * }
 */

public class MainActivity extends AppCompatActivity {
    private static final int FORMULARIO_INSERIR = 1200;
    private static final int FORMULARIO_EDITAR = 1201;

    private ListView lvProdutos;
    private CrudProdutos gerenciadorProdutos = CrudProdutos.getInstance(); // vai pegar a instância de CRUD de produtos
    private ProdutoAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MILHO", "MainActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getViews();     // vai pegar todas as views renderizadas na main + popular a ListView
        setListViewParams();

        adapter = new ProdutoAdapter(this);
        this.lvProdutos.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new BotaoListener());
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adicionar novo item?", Snackbar.LENGTH_LONG)
                        .setAction("Sim", new SnackbarBotaoClickListener()).show();
            }
        });
        */
    }

//    Pegar views
    private void getViews(){
        this.lvProdutos = (ListView) findViewById(R.id.lvMainProdutos);
        this.lvProdutos.setEmptyView(findViewById(R.id.tvMainEmptyItem));
    }

//    Setar onItemClickListener da ListView
    private void setListViewParams(){
        this.lvProdutos.setOnItemClickListener(new ListaClickListener());
        this.lvProdutos.setOnItemLongClickListener(new ListaLongClickListener());
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
        }
        if(id == R.id.action_orcamento) {
            Log.i("PRODUTOS", "Produtos: "+gerenciadorProdutos.getProdutos());
            Toast.makeText(getApplicationContext(),
                    gerenciadorProdutos.getProdutos().isEmpty() ? "Não há produtos na lista."
                    : "Total: R$"+gerenciadorProdutos.getTotal(), Toast.LENGTH_LONG).show();
        }
        if(id == R.id.action_zerar) {
            if(gerenciadorProdutos.getProdutos().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Sua lista já está vazia!", Toast.LENGTH_SHORT).show();
            }else {
                gerenciadorProdutos.zerar();
                this.atualizarLista();
                Toast.makeText(getApplicationContext(), "Itens zerados!", Toast.LENGTH_SHORT).show();
            }
        }
        if(id == R.id.action_ordenar) {
            AlertDialog.Builder builder;

            final ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
            arrAdapter.add("Descrição");
            arrAdapter.add("Quantidade");
            arrAdapter.add("Valor unitário");
            arrAdapter.add("Valor total");
            arrAdapter.add("Data");

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
            }
            else {
                builder = new AlertDialog.Builder(MainActivity.this);
            }
            builder.setTitle("Ordenar por...");
            builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            // povoa a lista dentro do popup
            builder.setAdapter(arrAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(gerenciadorProdutos.getProdutos().isEmpty() || gerenciadorProdutos.getProdutos().size() <= 1) {
                        Toast.makeText(MainActivity.this, "Não há itens suficientes para ordenar", Toast.LENGTH_SHORT).show();
                    }else {
                        String strName = arrAdapter.getItem(which);
                        if (strName != null) {
                            switch (strName) {
                                case "Descrição":
                                    MainActivity.this.gerenciadorProdutos.sortDesc();
                                    MainActivity.this.atualizarListaCustom();
                                    Toast.makeText(MainActivity.this, "Ordenando alfabeticamente", Toast.LENGTH_SHORT).show();
                                    break;
                                case "Quantidade":
                                    MainActivity.this.gerenciadorProdutos.sortQtd();
                                    MainActivity.this.atualizarListaCustom();
                                    Toast.makeText(MainActivity.this, "Ordenando de menor para maior quantidade", Toast.LENGTH_SHORT).show();
                                    break;
                                case "Valor unitário":
                                    MainActivity.this.gerenciadorProdutos.sortUn();
                                    MainActivity.this.atualizarListaCustom();
                                    Toast.makeText(MainActivity.this, "Ordenando do menor para maior valor unitário", Toast.LENGTH_SHORT).show();
                                    break;
                                case "Valor total":
                                    MainActivity.this.gerenciadorProdutos.sortTot();
                                    MainActivity.this.atualizarListaCustom();
                                    Toast.makeText(MainActivity.this, "Ordenando do menor para maior valor total", Toast.LENGTH_SHORT).show();
                                    break;
                                case "Data":
                                    MainActivity.this.gerenciadorProdutos.sortData();
                                    MainActivity.this.atualizarListaCustom();
                                    Toast.makeText(MainActivity.this, "Ordenando do mais antigo para mais recente", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    MainActivity.this.atualizarLista();
                                    break;
                            }
                        }
                    }
                }
            });

            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //    Povoar a ListView com um adapter customizado
    private void atualizarListaCustom(){
        ((BaseAdapter) this.lvProdutos.getAdapter()).notifyDataSetChanged();
    }
    private void atualizarLista(){
        this.gerenciadorProdutos.sort();
        ((BaseAdapter) this.lvProdutos.getAdapter()).notifyDataSetChanged();
/*
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
*/


    }
//    Single click num item da lista
    private class ListaClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent it = new Intent(MainActivity.this, FormularioActivity.class);
            it.putExtra("PRODUTO", gerenciadorProdutos.getProdutos().get(position));
            it.putExtra("POSICAO", position);
            startActivityForResult(it, FORMULARIO_EDITAR);
        }
    }

//    Long click num item da lista
//    TODO quer que exclua, mas antes aparecendo um popup de confirmação
    private class ListaLongClickListener implements AdapterView.OnItemLongClickListener{
        private AlertDialog ad;
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder ab;
            Produto p = gerenciadorProdutos.get(position);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ab = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
            }
            else {
                ab = new AlertDialog.Builder(MainActivity.this);
            }
            ab.setTitle("Excluir produto")
                .setMessage("Você está prestes a excluir" +
            (p.getQuantidade()==1 ? String.format(Locale.ENGLISH, " %.0f produto ", p.getQuantidade()) : String.format(Locale.ENGLISH, " %.0f produtos ", p.getQuantidade()))
                        + p.getDescricao()+". Continuar?")
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.gerenciadorProdutos.remove(position);
                        MainActivity.this.atualizarLista();
                        ad.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ad.dismiss();
                    }
                });
            ad = ab.create();
            ad.show();
            return true;
            /*
            Produto p = gerenciadorProdutos.getProdutos().get(position);
            String text = String.format("%s: total - R$%s", p.getDescricao(), p.getTotal());
            Toast.makeText(getApplicationContext(), text,Toast.LENGTH_LONG).show();
            return true;
            */
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MILHO", "OnActivityResult da MainActivity");
        Log.i("MILHO", "OnActivityResult. Result Code: "+resultCode+". Request Code: "+requestCode);
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            if(requestCode == FORMULARIO_INSERIR ){
//                Se for inserir, pega o produto da intent e joga na lista, depois atualiza ela.
                Produto prod = (Produto) data.getSerializableExtra("PRODUTO");
                this.gerenciadorProdutos.add(prod);
                this.atualizarLista();
            }
            else if(requestCode == FORMULARIO_EDITAR){
//                Se for editar, pega o produto E POSIÇÃO, pega o original, edita o original e depois atualiza a lista
                int posicao = data.getIntExtra("POSICAO", -1);
                try{
                    Produto alterado = (Produto) data.getSerializableExtra("PRODUTO");
                    Produto original = MainActivity.this.gerenciadorProdutos.get(posicao);
                    original.setQuantidade(alterado.getQuantidade());
                    original.setDescricao(alterado.getDescricao());
                    original.setValorUnitario(alterado.getValorUnitario());
//                    todo original.setImagem(Bitmap);
                    this.atualizarLista();
                }catch (Exception e){
//                    chegou até aqui, não achou a posição, logo, é -1 e deu NullPtrException
                    Log.i("EXCLUIR",this.gerenciadorProdutos.getProdutos().toString());
                    Log.i("EXCLUIR","Excluir: "+this.gerenciadorProdutos.get(posicao).getDescricao());
                    this.gerenciadorProdutos.remove(posicao);
                    this.atualizarLista();
                }

            }
            if (requestCode == RESULT_DELETE_OBJECT) {
                gerenciadorProdutos.remove(retorno1);
            }
            if (requestCode == RESULT_UPDATE_OBJECT) {
                Produto aux = gerenciadorProdutos.findProduto(retorno1.getDescricao());
                Produto novosDados = (Produto) data.getSerializableExtra("NOVOSDADOS");
                if (aux != null) {
                    aux.setQuantidade(novosDados.getQuantidade());
                    aux.setValorUnitario(novosDados.getValorUnitario());
                    aux.setDescricao(novosDados.getDescricao());
                }
            }
        }
    }

    @Override
    protected void onPause() {
        Log.i("MILHO","MainActivity, onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.i("MILHO","MainActivity, onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("MILHO","MainActivity, onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.i("MILHO","MainActivity, onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.i("MILHO","MainActivity, onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("MILHO","MainActivity, onRestart");
        super.onRestart();
    }

    private class BotaoListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(MainActivity.this, FormularioActivity.class);
            startActivityForResult(it, FORMULARIO_INSERIR);
        }
    }
}
