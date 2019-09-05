package tesis.cuentacuentos.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stuffaboutcode.bluedot.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tesis.cuentacuentos.Adaptadores.PreguntasAdapter;
import tesis.cuentacuentos.Modelos.Preguntas;
import tesis.cuentacuentos.Retrofit.APIUtils;
import tesis.cuentacuentos.Retrofit.PreguntaServicio;
import tesis.cuentacuentos.bluetooth.Inicio_Sesion;


public class Historia_Descripcion_Actividad extends AppCompatActivity {


    PreguntaServicio preguntaservicio;
    Button btnHistoriaPreguntas;
    Button btnCuenta;
    TextView txtHIDescripcion;
    TextView txtHTitulo;
    List<Preguntas> listt = new ArrayList<Preguntas>();
    String[] preguntas;

    tesis.cuentacuentos.bluetooth.Inicio_Sesion but;

    ArrayList<String> listarpregunta = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_historia);
        this.getSupportActionBar().hide();
        txtHIDescripcion = findViewById(R.id.txtHDescripcion);
        txtHTitulo = findViewById(R.id.txtHTitulo);
        btnHistoriaPreguntas = findViewById(R.id.btnHistoriaPreguntas);
        preguntaservicio = APIUtils.getPreguntasService();
        btnCuenta = findViewById(R.id.btncuenta);


        //String aux=historia.valorIdcuento();
        Bundle extras = getIntent().getExtras();
        final String historiaid = extras.getString("his_codigo");
        final String titulo = extras.getString("his_titulocuento");
        final String descripcion = extras.getString("his_descripcioncuento");

        but = new tesis.cuentacuentos.bluetooth.Inicio_Sesion();


        txtHIDescripcion.setText(descripcion);
        txtHTitulo.setText(titulo);

        btnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Historia " + descripcion);
                but.send_arduino(descripcion);

            }
        });

        btnHistoriaPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inicio_Sesion in = new Inicio_Sesion();
                //in.send1_raspberry("preg");
                Call<List<Preguntas>> call = preguntaservicio.getPreguntas();
                call.enqueue(new Callback<List<Preguntas>>() {
                    @Override
                    public void onResponse(Call<List<Preguntas>> call, Response<List<Preguntas>> response) {

                        if (response.isSuccessful()) {


                            listt = response.body();

                            PreguntasAdapter a = new PreguntasAdapter(Historia_Descripcion_Actividad.this, R.layout.list_preguntas, listt);

                            String[] listapre = new String[listt.toArray().length];

                            String question = "";

                            for (int i = 0; i < listt.toArray().length; i++) {

                                question = question + listt.get(i).getId() + "∉";
                                question = question + listt.get(i).getHistoria() + "∉";
                                question = question + listt.get(i).getPregunta1() + "∉";
                                question = question + listt.get(i).getPregunta2() + "∉";
                                question = question + listt.get(i).getPregunta3() + "∉";
                                question = question + listt.get(i).getPregunta4() + "∉";
                                question = question + listt.get(i).getPregunta5() + "∉";
                                question = question + listt.get(i).getPregunta6() + "∉";
                                question = question + listt.get(i).getPregunta7() + "∉";
                                question = question + listt.get(i).getPregunta8() + "∉";
                                question = question + listt.get(i).getPregunta9() + "∉";
                                question = question + listt.get(i).getPregunta10() + "∉";
                                question = question + listt.get(i).getRespuesta1() + "∉";
                                question = question + listt.get(i).getRespuesta2() + "∉";
                                question = question + listt.get(i).getRespuesta3() + "∉";
                                question = question + listt.get(i).getRespuesta4() + "∉";
                                question = question + listt.get(i).getRespuesta5() + "∉";
                                question = question + listt.get(i).getRespuesta6() + "∉";
                                question = question + listt.get(i).getRespuesta7() + "∉";
                                question = question + listt.get(i).getRespuesta8() + "∉";
                                question = question + listt.get(i).getRespuesta9() + "∉";
                                question = question + listt.get(i).getRespuesta10() + "∉";
                                question = question + listt.get(i).getOpcion_a1() + "∉";
                                question = question + listt.get(i).getOpcion_a2() + "∉";
                                question = question + listt.get(i).getOpcion_a3() + "∉";
                                question = question + listt.get(i).getOpcion_a4() + "∉";
                                question = question + listt.get(i).getOpcion_a5() + "∉";
                                question = question + listt.get(i).getOpcion_a6() + "∉";
                                question = question + listt.get(i).getOpcion_a7() + "∉";
                                question = question + listt.get(i).getOpcion_a8() + "∉";
                                question = question + listt.get(i).getOpcion_a9() + "∉";
                                question = question + listt.get(i).getOpcion_a10() + "∉";
                                question = question + listt.get(i).getOpcion_b1() + "∉";
                                question = question + listt.get(i).getOpcion_b2() + "∉";
                                question = question + listt.get(i).getOpcion_b3() + "∉";
                                question = question + listt.get(i).getOpcion_b4() + "∉";
                                question = question + listt.get(i).getOpcion_b5() + "∉";
                                question = question + listt.get(i).getOpcion_b6() + "∉";
                                question = question + listt.get(i).getOpcion_b7() + "∉";
                                question = question + listt.get(i).getOpcion_b8() + "∉";
                                question = question + listt.get(i).getOpcion_b9() + "∉";
                                question = question + listt.get(i).getOpcion_b10() + "∉";
                                question = question + "≠";


                            }
                            preguntas = question.split("≠");
                            Bundle extras = getIntent().getExtras();
                            final String codigoHistoria = extras.getString("his_codigo");

                            //System.out.println("este es el codigo de la historia"+codigoHistoria);

                            for (int i = 0; i < preguntas.length; i++) {

                                String[] codigo = preguntas[i].split("∉");
                                if (codigoHistoria.equals(codigo[1])) {

                                    Intent intent = new Intent(Historia_Descripcion_Actividad.this, Historia_Actividad_Pregunta.class);
                                    intent.putExtra("his_codigoPre", String.valueOf(historiaid));
                                    intent.putExtra("his_titulocuentoPre", String.valueOf(titulo));
                                    intent.putExtra("his_descripcioncuentoPre", String.valueOf(descripcion));
                                    intent.putExtra("his_pregunta", preguntas[i]);
                                    startActivity(intent);
                                }

                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Preguntas>> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });
            }
        });
    }

    public String[] ListarPreguntas() {
        Call<List<Preguntas>> call = preguntaservicio.getPreguntas();
        call.enqueue(new Callback<List<Preguntas>>() {
            @Override
            public void onResponse(Call<List<Preguntas>> call, Response<List<Preguntas>> response) {

                if (response.isSuccessful()) {
                    listt = response.body();

                    PreguntasAdapter a = new PreguntasAdapter(Historia_Descripcion_Actividad.this, R.layout.list_preguntas, listt);

                    String[] listapre = new String[listt.toArray().length];

                    String question = "";

                    for (int i = 0; i < listt.toArray().length; i++) {

                        question = question + listt.get(i).getId() + ",";
                        question = question + listt.get(i).getHistoria() + ",";
                        question = question + listt.get(i).getPregunta1() + ",";
                        question = question + listt.get(i).getPregunta2() + ",";
                        question = question + listt.get(i).getPregunta3() + ",";
                        question = question + listt.get(i).getPregunta4() + ",";
                        question = question + listt.get(i).getPregunta5() + ",";
                        question = question + listt.get(i).getPregunta6() + ",";
                        question = question + listt.get(i).getPregunta7() + ",";
                        question = question + listt.get(i).getPregunta8() + ",";
                        question = question + listt.get(i).getPregunta9() + ",";
                        question = question + listt.get(i).getPregunta10() + ",";
                        question = question + ";";


                    }
                    preguntas = question.split(";");
                    Bundle extras = getIntent().getExtras();
                    final String codigoHistoria = extras.getString("his_codigo");

                    //System.out.println("este es el codigo de la historia"+codigoHistoria);

                    for (int i = 0; i < preguntas.length; i++) {

                        String[] codigo = preguntas[i].split(",");
                        System.out.println("codigo" + codigo[1]);
                        if (codigoHistoria.equals(codigo[1])) {

                            //System.out.println("preguntas"+preguntas[i]);

                        }

                    }


                }
            }

            @Override
            public void onFailure(Call<List<Preguntas>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        return preguntas;

    }


}


