package com.orangeandbronze.enlistment;
import  org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.orangeandbronze.enlistment.Period.*;
import static com.orangeandbronze.enlistment.Days.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class StudentTest {

    static final Schedule DEFAULT_SCHEDULE = new Schedule(Days.MTH, Period.H1000);
    static final Room DEFAULT_ROOM = new Room("AG1710", 1);
    static final Subject DEFAULT_SUBJECT1 = new Subject("CCPROG1", 3, false);
    static final Subject DEFAULT_SUBJECT2 = new Subject("CCICOMP", 3, false);
    static final Subject DEFAULT_SUBJECT3 = new Subject("CCPROG2", 3, true);
    @Test
    void enlist_2_sections_no_conflict(){
        //Given 1 student and sections w/conflict
        Student student = new Student(1);
        Section sec1 = new Section("A", DEFAULT_SCHEDULE, DEFAULT_ROOM, DEFAULT_SUBJECT1);
        Section sec2 = new Section("B",  new Schedule(Days.MTH, Period.H0830, Period.H0900), DEFAULT_ROOM, DEFAULT_SUBJECT2);
        // When student enlist in both sections
        student.enlist(sec1);
        student.enlist(sec2);

        //Then the 2 sections should be found in the student
        // and student should have ONLY 2 section
        Collection<Section> sections = student.getSections();
        assertAll(
                () -> assertTrue(sections.containsAll(List.of(sec1,sec2))),
                () -> assertEquals(2, sections.size())
        );
    }
    @Test
    void enlist_2_section_same_schedule(){
        //Given a student and 2 sections w/ same schedule
        Student student = new Student(1);
        Section sec1 = new Section("A", DEFAULT_SCHEDULE, DEFAULT_ROOM, DEFAULT_SUBJECT1);
        Section sec2 = new Section("B", DEFAULT_SCHEDULE, DEFAULT_ROOM, DEFAULT_SUBJECT2);
        //When student enlist in both sections
        student.enlist(sec1);
        //Then on the 2nd enlistments on exception should be thrown
        assertThrows(Exception.class, ()-> student.enlist(sec2));
    }

    @Test
    void enlist_2_section_same_subject(){
        //Given a student and 2 sections w/ same subject
        Schedule schedule = new Schedule(Days.MTH, Period.H0830, Period.H0900);
        Student student = new Student(1);
        Section sec1 = new Section("A", DEFAULT_SCHEDULE, DEFAULT_ROOM, DEFAULT_SUBJECT1);
        Section sec2 = new Section("B", schedule, DEFAULT_ROOM, DEFAULT_SUBJECT1);
        //When student enlist in both sections
        student.enlist(sec1);
        //Then on the 2nd enlistments on exception should be thrown
        assertThrows(SubjectConflictException.class, ()-> student.enlist(sec2));
    }

    @Test
    void student_enlist_to_full_section(){
        Student student1 = new Student(1);
        Student student2 = new Student(2);
        Section sec1 = new Section("A", DEFAULT_SCHEDULE, DEFAULT_ROOM, DEFAULT_SUBJECT1);
        student1.enlist(sec1);
        assertThrows(Exception.class, ()-> student2.enlist(sec1));
    }

    @Test
    void student_enlist_to_open_section(){
        Student student1 = new Student(1);
        Room room = new Room("AG1710", 5);
        Section sec1 = new Section("A", DEFAULT_SCHEDULE, room, DEFAULT_SUBJECT1);
        student1.enlist(sec1);
        assertTrue(student1.getSections().containsAll(List.of(sec1)));
    }

    @Test
    void student_cancel_enlisted_section(){
        Student student1 = new Student(1);
        Room room = new Room("AG1710", 5);
        Section sec1 = new Section("A", DEFAULT_SCHEDULE, room, DEFAULT_SUBJECT1);
        student1.enlist(sec1);
        assertTrue(student1.getSections().containsAll(List.of(sec1)));
        student1.cancelEnlist(sec1);
        assertFalse(student1.getSections().containsAll(List.of(sec1)));
    }

    @Test
    void student_cancel_unenlisted_section(){
        Student student1 = new Student(1);
        Room room = new Room("AG1710", 5);
        Section sec1 = new Section("A", DEFAULT_SCHEDULE, room, DEFAULT_SUBJECT1);
        assertThrows(Exception.class, ()-> student1.cancelEnlist(sec1));
    }

    @Test
    void student_enlist_section_missing_prerequisites() {
        Student student = new Student(1);
        Collection<Subject> prerequisites = new HashSet<>();
        prerequisites.add(new Subject("CCPROG1", 3, false));
        Subject subject = new Subject("CCPROG2", 3, false, prerequisites);
        Section section = new Section("A", DEFAULT_SCHEDULE, DEFAULT_ROOM, subject);

        assertThrows(MissingPrerequisiteException.class, () -> student.enlist(section));
    }

    @Test
    void student_enlist_section_correct_prerequisites() {
        Collection<Subject> prerequisites = new HashSet<>();
        prerequisites.add(new Subject("CCPROG1", 3, false));
        Student student = new Student(1, Collections.emptyList(), prerequisites);
        Subject subject = new Subject("CCPROG2", 3, false, prerequisites);
        Section section = new Section("A", DEFAULT_SCHEDULE, DEFAULT_ROOM, subject);
        student.enlist(section);
        assertTrue(student.getSections().containsAll(List.of(section)));
    }

    @Test
    void student_enlist_section_missing_multiple_prerequisites() {
        Collection<Subject> studentCompletedSubjects = new HashSet<>();
        studentCompletedSubjects.add(new Subject("CCPROG1", 3, false));
        Student student = new Student(1, Collections.emptyList(), studentCompletedSubjects);

        Collection<Subject> subjectPrerequisites = new HashSet<>(studentCompletedSubjects);
        subjectPrerequisites.add(new Subject("CCPROG2", 3, false));

        Subject subject = new Subject("CCPROG3", 3, false, subjectPrerequisites);
        Section section = new Section("A", DEFAULT_SCHEDULE, DEFAULT_ROOM, subject);

        assertThrows(MissingPrerequisiteException.class, () -> student.enlist(section));
    }

    @Test
    void student_enlist_section_correct_multiple_prerequisites() {
        Collection<Subject> prerequisites = new HashSet<>();
        prerequisites.add(new Subject("CCPROG1", 3, false));
        prerequisites.add(new Subject("CCPROG2", 3, false));

        Student student = new Student(1, Collections.emptyList(), prerequisites);
        Subject subject = new Subject("CCPROG3", 3, false, prerequisites);
        Section section = new Section("A", DEFAULT_SCHEDULE, DEFAULT_ROOM, subject);
        student.enlist(section);
        assertTrue(student.getSections().containsAll(List.of(section)));
    }


    @Test
    void subject_negative_units(){
        assertThrows(IllegalArgumentException.class, ()-> new Subject("CCPROG2", -1.0, false));
    }

    @Test
    void student_request_assessment() {
        Student student1 = new Student(1);
        Schedule schedule = new Schedule(Days.MTH, Period.H0830, Period.H0900);
        Section sec1 = new Section("A", DEFAULT_SCHEDULE, DEFAULT_ROOM, DEFAULT_SUBJECT3);
        Section sec2 = new Section("B", schedule, DEFAULT_ROOM, DEFAULT_SUBJECT1);
        double assessment;
        student1.enlist(sec1);
        student1.enlist(sec2);
        assessment = student1.requestAssessment();
        assertEquals(17920, assessment);
    }

    @Test
    void student_request_assessment_no_sections() {
        Student student1 = new Student(1);
        assertThrows(Exception.class, student1::requestAssessment);
    }
}

