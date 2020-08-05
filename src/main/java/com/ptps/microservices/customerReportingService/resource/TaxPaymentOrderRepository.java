package com.ptps.microservices.customerReportingService.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxPaymentOrderRepository extends JpaRepository<TaxPaymentOrder, Long> {
	
	List<TaxPaymentOrder> findAllByCustomerName(String customerName);
}
