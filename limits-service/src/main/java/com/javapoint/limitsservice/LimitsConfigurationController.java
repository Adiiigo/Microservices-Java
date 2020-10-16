package com.javapoint.limitsservice;

import com.javapoint.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javapoint.limitsservice.Configuration ;

@RestController
public class LimitsConfigurationController {

    @Autowired
    Configuration configuration ;

    @GetMapping("/test")
    public String test(){
        return new String("Test") ;
    }

    //configuring fallback method
    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveConfigurations")
    public LimitConfiguration retrieveConfigurations(){
        throw new RuntimeException("Not Available");
    }

    //defining fallback method
    public LimitConfiguration fallbackRetrieveConfigurations(){
        return new LimitConfiguration(326,9) ;
    }

    @GetMapping("/limits")
    public LimitConfiguration retriveLimitsFromConfigurations(){
        /*
            Giving hardcoded value here is not correct
            We should assign these values using configuration file
         */

        /*
            Before configuration class
            return new LimitConfiguration(1000,1) ;
         */
        return new LimitConfiguration(
                configuration.getMaximum(),
                configuration.getMinimum() ) ;
    }

}


