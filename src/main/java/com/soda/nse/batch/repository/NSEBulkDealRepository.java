package com.soda.nse.batch.repository;

import com.soda.nse.batch.model.NSEBulkDeal;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface NSEBulkDealRepository extends MongoRepository<NSEBulkDeal, Integer> {

}
