package tesis.cuentacuentos.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.stuffaboutcode.bluedot.R;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;
import tesis.cuentacuentos.Activity.HistoriaActividad;
import tesis.cuentacuentos.Modelos.Historia;

public class HistoriaAdapterObject extends ArrayAdapter<Historia> {

    private Context context;
    private Historia historia;
    Bitmap bitmap;


    public HistoriaAdapterObject(@NonNull Context context, @LayoutRes int resource, @NonNull Historia objects) {
        super(context, resource, Arrays.asList(objects));
        this.context = context;
        this.historia = objects;
    }


    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_historia, parent, false);

        //TextView txtId = (TextView) rowView.findViewById(R.id.textTitulo);
        TextView txtTitulo = rowView.findViewById(R.id.textHisTitulo);
        //ImageView fotoHistoria = (ImageView) rowView.findViewById(R.id.fotoHistoria);
        CircleImageView circleImageView = rowView.findViewById(R.id.profile_image);

        //txtId.setText(String.format("#ID: %d", historia.getId()));
        txtTitulo.setText(String.format("Titulo: %s", historia.getTitulo()));
        String datos = historia.getBase();
        //DatosDescripcion();
        //DatosId();
        //DatosTitulo();
        Bitmap aux = decodeImg(datos);
        circleImageView.setImageBitmap(aux);
        //fotoHistoria.setImageBitmap(aux);


        //Log.e("ERRORrrrrrrrrrrssss: ", String.valueOf(historia.get(pos).getBase()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, HistoriaActividad.class);
                intent.putExtra("his_id", String.valueOf(historia.getId()));
                intent.putExtra("his_titulo", historia.getTitulo());
                intent.putExtra("his_descripcion", historia.getDescripcion());

                context.startActivity(intent);
            }
        });

        return rowView;
    }

    public Bitmap decodeImg(String s) {

        byte[] decodedString = Base64.decode(s, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }


    public String DatosTitulo() {

        String titulo;

        titulo = (historia.getTitulo());

        //System.out.println("LISTADO DESCRIPCION"+titulo);
        return titulo;
    }

    public int DatosID() {

        int codigo;
        codigo = (historia.getId());
        //System.out.print("Listado Id Historia"+codigo);
        return codigo;
    }

    public String DatosDescripcion() {

        String descripcion;
        descripcion = (historia.getDescripcion());
        //System.out.println("LISTADO DESCRIPCION"+descripcion);
        return descripcion;

    }

    public String DatosImagen() {

        String base;
        base = (historia.getBase());
        //System.out.println("LISTADO DESCRIPCION"+descripcion);
        return base;

    }

}
