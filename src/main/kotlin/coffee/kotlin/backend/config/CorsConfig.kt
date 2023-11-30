package coffee.kotlin.backend.config

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configurable
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource();
        val config = CorsConfiguration();

        config.allowedOrigins = listOf("*");
        config.allowedMethods = listOf("POST", "GET", "PUT", "PATCH", "DELETE", "OPTIONS");
        config.allowedHeaders = listOf("Origins", "Contents-Type", "Accept", "Authorization");

        // caching time, if maxAge is 0, not use caching.
        config.maxAge = 3600

        return CorsFilter(source);
    }

}