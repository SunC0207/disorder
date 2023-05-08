package com.disorder.infomation.Repository;

import com.disorder.infomation.Entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoRepository extends JpaRepository<Info, Integer> {
    @Query(value = "SELECT * FROM info ORDER BY date DESC",nativeQuery = true)
    List<Info> findAllOrdByDate();
}
