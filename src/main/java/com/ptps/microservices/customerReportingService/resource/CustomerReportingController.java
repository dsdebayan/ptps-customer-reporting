package com.ptps.microservices.customerReportingService.resource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class CustomerReportingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerReportingController.class);

	@Autowired
	private TaxPaymentOrderRepository repository;

	@GetMapping("/")
	@ApiOperation(value = "Health Check")
	public String imHealthy() {
		return "{healthy:true}";
	}

	@GetMapping("/customer-reporting/orders/{customerName}/{year}/{month}")
	@ApiOperation(value = "Retrieve Payment Order for a customer for specific year and month")
	public List<TaxPaymentOrder> retrievePaymentOrder(@PathVariable String customerName,
			@PathVariable int year, @PathVariable int month) {

		List<TaxPaymentOrder> taxPaymentOrders = repository.findAllByCustomerName(customerName);
		List<TaxPaymentOrder> filteredTpos = new ArrayList<>();

		int yearTmp;
		int monthTmp;
		for (TaxPaymentOrder tpo : taxPaymentOrders) {
			if (tpo.getSubmittedDate() != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(tpo.getSubmittedDate());
				yearTmp = cal.get(Calendar.YEAR);
				monthTmp = cal.get(Calendar.MONTH);
				if (yearTmp == year && monthTmp == (month - 1)) {
					filteredTpos.add(tpo);
				}

			}
		}

		LOGGER.info("{}", filteredTpos);
		return filteredTpos;
	}

}
