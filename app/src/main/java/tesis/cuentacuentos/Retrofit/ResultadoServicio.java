package tesis.cuentacuentos.Retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tesis.cuentacuentos.Modelos.Resultado;

public interface ResultadoServicio {

    @POST("resultados/")
    Call<Resultado> addResultado(@Body Resultado resultado);

}
