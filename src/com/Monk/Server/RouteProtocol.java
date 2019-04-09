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

        switch (request[0]){
            case Constants.READ:
                if(request.length != 2)
                    return Constants.REQ_ERROR;
                return database.getRoute(request[1]);
            case Constants.WRITE:
                if(request.length != 4)
                    return Constants.REQ_ERROR;
                Route tempRoute = new Route(request[1], request[2], request[3]);
                return database.addRoute(tempRoute);
            default:
                return Constants.REQ_ERROR;
        }
    }
}
