package by.javacource.task3.util;

public class BusStopIdGenerator {
    private static long counter;

    private BusStopIdGenerator(){

    }

    public static long generateId(){
        return ++counter;
    }
}
