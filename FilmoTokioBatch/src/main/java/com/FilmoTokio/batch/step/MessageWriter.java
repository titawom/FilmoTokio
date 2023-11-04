package com.FilmoTokio.batch.step;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletResponse;


/**
 * ItemWriter
 */
public class MessageWriter implements ItemWriter<String> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HttpServletResponse response;

    @Override
    public void write(Chunk<? extends String> inputMessage) throws Exception {
        for (String outputMsg : inputMessage) {
            this.exportToCsv(outputMsg);
            System.out.println("Received input data from Step:- " + outputMsg);

        }
    }

    public void exportToCsv(String inputMessage) throws IOException {
    FileWriter csvOutputFile = new FileWriter("FilmReview.csv", true);
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
        pw.println(inputMessage);
    }

}

}