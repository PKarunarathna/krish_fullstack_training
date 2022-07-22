package com.example.mementopattern.mementopattern;

public class Application {

    public static void main(String[]  args){

        CareTaker careTaker=new CareTaker();
        Cart cart=new Cart();
        cart.addItem(new Item("book"));
        cart.addItem(new Item("toy"));
        careTaker.save(cart);
        System.out.println(cart);

        cart.addItem(new Item("pen"));
        careTaker.save(cart);
        System.out.println(cart);

        cart.addItem(new Item("Chocolate"));
        careTaker.save(cart);
        System.out.println(cart);

        careTaker.revert(cart);
        System.out.println(cart);

        cart.addItem(new Item("Pencil"));
        careTaker.save(cart);
        System.out.println(cart);

    }
}
