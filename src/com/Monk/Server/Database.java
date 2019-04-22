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
     * @return WOK if successful WERR if not
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
     * @return ROK if successful RERR if not
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

    /**
     * Modifies the route that is specified by the ID given with the newRoute values
     * @param routeID ID of the route to be modified
     * @param newRoute the updated route
     * @return MOK if successful MERR if not
     */
    public String modifyRoute(String routeID, Route newRoute){
        if(getRoute(routeID) == Constants.READ_ERROR)
            return Constants.MODIFY_ERROR;
        writeLock.lock();
        try {
            database.remove(routeID);
            database.put(newRoute.getId(), newRoute);
            return Constants.MODIFY_OK;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Deletes the route that is specified by the routeID
     * @param routeID the ID of the route to be deleted
     * @return DOK if successfull DERR if not
     */
    public String deleteRoute(String routeID) {
        if(getRoute(routeID) == Constants.READ_ERROR)
            return Constants.DELETE_ERROR;
        writeLock.lock();
        try {
            database.remove(routeID);
            return Constants.DELETE_OK;
        } finally {
            writeLock.unlock();
        }
    }

}
