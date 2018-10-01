package com.soda.nse.batch.batch;

import com.soda.nse.batch.model.NSEBulkDeal;
import com.soda.nse.batch.repository.NSEBulkDealRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NSEBulkDealDBWriter implements ItemWriter<NSEBulkDeal> {

    @Autowired
    private NSEBulkDealRepository nseBulkDealRepository;

    @Override
    public void write(List<? extends NSEBulkDeal> nseBulkDeals) throws Exception {
        System.out.println("Data Saved for nseBulkDeals: " + nseBulkDeals);
        nseBulkDealRepository.saveAll(nseBulkDeals);
    }
}
