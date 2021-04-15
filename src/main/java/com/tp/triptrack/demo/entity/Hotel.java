package com.tp.triptrack.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Hotel {
    public String type ;
    public String hotelId ;
    public String chainCode ;
    public String dupeId ;
    public String name ;
    public int rating ;
    public String cityCode ;
    public double latitude ;
    public double longitude ;
    List<Offer> offers ;


}
