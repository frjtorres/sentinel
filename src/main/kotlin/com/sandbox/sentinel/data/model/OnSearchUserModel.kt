package com.sandbox.sentinel.data.model

import com.sandbox.sentinel.data.domain.UserStatus
import com.sandbox.sentinel.data.formatting.annotation.TemporalFormat
import java.time.Instant

data class OnSearchUserModel(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val nickname: String,
    val status: UserStatus,
    val addresses: Set<OnSearchAddressModel>,
    val phones: Set<OnSearchPhoneModel>,
    @field:TemporalFormat
    val createdDate: Instant
)

data class OnSearchAddressModel(
    val id: Long,
    val alphaCountryCode: String,
    val city: String,
    val street: String
)

data class OnSearchPhoneModel(
    val id: Long,
    val countryCallingCode: String,
    val phoneNumber: String
)
