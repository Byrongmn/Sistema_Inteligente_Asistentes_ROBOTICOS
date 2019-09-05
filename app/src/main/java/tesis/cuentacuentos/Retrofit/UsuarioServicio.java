package tesis.cuentacuentos.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tesis.cuentacuentos.Modelos.Usuario;

public interface UsuarioServicio {

    @GET("usuarios/")
    Call<List<Usuario>> getUser();

    @GET("usuarios/")
    Call<List<Usuario>> getUser1(@Path("id") int id);

    @POST("usuarios/")
    Call<Usuario> addUser(@Body Usuario user);

    @PUT("usuarios/{id}/")
    Call<Usuario> updateUser(@Path("id") int id, @Body Usuario usuario);

    @DELETE("usuarios/{id}/")
    Call<Usuario> deleteUser(@Path("id") int id);
}
