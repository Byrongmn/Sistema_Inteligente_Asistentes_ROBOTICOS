package tesis.cuentacuentos.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historia {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("titulo")
    @Expose
    private String titulo;

    @SerializedName("base")
    @Expose
    private String base;


    @SerializedName("audio")
    @Expose
    private String audio;


    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Historia() {
    }

    public Historia(int id, String titulo, String base, String descripcion, String baseaudio) {
        this.id = id;
        this.titulo = titulo;
        this.base = base;
        this.descripcion=descripcion;
        this.audio=baseaudio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}

