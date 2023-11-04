package com.FilmoTokio.batch.step;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.FilmoTokio.batch.entity.Film;
import com.FilmoTokio.batch.service.FilmService;

/**
 * ItemReader
 */
@Component
public final class MessageReader implements ItemReader<String>, InitializingBean {

    public static boolean returned = false;

    private static List<Film> films;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FilmService filmService;

    /**
     * It read the data from the given source
     *
     * @return String
     * @throws Exception
     */
    @Override
    public String read() throws Exception {

        String message = "";
        for (Film film : films) {
            message = message + "Título: " + film.getTitle() + " - ";
            message = message + "Director: " + film.getDirector().getName() + " - ";
            message = message + "Año: " + film.getYear() + " - ";
            message = message + "Duración: " + film.getDuration();
        }

        //read and pass message to processor to process the message
        if (!returned) {
            returned = true;
            return message;
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        films = filmService.getFilmsNotMigrated();
    }


}