package tesis.cuentacuentos.bluetooth;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.stuffaboutcode.bluedot.R;

import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tesis.cuentacuentos.Activity.Historia_Actividad_Resultado;
import tesis.cuentacuentos.Adaptadores.HistoriaAdapter;
import tesis.cuentacuentos.Modelos.Historia;
import tesis.cuentacuentos.Retrofit.APIUtils;
import tesis.cuentacuentos.Retrofit.HistoriaServicio;
import tesis.cuentacuentos.Retrofit.TutorServicio;
import tesis.cuentacuentos.Retrofit.UsuarioServicio;

public class Button extends AppCompatActivity {

    android.widget.TextView Lista;
    android.widget.Button btnGetHistoriaList1;
    ListView listView1;

    UsuarioServicio userService1;
    TutorServicio tutorService1;
    HistoriaServicio historiaServicio1;

    List<Historia> listh1 = new ArrayList<Historia>();

    LottieAnimationView animationView;
    LottieAnimationView animationBluet;
    TextView Status;
    public static String code;

    android.widget.Button salirbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        Bundle extras = getIntent().getExtras();
        final String usr_nombre = extras.getString("usr_nombre");
        final String usr_codigo = extras.getString("usr_codigo");
        code=usr_codigo;


        Lista = findViewById(R.id.Lista);
        btnGetHistoriaList1 = findViewById(R.id.btnGetHistoriaList1);
        listView1 = findViewById(R.id.listView1);
        userService1 = APIUtils.getUserService();
        tutorService1 = APIUtils.getTutorService();
        historiaServicio1 = APIUtils.getHistoriaService();
        animationView = findViewById(R.id.animation_view);
        animationBluet = findViewById(R.id.animation_bluet);
        Status = findViewById(R.id.status);
        salirbtn=findViewById(R.id.salirbtn);

        Status.setText("Bienvenido: "+usr_nombre);

        LinearLayout LinearLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) LinearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();

        this.getSupportActionBar().hide();

        btnGetHistoriaList1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHistoriaList();
            }
        });

        salirbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public void getHistoriaList() {
        Lista.setText("Cargando.......");
        btnGetHistoriaList1.setEnabled(false);
        listView1.setVisibility(View.INVISIBLE);

        animationView.setVisibility(View.VISIBLE);
        animationView.setAnimation("viaje.json");
        animationView.loop(true);
        animationView.playAnimation();


        Call<List<Historia>> call = historiaServicio1.getHistoria();
        call.enqueue(new Callback<List<Historia>>() {
            @Override
            public void onResponse(Call<List<Historia>> call, Response<List<Historia>> response) {
                if (response.isSuccessful()) {
                    listh1 = response.body();
                    Lista.setText("");

                    animationView.setVisibility(View.INVISIBLE);
                    animationView.cancelAnimation();

                    listView1.setAdapter(new HistoriaAdapter(Button.this, R.layout.list_historia, listh1));


                    ListView listView = findViewById(R.id.listView1);
                    setListViewHeightBasedOnChildren(listView);
                    btnGetHistoriaList1.setEnabled(true);
                    listView1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Historia>> call, Throwable t) {
                Log.d("ERROR:", t.getLocalizedMessage() == null ? "" : t.getLocalizedMessage());
            }
        });

    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Sesion Cerrada.", Toast.LENGTH_SHORT).show();
        super.onBackPressed();

    }


}
