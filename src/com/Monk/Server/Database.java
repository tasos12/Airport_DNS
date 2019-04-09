package com.Monk.Server;

import java.util.HashMap;

/**
 * A pseudo database that stores routes temporarily
 */
public class Database {

    private HashMap<String, Route> database;

    public Database(){
        database = new HashMap<String, Route>();
    }

    /**
     * Inserts the new route to the database if it does not exist
     * @param route the Route that is inserted in the database
     * @return WOK if successfull WERR if not
     * @see Route
     */
    public synchronized String addRoute(Route route) {
        if(database.containsKey(route.getId()))
            return Constants.WRITE_ERROR;
        database.put(route.getId(), route);
        return Constants.WRITE_OK;
    }

    /**
     * Gets the route based on the id that is requested
     * @param routeID the id of the route to be found
     * @return ROK if successfull RERR if not
     * @see Route
     */
    public synchronized String getRoute(String routeID) {
        if(database.containsKey(routeID))
            return Constants.READ_OK + " " + database.get(routeID).toString();
        return Constants.READ_ERROR;
    }

}
