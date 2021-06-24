package com.currency.conversion.config;

import com.currency.conversion.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-service")
public interface CurrencyProxy {
    @GetMapping("/currency/{from}/to/{to}")
    CurrencyConversion currencyConversion(@PathVariable("from") String from, @PathVariable("to") String to);

}
