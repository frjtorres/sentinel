package com.sandbox.sentinel.data.validation

object Constants {

    const val ALPHA_COUNTRY_CODE_REGEX = "^([A-Z]){3}$"
    const val COUNTRY_CALLING_CODE_REGEX = "^\\+(\\d{1}\\-)?(\\d{1,3})$"
    const val EMAIL_REGEX = "^(?=.*@)[A-Za-z0-9_\\-]+(\\.[A-Za-z0-9_\\-]+)*@[^-][A-Za-z0-9\\-]+(\\.[A-Za-z0-9\\-]+)?(\\.[A-Za-z]{2})?$"
    const val NATURAL_NAME_REGEX = "^([A-Za-z])([A-Za-z\\s])*([A-Za-z])$"
    const val NICKNAME_REGEX = "^([A-Za-z0-9_\\-])*$"
    const val PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^<>&*_-]).*$"
    const val PHONE_REGEX = "^([0-9])*$"
}