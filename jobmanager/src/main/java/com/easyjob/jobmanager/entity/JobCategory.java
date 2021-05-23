package com.easyjob.jobmanager.entity;

public enum JobCategory {

    CAR_BUSINESS("Автомобильный бизнес"),
    ADMINISTRATIVE_STAFF("Административный персонал"),
    BANKS_INVESTMENT_LEASING("Банки, инвестиции, лизинг"),
    SECURITY("Безопасность"),
    BOOKKEEPING("Бухгалтерия"),
    RAW_MATERIALS_EXTRACTION("Добыча сырья"),
    INFORMATION_TECHNOLOGY("Информационные технологии, интернет, телеком"),
    ART_ENTERTAINMENT_MASS_MEDIA("Искусство, развлечения, масс-медиа"),
    CONSULTING("Консультирование"),
    MARKETING_ADVERTISING_PR("Маркетинг, реклама, PR"),
    MEDICINE_PHARMACEUTICALS("Медицина, фармацевтика"),
    SCIENCE_EDUCATION("Наука, образование"),
    STUDENTS("Начало карьеры, студенты"),
    SALES("Продажи"),
    MANUFACTURING_AGRICULTURE("Производство, сельское хозяйство"),
    WORKING_STAFF("Рабочий персонал"),
    FITNESS("Спортивные клубы, фитнес, салоны красоты"),
    INSURANCE("Страхование"),
    REAL_ESTATE("Строительство, недвижимость"),
    TRANSPORT_LOGISTICS("Транспорт, логистика"),
    TOURISM("Туризм, гостиницы, рестораны"),
    LAWYERS("Юристы");

    private final String categoryName;

    JobCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
