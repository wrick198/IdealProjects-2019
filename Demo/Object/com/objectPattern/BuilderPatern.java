package com.objectPattern;

import java.util.ArrayList;
import java.util.List;

interface Item{
    public String name();
    public Packing packing();
    public float price();
}

abstract class Burger implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    public abstract float price() ;
}

class VegBurger extends Burger{
    @Override
    public String name() {
        return "Veg Burger!";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}

class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger!";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}

abstract class ColdDrink implements Item{
    @Override
    public Packing packing() {
        return new Bottle();
    }

    public abstract float price() ;
}

class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "Pepsi drinking!";
    }

    @Override
    public float price() {
        return 5.0f;
    }
}

class Coke extends ColdDrink{
    @Override
    public String name() {
        return "Coke drinking!";
    }

    @Override
    public float price() {
        return 5.5f;
    }
}

interface Packing{
    String pack();
}

class Wrapper implements Packing{
    @Override
    public String pack() {
        return "Wrapper";
    }
}

class Bottle implements Packing{
    @Override
    public String pack() {
        return "Bottle";
    }
}

class Meal{
    List<Item> mealList=new ArrayList<Item>();

    public void addItem(Item item){
        mealList.add(item);
    }

    public float getCost(){
        float cost=0.0f;
        for(Item it:mealList)
            cost+=it.price();
        return cost;
    }

    public void showItems(){
        for(Item it:mealList){
            System.out.println("商品名称"+it.name());
            System.out.println("商品打包"+it.packing().pack());
            System.out.println("价钱"+it.price());
        }
        System.out.println("总消费:"+getCost());
    }
}

public class BuilderPatern {
    public static void main(String[] args) {
        Meal meal=new Meal();
        meal.addItem(new Coke());
        meal.addItem(new VegBurger());
        meal.addItem(new ChickenBurger());
        meal.showItems();
    }
}
