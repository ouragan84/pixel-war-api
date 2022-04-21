package com.ouragan.pixelwarapi.Grid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GridRepository extends JpaRepository<GirdData, Long> {

//    @Query("SELECT g FROM grid_data g WHERE g.id = ?1")
//    Optional<GirdData> findGridById(Long id);
}
