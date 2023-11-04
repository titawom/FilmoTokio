package com.FilmoTokio.batch.step;

import org.springframework.batch.item.ItemProcessor;

/**
 * ItemProcessor
 */
public class MessageProcessor implements ItemProcessor<String, String> {

    /**
     * Read input data from itemReader, and then ItemProcessor applies the business logic here
     *
     * @param content
     * @return String
     * @throws Exception
     */
    @Override
    public String process(String content) throws Exception {
        return "[" + content.toUpperCase() + "]";
    }

}