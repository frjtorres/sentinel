package com.sandbox.sentinel.configuration

import com.sandbox.sentinel.configuration.properties.I18nProperties
import org.hibernate.cfg.AvailableSettings
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver

@Configuration
class I18nConfigurer(val i18nProperties: I18nProperties) {

    @Bean
    fun localeResolver(): LocaleResolver {
        val localeResolver = AcceptHeaderLocaleResolver()
        localeResolver.setDefaultLocale(i18nProperties.localeResolver.defaultLocale)
        localeResolver.setSupportedLocales(i18nProperties.localeResolver.supportedLocales)
        return localeResolver
    }

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename(i18nProperties.messageSource.basename)
        messageSource.setDefaultEncoding(i18nProperties.messageSource.defaultEncoding)
        return messageSource
    }

    @Bean
    fun localValidatorFactoryBean(messageSource: MessageSource): LocalValidatorFactoryBean {
        val localValidatorFactoryBean = LocalValidatorFactoryBean()
        localValidatorFactoryBean.setValidationMessageSource(messageSource)
        return localValidatorFactoryBean
    }

    @Bean
    fun hibernatePropertiesCustomizer(localValidatorFactoryBean: LocalValidatorFactoryBean): HibernatePropertiesCustomizer {
        return HibernatePropertiesCustomizer {
            properties: MutableMap<String, Any> ->
                properties[AvailableSettings.JAKARTA_VALIDATION_FACTORY] = localValidatorFactoryBean
        }
    }
}
