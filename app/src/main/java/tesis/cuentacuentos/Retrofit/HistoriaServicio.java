package tesis.cuentacuentos.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tesis.cuentacuentos.Modelos.Historia;


public interface HistoriaServicio {

    @GET("historias/{id}/")
    Call<Historia> getHistorias(@Path("id") int id);

    @GET("historias/")
    Call<List<Historia>> getHistoria();

    @POST("historias/")
    Call<Historia> addHistoria(@Body Historia historia);

    @PUT("historias/{id}/")
    Call<Historia> updateHistoria(@Path("id") int id, @Body Historia historia);

    @DELETE("historias/{id}/")
    Call<Historia> deleteHistoria(@Path("id") int id);

}