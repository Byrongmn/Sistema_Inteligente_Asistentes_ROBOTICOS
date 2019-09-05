package tesis.cuentacuentos.Retrofit;

public class APIUtils {

    public APIUtils() {
    }
    public static final String valor = "http://172.16.220.178:3306";
    //public static final String valor="http://192.168.1.52:3306";

    public String ip=valor;


    public static final String API_URL = valor+"/usuario/";
    public static final String url = valor+"/tutor/";
    public static final String urlh = valor+"/historia/";
    public static final String urlp = valor+"/pregunta/";
    public static final String urlr = valor+"/resultado/";




    public static UsuarioServicio getUserService(){
        return RetrofitClient.getClient(API_URL).create(UsuarioServicio.class);
    }

    public static TutorServicio getTutorService(){
        return RetrofitClient1.getClient(url).create(TutorServicio.class);
    }

    public static HistoriaServicio getHistoriaService(){
        return RetrofitClient2.getClient(urlh).create(HistoriaServicio.class);
    }

    public static PreguntaServicio getPreguntasService(){
        return RetrofitClient3.getClient(urlp).create(PreguntaServicio.class);
    }

    public static ResultadoServicio getResultadosService(){
        return RetrofitClient4.getClient(urlr).create(ResultadoServicio.class);
    }
}

