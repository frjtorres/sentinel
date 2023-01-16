package com.sandbox.sentinel.data.conversion

import com.sandbox.sentinel.data.entity.UserEntity
import com.sandbox.sentinel.data.model.OnSearchAddressModel
import com.sandbox.sentinel.data.model.OnSearchPhoneModel
import com.sandbox.sentinel.data.model.OnSearchUserModel
import org.springframework.core.convert.converter.Converter

class EntityToOnSearchUserModelConverter : Converter<UserEntity, OnSearchUserModel> {

    override fun convert(source: UserEntity) = OnSearchUserModel(
        id = source.id!!,
        firstName = source.firstName,
        lastName = source.lastName,
        email = source.email,
        nickname = source.nickname,
        status = source.status,
        addresses = source.addresses
            .map {
                OnSearchAddressModel(
                    id = it.id!!,
                    alphaCountryCode = it.alphaCountryCode,
                    city = it.city,
                    street = it.street
                )
            }
            .toSet(),
        phones = source.phones
            .map {
                OnSearchPhoneModel(
                    id = it.id!!,
                    countryCallingCode = it.countryCallingCode,
                    phoneNumber = it.phoneNumber
                )
            }
            .toSet(),
        createdDate = source.createdDate!!
    )
}
