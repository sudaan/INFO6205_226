# INFO6205_226
genetic algorithm project for 6205
## team member 
* Tianrun Gao
* Shaosheng Yin
## Problem to solve
The class-scheduling problem we will be looking at in this chapter is a college class scheduler that can create a college timetable based on data we provide it, such as the available professors, available rooms, timeslots and student groups.
Typical college timetables will frequently have free periods depending on how many modules the student enrolls in.

Each class will be assigned a timeslot, a professor, a room and a student group by the class scheduler. We can calculate the total number of classes that will need scheduling by summing the number of student groups multiplied by the number of modules each student group is enrolled in.

## Hard constraints

* Classes can only be scheduled in free classrooms
* A professor can only teach one class at any one time
* Classrooms must be big enough to accommodate the student group

## Soft constraints

* Professor teach course at his/her perferred time
* Professor teach course at his/her perferred room

## Code stucture

* Class 
* GeneticAlgoritm : crossover, mutate, evaluate
* Group
* individul
* Module
* Population
* Professor
* Room
* Timeslot
* Timetable
* TimetableGA : main fuction

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
