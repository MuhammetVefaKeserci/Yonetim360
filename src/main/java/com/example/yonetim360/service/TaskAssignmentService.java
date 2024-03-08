package com.example.yonetim360.service;

import com.example.yonetim360.controller.TaskAssigmentController;
import com.example.yonetim360.dto.TaskAssignDTO;
import com.example.yonetim360.entities.Beykoz;
import com.example.yonetim360.exceptions.TaskAssignNotFoundException;
import com.example.yonetim360.repository.TaskAssignmentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class TaskAssignmentService {

    private static final Logger LOGGER = Logger.getLogger(TaskAssigmentController.class.getName());

    private final TaskAssignmentRepo taskAssignmentRepo;

    public TaskAssignmentService(TaskAssignmentRepo taskAssignmentRepo) {

        this.taskAssignmentRepo = taskAssignmentRepo;

    }


    public void assignTask(Beykoz beykoz) {

        try {

            taskAssignmentRepo.save(beykoz);

        }
        catch (Exception e){

            LOGGER.warning("Kaydedilemedi");

        }
    }

    public void deleteAssign(TaskAssignDTO taskAssignDTO) {
        Optional<Beykoz> taskAssignOptional = taskAssignmentRepo.findById(taskAssignDTO.getId());

        if (taskAssignOptional.isPresent()) {
            Beykoz beykoz = taskAssignOptional.get();
            beykoz.setDeleted(true);
            taskAssignmentRepo.save(beykoz);
        } else {
            throw new TaskAssignNotFoundException("TaskAssign with ID " + taskAssignDTO.getId() + " not found.");
        }

    }

    public Optional<Beykoz> findByTaskAssign(Long id) {

        Optional<Beykoz> taskAssignOptional = taskAssignmentRepo.findById(id);

        if (taskAssignOptional.isPresent()) {
            Beykoz beykoz = taskAssignOptional.get();
            return Optional.of(beykoz);
        } else {
            throw new TaskAssignNotFoundException("TaskAssign with ID" + id + "not found");
        }

    }

    @Async
    public CompletableFuture<Boolean> getOneDeletedItems(TaskAssignDTO taskAssignDTO) {
        boolean resultLists = taskAssignmentRepo.getResultLists(taskAssignDTO.getId());
        return CompletableFuture.completedFuture(resultLists);
    }

    @Transactional
    public void deleteService(Long id) {
        taskAssignmentRepo.deleteByIsDeletedFalse(id);
        CompletableFuture.completedFuture("İşlem tamamlandı");
    }

    public void delete(Long taskAssign) {
        taskAssignmentRepo.deleteById(taskAssign);
    }

    public List<Beykoz> getDeletedItem(boolean deleted) {
        // Use orElse(Collections.emptyList()) to handle the case when the result is empty
        return taskAssignmentRepo.findByDeletedTrue(deleted);
    }


    @Transactional
    public void updateJobDescription(TaskAssignDTO taskAssignDTO) {
        Beykoz beykoz = taskAssignmentRepo.findById(taskAssignDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Beykoz not found with id: " + taskAssignDTO.getId()));

        beykoz.setJobDescription(taskAssignDTO.getJobDescription());
        taskAssignmentRepo.save(beykoz);
    }


}


