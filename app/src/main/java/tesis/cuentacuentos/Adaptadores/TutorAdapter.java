package tesis.cuentacuentos.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stuffaboutcode.bluedot.R;

import java.util.List;

import tesis.cuentacuentos.Activity.TutorActividad;
import tesis.cuentacuentos.Modelos.Tutor;

//import com.ups.edu.ec.cuentame.ejemplocrud.Modelos.Tutor;

public class TutorAdapter extends ArrayAdapter<Tutor> {

    private Context context;
    private List<Tutor> tutor;


    public TutorAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Tutor> objects) {
        super(context, resource, objects);
        this.context = context;
        this.tutor = objects;
    }


    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_tutor, parent, false);

        TextView txtTutorId = rowView.findViewById(R.id.txtTutorId);
        TextView txtTutorNombre = rowView.findViewById(R.id.txtTutorNombre);


        txtTutorId.setText(String.format("#ID: %d", tutor.get(pos).getId()));

        txtTutorNombre.setText(String.format("Nombre: %s", tutor.get(pos).getNombre()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, TutorActividad.class);
                intent.putExtra("tutor_id", String.valueOf(tutor.get(pos).getId()));
                intent.putExtra("tutor_nombre", tutor.get(pos).getNombre());
                context.startActivity(intent);
            }
        });

        return rowView;
    }

    public String[] DatosId() {

        String[] listaid = new String[tutor.toArray().length];


        for (int i = 0; i < tutor.toArray().length; i++) {
            // System.out.println(users.get(i).getTutor());
            listaid[i] = String.valueOf(tutor.get(i).getId());


        }
        /*
        System.out.println("\nLISTA DE CODIGOS "+listaid);
        for (int i = 0; i < listaid.length; i++) {
            System.out.println(listaid[i]);
        }*/
        return listaid;

    }

    public String[] DatosNombre() {


        String[] listaNombres = new String[tutor.toArray().length];

        for (int i = 0; i < tutor.toArray().length; i++) {

            listaNombres[i] = tutor.get(i).getNombre();

        }
        /*System.out.println("\nLISTA DATOS NOMBRES "+listaNombres);
        for (int i = 0; i < listaNombres.length; i++) {
            System.out.println(listaNombres[i]);
        }*/
        return listaNombres;

    }


}