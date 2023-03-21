package com.zhouruxuan.creational.builder.demo2.builders;

import com.zhouruxuan.creational.builder.demo2.cars.CarType;
import com.zhouruxuan.creational.builder.demo2.components.Engine;
import com.zhouruxuan.creational.builder.demo2.components.GPSNavigator;
import com.zhouruxuan.creational.builder.demo2.components.Transmission;
import com.zhouruxuan.creational.builder.demo2.components.TripComputer;

/**
 * Builder interface defines all possible ways to configure a product.
 */
public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}