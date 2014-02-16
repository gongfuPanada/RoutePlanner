package routeplanner

class RouteService {

    private List splitList(List list, Integer start, Integer end)
    {
        List temp = new ArrayList();
        for (int ii = start; ii <= end; ++ii)
        {
            temp.add(list.get(ii))
        }
        temp
    }

    private List removeItems(List list, Integer start, Integer end)
    {
        for (int ii = end; ii >= start; --ii)
        {
            list.remove(ii)
        }
        list
    }

    private boolean checkIfRoundTrip(List list)
    {
        list.get(0) == list.get(1)
    }

    Map adjustRouteForRoundTrip(Map model)
    {
        if (checkIfRoundTrip(model.locations))
        {
            Map address = model.locations[1]
            model.locations.remove(1)

            List<Map> temp = splitList(model.locations, (model.locations.size() / 2).intValue(), model.locations.size() - 1)
            model.locations = removeItems(model.locations, (model.locations.size() / 2).intValue(), model.locations.size() - 1)
            temp = temp.reverse()
            model.locations.addAll(temp)
            model.locations.add(address)
        }
        model
    }
}
