package com.sandbox.sentinel.data.formatting.text

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.sandbox.sentinel.data.formatting.Constants

class CapitalizeFirstWordDeserializer : StdDeserializer<String>(String::class.java) {

    override fun deserialize(parser: JsonParser, context: DeserializationContext) =
        parser.text.split(Constants.WORD_REGEX_SEPARATOR)
            .joinToString(separator = Constants.FORMATTED_SEPARATOR_CHAR)
            .lowercase()
            .replaceFirstChar { it.titlecase() }
}