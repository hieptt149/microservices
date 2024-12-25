package com.hieptt149.accounts.service.impl;

import com.hieptt149.accounts.dto.*;
import com.hieptt149.accounts.entity.Accounts;
import com.hieptt149.accounts.entity.Customer;
import com.hieptt149.accounts.exception.ResourceNotFoundException;
import com.hieptt149.accounts.mapper.AccountsMapper;
import com.hieptt149.accounts.mapper.CustomerMapper;
import com.hieptt149.accounts.repository.AccountsRepository;
import com.hieptt149.accounts.repository.CustomerRepository;
import com.hieptt149.accounts.service.ICustomersService;
import com.hieptt149.accounts.service.client.CardsFeignClient;
import com.hieptt149.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansResponse = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        customerDetailsDto.setLoansDto(loansResponse.getBody());

        ResponseEntity<CardsDto> cardsResponse = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        customerDetailsDto.setCardsDto(cardsResponse.getBody());

        return customerDetailsDto;
    }
}
