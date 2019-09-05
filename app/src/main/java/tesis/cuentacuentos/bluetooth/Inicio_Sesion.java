package tesis.cuentacuentos.bluetooth;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import tesis.cuentacuentos.Activity.UsuarioActividad;
import tesis.cuentacuentos.Adaptadores.UserAdapter;
import tesis.cuentacuentos.Modelos.Usuario;
import tesis.cuentacuentos.Retrofit.APIUtils;
import tesis.cuentacuentos.Retrofit.UsuarioServicio;

public class Inicio_Sesion extends AppCompatActivity {


    public static String mConnectedDeviceName1 = null;
    public static StringBuffer mOutStringBuffer1;
    public static StringBuffer mInStringBuffer1;
    public static BluetoothAdapter mBluetoothAdapter1 = null;
    public static BluetoothChatService mChatService1 = null;

    public static String mConnectedDeviceName2 = null;
    public static StringBuffer mOutStringBuffer2;
    public static StringBuffer mInStringBuffer2;
    public static BluetoothAdapter mBluetoothAdapter2 = null;
    public static BluetoothChatService mChatService2 = null;


    ///Conexion Devices 1
    public static String address1 = null;
    public static String deviceName1 = null;
    ///Conexion Devices 2
    public static String address2 = null;
    public static String deviceName2 = null;



    EditText Usuariotxt;
    //android.widget.TextView Listatxt;
    android.widget.Button btnAddUser1;
    android.widget.Button iniciarbtn;
    //android.widget.Button btnGetUsersList1;
    //android.widget.Button raspberry;
    //android.widget.Button Robot;

    //ListView listView1;
    UsuarioServicio userService1;
    List<Usuario> listu1 = new ArrayList<Usuario>();

    LottieAnimationView animationView;
    LottieAnimationView animationBluet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        Intent newint = getIntent();

        deviceName1 = newint.getStringExtra(Devices2.EXTRA_NAME1);
        address1 = newint.getStringExtra(Devices2.EXTRA_ADDRESS1);

        deviceName2 = newint.getStringExtra(Devices2.EXTRA_NAME2);
        address2 = newint.getStringExtra(Devices2.EXTRA_ADDRESS2);

        // Get local Bluetooth adapter
        mBluetoothAdapter1 = BluetoothAdapter.getDefaultAdapter();
        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter1 == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth is not available", Toast.LENGTH_LONG).show();
            this.finish();
        }
        // Initialize the BluetoothChatService to perform bluetooth connections
        mChatService1 = new BluetoothChatService(this, mHandler1);
        // Initialize the buffer for outgoing messages
        mOutStringBuffer1 = new StringBuffer();
        // Initialize the buffer for incoming messages
        mInStringBuffer1 = new StringBuffer();


        // Get local Bluetooth adapter
        mBluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();
        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter2 == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth is not available", Toast.LENGTH_LONG).show();
            this.finish();
        }
        // Initialize the BluetoothChatService to perform bluetooth connections
        mChatService2 = new BluetoothChatService(this, mHandler2);
        // Initialize the buffer for outgoing messages
        mOutStringBuffer2 = new StringBuffer();
        // Initialize the buffer for incoming messages
        mInStringBuffer2 = new StringBuffer();



        // Get the BluetoothDevice object
        BluetoothDevice device1 = mBluetoothAdapter2.getRemoteDevice(address1);
        // Attempt to connect to the device
        mChatService1.connect(device1, true);

        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter2.getRemoteDevice(address2);
        // Attempt to connect to the device
        mChatService2.connect(device, true);

        /*Listatxt = findViewById(R.id.Lista3);*/
        btnAddUser1 = findViewById(R.id.btnAddUser3);
        iniciarbtn=findViewById(R.id.iniciarbtn);
        Usuariotxt=findViewById(R.id.usuariotxt);
        /*btnGetUsersList1 = findViewById(R.id.btnGetUsersList3);
        listView1 = findViewById(R.id.listView3);*/
        userService1 = APIUtils.getUserService();
        animationView = findViewById(R.id.animation_view3);
        animationBluet = findViewById(R.id.animation_bluet3);


        LinearLayout LinearLayout = findViewById(R.id.layout3);
        AnimationDrawable animationDrawable = (AnimationDrawable) LinearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();

        this.getSupportActionBar().hide();

        iniciarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciaSesion(String.valueOf(Usuariotxt.getText()));
            }
        });


/*        btnGetUsersList1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUsersList();
            }
        });

*/
        btnAddUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio_Sesion.this, UsuarioActividad.class);
                intent.putExtra("user_name", "");
                intent.putExtra("user_tutor", "");
                startActivity(intent);
            }
        });
/*
        raspberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send1_raspberry("Raspberry");
            }
        });

        Robot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_arduino("Robot");
            }
        });
*/

    }

//Conexion 1
    public void send1_raspberry(String message) {

        // Check that we're actually connected before trying anything
        if (mChatService1.getState() != BluetoothChatService.STATE_CONNECTED) {
            Toast.makeText(this, "No se puede enviar mensaje - no conectado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check that there's actually something to send_arduino
        if (message.length() > 0) {

            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();


            mChatService1.write(send);

            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer1.setLength(0);
        }
    }

    private void disconnect1() {
        if (mChatService1 != null) {
            mChatService1.stop();
        }

        finish();
        restartApp();
    }

    private void msg1(String message) {
        TextView statusView = findViewById(R.id.status4);
        statusView.setText(message);
    }

    private void parseData1(String data) {
        //msg(data);

        // add the message to the buffer
        mInStringBuffer1.append(data);

        // debug - log data and buffer
        //Log.d("data", data);
        //Log.d("mInStringBuffer2", mInStringBuffer2.toString());
        //msg(data.toString());

        // find any complete messages
        String[] messages = mInStringBuffer1.toString().split("\\n");
        int noOfMessages = messages.length;
        // does the last message end in a \n, if not its incomplete and should be ignored
        if (!mInStringBuffer1.toString().endsWith("\n")) {
            noOfMessages = noOfMessages - 1;
        }

        // clean the data buffer of any processed messages
        if (mInStringBuffer1.lastIndexOf("\n") > -1)
            mInStringBuffer1.delete(0, mInStringBuffer1.lastIndexOf("\n") + 1);


    }



    //Conexion 2
    public void send_arduino(String message) {

        // Check that we're actually connected before trying anything
        if (mChatService2.getState() != BluetoothChatService.STATE_CONNECTED) {
            Toast.makeText(this, "No se puede enviar mensaje - no conectado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check that there's actually something to send_arduino
        if (message.length() > 0) {

            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();


            mChatService2.write(send);

            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer2.setLength(0);
        }
    }

    private void disconnect() {
        if (mChatService2 != null) {
            mChatService2.stop();
        }

        finish();
        restartApp();
    }

    private void msg(String message) {
        TextView statusView = findViewById(R.id.status3);
        statusView.setText(message);
    }

    private void parseData(String data) {
        //msg(data);

        // add the message to the buffer
        mInStringBuffer2.append(data);

        // debug - log data and buffer
        //Log.d("data", data);
        //Log.d("mInStringBuffer2", mInStringBuffer2.toString());
        //msg(data.toString());

        // find any complete messages
        String[] messages = mInStringBuffer2.toString().split("\\n");
        int noOfMessages = messages.length;
        // does the last message end in a \n, if not its incomplete and should be ignored
        if (!mInStringBuffer2.toString().endsWith("\n")) {
            noOfMessages = noOfMessages - 1;
        }

        // clean the data buffer of any processed messages
        if (mInStringBuffer2.lastIndexOf("\n") > -1)
            mInStringBuffer2.delete(0, mInStringBuffer2.lastIndexOf("\n") + 1);


    }


    private final Handler mHandler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            //send_arduino(Constants.CLIENT_NAME + "\n");
                            //Log.d("status","connected");
                            int unicode = 0x2714;
                            msg1(deviceName1+"  "+getEmojiByUnicode(unicode));

                            /*animationBluet.setVisibility(View.INVISIBLE);
                            animationBluet.cancelAnimation();

                            btnAddUser1.setVisibility(View.VISIBLE);
                            btnGetUsersList1.setVisibility(View.VISIBLE);
                            Listatxt.setVisibility(View.VISIBLE);

                            iniciarbtn.setVisibility(View.VISIBLE);
                            Usuariotxt.setVisibility(View.VISIBLE);*/

                            // send_arduino the protocol version to the server

                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            //Log.d("status","connecting");
                            msg1("CONECTANDO A: " + deviceName1 + " .........");

                            btnAddUser1.setVisibility(View.INVISIBLE);
                            //btnGetUsersList1.setVisibility(View.INVISIBLE);
                            //Listatxt.setVisibility(View.INVISIBLE);

                            iniciarbtn.setVisibility(View.INVISIBLE);
                            Usuariotxt.setVisibility(View.INVISIBLE);

                            animationBluet.setVisibility(View.VISIBLE);
                            animationBluet.setAnimation("1712-bms-rocket.json");
                            animationBluet.loop(true);
                            animationBluet.playAnimation();
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            //Log.d("status","not connected");

                            msg1("No connectado.");
                            disconnect();
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readData = new String(readBuf, 0, msg.arg1);
                    // message received
                    parseData(readData);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName1 = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != this) {
                        Toast.makeText(getApplicationContext(), "Conectado a: "
                                + mConnectedDeviceName1, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != this) {
                        Toast.makeText(getApplicationContext(), msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    };




    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

    /*
     * -----CONTROLA EL COMPORTAMIENTO DE LA APP
     * Indicamos el mensaje que se muestra intentar conectar la APP
     * Indicamos el mensaje que se muestra establecer la conexion
     * Indicamos el comportamiento de los botones ante la conexion
     * Ejemplo: Que estos no se vizualicen mientras no se realice la conexion exitosa
     * */
    private final Handler mHandler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            //send_arduino(Constants.CLIENT_NAME + "\n");
                            //Log.d("status","connected");
                            int unicode = 0x2714;
                            msg(deviceName2 +"  "+getEmojiByUnicode(unicode));

                            animationBluet.setVisibility(View.INVISIBLE);
                            animationBluet.cancelAnimation();

                            btnAddUser1.setVisibility(View.VISIBLE);
                            //btnGetUsersList1.setVisibility(View.VISIBLE);
                            //Listatxt.setVisibility(View.VISIBLE);

                            iniciarbtn.setVisibility(View.VISIBLE);
                            Usuariotxt.setVisibility(View.VISIBLE);

                            // send_arduino the protocol version to the server

                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            //Log.d("status","connecting");
                            msg("CONECTANDO A: " + deviceName2 + " .........");

                            /*btnAddUser1.setVisibility(View.INVISIBLE);
                            btnGetUsersList1.setVisibility(View.INVISIBLE);
                            Listatxt.setVisibility(View.INVISIBLE);

                            iniciarbtn.setVisibility(View.INVISIBLE);
                            Usuariotxt.setVisibility(View.INVISIBLE);

                            animationBluet.setVisibility(View.VISIBLE);
                            animationBluet.setAnimation("1712-bms-rocket.json");
                            animationBluet.loop(true);
                            animationBluet.playAnimation();*/
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            //Log.d("status","not connected");

                            msg("No connectado.");
                            disconnect();
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readData = new String(readBuf, 0, msg.arg1);
                    // message received
                    parseData(readData);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName2 = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != this) {
                        Toast.makeText(getApplicationContext(), "Conectado a: "
                                + mConnectedDeviceName2, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != this) {
                        Toast.makeText(getApplicationContext(), msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    };

    @Override
    public void onBackPressed() {
        disconnect();


    }

    public void IniciaSesion(final String Usuarionomb){
        if(Usuarionomb.trim().length()>0) {
            //Listatxt.setText("Cargando.......");
            //btnGetUsersList1.setEnabled(false);
            btnAddUser1.setEnabled(false);
            iniciarbtn.setEnabled(false);
            Usuariotxt.setEnabled(false);
            //listView1.setVisibility(View.INVISIBLE);


            animationView.setVisibility(View.VISIBLE);
            animationView.setAnimation("43-emoji-wink.json");
            animationView.loop(true);
            animationView.playAnimation();

            Call<List<Usuario>> call = userService1.getUser();
            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    if (response.isSuccessful()) {
                        listu1 = response.body();

                        //Listatxt.setText("USUARIOS");

                        animationView.setVisibility(View.INVISIBLE);
                        animationView.cancelAnimation();
                        UserAdapter a = new UserAdapter(Inicio_Sesion.this, R.layout.list_tutor, listu1);
/*                    for (int i = 0; i < a.DatosNombre().length; i++) {
                        System.out.println("Nombres "+a.DatosNombre()[i]);
                    }
                    */
                        String Result = metodo(a.DatosId(), a.DatosNombre(), Usuarionomb.trim());
                        if (Result != null) {
                            Toast.makeText(getApplicationContext(), "Bienvenido:  " + Usuarionomb.trim() + ".", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Inicio_Sesion.this, Button.class);
                            intent.putExtra("usr_codigo", String.valueOf(Result));
                            intent.putExtra("usr_nombre", String.valueOf(Usuarionomb.trim()));
                            startActivity(intent);
                            Usuariotxt.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "El jugador no existe.", Toast.LENGTH_SHORT).show();
                        }


                        //btnGetUsersList1.setEnabled(true);
                        btnAddUser1.setEnabled(true);
                        iniciarbtn.setEnabled(true);
                        Usuariotxt.setEnabled(true);
                        //listView1.setVisibility(View.VISIBLE);

                    }
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {
                    Log.d("ERROR:", t.getLocalizedMessage() == null ? "" : t.getLocalizedMessage());
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Ingrese un nombre.", Toast.LENGTH_SHORT).show();
        }
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

    public void getUsersList() {
        //Listatxt.setText("Cargando.......");
        //btnGetUsersList1.setEnabled(false);
        btnAddUser1.setEnabled(false);
        //listView1.setVisibility(View.INVISIBLE);

        animationView.setVisibility(View.VISIBLE);
        animationView.setAnimation("43-emoji-wink.json");
        animationView.loop(true);
        animationView.playAnimation();

        Call<List<Usuario>> call = userService1.getUser();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    listu1 = response.body();

                    //Listatxt.setText("USUARIOS");

                    animationView.setVisibility(View.INVISIBLE);
                    animationView.cancelAnimation();

                    //listView1.setAdapter(new UserAdapter(Inicio_Sesion.this, R.layout.list_user, listu1));
                    //ListView listView = findViewById(R.id.listView3);

                    //setListViewHeightBasedOnChildren(listView);
                    //btnGetUsersList1.setEnabled(true);
                    btnAddUser1.setEnabled(true);
                    //listView1.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.d("ERROR:", t.getLocalizedMessage() == null ? "" : t.getLocalizedMessage());
            }
        });
    }


    private void restartApp() {
        Intent intent = new Intent(getApplicationContext(), Devices.class);
        int mPendingIntentId = 0;
        PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() , mPendingIntent);
        System.exit(0);
    }

    //Adapta la pantalla al tamano del listview generado
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
}
