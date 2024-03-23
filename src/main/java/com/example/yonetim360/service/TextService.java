package com.example.yonetim360.service;


import com.example.yonetim360.entities.Text;
import com.example.yonetim360.repository.TextRepo;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class TextService {

    private final TextRepo textRepo;
    public TextService(TextRepo textRepo) {
        this.textRepo = textRepo;
    }

    private static final Logger LOGGER = Logger.getLogger(TextService.class.getName());

    public void textSend(Text text) {

        try {
            textRepo.save(text);
        }
        catch (Exception e) {
            LOGGER.warning("yazı eklenmedi");
        }
    }
}
