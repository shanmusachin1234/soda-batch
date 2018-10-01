package com.soda.nse.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class NSEBulkDealBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(NSEBulkDealBatchApplication.class, args);
	}
}
