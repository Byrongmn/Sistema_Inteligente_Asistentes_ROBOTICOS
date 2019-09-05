package tesis.cuentacuentos.Activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.ups.edu.ec.cuentame.ejemplocrud.Adaptadores.TutorAdapter;
import tesis.cuentacuentos.Adaptadores.TutorAdapter;
import tesis.cuentacuentos.Adaptadores.UserAdapter;
import tesis.cuentacuentos.Modelos.Tutor;
import tesis.cuentacuentos.Modelos.Usuario;

import com.airbnb.lottie.LottieAnimationView;
import com.stuffaboutcode.bluedot.R;

import tesis.cuentacuentos.Retrofit.APIUtils;
import tesis.cuentacuentos.Retrofit.TutorServicio;
import tesis.cuentacuentos.Retrofit.UsuarioServicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tesis.cuentacuentos.bluetooth.Inicio_Sesion;

import static android.widget.ArrayAdapter.createFromResource;


public class UsuarioActividad extends AppCompatActivity {

    UsuarioServicio userService;

    TutorServicio tutorService;

    TextView txtUUsername;
    TextView txtTutor;
    EditText edtUId;
    EditText edtUsername;
    Button btnSave;
    Button btnDel;
    TextView txtUId;
    Spinner spinner_tutor;
    List<Tutor> listt = new ArrayList<Tutor>();
    public static String[] ListaTutores = null;
    LottieAnimationView animation_user;
    List<Usuario> listu1 = new ArrayList<Usuario>();
    UsuarioServicio userService1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.getSupportActionBar().hide();
        tutorService = APIUtils.getTutorService();
        userService1 = APIUtils.getUserService();
        setTitle("Agregar Usuarios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] tutores = {"6", "2", "1"};

        LinearLayout LinearLayout = findViewById(R.id.layout1);
        AnimationDrawable animationDrawable = (AnimationDrawable) LinearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();

               /* ArrayAdapter<String> adaptertutor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,ListaTutores);
        spinner_tutor.setAdapter(adaptertutor);*/

        userService = APIUtils.getUserService();
        animation_user = findViewById(R.id.animation_user);

        String aux = "";

        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("user_id");
        String userName = extras.getString("user_name");

        txtUId = findViewById(R.id.txtUId);
        edtUId = findViewById(R.id.edtUId);
        edtUsername = findViewById(R.id.edtUsername);
        // edtTutor = (EditText) findViewById(R.id.edtTutor);
        spinner_tutor = findViewById(R.id.spinner_tutor);
        btnSave = findViewById(R.id.btnSave);
        txtUUsername=findViewById(R.id.txtUUsername);
        txtTutor=findViewById(R.id.txtTutor);
        btnDel = findViewById(R.id.btnDel);

        txtUUsername.setVisibility(View.INVISIBLE);
        txtTutor.setVisibility(View.INVISIBLE);

        edtUsername.setVisibility(View.INVISIBLE);
        spinner_tutor.setVisibility(View.INVISIBLE);
        btnSave.setVisibility(View.INVISIBLE);
        btnDel.setVisibility(View.INVISIBLE);
        txtUId.setVisibility(View.INVISIBLE);
        edtUId.setVisibility(View.INVISIBLE);

        animation_user.setVisibility(View.VISIBLE);
        animation_user.setAnimation("47-gears.json");
        animation_user.loop(true);
        animation_user.playAnimation();

        Call<List<Tutor>> call = tutorService.getTutor();
        call.enqueue(new Callback<List<Tutor>>() {
            @Override
            public void onResponse(Call<List<Tutor>> call, Response<List<Tutor>> response) {
                if (response.isSuccessful()) {
                    listt = response.body();

                    TutorAdapter a = new TutorAdapter(UsuarioActividad.this, R.layout.list_tutor, listt);
                    ListaTutores = a.DatosNombre();
                    /*String [] dat = a.DatosNombre();
                    ListaTutores=new String[dat.length+1];
                    for (int i = 0; i <dat.length ; i++) {
                        ListaTutores[i]=dat[i];
                    }
                    ListaTutores[dat.length]="4541";*/
                    ArrayAdapter<String> btcAdapter = new ArrayAdapter<String>(UsuarioActividad.this, android.R.layout.simple_spinner_dropdown_item, ListaTutores);
                    ArrayAdapter<String> btcAdapter1 = new ArrayAdapter<String>(UsuarioActividad.this, R.layout.color_spinner, ListaTutores);
                    btcAdapter1.setDropDownViewResource(R.layout.spinner_modificado);
                    spinner_tutor.setAdapter(btcAdapter1);



                    txtUUsername.setVisibility(View.VISIBLE);
                    txtTutor.setVisibility(View.VISIBLE);
                    edtUsername.setVisibility(View.VISIBLE);
                    spinner_tutor.setVisibility(View.VISIBLE);
                    btnSave.setVisibility(View.VISIBLE);


                    if (userId != null && userId.trim().length() > 0) {

                        btnDel.setVisibility(View.VISIBLE);
                    }
                    animation_user.setVisibility(View.INVISIBLE);
                    animation_user.cancelAnimation();
                }
            }

            @Override
            public void onFailure(Call<List<Tutor>> call, Throwable t) {
                Log.d("ERROR", t.getLocalizedMessage() == null ? "" : t.getLocalizedMessage());
            }
        });

        //String userTutor = extras.getString("user_tutor");
        for (int i = 0; i < tutores.length; i++) {

            if (tutores[i].equals(extras.getString("user_tutor"))) {

                spinner_tutor.setSelection(i);


            }
        }
        aux = String.valueOf(spinner_tutor.getSelectedItem());
        final String[] aux1 = aux.split("-");
        final String part2 = aux1[0];


        edtUId.setText(userId);
        edtUsername.setText(userName);
        //edtTutor.setText((String)spinner_tutor.getSelectedItem());

        if (userId != null && userId.trim().length() > 0) {
           // edtUId.setFocusable(false);
            System.out.println();
        } else {
            //txtUId.setVisibility(View.INVISIBLE);
            //edtUId.setFocusable(false);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<List<Usuario>> call2 = userService.getUser();
                call2.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        if(response.isSuccessful()) {
                            listu1 = response.body();
                            UserAdapter a = new UserAdapter(UsuarioActividad.this, R.layout.list_tutor, listu1);
                            System.out.println("Lista Usuario");

                            String Result = UserExist(edtUsername.getText().toString().trim(), a.DatosNombre());
                            if(Result==null){
                            Call<List<Tutor>> call3 = tutorService.getTutor();
                            call3.enqueue(new Callback<List<Tutor>>() {

                                @Override
                                public void onResponse(Call<List<Tutor>> call3, Response<List<Tutor>> response) {
                                    Usuario u = new Usuario();


                                    u.setNombre(edtUsername.getText().toString().trim());
                                    u.setTutor((String) spinner_tutor.getSelectedItem());

                                    if (response.isSuccessful()) {
                                        listt = response.body();

                                        TutorAdapter a = new TutorAdapter(UsuarioActividad.this, R.layout.list_tutor, listt);
                                        String ID = metodo(a.DatosId(), a.DatosNombre(), (String) spinner_tutor.getSelectedItem());
                                        u.setTutor(ID);

                                        if (userId != null && userId.trim().length() > 0) {
                                            //update user
                                            updateUser(Integer.parseInt(userId), u);
                                            onBackPressed();
/*
                                Intent intent = new Intent(UsuarioActividad.this, UsuarioActividad.class);
                                startActivity(intent);*/
                                        } else {
                                            //add user
                                            addUser(u);
                                            onBackPressed();
/*
                                Intent intent = new Intent(UsuarioActividad.this, MainActivity.class);
                                startActivity(intent);*/
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Tutor>> call, Throwable t) {
                                    Log.d("ERROR", t.getLocalizedMessage() == null ? "" : t.getLocalizedMessage());
                                }
                            });
                        }else {
                                Toast.makeText(UsuarioActividad.this, "El Jugador ya Existe!", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });





            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(Integer.parseInt(userId));
                onBackPressed();
                /*Intent intent = new Intent(UsuarioActividad.this, MainActivity.class);
                startActivity(intent);*/
            }
        });


    }

    public String UserExist(String Nombre,String [] Usuarios){
        for (int i = 0; i <Usuarios.length ; i++) {
            if (Nombre.equals(Usuarios[i])){
                return "existe";
            }
        }
        return null;
    }

    public void addUser(Usuario u) {
        Call<Usuario> call = userService.addUser(u);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UsuarioActividad.this, "Usuario creado exitosamente!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("ERROR", t.getLocalizedMessage() == null ? "" : t.getLocalizedMessage());
                Toast.makeText(UsuarioActividad.this, "Ocurrio un Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void listar() throws IOException {
        Call<List<Usuario>> call = userService.getUser();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UsuarioActividad.this, "Usuario Listado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.d("ERROR", t.getLocalizedMessage() == null ? "" : t.getLocalizedMessage());
            }
        });
    }

    public void updateUser(int id, Usuario u) {


        Call<Usuario> call = userService.updateUser(id, u);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UsuarioActividad.this, "Usuario actualizado exitosamente!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("ERROR", t.getLocalizedMessage() == null ? "" : t.getLocalizedMessage());
            }
        });
    }

    public void deleteUser(int id) {
        Call<Usuario> call = userService.deleteUser(id);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UsuarioActividad.this, "Usuario eliminado exitosamente!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("ERROR", t.getLocalizedMessage() == null ? "" : t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String metodo(String[] codigos, String[] nombres, String nomb) {
        // System.out.println("Mando a buscar "+nomb+"\n");
        String[][] matriz_tutores = new String[nombres.length][2];
        for (int i = 0; i < matriz_tutores.length; i++) {  //número de filas
            matriz_tutores[i][0] = codigos[i];
            for (int j = 0; j < matriz_tutores[i].length; j++) { //número de columnas de cada fila
                matriz_tutores[i][1] = nombres[i];
                //System.out.print(matriz_tutores[i][j] + " ");
            }
            //System.out.println();
        }
        //System.out.println("\n");
        for (int i = 0; i < matriz_tutores.length; i++) {  //número de filas
            for (int j = 0; j < matriz_tutores[i].length; j++) { //número de columnas de cada fila
                if (String.valueOf(matriz_tutores[i][1]).equals(nomb)) {
                    //System.out.println("El cod. de ["+nomb+"] es ["+matriz_tutores[i][0]+"]");
                    return matriz_tutores[i][0];
                }
            }
        }
        return null;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
