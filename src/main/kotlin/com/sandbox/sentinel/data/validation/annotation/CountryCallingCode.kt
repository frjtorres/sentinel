package com.sandbox.sentinel.data.validation.annotation

import com.sandbox.sentinel.data.validation.Constants
import jakarta.validation.Constraint
import jakarta.validation.OverridesAttribute
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

@MustBeDocumented
@Pattern(regexp = Constants.COUNTRY_CALLING_CODE_REGEX)
@Constraint(validatedBy = [])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class CountryCallingCode(
        @get:OverridesAttribute(constraint = Pattern::class, name = "message")
        val message: String = "{sentinel.validation.country-calling-code.message}",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)
