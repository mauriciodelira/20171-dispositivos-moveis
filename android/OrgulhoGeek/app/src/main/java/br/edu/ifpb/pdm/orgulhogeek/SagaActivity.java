package br.edu.ifpb.pdm.orgulhogeek;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class SagaActivity extends AppCompatActivity {
    private Saga saga;
    private ImageView ivPoster;
    private TextView tvTitulo, tvDescricao;
    private ListView lvComentarios;
    private RatingBar ratingBar;
    private Button btComentar;
    private EditText etComentario;
    static boolean isFullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saga);
        isFullScreen = false;
        this.ivPoster = (ImageView) findViewById(R.id.ivSagaPoster);
        this.tvTitulo = (TextView) findViewById(R.id.tvSagaTitulo);
        this.tvDescricao = (TextView) findViewById(R.id.tvSagaDescricao);
        this.lvComentarios = (ListView) findViewById(R.id.lvSagaComentarios);
        this.ratingBar = (RatingBar) findViewById(R.id.rbSagaRating);
        this.btComentar = (Button) findViewById(R.id.btSagaComentarioEnviar);
        this.etComentario = (EditText) findViewById(R.id.etSagaComentario);
        this.etComentario.clearFocus();

        Intent it = getIntent();
        this.saga = (Saga) it.getSerializableExtra("SAGA");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.ivPoster.setImageDrawable(this.getDrawable(this.saga.getImageLocal()));
        }else
            this.ivPoster.setImageResource(this.saga.getImageLocal());

        this.ivPoster.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(isFullScreen){
                    isFullScreen = false;
                    SagaActivity.this.ivPoster.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    SagaActivity.this.ivPoster.setAdjustViewBounds(true);
                }else {
                    isFullScreen = true;
                    SagaActivity.this.ivPoster.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    SagaActivity.this.ivPoster.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        this.tvTitulo.setText(this.saga.getName());
        this.tvDescricao.setText(this.saga.getDescription());
        this.tvDescricao.setMovementMethod(new ScrollingMovementMethod());
        this.ratingBar.setRating(this.saga.getRating());

        this.refreshComentarios();

        this.btComentar.setOnClickListener(new OnClickComentar());

    }

    private class OnClickComentar implements View.OnClickListener {
        String comentario;
        @Override
        public void onClick(View v) {
            this.comentario = SagaActivity.this.etComentario.getText().toString();
            SagaActivity.this.saga.addComentario(this.comentario);
            SagaActivity.this.refreshComentarios();
            SagaActivity.this.etComentario.clearFocus();
            SagaActivity.this.etComentario.setHint("Dê seu comentário!");
        }
    }

    private void refreshComentarios() {
        CommentsAdapter adapter = new CommentsAdapter(SagaActivity.this, this.saga.getComentarios());
        lvComentarios.setAdapter(adapter);

    }
}
