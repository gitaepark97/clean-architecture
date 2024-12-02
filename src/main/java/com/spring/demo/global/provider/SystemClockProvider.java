package com.spring.demo.global.provider;

import com.spring.demo.global.provider.port.ClockProvider;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
class SystemClockProvider implements ClockProvider {

    @Override
    public long millis() {
        return Clock.systemUTC().millis();
    }

}
