package com.soda.nse.batch.resource;

import com.soda.nse.batch.repository.NSEBulkDealRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.soda.nse.batch.model.NSEBulkDeal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class NSEBulkDealService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    private NSEBulkDealRepository NSEBulkDealRepository;

    public NSEBulkDealService(NSEBulkDealRepository NSEBulkDealRepository) {
        this.NSEBulkDealRepository = NSEBulkDealRepository;
    }

    @GetMapping
    public BatchStatus load() throws  Exception {


        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());

        System.out.println("Batch is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        return jobExecution.getStatus();
    }

    @GetMapping("/all")
    public List<NSEBulkDeal> getAll() {
        return NSEBulkDealRepository.findAll();
    }

    @GetMapping("/count")
    public long getCount() {
        return NSEBulkDealRepository.count();
    }

    @GetMapping("/year")
    public List<NSEBulkDeal> getYearCount() {
        List<NSEBulkDeal> byCustomQuery = NSEBulkDealRepository.findByCustomQuery(2016);
        System.out.println("byCustomQuery "+ byCustomQuery.size());
        return byCustomQuery;
    }

//    private NSEBulkDealRepository NSEBulkDealRepository;
//    public NSEBulkDealService(NSEBulkDealRepository NSEBulkDealRepository) {
//        this.NSEBulkDealRepository = NSEBulkDealRepository;
//    }
//
//    @GetMapping("/all")
//    public List<NSEBulkDeal> getAll() {
//        return NSEBulkDealRepository.findAll();
//    }
//    @RequestMapping("/{userID}")
//    public Optional<NSEBulkDeal> getUser(@PathVariable(value="userID") Integer id) {
//        return NSEBulkDealRepository.findById(id);
//    }
}
