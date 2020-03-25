package com.springusertrack.config;

import com.springusertrack.yandexgeocoder.Geocoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${yandex.apikey}")
    private String yandexApiKey;

    @Bean
    public Geocoder geocoder() throws Exception {
        return new Geocoder(yandexApiKey);
    }
}
