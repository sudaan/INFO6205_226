package classSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Timetable {
    private final HashMap<Integer, Room> rooms;
    private final HashMap<Integer, Professor> professors;
    private final HashMap<Integer, Module> modules;
    private final HashMap<Integer, Group> groups;
    private final HashMap<Integer, Timeslot> timeslots;
    private final HashMap<Integer, List<Class>> roomMap;
    private final HashMap<Integer, List<Class>> profMap;
    private final HashMap<Integer, List<Class>> moduleMap;
    private final HashMap<Integer, List<Class>> groupMap;
    private Class classes[];

    public HashMap<Integer, List<Class>> getRoomMap() {
        return roomMap;
    }

    public HashMap<Integer, List<Class>> getProfMap() {
        return profMap;
    }

    public HashMap<Integer, List<Class>> getModuleMap() {
        return moduleMap;
    }

    public HashMap<Integer, List<Class>> getGroupMap() {
        return groupMap;
    }

    private int numClasses = 0;

    /**
     * Initialize new Timetable *
     */

    public Timetable() {
        this.rooms = new HashMap<Integer, Room>();
        this.professors = new HashMap<Integer, Professor>();
        this.modules = new HashMap<Integer, Module>();
        this.groups = new HashMap<Integer, Group>();
        this.timeslots = new HashMap<Integer, Timeslot>();

        this.roomMap = new HashMap<>();
        this.profMap = new HashMap<>();
        this.moduleMap = new HashMap<>();
        this.groupMap = new HashMap<>();
    }

    public Timetable(Timetable cloneable) {
        this.rooms = cloneable.getRooms();
        this.professors = cloneable.getProfessors();
        this.modules = cloneable.getModules();
        this.groups = cloneable.getGroups();
        this.timeslots = cloneable.getTimeslots();

        this.roomMap = new HashMap<>();
        this.profMap = new HashMap<>();
        this.moduleMap = new HashMap<>();
        this.groupMap = new HashMap<>();
    }

    private HashMap<Integer, Group> getGroups() {
        return this.groups;
    }

    private HashMap<Integer, Timeslot> getTimeslots() {
        return this.timeslots;
    }

    private HashMap<Integer, Module> getModules() {
        return this.modules;
    }

    private HashMap<Integer, Professor> getProfessors() {
        return this.professors;
    }

    /**
     * Add new room
     *
     * @param roomId
     * @param roomName
     * @param capacity
     */

    public void addRoom(int roomId, String roomName, int capacity) {
        this.rooms.put(roomId, new Room(roomId, roomName, capacity));
    }

    /**
     * Add new professor
     *
     * @param professorId
     * @param professorName
     */

    public void addProfessor(int professorId, String professorName) {
        this.professors.put(professorId, new Professor(professorId, professorName));
    }

    /**
     * Add new professor with preferred room
     * for soft constraint use
     * @param professorId
     * @param professorName
     * @param preferedroom
     */

    public void addProfessor(int professorId, String professorName, int preferedroom){
        this.professors.put(professorId, new Professor(professorId, professorName, preferedroom));
    }

    /**
     * Add new professor with preferred room
     * for soft constraint use
     * @param professorId
     * @param professorName
     * @param preferedroom
     * @param preferedtime
     */

    public void addProfessor(int professorId, String professorName, int preferedroom, int preferedtime){
        this.professors.put(professorId, new Professor(professorId, professorName, preferedroom, preferedtime));
    }

    /**
     * * Add new module
     *
     * @param moduleId
     * @param moduleCode
     * @param module
     * @param professorIds
     */

    public void addModule(int moduleId, String moduleCode, String module, int professorIds[]) {
        this.modules.put(moduleId, new Module(moduleId, moduleCode, module, professorIds));
    }

    /**
     * Add new group
     *
     * @param groupId
     * @param groupSize
     * @param moduleIds
     */

    public void addGroup(int groupId, int groupSize, int moduleIds[]) {
        this.groups.put(groupId, new Group(groupId, groupSize, moduleIds));
        this.numClasses = 0;
    }

    /**
     * Add new timeslot
     *
     * @param timeslotId
     * @param timeslot
     */

    public void addTimeslot(int timeslotId, String timeslot) {
        this.timeslots.put(timeslotId, new Timeslot(timeslotId, timeslot));
    }

    /**
     * Create classes using individual's chromosome
     *
     * @param individual
     */

    public void createClasses(Individual individual) {
        // Init classes
        Class classes[] = new Class[this.getNumClasses()];
        // Get individual's chromosome
        int chromosome[] = individual.getChromosome();
        int chromosomePos = 0;
        int classIndex = 0;

        for (Group group : this.getGroupsAsArray()) {
            int moduleIds[] = group.getModuleIds();
            for (int moduleId : moduleIds) {

                Class newClass = new Class(classIndex, group.getGroupId(), moduleId);

                // Add timeslot
                newClass.addTimeslot(chromosome[chromosomePos]);
                chromosomePos++;

                // Add room
                newClass.setRoomId(chromosome[chromosomePos]);
                chromosomePos++;

                // Add professor
                newClass.addProfessor(chromosome[chromosomePos]);
                chromosomePos++;

                // Add class to maps
                this.roomMap.putIfAbsent(newClass.getRoomId(), new ArrayList<>());
                this.groupMap.putIfAbsent(newClass.getGroupId(), new ArrayList<>());
                this.moduleMap.putIfAbsent(newClass.getModuleId(), new ArrayList<>());
                this.profMap.putIfAbsent(newClass.getProfessorId(), new ArrayList<>());

                this.roomMap.get(newClass.getRoomId()).add(newClass);
                this.groupMap.get(newClass.getGroupId()).add(newClass);
                this.moduleMap.get(newClass.getModuleId()).add(newClass);
                this.profMap.get(newClass.getProfessorId()).add(newClass);

                classes[classIndex] = newClass;

                classIndex++;

            }
        }

        this.classes = classes;
    }

    /**
     * Get room from roomId
     *
     * @param roomId
     * @return room
     */

    public Room getRoom(int roomId) {
        if (!this.rooms.containsKey(roomId)) {
            System.out.println("Rooms doesn't contain key " + roomId);
        }
        return (Room) this.rooms.get(roomId);
    }

    public HashMap<Integer, Room> getRooms() {
        return this.rooms;
    }

    /** * Get random room
     * @return room */

    public Room getRandomRoom() {
        Object[] roomsArray = this.rooms.values().toArray();
        Room room = (Room) roomsArray[(int) (roomsArray.length * Math.random())];
        return room;
    }

    /** * Get professor from professorId
     * @param professorId
     * @return professor */

    public Professor getProfessor(int professorId) {
        return (Professor) this.professors.get(professorId);
    }

    /** * Get module from moduleId
     *  @param moduleId
     *  @return module */

    public Module getModule(int moduleId) {
        return (Module) this.modules.get(moduleId);
    }

    /** * Get moduleIds of student group
     *  @param groupId
     *  @return moduleId array */

    public int[] getGroupModules(int groupId) {
        Group group = (Group) this.groups.get(groupId);
        return group.getModuleIds();
    }

    /** * Get group from groupId
     *  @param groupId
     *  @return group */

    public Group getGroup(int groupId) {
        return (Group) this.groups.get(groupId);
    }

    /** * Get all student groups
     * @return array of groups */

    public Group[] getGroupsAsArray() {
        return (Group[]) this.groups.values().toArray(new Group[this.groups.size()]);
    }

    /** * Get timeslot by timeslotId
     *  @param timeslotId
     *  @return timeslot */

    public Timeslot getTimeslot(int timeslotId) {
        return (Timeslot) this.timeslots.get(timeslotId);
    }

    /**
     * Get random timeslotId
     * @return timeslot */

    public Timeslot getRandomTimeslot() {
        Object[] timeslotArray = this.timeslots.values().toArray();
        Timeslot timeslot = (Timeslot) timeslotArray[(int)(timeslotArray.length * Math.random())];
        return timeslot;

    }

    /** * Get classes
     * @return classes */

    public Class[] getClasses() {
        return this.classes;
    }

    /** * Get number of classes that need scheduling
     *  @return numClasses */

    public int getNumClasses() {
        if (this.numClasses > 0) {
            return this.numClasses;
        }
        int numClasses = 0;
        Group groups[] = (Group[]) this.groups.values().toArray(new Group[this.groups.size()]);
        for (Group group : groups) {
            numClasses += group.getModuleIds().length;
        }
        this.numClasses = numClasses;

        return this.numClasses;
    }

    /** * Calculate the number of clashes
     * @return numClashes
     * */

    public int calcClashes(int size) {
        //only hard constranit
        //int clashes = 0;

        //with soft constraint
        int clashes = 100;
        for (Class classA : this.classes) {
            //  Hard Constraint Check room capacity
            int roomCapacity = this.getRoom(classA.getRoomId()).getRoomCapacity();
            int groupSize = this.getGroup(classA.getGroupId()).getGroupSize();
            if (roomCapacity < groupSize) {
                clashes = clashes-33*size;
            }

            // Hard Constraint Check if room is taken
            for (Class classB : this.classes) {
                if (classA.getRoomId() == classB.getRoomId()&& classA.getTimeslotId() == classB.getTimeslotId()&& classA.getClassId() != classB.getClassId()) {
                    clashes = clashes-33*size;
                    break;
                }
            }

            // Hard Constraint Check if professor is available
            for (Class classB : this.classes) {
                if (classA.getProfessorId() == classB.getProfessorId() && classA.getTimeslotId() == classB.getTimeslotId() && classA.getClassId() != classB.getClassId()) {
                    clashes = clashes-33*size;
                    break;
                }
            }
            // Soft Constraint check if professor perferedroom match room
            for (Class classB : this.classes) {
                int tmp_Prof= classB.getProfessorId();
                int tmp_Room=classB.getRoomId();
                if (this.getProfessor(tmp_Prof).getPreferedroom()== tmp_Room){
                    clashes++;
                }
            }
            // Soft Constraint check if professor perferedtime match timeslot
            for (Class classB : this.classes) {
                int tmp_Prof= classB.getProfessorId();
                int tmp_Time=classB.getTimeslotId();
                if (this.getProfessor(tmp_Prof).getPreferedtime()== tmp_Time){
                    clashes=clashes+2;
                }
            }
        }

        return clashes;
    }


}
