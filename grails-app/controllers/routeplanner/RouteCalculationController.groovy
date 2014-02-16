package routeplanner

import groovyx.net.http.HTTPBuilder

class RouteCalculationController {
    def routeService
    def webCallService

    def index() { }

    def route(params)
    {
        Map model = [:]
        model.locations = new ArrayList<Map>()
        List<String> addresses = params.addresses.replaceAll(' ', '%20').split('\\r\\n')
        model.locations.add([distance: 0, address: addresses[0].replaceAll('%20', ' ')])

        String startingPoint = addresses[0]
        String endingPoint

        for (int ii = 1; ii < addresses.size(); ++ii)
        {
            endingPoint = addresses[ii]

            def http = new HTTPBuilder('http://maps.googleapis.com/maps/api/directions/json?'
                    + "origin=" + startingPoint + "&destination="
                    + endingPoint + "&sensor=false")

            Object json = webCallService.makeHttpRequest(http)
            model.locations.add([distance: json.routes.legs[0].distance[0].value, address: endingPoint.replaceAll('%20', ' ')])
        }

        model.locations.sort { a, b ->
           a.distance <=> b.distance ?: a.address <=> b.address
        }

        model = routeService.adjustRouteForRoundTrip(model)
        model
    }
}
