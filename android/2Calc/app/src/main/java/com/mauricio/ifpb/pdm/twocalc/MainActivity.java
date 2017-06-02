package com.mauricio.ifpb.pdm.twocalc;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView res, simbolo;
    Button btnSoma, btnSubtrai, btnDivide, btnMultiplica;
    double n1=0, n2=0, nRes=0;
    EditText etNum1, etNum2;
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.res = (TextView) findViewById(R.id.tvResultado);
        this.res.setText("Resultado");
        this.res.setTextColor(Color.LTGRAY);
        this.simbolo = (TextView) findViewById(R.id.tvSymbol);

        this.btnSoma = (Button) findViewById(R.id.btSoma);
        this.btnSoma.setOnClickListener(new toSum());

        this.btnSubtrai = (Button) findViewById(R.id.btSubtracao);
        this.btnSubtrai.setOnClickListener(new toSubtract());

        this.btnDivide = (Button) findViewById(R.id.btDivisao);
        this.btnDivide.setOnClickListener(new toDivide());

        this.btnMultiplica = (Button) findViewById(R.id.btMultiplicacao);
        this.btnMultiplica.setOnClickListener(new toMultiply());

    }
    private class toSum implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            MainActivity.this.getNumbers();
            MainActivity.this.res.setText(MainActivity.this.getCalc("+"));
            MainActivity.this.simbolo.setText("+");
        }
    }

    private class toSubtract implements View.OnClickListener{
        public void onClick(View v){
            MainActivity.this.getNumbers();
            MainActivity.this.res.setText(MainActivity.this.getCalc("-"));
            MainActivity.this.simbolo.setText("-");
        }
    }
    private class toMultiply implements View.OnClickListener{
        public void onClick(View v){
            MainActivity.this.getNumbers();
            MainActivity.this.nRes = MainActivity.this.n1*MainActivity.this.n2;
            MainActivity.this.res.setText(MainActivity.this.getCalc("*"));
            MainActivity.this.simbolo.setText("×");
        }
    }
    private class toDivide implements View.OnClickListener{
        public void onClick(View v){
            MainActivity.this.getNumbers();
            MainActivity.this.res.setText(MainActivity.this.getCalc("/"));
            MainActivity.this.simbolo.setText("÷");
        }
    }

    private void getNumbers(){
        MainActivity.this.etNum1 = (EditText) findViewById(R.id.etNum1);
        MainActivity.this.etNum2 = (EditText) findViewById(R.id.etNum2);

        if(MainActivity.this.etNum1.getText().toString()!=null)
            MainActivity.this.n1 = Double.valueOf(MainActivity.this.etNum1.getText().toString());
        if(MainActivity.this.etNum2.getText().toString()!=null)
            MainActivity.this.n2 = Double.valueOf(MainActivity.this.etNum2.getText().toString());
    }

    private String getCalc(String op){
        switch (op){
            case "+":
                MainActivity.this.nRes = MainActivity.this.n1 + MainActivity.this.n2;
                break;
            case "-":
                MainActivity.this.nRes = MainActivity.this.n1 - MainActivity.this.n2;
                break;
            case "*":
                MainActivity.this.nRes = MainActivity.this.n1 * MainActivity.this.n2;
                break;
            case "/":
                MainActivity.this.nRes = MainActivity.this.n1 / MainActivity.this.n2;
                break;
        }
        return String.valueOf(df.format(MainActivity.this.nRes));
    }
}
