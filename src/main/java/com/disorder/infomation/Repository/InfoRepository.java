package com.disorder.infomation.Repository;

import com.disorder.infomation.Entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Integer> {
}
