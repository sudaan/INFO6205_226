package classSchedule;

import java.util.List;

public class TimetableGA {

    /**
     * initializeTimetable
     *
     * @return
     */
    protected static Timetable initializeTimetable() {
        // Create timetable
        Timetable timetable = new Timetable();

        // Set up rooms
        timetable.addRoom(1, "A1", 15);
        timetable.addRoom(2, "B1", 30);
        timetable.addRoom(4, "D1", 20);
        timetable.addRoom(5, "F1", 25);
        timetable.addRoom(3, "C1", 20);

        // Set up timeslots
        timetable.addTimeslot(1, "Mon 9:00 - 11:00");
        timetable.addTimeslot(2, "Mon 11:00 - 13:00");
        timetable.addTimeslot(3, "Mon 13:00 - 15:00");
        timetable.addTimeslot(4, "Tue 9:00 - 11:00");
        timetable.addTimeslot(5, "Tue 11:00 - 13:00");
        timetable.addTimeslot(6, "Tue 13:00 - 15:00");
        timetable.addTimeslot(7, "Wed 9:00 - 11:00");
        timetable.addTimeslot(8, "Wed 11:00 - 13:00");
        timetable.addTimeslot(9, "Wed 13:00 - 15:00");
        timetable.addTimeslot(10, "Thu 9:00 - 11:00");
        timetable.addTimeslot(11, "Thu 11:00 - 13:00");
        timetable.addTimeslot(12, "Thu 13:00 - 15:00");
        timetable.addTimeslot(13, "Fri 9:00 - 11:00");
        timetable.addTimeslot(14, "Fri 11:00 - 13:00");
        timetable.addTimeslot(15, "Fri 13:00 - 15:00");

        // Set up professors
        timetable.addProfessor(1, "Dr P Smith", 2,8);
        timetable.addProfessor(2, "Mrs E Mitchell",2);
        timetable.addProfessor(3, "Dr R Williams",3);
        timetable.addProfessor(4, "Mr A Thompson",1);
        timetable.addProfessor(5, "Mr Robin", 5, 6);
        timetable.addProfessor(6, "Mr Peter O'reaily", 1, 10);

        // Set up modules and define the professors that teach them
        timetable.addModule(1, "cs1", "Computer Science", new int[] { 1, 2 ,4});
        timetable.addModule(2, "en1", "English", new int[] { 1, 3 ,5});
        timetable.addModule(3, "ma1", "Maths", new int[] { 1, 2 ,5});
        timetable.addModule(4, "ph1", "Physics", new int[] { 3, 4 ,6});
        timetable.addModule(5, "hi1", "History", new int[] { 4 });
        timetable.addModule(6, "dr1", "Drama", new int[] { 1, 4,6 });
        timetable.addModule(7, "cs2", "Algorithm", new int[]{2,5,6});

        // Set up student groups and the modules they take.
        timetable.addGroup(1, 10, new int[] { 1, 3, 4 });
        timetable.addGroup(2, 30, new int[] { 2, 3, 5, 6 });
        timetable.addGroup(3, 18, new int[] { 3, 4, 5 });
        timetable.addGroup(4, 25, new int[] { 1, 4 ,7});
        timetable.addGroup(5, 20, new int[] { 2, 3, 5 });
        timetable.addGroup(6, 22, new int[] { 1, 4, 5 });
        timetable.addGroup(7, 16, new int[] { 1, 3 });
        timetable.addGroup(8, 18, new int[] { 2, 6 ,7});
        timetable.addGroup(9, 24, new int[] { 1, 6 });
        timetable.addGroup(10, 25, new int[] { 3, 4 ,7});

        return timetable;
    }


    public static void PrintClassAll(Timetable timetable){
        Class classes[] = timetable.getClasses();
        int classIndex = 1;
        for (Class bestClass : classes) {
            System.out.println("Class " + classIndex + ":");
            System.out.println("Module: " + timetable.getModule(bestClass.getModuleId()).getModuleName());
            System.out.println("Group: " + timetable.getGroup(bestClass.getGroupId()).getGroupId());
            System.out.println("Room: " + timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
            System.out.println("Professor: " + timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
            System.out.println("Time: " + timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
            System.out.println("-----");
            classIndex++;
        }
    }



    public static List<Class> getClasses(Timetable timetable, String type, int id) {
        switch (type) {
            case "ROOM":
                return timetable.getRoomMap().get(id);
            case "PROF":
                return timetable.getProfMap().get(id);
            case "MODULE":
                return timetable.getModuleMap().get(id);
            case "GROUP":
                return timetable.getGroupMap().get(id);
            default:
                return null;
        }
    }


    /**
     *
     * @param timetable
     * @param id
     */
    public static void PrintClasses(Timetable timetable, String type, int id){
        List<Class> classes = getClasses(timetable, type, id);
        for (Class bestClass: classes){
            printClass(timetable, bestClass);
        }
    }

    /**
     *
     * @param timetable
     * @param bestClass
     */
    public static void printClass(Timetable timetable, Class bestClass) {
        System.out.println("Module: " + timetable.getModule(bestClass.getModuleId()).getModuleName());
        System.out.println("Group: " + timetable.getGroup(bestClass.getGroupId()).getGroupId());
        System.out.println("Room: " + timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
        System.out.println("Professor: " + timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
        System.out.println("Time: " + timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
        System.out.println("-----");
    }


    public static void main(String[] args) {
        // TODO: Create Timetable and initialize with all the available courses, rooms, timeslots, professors, modules, and groups
        Timetable timetable = initializeTimetable();

        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(1000, 0.01, 0.9, 2, 5);

        // TODO: Initialize population
        Population population = ga.initPopulation(timetable);

        // TODO: Evaluate population
        ga.evalPopulation(population, timetable);

        // Keep track of current generation
        int generation = 1;

        // Start evolution loop
            // TODO: Add termination condition
        while (ga.isTerminationConditionMet(generation, 1000) == false && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
            System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());

            // Apply crossover
            population = ga.crossoverPopulation(population);

            // TODO: Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // TODO: Evaluate population
            ga.evalPopulation(population, timetable);
            // Increment the current generation
            generation++;
        }

        // Print fitness
        timetable.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Clashes: " + timetable.calcClashes(1000));

        PrintClassAll(timetable);

    }
}
