package com.getir.casestudy.repository;

import com.getir.casestudy.domain.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IStatisticRepository extends MongoRepository<Statistic, String> {
    List<Statistic> findAllByUserId(String userId);
}
