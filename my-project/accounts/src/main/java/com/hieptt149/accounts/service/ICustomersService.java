package com.hieptt149.accounts.service;

import com.hieptt149.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber);
}
