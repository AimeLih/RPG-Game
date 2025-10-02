package com.aimestart;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // List of starting weapons

        //List of starting monsters



        Player player1 = new Player(100, 16);
        Enemy enemy = new Enemy();
        System.out.println(player1.getWeapon());
        player1.check();
        System.out.println(player1.getAtk());
        System.out.println(player1.getCrit());
        System.out.println(enemy.getName());
        System.out.println(enemy.getAtk());

    }
}