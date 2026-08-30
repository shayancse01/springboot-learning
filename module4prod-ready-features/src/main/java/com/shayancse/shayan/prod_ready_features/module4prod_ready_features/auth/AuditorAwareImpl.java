package com.shayancse.shayan.prod_ready_features.module4prod_ready_features.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of("Shayan");
        //But we should use spring security here
        //step1 -> get security context
        //step2 -> get authentication
        //step3 -> get the principles
        //step4 -> get the username
        //Then we will use the username here instead of hardcode "Shayan"


    }

}
