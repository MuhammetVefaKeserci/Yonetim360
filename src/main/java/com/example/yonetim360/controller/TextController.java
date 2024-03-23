package com.example.yonetim360.controller;

import com.example.yonetim360.entities.Text;
import com.example.yonetim360.service.TextService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@Data
@RequestMapping("/text")
public class TextController {

    private static final Logger LOGGER = Logger.getLogger(TaskAssigmentController.class.getName());

    private TextService textService;
    public TextController(TextService textService) {
        this.textService = textService;
    }

    @CrossOrigin(origins = "http://localhost:9091")
    @PostMapping("/send")
    public ResponseEntity<String> textSend(@RequestBody Text text) {

        try {

            textService.textSend(text);

            return ResponseEntity.ok("Görev Atama Başarılı");

        } catch(Exception e) {

            LOGGER.warning("görev atama sırasında bir hata oluştu" + e.getMessage());

            return ResponseEntity.status(500).body("görev atama sırasında bir hata oluştu");

        }
    }
}
