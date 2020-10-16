package com.javatpoint.microservice.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//1. //@FeignClient(name="currency-exchange-service" , url="localhost:8081")
@RibbonClient(name="currency-exchange-service")
//2. //@FeignClient(name="currency-exchange-service" )
//3.
@FeignClient(name="netflix-zuul-api-gateway-server")
public interface CurrencyExchangeServiceProxy {

    /*
    *  Consider a scenario in which currency-exchange-service offers fifteen different services.
    *   All the details related to these services must be defined in one place that is
    *   CurrencyExchangeServiceProxy interface.
    *
    *
    * */

//  @GetMapping("/currency-exchange/from/{from}/to/{to}")
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
