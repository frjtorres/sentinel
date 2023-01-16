package com.sandbox.sentinel.data.formatting.temporal

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.Instant
import java.time.format.DateTimeFormatter

class InstantSerializer : StdSerializer<Instant>(Instant::class.java) {

    override fun serialize(value: Instant, generator: JsonGenerator, provider: SerializerProvider) {
        generator.writeString(DateTimeFormatter.ISO_INSTANT.format(value))
    }
}