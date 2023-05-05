package com.titzko.freemarkertodo.repository;

import com.titzko.freemarkertodo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {


    @Modifying
    @Query("""
              update  Task t SET t.done =
              CASE t.done
                WHEN TRUE THEN FALSE
                    ELSE TRUE END
            where t.id = :id
            """)
    void updateTaskStatus(@Param("id") Long id);

    List<Task> findAllByUserId(Long userId);
}
