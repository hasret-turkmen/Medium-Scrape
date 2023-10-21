package com.mediumScrape.MediumScrape.Model;


//THIS IS THE RETURN OBJECT
//WHEN I SEND A REQUEST I WANT TO SEE NAME,URL AND PRICE OF THE CAR

import lombok.Data;
@Data
public class ResponseDTO {

    String title;
    String url;
    //price is string since some of them are marked as "negotiable"
    String price;


}
