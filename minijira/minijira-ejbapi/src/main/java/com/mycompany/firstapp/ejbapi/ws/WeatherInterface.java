package com.mycompany.firstapp.ejbapi.ws;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    18.09.13
 * Time:    13:54
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Local
public interface WeatherInterface {
    String getWeather(String city, String country);
    List<String> getCitiesByCountry(String country);
}
