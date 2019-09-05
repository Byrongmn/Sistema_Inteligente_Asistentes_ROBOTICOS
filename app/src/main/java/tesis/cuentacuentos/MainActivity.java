package tesis.cuentacuentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.stuffaboutcode.bluedot.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tesis.cuentacuentos.Activity.TutorActividad;
import tesis.cuentacuentos.Activity.UsuarioActividad;
import tesis.cuentacuentos.Adaptadores.HistoriaAdapter;
import tesis.cuentacuentos.Adaptadores.TutorAdapter;
import tesis.cuentacuentos.Adaptadores.UserAdapter;
import tesis.cuentacuentos.Modelos.Historia;
import tesis.cuentacuentos.Modelos.Tutor;
import tesis.cuentacuentos.Modelos.Usuario;
import tesis.cuentacuentos.Retrofit.APIUtils;
import tesis.cuentacuentos.Retrofit.HistoriaServicio;
import tesis.cuentacuentos.Retrofit.TutorServicio;
import tesis.cuentacuentos.Retrofit.UsuarioServicio;

//import com.ups.edu.ec.cuentame.ejemplocrud.Activity.TutorActividad;

public class MainActivity extends AppCompatActivity {

    Button btnAddUser;
    Button btnGetUsersList;
    Button btnAddtutor;
    Button btnGetTutorList;
    Button btnGetHistoriaList;
    ListView listView;

    UsuarioServicio userService;
    TutorServicio tutorService;
    HistoriaServicio historiaServicio;

    List<Usuario> listu = new ArrayList<Usuario>();
    List<Tutor> listt = new ArrayList<Tutor>();
    List<Historia> listh = new ArrayList<Historia>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Crud Cuentame");

        btnAddUser = findViewById(R.id.btnAddUser);
        btnAddtutor = findViewById(R.id.btnAddTutor);
        btnGetUsersList = findViewById(R.id.btnGetUsersList);
        btnGetTutorList = findViewById(R.id.btnGetTutorList);
        btnGetHistoriaList = findViewById(R.id.btnGetHistoriaList);
        listView = findViewById(R.id.listView);
        userService = APIUtils.getUserService();
        tutorService = APIUtils.getTutorService();
        historiaServicio = APIUtils.getHistoriaService();

        btnGetUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getUsersList();
            }
        });

        btnGetHistoriaList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHistoriaList();
            }
        });

        btnGetTutorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTutorList();
            }
        });


        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsuarioActividad.class);
                intent.putExtra("user_name", "");
                intent.putExtra("user_tutor", "");
                startActivity(intent);
            }
        });

        btnAddtutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TutorActividad.class);
                intent.putExtra("tutor_nombre", "");
                intent.putExtra("tutor_apellido", "");
                intent.putExtra("tutor_edad", "");
                intent.putExtra("tutor_telefono", "");
                intent.putExtra("tutor_email", "");
                intent.putExtra("tutor_domicilio", "");
                startActivity(intent);
            }
        });
    }

    public void getUsersList() {
        Call<List<Usuario>> call = userService.getUser();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    listu = response.body();
                    listView.setAdapter(new UserAdapter(MainActivity.this, R.layout.list_user, listu));

                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void getHistoriaList() {
        Call<List<Historia>> call = historiaServicio.getHistoria();
        call.enqueue(new Callback<List<Historia>>() {
            @Override
            public void onResponse(Call<List<Historia>> call, Response<List<Historia>> response) {
                if (response.isSuccessful()) {
                    listh = response.body();
                    listView.setAdapter(new HistoriaAdapter(MainActivity.this, R.layout.list_historia, listh));
                }
            }

            @Override
            public void onFailure(Call<List<Historia>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void getTutorList() {
        Call<List<Tutor>> call = tutorService.getTutor();
        call.enqueue(new Callback<List<Tutor>>() {
            @Override
            public void onResponse(Call<List<Tutor>> call, Response<List<Tutor>> response) {
                if (response.isSuccessful()) {
                    listt = response.body();
                    listView.setAdapter(new TutorAdapter(MainActivity.this, R.layout.list_tutor, listt));
                }
            }

            @Override
            public void onFailure(Call<List<Tutor>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
