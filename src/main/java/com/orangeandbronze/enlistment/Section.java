package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.*;
class Section {

    private final String sectionId;
    private final Schedule schedule;
    private final Room classroom;

    private int numberOfEnlistedStudent;
    public Section(String sectionId, Schedule schedule, Room classroom) {
        notNull(schedule);
        notNull(classroom);
        notBlank(sectionId);
        isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectionId must be alphanumeric, was:" + sectionId);
        this.sectionId = sectionId;
        this.schedule = schedule;
        this.classroom = classroom;
        this.numberOfEnlistedStudent = 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(sectionId, section.sectionId);
    }

    private void checkAvailableSlot(){
        if(numberOfEnlistedStudent==classroom.getRoomCapacity()){
            throw new SectionNoAvailableSlotException(
                    "This section is already full"
            );
        }
    }

    void enlistStudent(){
        checkAvailableSlot();
        this.numberOfEnlistedStudent = numberOfEnlistedStudent + 1;
    }

    void dropStudent(){
        this.numberOfEnlistedStudent = numberOfEnlistedStudent - 1;
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


