package example.android.ubsmanaus.Model;

import java.io.Serializable;

public class Ubs implements Serializable {
    public long id;
    public String nome;
    public String endereco;
    public String bairro;
    public String latitude;
    public String longitude;
    public String servicos;
    public String url_foto;
    public String zona;

    public Ubs(long id, String nome, String endereco, String bairro, String latitude,
               String longitude, String servicos, String url_foto, String zona) {

        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.servicos = servicos;
        this.url_foto = url_foto;
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Ubs{" +
                " nome='" + nome + '\'' +
                " bairro='" + bairro + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getServicos() {
        return servicos;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public String getZona() {
        return zona;
    }
}
