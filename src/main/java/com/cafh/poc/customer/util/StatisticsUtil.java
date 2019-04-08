package com.cafh.poc.customer.util;

import com.cafh.poc.customer.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticsUtil {

    public Double getAverage(List<Integer> dataList) {
        return dataList.stream().parallel()
                .mapToInt(Integer::intValue)
                .average().orElse(0.0);
    }

    public Double getStDev(List<Integer> dataList) {
            return Math.sqrt(getVariance(dataList));
    }

    public Double getVariance(List<Integer> dataList) {
        double avg = getAverage(dataList);
        return dataList.stream().parallel().map(data -> (data - avg) * (data - avg))
                .mapToDouble(Double::doubleValue)
                .sum() / (dataList.size() - 1);
    }

}
