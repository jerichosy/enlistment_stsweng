# Enlistment

## Iteration 1 Requirements

1. [x] A student is identified by his/her student number, which is non-negative integer

2. [x] Student enlists in one or more sections The student may have already previously enlisted in other sections

3. [x] A section is identified by its section ID, which is alphanumeric

4. [x] A student cannot enlist in the same section more than once

5. [x] The system makes sure that the student cannot enlist in any section that has a conflict with previously enlisted sections

6. [x] A section is in conflict with another section if the schedules are in conflict. Schedules are as follows:
   
    **Days**
      - Mon/Thu
      - Tue/Fri
      - Wed/Sat

    **Periods**
      - 8:30am-10am 
      - 10am-11:30
      - 11:30am-1pm
      - 1pm-2:30pm
      - 2:30pm-4pm
      - 4pm-5:30pm

7. [x] A section has a room
8. [x] A room is identified by its room name, which is alphanumeric
9. [x] A room has a capacity
10. [x] Section enlistment may not exceed the capacity of its room
11. [x] A student may cancel an enlisted section.

## Iteration 2 Requirements

1. [x] All Iteration 1 requirements.
2. [ ] A section has a subject.
3. [ ] A subject is identified by its alphanumeric Subject ID.
4. [ ] A student cannot enlist in two sections with the same subject.
5. [ ] A subject may or may not have one or more prerequisite subjects.
6. [ ] A student may not enlist in a section if the student has not yet taken the prerequisite subjects.
7. [ ] Each subject has a corresponding number of units.
8. [ ] Some subjects may be designated as "laboratory" subjects.
9. [ ] A student can request to be assessed, which is simply a request for total amount of money that the student will need to pay. It is computed as follows:
10. [ ] Each unit is ₱2,000
11. [ ] Laboratory subjects have an additional ₱1,000 laboratory fee per subject
12. [ ] Miscellaneous fees are ₱3,000
13. [ ] Value Added Tax (VAT) is 12%
