package com.ingaia.apistarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingaia.apistarter.model.Statistic;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long>{

}
