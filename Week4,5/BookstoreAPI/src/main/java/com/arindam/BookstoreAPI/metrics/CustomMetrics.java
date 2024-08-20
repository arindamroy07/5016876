package com.arindam.BookstoreAPI.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {
    @Autowired
    private MeterRegistry meterRegistry;

    @PostConstruct
    public void init() {
        meterRegistry.counter("custom.metric.book.count").increment();
    }
}
