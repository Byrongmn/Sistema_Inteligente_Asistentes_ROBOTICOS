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

import java.util.ArrayList;
import java.util.List;

import tesis.cuentacuentos.Activity.UsuarioActividad;
import tesis.cuentacuentos.Modelos.Usuario;

public class UserAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private List<Usuario> users;
    private ArrayList<String> lista = new ArrayList<>();

    public UserAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Usuario> objects) {
        super(context, resource, objects);
        this.context = context;
        this.users = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_user, parent, false);
        //System.out.println("codigo raro"+rowView);

        TextView txtUserId = rowView.findViewById(R.id.txtUserId);
        TextView txtUsername = rowView.findViewById(R.id.txtUsername);
        TextView txtUsertutor = rowView.findViewById(R.id.txtTutor);


        //txtUserId.setText(String.format("#ID: %d", users.get(pos).getId()));
        txtUsername.setText(String.format("Usuario: %s", users.get(pos).getNombre()));
        txtUsertutor.setText(String.format("Id Tutor: %s", users.get(pos).getTutor()));


        //System.out.println("lista nueva"+listanueva);
        //System.out.println(Datos(String.valueOf(users.get(pos).getId()),users.get(pos).getNombre(),users.get(pos).getTutor()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, UsuarioActividad.class);
                intent.putExtra("user_id", String.valueOf(users.get(pos).getId()));
                intent.putExtra("user_name", users.get(pos).getNombre());
                intent.putExtra("user_tutor", users.get(pos).getTutor());
                context.startActivity(intent);
            }
        });

        return rowView;
    }

    public String[] DatosId() {

        String[] listaid = new String[users.toArray().length];


        for (int i = 0; i < users.toArray().length; i++) {
            // System.out.println(users.get(i).getTutor());
            listaid[i] = String.valueOf(users.get(i).getId());


        }
        /*
        System.out.println("\nLISTA DE CODIGOS "+listaid);
        for (int i = 0; i < listaid.length; i++) {
            System.out.println(listaid[i]);
        }*/
        return listaid;

    }

    public String[] DatosNombre() {


        String[] listaNombres = new String[users.toArray().length];

        for (int i = 0; i < users.toArray().length; i++) {

            listaNombres[i] = users.get(i).getNombre();

        }
        /*System.out.println("\nLISTA DATOS NOMBRES "+listaNombres);
        for (int i = 0; i < listaNombres.length; i++) {
            System.out.println(listaNombres[i]);
        }*/
        return listaNombres;

    }
}