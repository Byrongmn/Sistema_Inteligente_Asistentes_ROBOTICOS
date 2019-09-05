package tesis.cuentacuentos.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resultado {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("correctas")
    @Expose
    private int correctas;

    @SerializedName("incorrectas")
    @Expose
    private int incorrectas;

    @SerializedName("historia")
    @Expose
    private String historia;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("fecha")
    @Expose
    private String fecha;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrectas() {
        return correctas;
    }

    public void setCorrectas(int correctas) {
        this.correctas = correctas;
    }

    public int getIncorrectas() {
        return incorrectas;
    }

    public void setIncorrectas(int incorrectas) {
        this.incorrectas = incorrectas;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}
