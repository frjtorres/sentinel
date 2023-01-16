package com.sandbox.sentinel.data.model

import com.sandbox.sentinel.data.formatting.annotation.TextFormat
import com.sandbox.sentinel.data.validation.annotation.*
import jakarta.validation.Valid
import jakarta.validation.constraints.Size

data class OnCreateUserModel(
    @field:TextFormat(style = TextFormat.Style.CAPITALIZE_EACH_WORD)
    @field:Name(min = 2, max = 50)
    val firstName: String,
    @field:TextFormat(style = TextFormat.Style.CAPITALIZE_EACH_WORD)
    @field:Name(min = 2, max = 50)
    val lastName: String,
    @field:TextFormat(style = TextFormat.Style.LOWER_CASE)
    @field:Email(min = 6, max = 50)
    val email: String,
    @field:TextFormat(style = TextFormat.Style.LOWER_CASE)
    @field:Nickname(min = 2, max = 30)
    val nickname: String,
    @field:Password(min = 10, max = 30)
    var password: String,
    @field:Valid
    val addresses: Set<OnCreateAddressModel>,
    @field:Valid
    val phones: Set<OnCreatePhoneModel>
)

data class OnCreateAddressModel(
    @field:AlphaCountryCode
    val alphaCountryCode: String,
    @field:TextFormat(style = TextFormat.Style.CAPITALIZE_EACH_WORD)
    @field:Name(min = 2, max = 50)
    val city: String,
    @field:Size(min = 2, max = 100)
    val street: String
)

data class OnCreatePhoneModel(
    @field:CountryCallingCode
    val countryCallingCode: String,
    @field:PhoneNumber
    val phoneNumber: String
)
