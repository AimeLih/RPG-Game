package com.aimestart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Player {

    private int hp;
    private String Weapon;
    private int atk;
    private int crit = 0;
    private int speed;
    private int level = 1;
    private int xpbar = 0;
    private int endbar = 100;
   //Gives each player a different starting weapon
    Random rand = new Random();
    ArrayList<String> StartingWeapons = new ArrayList<>();
    Enemy enemy;
    Timer timer = new Timer();
    public Player(int hp, int speed){
        StartingWeapons.addAll(Arrays.asList("Wooden Dagger", "Wooden Sword", "Wooden Scythe"));
        int randomness = rand.nextInt(StartingWeapons.size());
        String randomweapon = StartingWeapons.get(randomness);
        this.hp = hp;
        this.Weapon = randomweapon;
        this.speed = speed;
    }

    public int atkrange(int min, int max){
        return rand.nextInt(max - min + 1) + min;
    }

    public String getWeapon() {
        return Weapon;
    }

    public void setWeapon(String weapon) {
        Weapon = weapon;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXpbar() {
        return xpbar;
    }

    public void setXpbar(int xpbar) {
        this.xpbar = xpbar;
    }

    public int getEndbar() {
        return endbar;
    }

    public void setEndbar(int endbar) {
        this.endbar = endbar;
    }

    public void check(){
        switch(this.getWeapon()){
            case "Wooden Dagger":
                this.atk = atkrange(2,4);
                break;
            case "Wooden Sword":
                this.atk = atkrange(5,8);
                break;
            case "Wooden Scythe":
                this.atk = atkrange(1,4);
                this.crit = 30;
        }
    }
    public void levelup(){
        if(xpbar == endbar){
            level++;
            hp += 10;
        }
        endbar += 25;
    }
    public void battle(){
        Scanner scan = new Scanner(System.in);
        Enemy enemy = new Enemy();
        System.out.println("Battling against" + "" + enemy.getName());
        outerloop:
        while(enemy.getHp() > 0 || this.getHp() > 0){
            System.out.println("Player choose your action");
            System.out.println("Player HP: " + this.getHp() + "                             " + "Monster HP: " + enemy.getHp());
            System.out.println("1. Attack with " + this.getWeapon());
            int s1 = scan.nextInt();
            switch (s1){
                case 1:
                    this.check();
                    int damage = this.getAtk();
                    enemy.setHp(enemy.getHp() - damage);
                    System.out.println("Player hits enemy for " + this.getAtk());
                    if(enemy.getHp() <= 0){
                        System.out.println("PLayer wins!!!");
                        break outerloop;
                    }
            }
            System.out.println("Enemy hits player for " + enemy.getAtk());
            this.setHp(this.getHp() - enemy.getAtk());
        }
    }
}
