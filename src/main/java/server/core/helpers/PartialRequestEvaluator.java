package server.core.helpers;

import server.core.requests.HeaderParser;
import server.core.requests.Request;

import java.util.HashMap;
import java.util.Map;

public class PartialRequestEvaluator {
    public static Map<String, Integer> getRangeBoundaries(Request request, int fileLength) {
        String range = HeaderParser.parseRange(request);
        return parseRangeBoundaries(range, fileLength);
    }

    private static Map<String, Integer> parseRangeBoundaries(String range, int fileLength) {
        Map<String, Integer> rangeBoundaries = new HashMap<>();

        String[] rangeValues = range.split("-");
        int startIndex;
        int endIndex;

        if (range.lastIndexOf("-") == range.length()-1) {
            startIndex = Integer.parseInt(rangeValues[0]);
            endIndex = fileLength - 1;
        } else if (range.lastIndexOf("-") == 0) {
            startIndex = (fileLength) - Integer.parseInt(rangeValues[1]);
            endIndex = fileLength - 1;
        } else {
            startIndex = Integer.parseInt(rangeValues[0]);
            endIndex = Integer.parseInt(rangeValues[1]);
        }

        rangeBoundaries.put("startIndex", startIndex);
        rangeBoundaries.put("endIndex", endIndex);

        return rangeBoundaries;
    }
}
