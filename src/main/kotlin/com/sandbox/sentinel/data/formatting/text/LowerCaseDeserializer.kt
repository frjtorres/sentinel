package com.sandbox.sentinel.data.formatting.text

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

class LowerCaseDeserializer : StdDeserializer<String>(String::class.java) {

    override fun deserialize(parser: JsonParser, context: DeserializationContext) = parser.text.lowercase()
}