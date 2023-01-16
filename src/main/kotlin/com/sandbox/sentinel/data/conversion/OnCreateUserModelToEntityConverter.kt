package com.sandbox.sentinel.data.conversion

import com.sandbox.sentinel.data.domain.UserStatus
import com.sandbox.sentinel.data.entity.AddressEntity
import com.sandbox.sentinel.data.entity.PhoneEntity
import com.sandbox.sentinel.data.entity.UserEntity
import com.sandbox.sentinel.data.model.OnCreateUserModel
import org.springframework.core.convert.converter.Converter

class OnCreateUserModelToEntityConverter : Converter<OnCreateUserModel, UserEntity> {

    override fun convert(source: OnCreateUserModel) = UserEntity(
        firstName = source.firstName,
        lastName = source.lastName,
        email = source.email,
        nickname = source.nickname,
        password = source.password,
        status = UserStatus.ACTIVE,
        addresses = source.addresses
            .map {
                AddressEntity(
                    alphaCountryCode = it.alphaCountryCode,
                    city = it.city,
                    street = it.street
                )
            }
            .toSet(),
        phones = source.phones
            .map {
                PhoneEntity(
                    countryCallingCode = it.countryCallingCode,
                    phoneNumber = it.phoneNumber
                )
            }
            .toSet()
    )
}