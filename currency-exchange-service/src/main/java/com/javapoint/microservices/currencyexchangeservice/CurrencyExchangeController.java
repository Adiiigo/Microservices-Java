package com.javapoint.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    Environment environment ;

    @Autowired
    private ExchangeValueRepository repository ;

    @GetMapping("/test")
    public String test(){
        return new String("Test") ;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from ,
                                               @PathVariable String to) {

//        ExchangeValue exchangeValue = new ExchangeValue(1000L ,
//                                                            from ,
//                                                            to ,
//                                                            BigDecimal.valueOf(65) ) ;

        ExchangeValue exchangeValue = repository.findByFromAndTo(from,to) ;

        //Setting the port
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        log.info("{}" , exchangeValue);

        return exchangeValue ;

    }

}
