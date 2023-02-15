package com.orangeandbronze.enlistment;

import static org.apache.commons.lang3.Validate.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class DegreeProgram {
    private final String degreeProgramName;
    private final Collection<Subject> degreeProgramSubjects = new HashSet<>();

     DegreeProgram(String degreeProgramName, Collection<Subject> degreeProgramSubjects) {
        notBlank(degreeProgramName);
        notNull(degreeProgramSubjects);
        this.degreeProgramSubjects.addAll(degreeProgramSubjects);
        this.degreeProgramSubjects.removeIf(Objects::isNull);
        this.degreeProgramName= degreeProgramName;
    }


     void checkIfSubjectPartOfProgram(Subject subject){
        if (!degreeProgramSubjects.contains(subject)){
            throw new RuntimeException(
                    "The following doesn't belong to the degree" + degreeProgramName );
        }
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DegreeProgram that = (DegreeProgram) o;
        return degreeProgramName.equals(that.degreeProgramName) && degreeProgramSubjects.equals(that.degreeProgramSubjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degreeProgramName, degreeProgramSubjects);
    }
}
