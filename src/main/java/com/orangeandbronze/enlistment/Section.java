package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.*;
class Section {

    private final String sectionId;
    public Section(String sectionId) {
        notBlank(sectionId);
        isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectionId must be alphanumeric, was:" + sectionId);
        this.sectionId = sectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(sectionId, section.sectionId);
    }

    @Override
    public String toString() {
        return "Section" + sectionId ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionId);
    }
}
