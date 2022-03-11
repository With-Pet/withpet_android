package com.withpet.withpet_android.enums

enum class LoginEnum(val value: Int) {
    NOTHING(-1),
    GOOGLE(0),
    NAVER(1),
    KAKAO(2)
}

fun getLoginEnumFromInt(value: Int) = when (value) {
    0 -> LoginEnum.GOOGLE
    1 -> LoginEnum.NAVER
    2 -> LoginEnum.KAKAO
    else -> LoginEnum.NOTHING
}