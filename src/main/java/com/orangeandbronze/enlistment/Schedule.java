package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.Validate;

import java.util.Objects;

class Schedule {
    private final Days days;
    private final Period startPeriod;
    private final Period endPeriod;


    Schedule(Days days, Period startPeriod, Period endPeriod) {
        Validate.notNull(days);
        Validate.notNull(startPeriod);
        Validate.notNull(endPeriod);
        this.days = days;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return days == schedule.days && startPeriod == schedule.startPeriod && endPeriod == schedule.endPeriod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(days, startPeriod, endPeriod);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "days=" + days +
                ", startPeriod=" + startPeriod +
                ", endPeriod=" + endPeriod +
                '}';
    }
}

enum Days {
    MTH, TF, WS
}

enum Period {
    H0830(830), H0900(900), H0930(930), H1000(1000), H1030(1030), H1100(1100),
    H1130(1130), H1200(1200), H1230(1230), H1300(1300), H1330(1330), H1400(1400),
    H1430(1430), H1500(1500), H1530(1530), H1600(1600), H1630(1630), H1700(1700), H1730(1730);
    private int value;

    Period(int value) {
        this.value = value;
    }

}
