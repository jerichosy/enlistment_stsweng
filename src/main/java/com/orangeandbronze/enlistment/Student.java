package com.orangeandbronze.enlistment;

import static org.apache.commons.lang3.Validate.*;

import java.math.BigDecimal;
import java.util.*;

class Student {
    private final int studentNumber;
    private final Collection<Section> sections = new HashSet<>();
    private final Collection<Subject> completedSubjects = new HashSet<>();

    private final DegreeProgram studentDegreeProgram;
    Student(int studentNumber, Collection<Section> sections, Collection<Subject> completedSubjects, DegreeProgram studentDegreeProgram){
        if (studentNumber < 0){
            throw new IllegalArgumentException(
                    "studentNumber should be non-negative, was:" + studentNumber);
        }
        if (sections == null){
            throw new NullPointerException("sections should not be null");
        }
        notNull(studentDegreeProgram);
        this.studentNumber = studentNumber;
        this.studentDegreeProgram = studentDegreeProgram;
        this.sections.addAll(sections);
        this.sections.removeIf(Objects::isNull);
        this.completedSubjects.addAll(completedSubjects);
    }

    public Student(int studentNumber, DegreeProgram degreeProgram) {
        this(studentNumber, Collections.emptyList(), Collections.emptyList(), degreeProgram);
    }

    void enlist(Section newSection){
        notNull(newSection);
        //loop through all current sections, check for same sched
        sections.forEach( currSection -> {
            currSection.checkForConflict(newSection);
        });
        newSection.checkIfSubjectPartofDegreeProgram(this.studentDegreeProgram);
        newSection.checkForMissingPrerequisites(this.completedSubjects);
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

    Collection<Subject> getCompletedSubjects(){
        return new HashSet<>(completedSubjects);
    }

    BigDecimal requestAssessment() {
        notEmpty(sections);
        final BigDecimal VALUE_ADDED_TAX_MULTIPLIER = new BigDecimal("1.12");
        final BigDecimal MISC_FEES = new BigDecimal(3000);
        final BigDecimal LABORATORY_FEE = new BigDecimal(1000);
        final int COST_PER_UNIT = 2000;
        BigDecimal total = BigDecimal.ZERO;
        for (Section currSection : sections) {
            double units = currSection.getSubject().getUnits();  // I'm not sure if this should be BigDecimal
            BigDecimal unitCost = new BigDecimal(COST_PER_UNIT * units);
            total = total.add(unitCost);
            if (currSection.getSubject().getIsLaboratory()) {
                total = total.add(LABORATORY_FEE);
            }
        }

        total = total.add(MISC_FEES);
        total = total.multiply(VALUE_ADDED_TAX_MULTIPLIER);

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
