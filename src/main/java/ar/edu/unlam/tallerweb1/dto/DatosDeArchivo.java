package ar.edu.unlam.tallerweb1.dto;

import org.springframework.web.multipart.MultipartFile;

public class DatosDeArchivo {

    private MultipartFile archivo;


    public MultipartFile getArchivo(){
        return archivo;
    }

    public void setArchivo (MultipartFile archivo){
        this.archivo=archivo;
    }
}
