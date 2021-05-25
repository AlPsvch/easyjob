package com.easyjob.jobmanager.entity.user;

public enum PersonalityType {

    ESTJ("ESTJ - УПРАВЛЕНЕЦ"),
    ENTJ("ENTJ - КОМАНДИР"),
    ESFJ("ESFJ - УЧИТЕЛЬ"),
    ESTP("ESTP - МАРШАЛ"),
    ENFJ("ENFJ - НАСТАВНИК"),
    ENTP("ENTP - ИЗОБРЕТАТЕЛЬ"),
    ESFP("ESFP - ПОЛИТИК"),
    ENFP("ENFP - ЧЕМПИОН"),
    INFP("INFP - ЦЕЛИТЕЛЬ"),
    ISFP("ISFP - КОМПОЗИТОР"),
    INTP("INTP - АРХИТЕКТОР"),
    INFJ("INFJ - СОВЕТНИК"),
    INTJ("INTJ - ВДОХНОВИТЕЛЬ"),
    ISFJ("ISFJ - ЗАЩИТНИК"),
    ISTP("ISTP - УМЕЛЕЦ"),
    ISTJ("ISTJ - ИНСПЕКТОР");

    private String description;

    PersonalityType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
