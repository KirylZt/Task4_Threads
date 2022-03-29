package by.javacource.task3.main;

import by.javacource.task3.entity.Bus;
import by.javacource.task3.exception.ApplicationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws ApplicationException {

        List<Long> randomBusStops1 = Arrays.asList(3L,6L,2L,5L,1L,4L,7L);
        List<Long> randomBusStops2 = Arrays.asList(3L,0L,5L,4L,7L,2L,5L);
//        List<Long> randomBusStops3 = Arrays.asList(1L,7L,5L,4L,2L,3L,6L);
        Bus bus1 = new Bus(35,20, randomBusStops1);
        Bus bus2 = new Bus(45,27, randomBusStops2);
//        Bus bus3 = new Bus(43,9, randomBusStops3);

        List<Bus> buses = new ArrayList<>();
        buses.add(bus1);
        buses.add(bus2);
//        buses.add(bus3);

        ExecutorService executorService = Executors.newFixedThreadPool(buses.size());
        buses.forEach(executorService::execute);
        executorService.shutdown();
    }
}
