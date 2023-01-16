package com.sandbox.sentinel.data.formatting.text

import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.sandbox.sentinel.data.formatting.annotation.TextFormat
import com.sandbox.sentinel.exception.SentinelException
import java.util.*

object TextDeserializerProvider {

    private val textDeserializers: Map<TextFormat.Style, StdDeserializer<String>> = mapOf(
        TextFormat.Style.UPPER_CASE to UpperCaseDeserializer(),
        TextFormat.Style.LOWER_CASE to LowerCaseDeserializer(),
        TextFormat.Style.CAPITALIZE_FIRST_WORD to CapitalizeFirstWordDeserializer(),
        TextFormat.Style.CAPITALIZE_EACH_WORD to CapitalizeEachWordDeserializer()
    )

    fun get(style: TextFormat.Style): StdDeserializer<String> =
        Optional.ofNullable(textDeserializers[style])
            .orElseThrow { SentinelException("text deserializer for style [${style.name}] not found") }
}
