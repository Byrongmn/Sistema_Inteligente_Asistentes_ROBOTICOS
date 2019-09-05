package tesis.cuentacuentos.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.stuffaboutcode.bluedot.R;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tesis.cuentacuentos.Adaptadores.HistoriaAdapterObject;
import tesis.cuentacuentos.Modelos.Historia;
import tesis.cuentacuentos.Retrofit.APIUtils;
import tesis.cuentacuentos.Retrofit.HistoriaServicio;

public class HistoriaActividad extends AppCompatActivity implements MediaPlayer.OnBufferingUpdateListener,MediaPlayer.OnCompletionListener {


    HistoriaServicio histservice;
    Button btnDel;
    TextView txtHTitulo;
    CircleImageView foto;
    ArrayList<String> listarCuento = new ArrayList<String>();
    Object auxiliar = null;
    String bdefecto="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";
    byte[] imagdefecto= Base64.decode(bdefecto, Base64.DEFAULT);

    private ImageButton btn_play_pause;
    private SeekBar seekBar;


    private MediaPlayer mediaPlayer;
    private int mediaFileLength;
    private int realtimeLength;
    final Handler handler = new Handler();

    TextView tiempobtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);
        this.getSupportActionBar().hide();
        setTitle("Agregar Historia");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnDel = findViewById(R.id.btnDel);
        foto = findViewById(R.id.HistoriaFotoID);
        txtHTitulo = findViewById(R.id.txtHTitulo);
        histservice = APIUtils.getHistoriaService();
        tiempobtn= findViewById(R.id.tiempobtn);
        Bundle extras = getIntent().getExtras();
        final String historiaid = extras.getString("his_id");
        final String baseid = extras.getString("his_base");
        final String titulo = extras.getString("his_titulo");
        final String descripcion = extras.getString("his_descripcion");
        final String audio=extras.getString("his_audio");

        Bitmap aux = decodeImg(baseid);
        foto.setImageBitmap(aux);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                Intent intent = new Intent(HistoriaActividad.this, Historia_Descripcion_Actividad.class);
                intent.putExtra("his_codigo", String.valueOf(historiaid));
                intent.putExtra("his_titulocuento", String.valueOf(titulo));
                intent.putExtra("his_descripcioncuento", String.valueOf(descripcion));
                listar_historias(Integer.parseInt(historiaid));
                //valorIdcuento();
                startActivity(intent);

            }
        });


        seekBar = (SeekBar)findViewById(R.id.seekbar);
        seekBar.setMax(99); // 100% (0~99)
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mediaPlayer.isPlaying())
                {
                    SeekBar seekBar = (SeekBar)v;
                    int playPosition = (mediaFileLength/100)*seekBar.getProgress();
                    mediaPlayer.seekTo(playPosition);
                }
                return false;
            }
        });


        btn_play_pause = (ImageButton) findViewById(R.id.btn_play_pause);
        btn_play_pause.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(HistoriaActividad.this);
                AsyncTask<String,String,String> mp3Play = new AsyncTask<String, String, String>() {

                    @Override
                    protected void onPreExecute() {
                        mDialog.setMessage("Cargando ...");
                        mDialog.show();
                    }

                    @Override
                    protected String doInBackground(String... params) {
                        try{
                            mediaPlayer.setDataSource(params[0]);
                            mediaPlayer.prepare();
                        }
                        catch (Exception ex)
                        {

                        }
                        return "";
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        mediaFileLength = mediaPlayer.getDuration();
                        realtimeLength = mediaFileLength;
                        if(!mediaPlayer.isPlaying())
                        {
                            mediaPlayer.start();
                            btn_play_pause.setImageResource(R.drawable.ic_pause);
                        }
                        else
                        {
                            mediaPlayer.pause();
                            btn_play_pause.setImageResource(R.drawable.ic_play);
                        }

                        updateSeekBar();
                        mDialog.dismiss();
                    }
                };

                APIUtils api= new APIUtils();
                String ip=api.ip+audio;
                System.out.println(""+ip.toString()+"");
                mp3Play.execute(ip);


            }
        });

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);

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

    public void listar_historias(int id) {
        Call<Historia> call = histservice.getHistorias(id);

        call.enqueue(new Callback<Historia>() {
            @Override
            public void onResponse(Call<Historia> call, Response<Historia> response) {

                if (response.isSuccessful()) {

                    auxiliar = response.body();

                    HistoriaAdapterObject a = new HistoriaAdapterObject(HistoriaActividad.this, R.layout.list_historia, (Historia) auxiliar);
                    a.DatosDescripcion();
                    a.DatosID();
                    a.DatosTitulo();
                    a.DatosImagen();
                    listarCuento.add(a.DatosDescripcion());
                    listarCuento.add(a.DatosTitulo());
                    listarCuento.add(String.valueOf(a.DatosID()));
                    listarCuento.add(a.DatosImagen());


                }
            }

            @Override
            public void onFailure(Call<Historia> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public Bitmap decodeImg(String s) {


        byte[] decodedString;
        if (s==null){
            decodedString = imagdefecto;
        }else {
            decodedString = Base64.decode(s, Base64.DEFAULT);
        }
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    private void updateSeekBar() {
        seekBar.setProgress((int)(((float)mediaPlayer.getCurrentPosition() / mediaFileLength)*100));
        if(mediaPlayer.isPlaying())
        {
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                    updateSeekBar();
                    realtimeLength-=1000; // declare 1 second
                    tiempobtn.setText(String.format("%02d: %02d ",
                            TimeUnit.MILLISECONDS.toMinutes( mediaPlayer.getCurrentPosition()),
                            TimeUnit.MILLISECONDS.toSeconds( mediaPlayer.getCurrentPosition()) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes( mediaPlayer.getCurrentPosition()))
                    ));

                }

            };
            handler.postDelayed(updater,1000); // 1 second
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBar.setSecondaryProgress(percent);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        btn_play_pause.setImageResource(R.drawable.ic_play);

    }

    @Override
    public void onBackPressed() {
        mediaPlayer.pause();
        super.onBackPressed();
    }
}
