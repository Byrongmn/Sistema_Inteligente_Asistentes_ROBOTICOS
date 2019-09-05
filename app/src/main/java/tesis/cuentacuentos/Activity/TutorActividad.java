package tesis.cuentacuentos.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tesis.cuentacuentos.MainActivity;
import tesis.cuentacuentos.Modelos.Tutor;

import com.stuffaboutcode.bluedot.R;

import tesis.cuentacuentos.Retrofit.APIUtils;
import tesis.cuentacuentos.Retrofit.TutorServicio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TutorActividad extends AppCompatActivity {

    TutorServicio tutorService;
    EditText edtTid;
    EditText edtTnombre;

    Button btnSave;
    Button btnDel;
    TextView txtTid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        setTitle("Agregar Tutores");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTid = findViewById(R.id.txtTid);
        edtTid = findViewById(R.id.edtTid);
        edtTnombre = findViewById(R.id.edtTNombre);


        btnSave = findViewById(R.id.btnSave);
        btnDel = findViewById(R.id.btnDel);

        tutorService = APIUtils.getTutorService();

        Bundle extras = getIntent().getExtras();
        final String tutorId = extras.getString("tutor_id");
        String tutorNombre = extras.getString("tutor_nombre");


        edtTid.setText(tutorId);
        edtTnombre.setText(tutorNombre);


        if (tutorId != null && tutorId.trim().length() > 0) {
            edtTid.setFocusable(false);
        } else {
            txtTid.setVisibility(View.INVISIBLE);
            edtTid.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tutor t = new Tutor();
                t.setNombre(edtTnombre.getText().toString());
                if (tutorId != null && tutorId.trim().length() > 0) {
                    //update user
                    updateTutor(Integer.parseInt(tutorId), t);
                    Intent intent = new Intent(TutorActividad.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    //add user
                    addTutor(t);
                    Intent intent = new Intent(TutorActividad.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTutor(Integer.parseInt(tutorId));
                Intent intent = new Intent(TutorActividad.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addTutor(Tutor t) {
        Call<Tutor> call = tutorService.addTutor(t);
        call.enqueue(new Callback<Tutor>() {
            @Override
            public void onResponse(Call<Tutor> call, Response<Tutor> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TutorActividad.this, "Tutor creado exitosamente!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Tutor> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateTutor(int id, Tutor t) {
        Call<Tutor> call = tutorService.updateTutor(id, t);
        call.enqueue(new Callback<Tutor>() {
            @Override
            public void onResponse(Call<Tutor> call, Response<Tutor> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TutorActividad.this, "Tutor actualizado exitosamente!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Tutor> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteTutor(int id) {
        Call<Tutor> call = tutorService.deleteTutor(id);
        call.enqueue(new Callback<Tutor>() {
            @Override
            public void onResponse(Call<Tutor> call, Response<Tutor> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TutorActividad.this, "Tutor eliminado exitosamente!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Tutor> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
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
}
