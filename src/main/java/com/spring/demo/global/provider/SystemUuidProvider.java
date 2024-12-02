package com.spring.demo.global.provider;

import com.spring.demo.global.provider.port.UuidProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class SystemUuidProvider implements UuidProvider {

    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }

}
