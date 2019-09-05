package tesis.cuentacuentos.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stuffaboutcode.bluedot.R;

import java.util.ArrayList;
import java.util.Set;

public class Devices2 extends AppCompatActivity {

    ListView devicelist;
    ImageButton infoButton;
    public static String address1 = null;
    public static String deviceName1 = null;
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;


    public static String EXTRA_ADDRESS1 = "device_address1";
    public static String EXTRA_NAME1 = "device_name1";
    public static String EXTRA_ADDRESS2 = "device_address2";
    public static String EXTRA_NAME2 = "device_name2";
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Seleccione HC-05");
        setContentView(R.layout.activity_devices);
        //ConstraintLayout ct = findViewById(R.id.device_bg);
        //ct.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.historia_bg));

        Intent newint = getIntent();
        deviceName1 = newint.getStringExtra(Devices.EXTRA_NAME);
        address1 = newint.getStringExtra(Devices.EXTRA_ADDRESS);

        devicelist = findViewById(R.id.listView);
        infoButton = findViewById(R.id.infoButton);

        //if the device has bluetooth
        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if (myBluetooth == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();

            //finish apk
            this.finish();
            System.exit(0);

        } else if (!myBluetooth.isEnabled()) {
            //Ask to the user turn the bluetooth on
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon, 1);
        }

        infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("https://bluedot.readthedocs.io");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        pairedDevicesList();

    }

    private void pairedDevicesList() {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() > 0) {
            // create a list of paired bluetooth devices
            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address2
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        adapter = new ArrayAdapter(this, R.layout.nombre_dispositivos, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            // Get the device MAC address2, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String deviceName = info.split("\n")[0];
            String address = info.split("\n")[1];
            //CASA
            //if(address.equals("20:16:07:05:99:98")) {
            //ROBOT
            if(address.equals("98:D3:71:FD:3B:19")) {
            // Make an intent to start next activity.
            Intent i = new Intent(Devices2.this, Inicio_Sesion.class);
            //Change the activity.
            //Conexion 1
            i.putExtra(EXTRA_NAME1, deviceName1);
            i.putExtra(EXTRA_ADDRESS1, address1);
            //Conexion 2
            i.putExtra(EXTRA_NAME2, deviceName);
            i.putExtra(EXTRA_ADDRESS2, address);
            startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), "Conecte a HC-05.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}

