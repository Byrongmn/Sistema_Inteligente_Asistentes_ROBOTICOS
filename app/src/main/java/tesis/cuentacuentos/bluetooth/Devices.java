package tesis.cuentacuentos.bluetooth;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.net.Uri;

import com.stuffaboutcode.bluedot.R;

import java.util.Set;
import java.util.ArrayList;

public class Devices extends AppCompatActivity {

    ListView devicelist;
    ImageButton infoButton;

    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";
    public static String EXTRA_NAME = "device_name";
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Seleccione Raspberry");
        setContentView(R.layout.activity_devices);

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

            if(address.equals("B8:27:EB:2A:55:83")) {
                // Make an intent to start next activity.
                Intent i = new Intent(Devices.this, Devices2.class);
                //Change the activity.
                i.putExtra(EXTRA_NAME, deviceName);
                i.putExtra(EXTRA_ADDRESS, address);
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), "Conecte a raspberry.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}

