package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.Validate;

import java.util.*;

class Student {
    private final int studentNumber;
    private final Collection<Section> sections = new HashSet<>();

    Student(int studentNumber, Collection<Section> sections){
        if (studentNumber < 0){
            throw new IllegalArgumentException(
                    "studentNumber should be non-negative, was:" + studentNumber);
        }
        if (sections == null){
            throw new NullPointerException("sections should not be null");
        }
        this.studentNumber = studentNumber;
        this.sections.addAll(sections);
        this.sections.removeIf(Objects::isNull);
    }

    public Student(int studentNumber) {
        this(studentNumber, Collections.emptyList());
    }

    void enlist(Section newSection){
        Validate.notNull(newSection);
        //loop through all current sections, check for same sched
        sections.forEach( currSection -> {
            currSection.checkForConflict(newSection);
        });
        newSection.enlistStudent();
        this.sections.add(newSection);

    }

    void cancelEnlist(Section currentSection){
        if(!sections.contains(currentSection)){
            throw new RuntimeException(
                    "You are not enlisted to the section you want to cancel");
        }
        sections.remove(currentSection);
        currentSection.dropStudent();
    }

    Collection<Section> getSections(){
        return new ArrayList<>(sections);
    }

    double requestAssessment() {
        double valueAddedTax;
        double total = 0;
        double misc = 3000;
        Validate.notEmpty(sections);
        for (Section currSection : sections) {
            double units;
            double unitCost;
            units = currSection.getSubject().getUnits();
            unitCost = units * 2000;
            total += unitCost;
            if (currSection.getSubject().getIsLaboratory()) {
                total += 1000;
            }
        }

        total += misc;
        valueAddedTax = total * 0.12;
        total += valueAddedTax;

        return total;
    }

    @Override
    public String toString() {
        return "Student#" + studentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentNumber == student.studentNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNumber);
    }
}
