package com.withpet.withpet_android.others

enum class LoginEnum(val value: Int) {
    ERROR(-1),
    GOOGLE(0),
    KAKAO(1),
    NAVER(2)
}

fun getLoginEnumFromInt(value: Int) = when (value) {
    0 -> LoginEnum.GOOGLE
    1 -> LoginEnum.KAKAO
    2 -> LoginEnum.NAVER
    else -> LoginEnum.ERROR
}