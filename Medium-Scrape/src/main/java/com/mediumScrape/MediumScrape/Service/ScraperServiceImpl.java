package com.mediumScrape.MediumScrape.Service;

import com.mediumScrape.MediumScrape.Model.ResponseDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ScraperServiceImpl implements ScraperService {


    @Override
    public Set<ResponseDTO> getVehicleByModel(String vehicleModel) {
        //create a set to add all responses
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        String url = "https://riyasewana.com/search/";
        extractDataFromRiyasewana(responseDTOS, url + vehicleModel);

        return responseDTOS;
    }

    private void extractDataFromRiyasewana(Set<ResponseDTO> responseSet, String url) {

        try {
            //set agent so that it does not block my IP
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .get();

        //the most outer html element is content
            Element element = document.getElementById("content");
        //each car is stored as a list element
            Elements allElements = element.getElementsByTag("li");

            //check for each car
            for (Element a : allElements) {
                ResponseDTO responseDTO = new ResponseDTO();

                //title and href are stored in the imgbox div
                Element aTag = a.select("div.imgbox a").first();

                //price is stored in the boxintxt div
                Element prices = a.select("div.boxintxt.b").first();

                if (aTag != null && prices != null) {
                    //take values from all needed attributes
                    String name = aTag.attr("title");
                    String href = aTag.attr("href");
                    String priceOfCar = prices.text();
                    //set values
                    responseDTO.setTitle(name);
                    responseDTO.setUrl(href);
                    responseDTO.setPrice(priceOfCar);

                }
                //add these responses to the responselist
                if (responseDTO.getUrl() != null) responseSet.add(responseDTO);
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}