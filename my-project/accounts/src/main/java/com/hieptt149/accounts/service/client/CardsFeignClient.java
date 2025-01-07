package com.hieptt149.accounts.service.client;

import com.hieptt149.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static com.hieptt149.accounts.constants.AccountsConstants.EAZYBANK_CORRELATION_ID;

@FeignClient(name = "cards", url = "http://cards:9000", fallback = CardsFallback.class)
public interface CardsFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader(EAZYBANK_CORRELATION_ID) String correlationId, @RequestParam String mobileNumber);
}
