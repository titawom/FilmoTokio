package com.example.FilmoTokio.controller;

import com.example.FilmoTokio.DTO.FilmDTO;
import com.example.FilmoTokio.DTO.SearchDTO;
import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Person;
import com.example.FilmoTokio.entity.Review;
import com.example.FilmoTokio.entity.enums.TypePersonEnum;
import com.example.FilmoTokio.service.FilmService;
import com.example.FilmoTokio.service.ReviewService;
import com.example.FilmoTokio.service.ScoreService;
import com.example.FilmoTokio.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    private final ReviewService reviewService;
    private final ScoreService scoreService;
    
    @Autowired
    public FilmController(FilmService filmService, 
                            ReviewService reviewService, 
                            UserService userService, 
                            ScoreService scoreService) {
        this.filmService = filmService;
        this.reviewService = reviewService;
        this.scoreService = scoreService;
    }

    @GetMapping("/new-film")
    public String registration(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = grantedAuthority.iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
        }
        return "film/new-film";
    }

    @PostMapping("/save")
    public String saveFilm(FilmDTO filmDTO, @RequestParam("poster") MultipartFile file) throws IOException {
        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setYear(Integer.parseInt(filmDTO.getYear()));
        film.setDuration(Integer.parseInt(filmDTO.getDuration()));

        Person director = new Person();
        director.setName(filmDTO.getDirector());
        director.setType(TypePersonEnum.DIRECTOR);
        film.setDirector(director);

        Person musician = new Person();
        musician.setName(filmDTO.getMusico());
        musician.setType(TypePersonEnum.MUSICO);
        Set<Person> listMusicians = new HashSet<Person>();
        listMusicians.add(musician);
        film.setMusician(listMusicians);

        Person screenwriter = new Person();
        screenwriter.setName(filmDTO.getGuionista());
        screenwriter.setType(TypePersonEnum.GUIONISTA);
        Set<Person> listScreenwriters = new HashSet<Person>();
        listScreenwriters.add(screenwriter);
        film.setScreenwriter(listScreenwriters);

        Person actor = new Person();
        actor.setName(filmDTO.getActor());
        actor.setType(TypePersonEnum.ACTOR);
        Set<Person> listActors = new HashSet<Person>();
        listActors.add(actor);
        film.setActor(listActors);

        Person photographer = new Person();
        photographer.setName(filmDTO.getFotografo());
        photographer.setType(TypePersonEnum.FOTOGRAFO);
        Set<Person> listPhotographers = new HashSet<Person>();
        listPhotographers.add(photographer);
        film.setPhotographer(listPhotographers); 

        film.setSypnosis(filmDTO.getSynopsis());
        film.setPoster(filmDTO.getPoster().getOriginalFilename());

        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get("FilmoTokioWeb/src/main/resources/static/images", file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        filmService.saveFilm(film);

        return "redirect:/administrador/index";
    } /*En este código, se crea un objeto  Film  y se asignan los valores correspondientes a partir de los datos recibidos en el objeto
     FilmDTO . Se crean objetos  Person  para el director, guionista, actor, músico y fotógrafo, y se asignan a
     las propiedades correspondientes de la película. Luego, se guarda la película utilizando el método  saveFilm  del servicio  filmService . */

    @GetMapping("/search-film")
    public String searchFilm(SearchDTO searchDTO, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = grantedAuthority.iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
        }
        return "film/search-film";
    }

    @PostMapping("/search")
    public String search(SearchDTO searchDTO, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = grantedAuthority.iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
        }

        Film film = filmService.getFilmByTitle(searchDTO.getTitle());
        return "redirect:/films/searched-film/" + film.getId();
    }

    @GetMapping("/searched-film/{id}")
    public String searchedFilm(@PathVariable("id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = grantedAuthority.iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
        }

        Film film = filmService.getFilmById(id);
        model.addAttribute("film", film);
        List<Review> reviews = reviewService.getReviewsByFilm(film);
        model.addAttribute("reviews", reviews);
        model.addAttribute("isScoreDone", scoreService.isScoreDone(film));
        return "film/film";
    } 
}
