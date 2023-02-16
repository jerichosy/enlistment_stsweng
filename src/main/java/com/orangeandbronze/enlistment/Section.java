package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.*;
class Section {

    private final String sectionId;
    private final Schedule schedule;
    private final Room classroom;
    private final Subject subject;

    private int numberOfEnlistedStudent;
    public Section(String sectionId, Schedule schedule, Room classroom, Subject subject) {
        notNull(schedule);
        notNull(classroom);
        notNull(subject);
        notBlank(sectionId);
        isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectionId must be alphanumeric, was:" + sectionId);
        Collection<Schedule> timeSlots = classroom.getTakenTimeSlots();
        for (Schedule currTimeSlot : timeSlots) {
            if (currTimeSlot.checkOverlap(schedule)) {
                throw new SameRoomOverlapException("This section shares the same room with another section at the" +
                        "assigned Schedule.");
            }
        }
        this.subject = subject;
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

    void checkForConflict(Section section) {
        if(this.hasScheduleConflict(section)){
            throw new ScheduleConflictException("Current section " + this + " has an overlapping schedule with the new section "
                    + section + " at schedule " + this.getSchedule() + " and " +  section.getSchedule());
        }
        if (this.hasSubjectConflict(section)){
            throw new SubjectConflictException("Current section " + section +
                    " has the same subject " + section.getSubject() + " as the new section.");
        }
    }

    void checkForMissingPrerequisites(Collection<Subject> completedSubjects) {
        if (this.hasMissingPrerequisite(completedSubjects)) {
            throw new MissingPrerequisiteException("Student is missing prerequisites" + this.getSubject().getPrerequisites());
        }
    }
    void checkIfSubjectPartofDegreeProgram(DegreeProgram degreeProgram){
        degreeProgram.checkIfSubjectPartOfProgram(subject);
    }

    boolean hasScheduleConflict(Section section){
        if (this.schedule.equals(section.getSchedule())){
            return true;
        }
        // check for overlap
        return this.getSchedule().checkOverlap(section.getSchedule());
    }

    boolean hasSubjectConflict(Section section) {
        return this.subject.equals(section.getSubject());
    }

    boolean hasMissingPrerequisite(Collection<Subject> completedSubjects) {
        if (this.getSubject().getPrerequisites().isEmpty())
            return false;
        return !(completedSubjects.containsAll(this.getSubject().getPrerequisites()));
    }

    public double getSectionSubjectUnits(){
        return subject.getUnits();
    }

    public Schedule getSchedule(){
        return schedule;
    }

    public Subject getSubject() {
        return subject;
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


