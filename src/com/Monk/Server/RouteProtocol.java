package com.Monk.Server;

/**
 * A protocol that manages incoming queries
 */
public class RouteProtocol {

    private Database database;

    public RouteProtocol(Database database) {
        this.database = database;
    }

    /**
     * Parses the request and forms a reply to be sent
     * @param req the request that is goint to be parsed
     * @return a reply to the request in text form
     */
    public String processRequest(String req) {
        String[] request = req.split(" ");

        Route tempRoute;
        switch (request[0]){
            case Constants.READ:
                if(request.length != 2)
                    return Constants.REQ_ERROR;
                return database.getRoute(request[1]);
            case Constants.WRITE:
                if(request.length != 4)
                    return Constants.REQ_ERROR;
                tempRoute = new Route(request[1], request[2], request[3]);
                return database.addRoute(tempRoute);
            case Constants.MODIFY:
                if(request.length != 5)
                    return Constants.REQ_ERROR;
                tempRoute = new Route(request[2], request[3], request[4]);
                return database.modifyRoute(request[1], tempRoute);
            case Constants.DELETE:
                if(request.length != 2)
                    return Constants.REQ_ERROR;
                return database.deleteRoute(request[1]);
            default:
                return Constants.REQ_ERROR;
        }
    }
}
