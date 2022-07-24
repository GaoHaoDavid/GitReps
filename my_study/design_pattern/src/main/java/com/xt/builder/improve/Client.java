package com.xt.builder.improve;

public class Client {
    public static void main(String[] args) {

        CommonHouse commonHouse=new CommonHouse();
        HouseDirector houseDirector=new HouseDirector(commonHouse);
        houseDirector.constructHouse();

        HighHouse highHouse=new HighHouse();
        houseDirector.setHouseBuilder(highHouse);
        houseDirector.constructHouse();
    }
}
