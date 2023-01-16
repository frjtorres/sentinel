package com.sandbox.sentinel.data.validation.annotation

import com.sandbox.sentinel.data.validation.Constants
import jakarta.validation.Constraint
import jakarta.validation.OverridesAttribute
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import kotlin.reflect.KClass

@MustBeDocumented
@Size(min = 10,
      max = 10)
@Pattern(regexp = Constants.PHONE_REGEX)
@Constraint(validatedBy = [])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class PhoneNumber(
        @get:OverridesAttribute(constraint = Pattern::class, name = "message")
        val message: String = "{sentinel.validation.phone-number.pattern.message}",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)
