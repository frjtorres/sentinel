package com.sandbox.sentinel.data.formatting

import com.fasterxml.jackson.databind.introspect.Annotated
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector
import com.sandbox.sentinel.data.formatting.annotation.TemporalFormat
import com.sandbox.sentinel.data.formatting.annotation.TextFormat
import com.sandbox.sentinel.data.formatting.temporal.TemporalSerializerProvider
import com.sandbox.sentinel.data.formatting.text.TextDeserializerProvider
import java.time.temporal.Temporal

class SentinelAnnotationIntrospector : NopAnnotationIntrospector() {

    override fun findDeserializer(annotated: Annotated): Any? {
        annotated.getAnnotation(TextFormat::class.java)?.let {
            return TextDeserializerProvider.get(it.style)
        }

        return null
    }

    @Suppress("UNCHECKED_CAST")
    override fun findSerializer(annotated: Annotated): Any? {
        annotated.getAnnotation(TemporalFormat::class.java)?.let {
            return TemporalSerializerProvider.get(annotated.rawType as Class<out Temporal>)
        }

        return null
    }
}