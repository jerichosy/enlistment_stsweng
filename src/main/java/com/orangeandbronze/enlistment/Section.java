package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.*;
class Section {

    private final String sectionId;
    private final Schedule schedule;

    public Section(String sectionId, Schedule schedule) {
        notNull(schedule);
        notBlank(sectionId);
        isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectionId must be alphanumeric, was:" + sectionId);
        this.sectionId = sectionId;
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(sectionId, section.sectionId);
    }


    void checkForConflict(Section section){
        if(this.hasConflict(section)){
            throw new ScheduleConflictException("current section " + section + " has same schedule as new section "
                    + section + " at schedule" + section.getSchedule());
        }
    }
    boolean hasConflict(Section section){
        return this.schedule.equals(section.getSchedule());
    }
    public Schedule getSchedule(){
        return schedule;
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


