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
2. [x] A section has a subject.
3. [x] A subject is identified by its alphanumeric Subject ID.
4. [x] A student cannot enlist in two sections with the same subject.
5. [x] A subject may or may not have one or more prerequisite subjects.
6. [x] A student may not enlist in a section if the student has not yet taken the prerequisite subjects.
7. [x] Each subject has a corresponding number of units.
8. [x] Some subjects may be designated as "laboratory" subjects.
9. [x] A student can request to be assessed, which is simply a request for total amount of money that the student will need to pay. It is computed as follows:
   1. [x] Each unit is ₱2,000
   2. [x] Laboratory subjects have an additional ₱1,000 laboratory fee per subject
   3. [x] Miscellaneous fees are ₱3,000
   4. [x] Value Added Tax (VAT) is 12%

### Iteration 3 Requirements
1. [x] A student cannot enlist in more than 24 units.
2. [ ] No two sections can share the same room if their schedules overlap.
3. [x] A student belongs to a degree program.
4. [x] A degree program is a collection of subjects that a student may take.
5. [x] A student cannot enroll in section if the subject of the section is not part of the student's degree program.
6. [x] All financial values should be represented as BigDecimal.

   #### Urgent Changes
7. [x] A student may not enlist in a section if its schedule overlaps with the schedule of any of its currently enlisted sections.
8. [x] Periods may be of any duration of 30-min increments, w/in the hours of 8:30am - 5:30pm.
9. [x] Periods may begin and may end at the top of each hour (9:00, 10:00, 11:00...) or at the bottom of each hour (9:30, 10:30, 11:30...).
10. [x] End of a period may not be on or before the start of the period.

##### Examples

   **Valid Periods**
   - 8:30am - 9:00am
   - 9:00am - 12:00nn
   - 2:30pm - 4:30pm
   - 9:00am - 10:30am

   **Invalid Periods**
   - 8:45am - 10:15am
   - Does not start at top or bottom of the hour
   - 12:00pm - 12:02pm
   - Not a 30 minute increment
   - 4:00pm - 3:00pm
     - Start time is after end time
   - 4:30pm - 6:00pm
     - End time is after 5:30pm
