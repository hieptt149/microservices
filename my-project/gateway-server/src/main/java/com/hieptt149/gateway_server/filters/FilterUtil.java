package com.hieptt149.gateway_server.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Optional;


@Component
public class FilterUtil {

    public static final String CORRELATION_ID = "eazybank-correlation-id";

    public String getCorrelationId(HttpHeaders headers) {
        if (headers.get(CORRELATION_ID) != null) {
            List<String> requestHeaderList = headers.get(CORRELATION_ID);
            return Optional.ofNullable(requestHeaderList)
                    .map(list -> list.get(0))
                    .orElse(null);
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String key, String value) {
        return exchange.mutate()
                .request(
                        exchange.getRequest()
                                .mutate()
                                .header(key, value)
                                .build()
                )
                .build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }
}
