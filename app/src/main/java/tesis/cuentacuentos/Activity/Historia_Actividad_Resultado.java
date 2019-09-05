package tesis.cuentacuentos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import com.stuffaboutcode.bluedot.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tesis.cuentacuentos.Modelos.Resultado;
import tesis.cuentacuentos.Retrofit.APIUtils;
import tesis.cuentacuentos.Retrofit.ResultadoServicio;
import tesis.cuentacuentos.bluetooth.Inicio_Sesion;

public class Historia_Actividad_Resultado extends AppCompatActivity {

    Bundle extras;
    ArrayList<Integer> valor = new ArrayList<>();
    ArrayList<Integer> valorincorrectas = new ArrayList<>();
    ArrayList<String> codigoHistoria = new ArrayList<>();
    ArrayList<String> titulos = new ArrayList<>();
    int valorcorrecto=0;
    int valorincorrect=0;
    String codigo=null;
    String tituloHistoria=null;
    ResultadoServicio resultadoservicio;
    Button btnGuardar;
    TextView textResultado_Titulo;
    TextView textResultado_Correctas;
    TextView textResultado_Incorrectas;
    TextView textResultado_Fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_resultado);
        setTitle("Agregar Historia");
        this.getSupportActionBar().hide();
        int[] photos={R.drawable.fondo4,R.drawable.fondo5,R.drawable.fondo6,R.drawable.fondo9,R.drawable.fondo10,R.drawable.fondo12,R.drawable.fondo17,R.drawable.fondo18,R.drawable.fondo20,R.drawable.fondo21};
        Random ran=new Random();
        int it=ran.nextInt(photos.length);
        LinearLayout linearLayout= findViewById(R.id.resultadobg);
        linearLayout.setBackground(getApplicationContext().getResources().getDrawable(photos[it]));
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        textResultado_Titulo = (TextView)findViewById(R.id.textResultado_Titulo);
        textResultado_Correctas = (TextView)findViewById(R.id.textResultado_Correctas);
        textResultado_Incorrectas = (TextView)findViewById(R.id.textResultado_Incorrectas);
        textResultado_Fecha =(TextView)findViewById(R.id.textResultado_Fecha);

        resultadoservicio = APIUtils.getResultadosService();
        final Inicio_Sesion bluet=new Inicio_Sesion();
        bluet.send_arduino("j");
        bluet.send1_raspberry("fin");
        extras = getIntent().getExtras();
        String historiaid = extras.getString("his_codigoPre");
        String historiaid1= extras.getString("his_codigoPre1");
        String historiaid2 = extras.getString("his_codigoPre2");
        String historiaid3 = extras.getString("his_codigoPre3");
        String historiaid4 = extras.getString("his_codigoPre4");
        String historiaid5 = extras.getString("his_codigoPre5");
        String historiaid6 = extras.getString("his_codigoPre6");
        String historiaid7 = extras.getString("his_codigoPre7");
        String historiaid8 = extras.getString("his_codigoPre8");
        String historiaid9 = extras.getString("his_codigoPre9");
        String historiaid10 = extras.getString("his_codigoPre10");

        String titulo= extras.getString("his_titulocuentoPre");
        String titulo1= extras.getString("his_titulocuentoPre1");
        String titulo2= extras.getString("his_titulocuentoPre2");
        String titulo3= extras.getString("his_titulocuentoPre3");
        String titulo4= extras.getString("his_titulocuentoPre4");
        String titulo5= extras.getString("his_titulocuentoPre5");
        String titulo6= extras.getString("his_titulocuentoPre6");
        String titulo7= extras.getString("his_titulocuentoPre7");
        String titulo8= extras.getString("his_titulocuentoPre8");
        String titulo9= extras.getString("his_titulocuentoPre9");
        String titulo10= extras.getString("his_titulocuentoPre10");

        int resultadocorrectas=extras.getInt("his_contador_correctas");
        int resultadoincorrectas=extras.getInt("his_contador_incorrectas");
        int resultadocorrectas1=extras.getInt("his_contador_correctas1");
        int resultadoincorrectas1=extras.getInt("his_contador_incorrectas1");
        int resultadocorrectas2=extras.getInt("his_contador_correctas2");
        int resultadoincorrectas2=extras.getInt("his_contador_incorrectas2");
        int resultadocorrectas3=extras.getInt("his_contador_correctas3");
        int resultadoincorrectas3=extras.getInt("his_contador_incorrectas3");
        int resultadocorrectas4=extras.getInt("his_contador_correctas4");
        int resultadoincorrectas4=extras.getInt("his_contador_incorrectas4");
        int resultadocorrectas5=extras.getInt("his_contador_correctas5");
        int resultadoincorrectas5=extras.getInt("his_contador_incorrectas5");
        int resultadocorrectas6=extras.getInt("his_contador_correctas6");
        int resultadoincorrectas6=extras.getInt("his_contador_incorrectas6");
        int resultadocorrectas7=extras.getInt("his_contador_correctas7");
        int resultadoincorrectas7=extras.getInt("his_contador_incorrectas7");
        int resultadocorrectas8=extras.getInt("his_contador_correctas8");
        int resultadoincorrectas8=extras.getInt("his_contador_incorrectas8");
        int resultadocorrectas9=extras.getInt("his_contador_correctas9");
        int resultadoincorrectas9=extras.getInt("his_contador_incorrectas9");

        titulos.add(titulo);
        titulos.add(titulo1);
        titulos.add(titulo2);
        titulos.add(titulo3);
        titulos.add(titulo4);
        titulos.add(titulo5);
        titulos.add(titulo6);
        titulos.add(titulo7);
        titulos.add(titulo8);
        titulos.add(titulo9);
        titulos.add(titulo10);

        for (int i = 0; i < titulos.size(); i++) {

            if(titulos.get(i)!= null){

                tituloHistoria=titulos.get(i);
                break;
            }

        }

        codigoHistoria.add(historiaid);
        codigoHistoria.add(historiaid1);
        codigoHistoria.add(historiaid2);
        codigoHistoria.add(historiaid3);
        codigoHistoria.add(historiaid4);
        codigoHistoria.add(historiaid5);
        codigoHistoria.add(historiaid6);
        codigoHistoria.add(historiaid7);
        codigoHistoria.add(historiaid8);
        codigoHistoria.add(historiaid9);
        codigoHistoria.add(historiaid10);

        for (int i = 0; i < codigoHistoria.size(); i++) {

            if(codigoHistoria.get(i)!= null){

                codigo=codigoHistoria.get(i);
                System.out.println("este codigo es"+codigo);
                break;
            }

        }

        valor.add(resultadocorrectas);
        valor.add(resultadocorrectas1);
        valor.add(resultadocorrectas2);
        valor.add(resultadocorrectas3);
        valor.add(resultadocorrectas4);
        valor.add(resultadocorrectas5);
        valor.add(resultadocorrectas6);
        valor.add(resultadocorrectas7);
        valor.add(resultadocorrectas8);
        valor.add(resultadocorrectas9);

        valorincorrectas.add(resultadoincorrectas);
        valorincorrectas.add(resultadoincorrectas1);
        valorincorrectas.add(resultadoincorrectas2);
        valorincorrectas.add(resultadoincorrectas3);
        valorincorrectas.add(resultadoincorrectas4);
        valorincorrectas.add(resultadoincorrectas5);
        valorincorrectas.add(resultadoincorrectas6);
        valorincorrectas.add(resultadoincorrectas7);
        valorincorrectas.add(resultadoincorrectas8);
        valorincorrectas.add(resultadoincorrectas9);

        for (int i = 0; i < valor.size(); i++) {

            if(valor.get(i)!= 0 || valorincorrectas.get(i) !=0){

                valorcorrecto=valor.get(i);
                valorincorrect=valorincorrectas.get(i);
                break;
            }
        }
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        Date currentTime = localCalendar.getTime();
        int currentDay = localCalendar.get(Calendar.DATE);
        int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
        int currentYear = localCalendar.get(Calendar.YEAR);
        int cHour = currentTime.getHours();
        int cMinute = currentTime.getMinutes();
        int cSecond = currentTime.getSeconds();
        final String State =currentDay +"/"+ currentMonth+"/"+currentYear+"\n"+cHour+":"+cMinute+":"+cSecond;


        tesis.cuentacuentos.bluetooth.Button datos = new tesis.cuentacuentos.bluetooth.Button();
        String usr_code = datos.code;

        System.out.println("valores incorrectos "+valorincorrect+" valorees correctos "+valorcorrecto+"id_historia"+codigo + "este es el titulo"+tituloHistoria);
        textResultado_Titulo.setText(tituloHistoria);
        textResultado_Correctas.setText(String.valueOf(valorcorrecto));
        textResultado_Incorrectas.setText(String.valueOf(valorincorrect));
        textResultado_Fecha.setText(State);

        Resultado t = new Resultado();
        t.setCorrectas(valorcorrecto);
        t.setIncorrectas(valorincorrect);
        t.setHistoria(String.valueOf(codigo));
        t.setFecha((State));
        t.setUsuario(usr_code);
        addResultado(t);



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });
    }

    public void addResultado(Resultado r){
        Call<Resultado> call = resultadoservicio.addResultado(r);
        call.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Historia_Actividad_Resultado.this, "Resultado creado exitosamente!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
                Toast.makeText(Historia_Actividad_Resultado.this, "Ocurrio un Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void restart() {
        Intent intent = new Intent(this, tesis.cuentacuentos.bluetooth.Button.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        restart();
    }
}
