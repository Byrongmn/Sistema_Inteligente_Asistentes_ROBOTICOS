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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import tesis.cuentacuentos.Activity.HistoriaActividad;
import tesis.cuentacuentos.Modelos.Historia;

public class HistoriaAdapter extends ArrayAdapter<Historia> {

    private Context context;
    private List<Historia> historia;
    Bitmap bitmap;

    String bdefecto="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";
    byte[] imagdefecto= Base64.decode(bdefecto, Base64.DEFAULT);


    public HistoriaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Historia> objects) {
        super(context, resource, objects);
        this.context = context;
        this.historia = objects;
    }


    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_historia, parent, false);

        TextView txtTitulo = rowView.findViewById(R.id.textHisTitulo);
        CircleImageView fotoHistoria = rowView.findViewById(R.id.profile_image);

        txtTitulo.setText(String.format(historia.get(pos).getTitulo()));
        String datos = historia.get(pos).getBase();
        Bitmap aux = decodeImg(datos);
        fotoHistoria.setImageBitmap(aux);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, HistoriaActividad.class);
                intent.putExtra("his_id", String.valueOf(historia.get(pos).getId()));
                intent.putExtra("his_titulo", historia.get(pos).getTitulo());
                intent.putExtra("his_base", historia.get(pos).getBase());
                intent.putExtra("his_descripcion", historia.get(pos).getDescripcion());
                intent.putExtra("his_audio", historia.get(pos).getAudio());

                context.startActivity(intent);
            }
        });

        return rowView;
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

    public String[] DatosId() {

        String[] listaid = new String[historia.toArray().length];


        for (int i = 0; i < historia.toArray().length; i++) {
            // System.out.println(users.get(i).getTutor());
            listaid[i] = (String.valueOf(historia.get(i).getId()));


        }
        //System.out.println("LISTA ID hISTORIA"+String.valueOf(listaid));
        return listaid;

    }

    public String[] DatosTitulo() {
        String[] listaNombres = new String[historia.toArray().length];

        for (int i = 0; i < historia.toArray().length; i++) {

            listaNombres[i] = (historia.get(i).getTitulo());

        }
        //System.out.println("LISTADO TITULO HISTORIA"+listaNombres);
        return listaNombres;
    }

    public String Datos() {


        return null;
    }

    public String[] DatosDescripcion() {


        String[] listaNombres = new String[historia.toArray().length];

        for (int i = 0; i < historia.toArray().length; i++) {

            listaNombres[i] = (historia.get(i).getDescripcion());

        }
        //System.out.println("LISTADO DESCRIPCION"+listaNombres);
        return listaNombres;

    }

}
