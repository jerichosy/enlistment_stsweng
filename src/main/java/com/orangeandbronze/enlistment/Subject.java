package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import static org.apache.commons.lang3.Validate.*;

class Subject {
    private final String subjectId;


    public Subject(String subjectId){
        notBlank(subjectId);
        isTrue(StringUtils.isAlphanumeric(subjectId),
                "subjectId must be alphanumeric, was:" + subjectId);

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