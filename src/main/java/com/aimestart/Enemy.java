
package com.aimestart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Enemy {
    private String name;
    private int hp;
    private int atk;
    private int def;
    private int xp;

    ArrayList<String> Monsters = new ArrayList<>();
    Random rand = new Random();

    public int atkrange(int min, int max){
        return rand.nextInt(max - min + 1) + min;
    }

    public Enemy(){
        Monsters.addAll(Arrays.asList("Wolf", "Golem", "Slime"));
        int randomness = rand.nextInt(Monsters.size());
        switch(Monsters.get(randomness)){
            case "Wolf":
                this.name = "Wolf";
                this.hp = 20;
                this.atk = atkrange(3,5);
                this.xp = 10;
                break;
            case "Slime":
                this.name = "Slime";
                this.hp = 14;
                this.atk = atkrange(2,4);
                this.xp = 7;
                break;
            case "Golem":
                this.name = "Golem";
                this.hp = 50;
                this.atk = atkrange(7,15);
                this.def = 10;
                this.xp = 40;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
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

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
    public void death(Player player){
        int initial = player.getXpbar();
        initial += this.xp;
        player.setXpbar(initial);
    }
}
