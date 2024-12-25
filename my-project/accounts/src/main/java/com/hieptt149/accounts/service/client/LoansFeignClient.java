package com.hieptt149.accounts.service.client;

import com.hieptt149.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static com.hieptt149.accounts.constants.AccountsConstants.EAZYBANK_CORRELATION_ID;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader(EAZYBANK_CORRELATION_ID) String correlationId, @RequestParam String mobileNumber);
}
