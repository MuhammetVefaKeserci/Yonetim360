package com.example.yonetim360.repository;

import com.example.yonetim360.entities.Beykoz;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskAssignmentRepo extends JpaRepository<Beykoz, Long> {


    @Query(value = "SELECT is_deleted FROM Beykoz WHERE id = :id", nativeQuery = true)
    Boolean getResultLists(Long id);


    @Modifying
    @Query(value = "UPDATE Beykoz SET is_deleted = true WHERE id = :id", nativeQuery = true)
    void deleteByIsDeletedFalse(@Param("id") Long id);

    @Query(value = "SELECT * FROM Beykoz WHERE is_deleted = :deleted", nativeQuery = true)
    List<Beykoz> findByDeletedTrue(@Param("deleted") boolean deleted);

    @Modifying
    @Query(value = "UPDATE Beykoz SET job_description = :jobDescription WHERE id = :id", nativeQuery = true)
    void updateJobDescription(@Param("id") Long id, @Param("jobDescription") String jobDes);

}
