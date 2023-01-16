package com.sandbox.sentinel.configuration

import com.fasterxml.jackson.databind.AnnotationIntrospector
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.sandbox.sentinel.data.conversion.ConversionServiceWrapper
import com.sandbox.sentinel.data.formatting.SentinelAnnotationIntrospector
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.support.DefaultConversionService
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
@ConfigurationPropertiesScan
class SentinelConfigurer {

    @Bean
    fun objectMapper(): JsonMapper = JsonMapper.builder()
        .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .annotationIntrospector(
            AnnotationIntrospector.pair(
                JacksonAnnotationIntrospector(),
                SentinelAnnotationIntrospector()
            )
        )
        .findAndAddModules()
        .addModule(
            KotlinModule.Builder()
                .enable(KotlinFeature.StrictNullChecks)
                .build()
        )
        .build()

    @Bean
    fun conversionServiceWrapper() : ConversionServiceWrapper {
        val conversionServiceWrapper = ConversionServiceWrapper(DefaultConversionService())
        conversionServiceWrapper.addLocalConverters()
        return conversionServiceWrapper
    }
}
