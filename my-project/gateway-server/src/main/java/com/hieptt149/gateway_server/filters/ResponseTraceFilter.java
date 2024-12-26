package com.hieptt149.gateway_server.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {

    private static final Logger log = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    private FilterUtil filterUtil;

    @Bean
    public GlobalFilter provideResponseTraceFilter() {
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
            String correlationId = filterUtil.getCorrelationId(requestHeaders);
            if (!exchange.getResponse().getHeaders().containsKey(FilterUtil.CORRELATION_ID)) {
                log.debug("Updated the correlation id to the outbound headers: {}", correlationId);
                exchange.getResponse().getHeaders().add(FilterUtil.CORRELATION_ID, correlationId);
            }
        }));
    }
}
