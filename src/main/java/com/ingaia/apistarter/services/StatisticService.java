package com.ingaia.apistarter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ingaia.apistarter.model.Statistic;
import com.ingaia.apistarter.repository.StatisticRepository;

@Service
public class StatisticService {
	
	@Autowired
	private StatisticRepository statisticRepository;
	
	@Cacheable("statistics")
	public List<Statistic> returnStatistics(){
		List<Statistic> statistics = statisticRepository.findAll();
		
		if(statistics == null || statistics.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return statistics;
	}

}
