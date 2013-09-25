package com.mycompany.firstapp.ejb.ws;

import com.mycompany.firstapp.ejb.util.Log;
import com.mycompany.firstapp.ejb.ws.webservicex.*;
import com.mycompany.firstapp.ejbapi.ws.WeatherInterface;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by  Alexey Gorovoy
 * Date:    18.09.13
 * Time:    13:55
 * Email:   alexey.gorovoy.work@gmail.com
 */
@Local (value = WeatherInterface.class)
@Stateless
public class Weather implements WeatherInterface{

    @WebServiceRef(wsdlLocation="http://www.webservicex.net/globalweather.asmx?WSDL")
    GlobalWeather globalWeather;

    @Override
    public String getWeather(String city, String country) {
        Log.getLogger().info("getWeather called: " + city + ", " + country + ";");
        GlobalWeatherSoap globalWeatherSoap = globalWeather.getGlobalWeatherSoap();
        String response = globalWeatherSoap.getWeather(city, country);
        StringBuilder sb = new StringBuilder();
        sb.append("Temperature: ");
        sb.append(response.substring(response.indexOf("<Temperature>") + 13, response.indexOf("</Temperature>")));
        return sb.toString();
    }

    @Override
    public List<String> getCitiesByCountry(String countryName) {
        GlobalWeatherSoap globalWeatherSoap = globalWeather.getGlobalWeatherSoap();
        String allCities = globalWeatherSoap.getCitiesByCountry(countryName);

        List<String> cities = new LinkedList<String>();

        int firstIndex = 0;
        int secondIndex = 1;
        while (true) {
            firstIndex = allCities.indexOf("<City>", secondIndex);
            secondIndex = allCities.indexOf("</City>", firstIndex);
            if (firstIndex != -1) {
                cities.add(allCities.substring(firstIndex+6, secondIndex)); }
            else {
                break;
            }

        }
        return  cities;

    }


}
