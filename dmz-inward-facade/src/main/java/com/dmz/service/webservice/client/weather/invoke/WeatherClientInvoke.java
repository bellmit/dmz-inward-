package com.dmz.service.webservice.client.weather.invoke;

import java.util.List;

/**
 * Created by dmz on 2016/2/29.
 * generate WeatherMethod
 */
public interface WeatherClientInvoke {
    List<String> getWeatherbyCityName(String city);
}
