package tesis.cuentacuentos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stuffaboutcode.bluedot.R;

import java.util.Random;

import tesis.cuentacuentos.bluetooth.Inicio_Sesion;

public class Historia_Actividad_Pregunta extends AppCompatActivity {


    TextView txtPreguntaCodigo;
    TextView txtPreguntaTitulo;
    TextView txtPreguntaUNO;

    Button BottonOpcion1;
    Button BottonOpcion2;
    Button BottonOpcion3;
    String [] valores;
    String pregunta1;
    Bundle extras;
    int contador=0;
    int contadorIncorrectas=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1_historia);
        this.getSupportActionBar().hide();

        int[] photos={R.drawable.fondo4,R.drawable.fondo5,R.drawable.fondo6,R.drawable.fondo9,R.drawable.fondo10,R.drawable.fondo12,R.drawable.fondo17,R.drawable.fondo18,R.drawable.fondo20,R.drawable.fondo21};
        Random ran=new Random();
        int i=ran.nextInt(photos.length);
        LinearLayout linearLayout= findViewById(R.id.preguntabg);
        linearLayout.setBackground(getApplicationContext().getResources().getDrawable(photos[i]));

        txtPreguntaCodigo = (TextView)findViewById(R.id.txtPreguntaCodigo);
        txtPreguntaTitulo = (TextView)findViewById(R.id.txtPreguntaTitulo);
        txtPreguntaUNO = (TextView) findViewById(R.id.txtPreguntaUNO);
        BottonOpcion1 = (Button)findViewById(R.id.BottonOpcion1);
        BottonOpcion2 = (Button)findViewById(R.id.BottonOpcion2);
        BottonOpcion3 = (Button)findViewById(R.id.BottonOpcion3);

        valores(12,22,32);
       extras = getIntent().getExtras();
       final String historiaid = extras.getString("his_codigoPre");
       final String titulo= extras.getString("his_titulocuentoPre");
       pregunta1= extras.getString("his_pregunta");

       valores=pregunta1.split("∉");

        txtPreguntaTitulo.setText(titulo);
        txtPreguntaUNO.setText(valores[2]);

        final Inicio_Sesion bluet= new Inicio_Sesion();
        BottonOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("este es la respuesaa"+valores[12]);
                if(BottonOpcion1.getText().equals(valores[12])){

                    bluet.send1_raspberry("correcto");
                    bluet.send_arduino("k");
                    bluet.send_arduino("a");
                    bluet.send_arduino("d");
                    contador=contador+1;
                    System.out.println("contadorcorrectas="+contador);


                }else{
                    bluet.send1_raspberry("incorrecto");
                    bluet.send_arduino("l");
                    bluet.send_arduino("b");
                    bluet.send_arduino("c");
                    contadorIncorrectas=contadorIncorrectas+1;
                    System.out.println("contadorincorrectas"+contadorIncorrectas);

                }

                if(valores[3].length()>1){


                    Intent intent = new Intent(Historia_Actividad_Pregunta.this, Historia_Actividad_Pregunta1.class);
                    intent.putExtra("his_codigoPre1", String.valueOf(historiaid));
                    intent.putExtra("his_titulocuentoPre1", String.valueOf(titulo));
                    intent.putExtra("his_pregunta1",pregunta1);
                    intent.putExtra("his_contador_correctas",contador);
                    intent.putExtra("his_contador_incorrectas",contadorIncorrectas);
                    startActivity(intent);


                }else{

                    Intent intent = new Intent(Historia_Actividad_Pregunta.this, Historia_Actividad_Resultado.class);
                    intent.putExtra("his_codigoPre1", String.valueOf(historiaid));
                    intent.putExtra("his_titulocuentoPre1", String.valueOf(titulo));
                    intent.putExtra("his_pregunta1",pregunta1);
                    intent.putExtra("his_contador_correctas",contador);
                    intent.putExtra("his_contador_incorrectas",contadorIncorrectas);

                    startActivity(intent);

                }

            }
        });

        BottonOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("este es la respuesaa"+valores[12]);
                if(BottonOpcion2.getText().equals(valores[12])){
                    bluet.send1_raspberry("correcto");
                    bluet.send_arduino("k");
                    bluet.send_arduino("a");
                    bluet.send_arduino("d");
                    contador=contador+1;
                    System.out.println("contadorcorrectas="+contador);


                }else{
                    bluet.send1_raspberry("incorrecto");
                    bluet.send_arduino("l");
                    bluet.send_arduino("b");
                    bluet.send_arduino("c");
                    contadorIncorrectas=contadorIncorrectas+1;
                    System.out.println("contadorincorrectas"+contadorIncorrectas);


                }


                if(valores[3].length()>1){

                    Intent intent = new Intent(Historia_Actividad_Pregunta.this, Historia_Actividad_Pregunta1.class);
                    intent.putExtra("his_codigoPre1", String.valueOf(historiaid));
                    intent.putExtra("his_titulocuentoPre1", String.valueOf(titulo));
                    intent.putExtra("his_pregunta1",pregunta1);
                    intent.putExtra("his_contador_correctas",contador);
                    intent.putExtra("his_contador_incorrectas",contadorIncorrectas);
                    startActivity(intent);


                }else{

                    Intent intent = new Intent(Historia_Actividad_Pregunta.this, Historia_Actividad_Resultado.class);
                    intent.putExtra("his_codigoPre1", String.valueOf(historiaid));
                    intent.putExtra("his_titulocuentoPre1", String.valueOf(titulo));
                    intent.putExtra("his_pregunta1",pregunta1);
                    intent.putExtra("his_contador_correctas",contador);
                    intent.putExtra("his_contador_incorrectas",contadorIncorrectas);
                    startActivity(intent);

                }

            }
        });

        BottonOpcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(BottonOpcion3.getText().equals(valores[12])){

                    bluet.send1_raspberry("correcto");
                    bluet.send_arduino("k");
                    bluet.send_arduino("a");
                    bluet.send_arduino("d");
                    contador=contador+1;
                    System.out.println("contadorcorrectas="+contador);


                }else{
                    bluet.send1_raspberry("incorrecto");
                    bluet.send_arduino("l");
                    bluet.send_arduino("b");
                    bluet.send_arduino("c");
                    contadorIncorrectas=contadorIncorrectas+1;
                    System.out.println("contadorincorrectas"+contadorIncorrectas);

                }

                if(valores[3].length()>1){

                    Intent intent = new Intent(Historia_Actividad_Pregunta.this, Historia_Actividad_Pregunta1.class);
                    intent.putExtra("his_codigoPre1", String.valueOf(historiaid));
                    intent.putExtra("his_titulocuentoPre1", String.valueOf(titulo));
                    intent.putExtra("his_pregunta1",pregunta1);
                    intent.putExtra("his_contador_correctas",contador);
                    intent.putExtra("his_contador_incorrectas",contadorIncorrectas);
                    startActivity(intent);


                }else{

                    Intent intent = new Intent(Historia_Actividad_Pregunta.this, Historia_Actividad_Resultado.class);
                    intent.putExtra("his_codigoPre1", String.valueOf(historiaid));
                    intent.putExtra("his_titulocuentoPre1", String.valueOf(titulo));
                    intent.putExtra("his_pregunta1",pregunta1);
                    intent.putExtra("his_contador_correctas",contador);
                    intent.putExtra("his_contador_incorrectas",contadorIncorrectas);
                    startActivity(intent);

                }
            }
        });

    }
    public void valores(int a,int b, int c){

        extras = getIntent().getExtras();
        pregunta1= extras.getString("his_pregunta");
        valores=pregunta1.split("∉");
            int[] available_cards = {a, b, c};
            java.util.Random random = new java.util.Random();
            int random_computer_card = random.nextInt(available_cards.length);
            int valor1=available_cards[random_computer_card];
            int[]aux=removeTheElement(available_cards,random_computer_card);
            int random_computer_card1 = random.nextInt(aux.length);
            int valor2=aux[random_computer_card1];
            int[]aux1=removeTheElement(aux,random_computer_card1);
            int valor3=aux1[0];

        BottonOpcion1.setText(valores[valor1]); //aux[12] respuestas
        BottonOpcion2.setText(valores[valor2]);
        BottonOpcion3.setText(valores[valor3]);

    }
    public static int[] removeTheElement(int[] arr, int index)
    {
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }
        int[] anotherArray = new int[arr.length - 1];


        for (int i = 0, k = 0; i < arr.length; i++) {

            if (i == index) {
                continue;
            }
            anotherArray[k++] = arr[i];
        }
        return anotherArray;
    }

    public void restart() {
        Intent intent = new Intent(this, Historia_Descripcion_Actividad.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        restart();
    }

}
