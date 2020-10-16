package com.javapoint.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    // Query method
    ExchangeValue findByFromAndTo(String from, String to) ;

}
