package com.example.FilmoTokio.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilmDTO {
    private String title;
    private String year;
    private String  duration;
    private String director;
    private String guionista;
    private String actor;
    private String musico;
    private  String fotografo;
    private String synopsis;
    private MultipartFile poster;

}
