package com.Monk.Server;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A pseudo database that stores routes temporarily
 */
public class Database {

    private HashMap<String, Route> database;
    private ReentrantReadWriteLock rwLock;
    private Lock readLock, writeLock;

    public Database(){
        database = new HashMap<>();
        rwLock = new ReentrantReadWriteLock(true);
        readLock = rwLock.readLock();
        writeLock = rwLock.writeLock();
    }

    /**
     * Inserts the new route to the database if it does not exist
     * @param route the Route that is inserted in the database
     * @return WOK if successfull WERR if not
     * @see Route
     */
    public String addRoute(Route route) {

        readLock.lock();
        try {
            if(database.containsKey(route.getId()))
                return Constants.WRITE_ERROR;
        } finally {
            readLock.unlock();
        }

        writeLock.lock();
        try {
            database.put(route.getId(), route);
        } finally {
            writeLock.unlock();
        }
        return Constants.WRITE_OK;
    }

    /**
     * Gets the route based on the id that is requested
     * @param routeID the id of the route to be found
     * @return ROK if successfull RERR if not
     * @see Route
     */
    public String getRoute(String routeID) {
        readLock.lock();
        try {
            if(database.containsKey(routeID))
                return Constants.READ_OK + " " + database.get(routeID).toString();
        } finally {
            readLock.unlock();
        }
        return Constants.READ_ERROR;
    }

}
