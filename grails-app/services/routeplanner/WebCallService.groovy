package routeplanner
import grails.converters.JSON
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class WebCallService
{
    Object makeHttpRequest(HTTPBuilder http)
    {
        String temp
        synchronized (Thread.currentThread())
        {
            http.request(Method.GET, JSON)
            {
                Thread.currentThread().wait(400)
                response.success = { resp, json ->
                    temp = json.getText()
                    temp = temp.replaceAll('\\r\\n', '')
                }
                response.failure = { resp ->
                    return [status: false];
                }
            }
        }
        JSON.parse(temp)
    }
}
