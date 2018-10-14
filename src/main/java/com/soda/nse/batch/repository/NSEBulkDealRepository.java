package com.soda.nse.batch.repository;

import com.soda.nse.batch.model.NSEBulkDeal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface NSEBulkDealRepository extends MongoRepository<NSEBulkDeal, Integer> {

    @Query("{$expr:{$eq:[{$year:'$date'}, ?0]}}")
    List<NSEBulkDeal> findByCustomQuery(int year);

}
