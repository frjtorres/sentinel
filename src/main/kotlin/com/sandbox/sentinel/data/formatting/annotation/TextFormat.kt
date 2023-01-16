package com.sandbox.sentinel.data.formatting.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class TextFormat(val style: Style) {
    enum class Style {
        UPPER_CASE,
        LOWER_CASE,
        CAPITALIZE_FIRST_WORD,
        CAPITALIZE_EACH_WORD
    }
}
