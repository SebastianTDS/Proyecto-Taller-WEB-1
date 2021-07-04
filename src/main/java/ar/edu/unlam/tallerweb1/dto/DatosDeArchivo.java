package ar.edu.unlam.tallerweb1.dto;

import org.springframework.web.multipart.MultipartFile;

public class DatosDeArchivo {

    private MultipartFile archivo;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MultipartFile getArchivo() {
        return archivo;
    }

    public void setArchivo(MultipartFile archivo) {
        this.archivo = archivo;
    }

}
