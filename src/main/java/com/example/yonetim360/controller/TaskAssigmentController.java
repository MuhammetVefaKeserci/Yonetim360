package com.example.yonetim360.controller;

import com.example.yonetim360.dto.TaskAssignDTO;
import com.example.yonetim360.entities.Beykoz;
import com.example.yonetim360.service.TaskAssignmentService;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.yonetim360.configuration.MinIoConfiguration.demo;


    @RestController
    @Data
    @RequestMapping("/")
    public class TaskAssigmentController {

        private static final Logger LOGGER = Logger.getLogger(TaskAssigmentController.class.getName());

        private TaskAssignmentService taskAssignmentService;

        public TaskAssigmentController(TaskAssignmentService taskAssignmentService) {

            this.taskAssignmentService = taskAssignmentService;

        }

        public static void main(String[] args){
            MinioClient minioClient = demo();
            try {
                List<Bucket> bList = minioClient.listBuckets();
                System.out.println("coonection success, total buckets: " + bList.size());
            }catch (MinioException e){
                System.out.println("connection failed: " + e.getMessage());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @CrossOrigin(origins = "http://localhost:8081")
        @PostMapping("/assign")
        public ResponseEntity<String> assign(@RequestBody Beykoz beykoz) {

            try {

                taskAssignmentService.assignTask(beykoz);

                return ResponseEntity.ok("Görev Atama Başarılı");

            } catch(Exception e) {

                LOGGER.warning("görev atama sırasında bir hata oluştu" + e.getMessage());

                return ResponseEntity.status(500).body("görev atama sırasında bir hata oluştu");

            }
        }

        @DeleteMapping("/delete")
        public void delete(@RequestBody Beykoz beykoz) {

            taskAssignmentService.delete(beykoz.getId());

        }

        @DeleteMapping("/assignDelete")
        public void deleteAssign(@RequestBody TaskAssignDTO taskAssignDTO) {

            try {
                List<Beykoz> optionalBeykoz = taskAssignmentService.getDeletedItem(taskAssignDTO.isDeleted());

                if (optionalBeykoz.isEmpty()) {

                    LOGGER.log(Level.SEVERE, "Bu Öğe bulunmamaktadır");

                } else {

                    taskAssignmentService.deleteAssign(taskAssignDTO);

                }
            } catch (Exception e) {
                // Handle the exception
                LOGGER.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
                // You may also throw a custom exception or return an error response depending on your needs
            }
        }

        @PutMapping("/update")
        public ResponseEntity<String> updateAssign(@RequestBody TaskAssignDTO taskAssignDTO){

            try {
                    List<Beykoz> optionalBeykoz = taskAssignmentService.getDeletedItem(taskAssignDTO.isDeleted());

                  if (optionalBeykoz.isEmpty())
                  {

                     LOGGER.log(Level.SEVERE, "Bu Öğe bulunmamaktadır");

                     return ResponseEntity.badRequest().body("Görev Bulunamadı");

                  } else {  

                    taskAssignmentService.updateJobDescription(taskAssignDTO);

                    return ResponseEntity.ok("İşlem Başarılı");

                  }
                }
            catch (Exception e){

                LOGGER.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Güncelleme sırasında bir hata oluştu.");

            }
        }
    }