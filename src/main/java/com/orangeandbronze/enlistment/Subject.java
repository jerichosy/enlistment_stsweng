package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import static org.apache.commons.lang3.Validate.*;

class Subject {
    private final String subjectId;
    private final double units;


    public Subject(String subjectId, double units){
        notBlank(subjectId);
        notNull(units);
        if (units < 0) {
            throw new IllegalArgumentException("Subject units cannot be negative");
        }
        isTrue(StringUtils.isAlphanumeric(subjectId),
                "subjectId must be alphanumeric, was:" + subjectId);
        this.units = units;
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return subjectId.equals(subject.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId);
    }
}