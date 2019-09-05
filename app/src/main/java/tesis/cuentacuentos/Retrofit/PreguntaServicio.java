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
import tesis.cuentacuentos.Modelos.Preguntas;

public interface PreguntaServicio {

    @GET("preguntas/{id}/")
    Call<Preguntas> getPreguntasid(@Path("id") int id);

    @GET("preguntas/")
    Call<List<Preguntas>> getPreguntas();

    @POST("preguntas/")
    Call<Preguntas> addPreguntas(@Body Preguntas preguntas);

    @PUT("preguntas/{id}/")
    Call<Preguntas> updatePreguntas(@Path("id") int id, @Body Preguntas preguntas);

    @DELETE("preguntas/{id}/")
    Call<Preguntas> deletePreguntas(@Path("id") int id);
}
