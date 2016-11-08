package lodeinfo.controller;

import lodeinfo.repository.AsteriskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(
        value = "/asterisk")
public class AsteriskController {

    @Autowired
    AsteriskRepository asteriskRepository;


    @RequestMapping(
            value = "/getStatForDay",
            method = RequestMethod.GET
    ) public Map<String, Integer> getStatsForDay(@RequestParam String day) {

        Map<String, Integer> dayStats = new HashMap<String, Integer>();

        dayStats.put("curDay", Integer.parseInt(day.substring(8)));
        dayStats.put("missedCount", asteriskRepository.missedCountForDay(day));
        dayStats.put("incomingCount", asteriskRepository.incomingCountForDay(day));
        dayStats.put("answeredCount", asteriskRepository.answeredCountForDay(day));
        dayStats.put("avgTime", asteriskRepository.avgTimeForDay(day));

        return dayStats;
    }

    @RequestMapping(
            value = "/getStatForHour",
            method = RequestMethod.GET
    ) public Map<String, Integer> getStatsForHour(@RequestParam String day, @RequestParam int hour) {

        Map<String, Integer> hourStats = new HashMap<String, Integer>();

        // Error to big hour handle
        if (hour > 23) {
            hourStats.put("curHour", 9999);
            return hourStats;
        }

        hourStats.put("curHour", hour);
        hourStats.put("missedCount", asteriskRepository.missedCountForHour(day, hour));
        hourStats.put("incomingCount", asteriskRepository.incomingCountForHour(day, hour));
        hourStats.put("outgoingCount", asteriskRepository.outgoingCountForHour(day, hour));
        hourStats.put("callDuration", asteriskRepository.callDurationForHour(day, hour));

        return hourStats;
    }
}
