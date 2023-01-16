package com.sandbox.sentinel.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.Locale

@ConfigurationProperties("i18n")
data class I18nProperties(
    val localeResolver: LocaleResolver,
    val messageSource: MessageSource
)

data class LocaleResolver(
    val supportedLocales: List<Locale>,
    val defaultLocale: Locale
)

data class MessageSource(
    val basename: String,
    val defaultEncoding: String
)
