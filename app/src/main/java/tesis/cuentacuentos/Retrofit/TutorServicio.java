package tesis.cuentacuentos.Retrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tesis.cuentacuentos.Modelos.Tutor;

public interface TutorServicio {

    @GET("tutores/")
    Call<List<Tutor>> getTutor();

    @POST("tutores/")
    Call<Tutor> addTutor(@Body Tutor tutor);

    @PUT("tutores/{id}/")
    Call<Tutor> updateTutor(@Path("id") int id, @Body Tutor tutor);

    @DELETE("tutores/{id}/")
    Call<Tutor> deleteTutor(@Path("id") int id);
}
