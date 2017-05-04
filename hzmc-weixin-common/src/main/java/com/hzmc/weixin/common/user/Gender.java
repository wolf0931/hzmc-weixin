package com.hzmc.weixin.common.user;

public enum Gender {

    UNKNOWN(0), MALE(1), FEMALE(2);

    private int code;

    private Gender(int code) {
        this.code = code;
    }

    public static Gender fromCode(int code) {
        for (Gender gender : Gender.values()) {
            if (gender.code == code) {
                return gender;
            }
        }

        return Gender.UNKNOWN;
    }

    public int getCode() {
        return this.code;
    }
}
