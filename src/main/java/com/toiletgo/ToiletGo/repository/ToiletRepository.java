package com.toiletgo.ToiletGo.repository;

import com.toiletgo.ToiletGo.entity.Toilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToiletRepository extends JpaRepository<Toilet, Long> {
    List<Toilet> findActiveToilets();
}
