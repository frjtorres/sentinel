package com.sandbox.sentinel.data.conversion

import com.sandbox.sentinel.data.conversion.Constants.LOCALE_REGEX_SEPARATOR
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.Locale

@Component
@ConfigurationPropertiesBinding
class StringToLocalesConverter : Converter<String, List<Locale>> {

    override fun convert(source: String) = source
        .split(LOCALE_REGEX_SEPARATOR)
        .map { Locale(it) }
}