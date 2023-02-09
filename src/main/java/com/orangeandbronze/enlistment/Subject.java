package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import static org.apache.commons.lang3.Validate.*;

class Subject {
    private final String subjectId;
    private final double units;
    private final boolean isLaboratory;
    private final Collection<Subject> prerequisites = new HashSet<>();

    public Subject(String subjectId, double units, boolean isLaboratory, Collection<Subject> prerequisites) {
        notBlank(subjectId);
        notNull(units);
        notNull(isLaboratory);
        notNull(prerequisites);
        if (units < 0) {
            throw new IllegalArgumentException("Subject units cannot be negative");
        }
        isTrue(StringUtils.isAlphanumeric(subjectId),
                "subjectId must be alphanumeric, was:" + subjectId);
        this.units = units;
        this.subjectId = subjectId;
        this.isLaboratory = isLaboratory;
        this.prerequisites.addAll(prerequisites);
    }

    public Subject(String subjectId, double units, boolean isLaboratory) {
        this(subjectId, units, isLaboratory, Collections.emptyList());
    }

    public double getUnits() {
        return units;
    }

    public boolean getIsLaboratory(){
        return isLaboratory;
    }

    public Collection<Subject> getPrerequisites() {
        return prerequisites;
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