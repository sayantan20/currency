package com.currency.exchange.controller;

import com.currency.exchange.model.ExchangeValue;
import com.currency.exchange.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
@RequestMapping(path = "/currency")
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/{from}/to/{to}")
    public ExchangeValue exchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
        ExchangeValue value = exchangeValueRepository.findByFromAndTo(from, to);
        LOGGER.info("{}{}{}", from, to, value);
        if (value != null)
            return value;
        else
            throw new RuntimeException(String.format("Unable to find data to convert %s to %s", from, to));
    }
}
