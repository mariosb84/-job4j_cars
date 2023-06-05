package ru.job4j.utilites;

import java.time.LocalDateTime;

public class DateTime {

    public static LocalDateTime getDateTimeLastDay() {
return LocalDateTime.now().minusHours(24);
    }

}
