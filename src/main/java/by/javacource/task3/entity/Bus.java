package by.javacource.task3.entity;

import by.javacource.task3.util.BusIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Bus implements Runnable{

    private static final Logger LOGGER = LogManager.getLogger();
    private final List<Long> busStopNumbers;
    private final int MAX_CAPACITY;
    private int currentPeopleAmount;
    private final long BUS_ID;
    private State state;

    public enum State {
        WAITING, RUNNING, COMPLETED
    }

    public Bus(int maxCapacity, int currentPeopleAmount, List<Long> busStopNumbers) {
        MAX_CAPACITY = maxCapacity;
        this.currentPeopleAmount = currentPeopleAmount;
        this.busStopNumbers = busStopNumbers;
        BUS_ID = BusIdGenerator.generateId();
    }

    public long getBusId() {
        return BUS_ID;
    }

    public int getCurrentPeopleAmount() {
        return currentPeopleAmount;
    }

    public void setCurrentPeopleAmount(int currentPeopleAmount) {
        this.currentPeopleAmount = currentPeopleAmount;
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void run() {
        LOGGER.info("Bus {} started its route", BUS_ID);

        Route route = Route.getInstance();
        for (long busStopNumber : busStopNumbers) {
            BusStop busStop = new BusStop();
            try {
                busStop = route.obtainBusStop(busStopNumber, this);
                LOGGER.info("Bus {} arrived to a bus stop {} with {} people",
                        BUS_ID, busStop.getBusStopId(), currentPeopleAmount);

                busStop.processBus(this);
            } finally {
                route.releaseBusStop(busStop);
                LOGGER.info("Bus {} drove away from a bus stop {} with {} people",
                        BUS_ID, busStop.getBusStopId(), currentPeopleAmount);
            }
        }

        LOGGER.info("Bus {} completed its route", BUS_ID);
        state = State.COMPLETED;
    }
}
