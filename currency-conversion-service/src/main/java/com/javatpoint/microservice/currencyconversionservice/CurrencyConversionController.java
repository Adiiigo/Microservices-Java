package com.javatpoint.microservice.currencyconversionservice;

import io.micrometer.core.ipc.http.HttpSender;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class CurrencyConversionController {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    CurrencyExchangeServiceProxy proxy ;

    @GetMapping("/test")
    public String test(){
        return new String("Test") ;
    }

    public CurrencyConversionController() {
    }

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from ,
                                                  @PathVariable String to ,
                                                  @PathVariable BigDecimal quantity){

        // return new CurrencyConversionBean(1L,from,to,BigDecimal.ONE,quantity,quantity,0) ;

        /*
            Feign Problem 1
         */

        //Setting variables to currency exchange service
        Map<String, String> uriVariables = new HashMap<String,String>() ;

        uriVariables.put("from" ,from) ;
        uriVariables.put("to" , to) ;

        //calling currency-exchange service ;
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate()
                                            .getForEntity("http://localhost:8081/currency-exchange/from/{from}/to/{to}",
                                                CurrencyConversionBean.class , uriVariables) ;

        CurrencyConversionBean response = responseEntity.getBody() ;

        return new CurrencyConversionBean(response.getId() ,
                                          from , to ,
                                          response.getConversionMultiple() ,
                                          quantity ,
                                          quantity.multiply(response.getConversionMultiple()),
                                          response.getPort() ) ;
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from ,
                                                  @PathVariable String to ,
                                                  @PathVariable BigDecimal quantity){

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from,to) ;

        log.info("{}", response);

        return new CurrencyConversionBean(response.getId() ,
                from , to ,
                response.getConversionMultiple() ,
                quantity ,
                quantity.multiply(response.getConversionMultiple()),
                response.getPort() ) ;
    }

}


