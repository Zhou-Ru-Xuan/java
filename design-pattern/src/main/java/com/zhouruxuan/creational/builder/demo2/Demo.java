package com.zhouruxuan.creational.builder.demo2;

import com.zhouruxuan.creational.builder.demo2.builders.CarBuilder;
import com.zhouruxuan.creational.builder.demo2.builders.CarManualBuilder;
import com.zhouruxuan.creational.builder.demo2.cars.Car;
import com.zhouruxuan.creational.builder.demo2.cars.Manual;
import com.zhouruxuan.creational.builder.demo2.director.Director;

/**
 * Step-by-step car production
 * In this example, the Builder pattern allows step by step construction of different car models.
 * <p>
 * The example also shows how Builder produces products of different kinds (car manual) using the same building steps.
 * <p>
 * The Director controls the order of the construction. It knows which building steps to call to produce this or that car model. It works with builders only via their common interface. This allows passing different types of builders to the director.
 * <p>
 * The end result is retrieved from the builder object because the director can’t know the type of resulting product. Only the Builder object knows what does it build exactly.
 */
public class Demo {

    public static void main(String[] args) {
        Director director = new Director();

        // Director gets the concrete builder object from the client
        // (application code). That's because application knows better which
        // builder to use to get a specific product.
        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);

        // The final product is often retrieved from a builder object, since
        // Director is not aware and not dependent on concrete builders and
        // products.
        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.getCarType());


        CarManualBuilder manualBuilder = new CarManualBuilder();

        // Director may know several building recipes.
        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }

}