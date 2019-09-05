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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tesis.cuentacuentos.Activity.UsuarioActividad;
import tesis.cuentacuentos.Modelos.Preguntas;

import com.stuffaboutcode.bluedot.R;

public class PreguntasAdapter extends ArrayAdapter<Preguntas> {

    private Context context;
    private List<Preguntas> pre;
    private ArrayList<String> lista;
    ArrayList<String> preguntas = new ArrayList<String>();

    public PreguntasAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Preguntas> objects) {
        super(context, resource, objects);
        this.context = context;
        this.pre = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_preguntas, parent, false);
        //System.out.println("codigo raro"+rowView);

        TextView txtPreId = rowView.findViewById(R.id.txtPreId);
        TextView txtPreHis = rowView.findViewById(R.id.txtPreHis);
        TextView txtPregunta1 = rowView.findViewById(R.id.txtPregunta1);
        TextView txtPregunta2 = rowView.findViewById(R.id.txtPregunta2);
        TextView txtPregunta3 = rowView.findViewById(R.id.txtPregunta3);
        TextView txtPregunta4 = rowView.findViewById(R.id.txtPregunta4);
        TextView txtPregunta5 = rowView.findViewById(R.id.txtPregunta5);
        TextView txtPregunta6 = rowView.findViewById(R.id.txtPregunta6);
        TextView txtPregunta7 = rowView.findViewById(R.id.txtPregunta7);
        TextView txtPregunta8 = rowView.findViewById(R.id.txtPregunta8);
        TextView txtPregunta9 = rowView.findViewById(R.id.txtPregunta9);
        TextView txtPregunta10 = rowView.findViewById(R.id.txtPregunta10);


        txtPreId.setText(String.format("#ID: %d", pre.get(pos).getId()));
        txtPreHis.setText(String.format("HISTORIA: %s", pre.get(pos).getHistoria()));
        txtPregunta1.setText(String.format("PREGUNTA 1: %s", pre.get(pos).getPregunta1()));
        txtPregunta2.setText(String.format("PREGUNTA 2: %s", pre.get(pos).getPregunta2()));
        txtPregunta3.setText(String.format("PREGUNTA 3: %s", pre.get(pos).getPregunta3()));
        txtPregunta4.setText(String.format("PREGUNTA 4: %s", pre.get(pos).getPregunta4()));
        txtPregunta5.setText(String.format("PREGUNTA 5: %s", pre.get(pos).getPregunta5()));
        txtPregunta6.setText(String.format("PREGUNTA 6: %s", pre.get(pos).getPregunta6()));
        txtPregunta7.setText(String.format("PREGUNTA 7: %s", pre.get(pos).getPregunta7()));
        txtPregunta8.setText(String.format("PREGUNTA 8: %s", pre.get(pos).getPregunta8()));
        txtPregunta9.setText(String.format("PREGUNTA 9: %s", pre.get(pos).getPregunta9()));
        txtPregunta10.setText(String.format("PREGUNTA 10: %s", pre.get(pos).getPregunta10()));


        //System.out.println("lista nueva"+listanueva);
        //System.out.println(Datos(String.valueOf(users.get(pos).getId()),users.get(pos).getNombre(),users.get(pos).getTutor()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, UsuarioActividad.class);
                intent.putExtra("pre_id", String.valueOf(pre.get(pos).getId()));
                intent.putExtra("pre_historia", pre.get(pos).getHistoria());
                intent.putExtra("pre_pregunta1", pre.get(pos).getPregunta1());
                intent.putExtra("pre_pregunta2", pre.get(pos).getPregunta1());
                intent.putExtra("pre_pregunta3", pre.get(pos).getPregunta1());
                intent.putExtra("pre_pregunta4", pre.get(pos).getPregunta1());
                intent.putExtra("pre_pregunta5", pre.get(pos).getPregunta1());
                intent.putExtra("pre_pregunta6", pre.get(pos).getPregunta1());
                intent.putExtra("pre_pregunta7", pre.get(pos).getPregunta1());
                intent.putExtra("pre_pregunta8", pre.get(pos).getPregunta1());
                intent.putExtra("pre_pregunta9", pre.get(pos).getPregunta1());
                intent.putExtra("pre_pregunta10", pre.get(pos).getPregunta1());


                context.startActivity(intent);
            }
        });

        return rowView;
    }


    public String[] DatosPreguntas() {

        String[] listapre = new String[pre.toArray().length];

        for (int i = 0; i < pre.toArray().length; i++) {

            listapre[i] = (pre.get(i).getPregunta1());
            listapre[i] = (String.valueOf(pre.get(i).getId()));
            listapre[i] = (String.valueOf(pre.get(i).getHistoria()));

            //lista.add(listapre[i]);

        }
        System.out.println("Pregunta 1" + Arrays.toString(listapre));
        return listapre;

    }

}