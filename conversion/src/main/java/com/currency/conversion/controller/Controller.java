package com.currency.conversion.controller;

import com.currency.conversion.config.CurrencyProxy;
import com.currency.conversion.model.CurrencyConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RefreshScope
@RestController
public class Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private CurrencyProxy proxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    CurrencyConversion currencyConversion(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {
        LOGGER.info("Received Request to convert from {} {} to {}. ", quantity, from, to);
        CurrencyConversion response = proxy.currencyConversion(from, to);
        BigDecimal convertedValue = quantity.multiply(response.getConversionMultiple());
        return new CurrencyConversion(response.getId(), from, to, response.getConversionMultiple(), quantity, convertedValue);
    }
}
