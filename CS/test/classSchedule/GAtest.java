package classSchedule;

import org.junit.Test;

import static classSchedule.TimetableGA.*;

public class GAtest {

    /**
     * test evalPopulation()
     * since the room in timetable match professor perferedroom the fitness should be 1.01
     *
     * @throws Exception
     */
    @Test
    public void testEval() throws Exception{
        Timetable timetable = new Timetable();

        // Set up rooms
        timetable.addRoom(1, "A1", 15);
        timetable.addTimeslot(1,"Mon 9:00 - 11:00");
        timetable.addProfessor(1,"proftest1",1);
        timetable.addModule(1,"cs1","computer science", new int[]{1});
        timetable.addGroup(1,10,new int[]{1});

        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
        Population population = ga.initPopulation(timetable);
        ga.evalPopulation(population, timetable);
        int generation = 1;
        while (ga.isTerminationConditionMet(generation, 1000) == false && ga.isTerminationConditionMet(population) == false) {
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
        System.out.println("Clashes: " + timetable.calcClashes(100));

        PrintClassAll(timetable);
        assert population.getFittest(0).getFitness() == 1.01 : "not eval";
    }

    @Test
    public void testMutate() throws Exception{
        Timetable timetable = new Timetable();
        timetable.addRoom(1, "A1", 15);
        timetable.addRoom(2, "B1", 20);
        timetable.addTimeslot(1,"Tue 9:00 - 11:00");
        timetable.addTimeslot(2,"Tue 13:00 - 15:00");
        timetable.addProfessor(1,"proftest1");
        timetable.addProfessor(2,"proftest2");
        timetable.addModule(1,"cs1","computer science", new int[]{1,2});
        timetable.addModule(2, "cs2", "Algrothim", new int[]{1,2});
        timetable.addGroup(1,10,new int[]{1,2});
        //timetable.addGroup(2,10,new int[]{1,2});

        GeneticAlgorithm ga = new GeneticAlgorithm(100, 1, 0, 0, 5);
        Population population = ga.initPopulation(timetable);
        ga.evalPopulation(population, timetable);
        int generation = 1;
        while (ga.isTerminationConditionMet(generation, 2) == false) {
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // TODO: Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // TODO: Evaluate population
            ga.evalPopulation(population, timetable);
            // Increment the current generation
            generation++;
            timetable.createClasses(population.getFittest(0));
            PrintClassAll(timetable);
        }
    }

    /**
     * print timetable for specific professor
     * @throws Exception
     */
    @Test
    public void testPrintProfTable() throws Exception{
        Timetable timetable = initializeTimetable();
        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

        // TODO: Initialize population
        Population population = ga.initPopulation(timetable);

        // TODO: Evaluate population
        ga.evalPopulation(population, timetable);

        // Keep track of current generation
        int generation = 1;

        // Start evolution loop
        // TODO: Add termination condition
        while (ga.isTerminationConditionMet(generation, 200) == false && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
            //System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // TODO: Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // TODO: Evaluate population
            ga.evalPopulation(population, timetable);
            // Increment the current generation
            generation++;
        }
        timetable.createClasses(population.getFittest(0));
        PrintClasses(timetable, "PROF", 3);
    }

    /**
     * print timetable for specific group
     *
     * @throws Exception
     */
    @Test
    public void testPrintGroupTable() throws Exception{
        Timetable timetable = initializeTimetable();
        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

        // TODO: Initialize population
        Population population = ga.initPopulation(timetable);

        // TODO: Evaluate population
        ga.evalPopulation(population, timetable);

        // Keep track of current generation
        int generation = 1;

        // Start evolution loop
        // TODO: Add termination condition
        while (ga.isTerminationConditionMet(generation, 300) == false && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
            //System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // TODO: Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // TODO: Evaluate population
            ga.evalPopulation(population, timetable);
            // Increment the current generation
            generation++;
        }
        timetable.createClasses(population.getFittest(0));
        PrintClasses(timetable, "GROUP", 3);
    }

    /**
     * print timetable for specific room
     *
     * @throws Exception
     */
    @Test
    public void testPrintRoomTable() throws Exception{
        Timetable timetable = initializeTimetable();
        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

        // TODO: Initialize population
        Population population = ga.initPopulation(timetable);

        // TODO: Evaluate population
        ga.evalPopulation(population, timetable);

        // Keep track of current generation
        int generation = 1;

        // Start evolution loop
        // TODO: Add termination condition
        while (ga.isTerminationConditionMet(generation, 400) == false && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
            //System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // TODO: Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // TODO: Evaluate population
            ga.evalPopulation(population, timetable);
            // Increment the current generation
            generation++;
        }
        timetable.createClasses(population.getFittest(0));
        PrintClasses(timetable, "ROOM", 3);
    }

    /**
     * print timetable for specific module
     *
     * @throws Exception
     */
    @Test
    public void testPrintModuleTable() throws Exception{
        Timetable timetable = initializeTimetable();
        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

        // TODO: Initialize population
        Population population = ga.initPopulation(timetable);

        // TODO: Evaluate population
        ga.evalPopulation(population, timetable);

        // Keep track of current generation
        int generation = 1;

        // Start evolution loop
        // TODO: Add termination condition
        while (ga.isTerminationConditionMet(generation, 250) == false && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
            //System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // TODO: Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // TODO: Evaluate population
            ga.evalPopulation(population, timetable);
            // Increment the current generation
            generation++;
        }
        timetable.createClasses(population.getFittest(0));
        PrintClasses(timetable, "MODULE", 3);
    }

    /**
     * print full timetable
     *
     * @throws Exception
     */
    @Test
    public void testPrintAll() throws  Exception{
        // TODO: Create Timetable and initialize with all the available courses, rooms, timeslots, professors, modules, and groups
        Timetable timetable = initializeTimetable();

        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

        // TODO: Initialize population
        Population population = ga.initPopulation(timetable);

        // TODO: Evaluate population
        ga.evalPopulation(population, timetable);

        // Keep track of current generation
        int generation = 1;

        // Start evolution loop
        // TODO: Add termination condition
        while (ga.isTerminationConditionMet(generation, 100) == false && ga.isTerminationConditionMet(population) == false) {
            population = ga.crossoverPopulation(population);
            population = ga.mutatePopulation(population, timetable);
            ga.evalPopulation(population, timetable);
            generation++;
        }

        // Print fitness
        timetable.createClasses(population.getFittest(0));
        PrintClassAll(timetable);

    }
}
