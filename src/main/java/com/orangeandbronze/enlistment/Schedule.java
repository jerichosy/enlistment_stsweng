package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.Validate;

import java.util.Objects;

class Schedule {
    private final Days days;
    private final Period period;

    Schedule(Days days, Period period) {
        Validate.notNull(days);
        Validate.notNull(period);
        this.days = days;
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return days == schedule.days && period == schedule.period;
    }

    @Override
    public String toString() {
        return days+" "+period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(days, period);
    }
}

enum Days {
    MTH, TF, WS
}

enum Period {
    H0830, H1000, H1130, H1300, H1430, H1600

}
