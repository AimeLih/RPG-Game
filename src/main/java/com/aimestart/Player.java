package com.aimestart;

import java.util.*;


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
    Scanner scan = new Scanner(System.in);
    public Player(int hp, int speed){
       // StartingWeapons.addAll(Arrays.asList("Rusted Dagger", "Wooden Sword", "Old Scythe"));
        //int randomness = rand.nextInt(StartingWeapons.size());
      //  String randomweapon = StartingWeapons.get(randomness);
        this.hp = hp;
       // this.Weapon = randomweapon;
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
            case "Rusted Dagger":
                this.atk = atkrange(2,4);
                this.crit = 35;
                break;
            case "Wooden Sword":
                this.atk = atkrange(5,8);
                this.crit = 10;
                break;
            case "Old Scythe":
                this.atk = atkrange(1,4);
                this.crit = 60;
        }
    }
    public void levelup(){
        if(xpbar == endbar){
            level++;
            hp += 10;
        }
        endbar += 25;
    }
    //Helps to adjust battle flow
    private void addDelay(int milliseconds) {
        try {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    synchronized (this) {
                        this.notify();
                    }
                }
            };

            synchronized (task) {
                timer.schedule(task, milliseconds);
                task.wait(); // Wait for the timer to complete
            }
            timer.cancel(); // Clean up the timer
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void start() {
        boolean weaponselected = false;
        System.out.println("Welcome traveler to the world of Rashinova.");
             addDelay(1250);
        System.out.println("Rashinova used to be a peaceful realm until it fell to the clutches of evil and now its a home for monsters and evil alike.");
        addDelay(1750);
        System.out.println("Please take up arms and bring peace back to Rashinova traveler");
        addDelay(1250);
        System.out.println("-----------------------------------------------------------------");

        addDelay(3500);
        System.out.println("A traveler cannot be safe without a trusty weapon at their side. It's unfortunate that we only have these basic weapons but please pick one!");
        addDelay(3000);
        while (weaponselected != true) {
            System.out.println("Wooden Sword\n" +
                    "AtkR: 5-8\n" +
                    "Crit Chance: 10%\n" +
                    "--------------------\n" +
                    "Rusted Dagger\n" +
                    "AtkR: 2-4\n" +
                    "Crit Chance: 35%\n" +
                    "-------------------\n" +
                    "Old Scythe\n" +
                    "AtkR: 1-4\n" +
                    "Crit Chance:60%");

            String weaponselection = scan.nextLine();
            switch (weaponselection) {
                case "Wooden Sword":
                    System.out.println("Wooden Sword -");
                    addDelay(750);
                    System.out.println("A sword that was crafted out of some dusty wood on a cool weekday. Make sure not to overuse it");
                    addDelay(2750);
                    System.out.println("Will you travel with this weapon?\n" +
                            "Y-N");
                    String s4 = scan.nextLine();
                    if (s4.equals("Y")) {
                        weaponselected = true;
                        this.setWeapon(weaponselection);
                        break;
                    }
                    break;
                case "Rusted Dagger":
                    System.out.println("Rusted Dagger -");
                    addDelay(750);
                    System.out.println("A dagger that has gone past its usefulness. Might be able to inflict minor wounds");
                    addDelay(2750);
                    System.out.println("Will you travel with this weapon?\n" +
                            "Y-N");
                    String s5 = scan.nextLine();
                    if (s5.equals("Y")) {
                        weaponselected = true;
                        this.setWeapon(weaponselection);
                        break;
                    }
                    break;
                case "Old Scythe":
                    System.out.println("Old Scythe -");
                    addDelay(750);
                    System.out.println("A scythe that was handled with care until its owner passed away. You feel as if it yearns to be used once more");
                    addDelay(2750);
                    System.out.println("Will you travel with this weapon?\n" +
                            "Y-N");
                    String s6 = scan.nextLine();
                    if (s6.equals("Y")) {
                        weaponselected = true;
                        this.setWeapon(weaponselection);
                        break;
                    }
                    break;
            }
        }

    }
    public void battle(){
        Scanner scan = new Scanner(System.in);
        Enemy enemy = new Enemy();
        System.out.println("Battling against" + "" + enemy.getName());
        //We exit this loop once either player or enemy reaches 0 hp
        outerloop:
        while(enemy.getHp() > 0 || this.getHp() > 0){
            int reduction = 0;
            boolean crit = false;

            System.out.println("Player choose your action");
            System.out.println("Player HP: " + this.getHp() + "                             " + "Monster HP: " + enemy.getHp());
            System.out.println("1. Attack with " + this.getWeapon());
            int s1 = scan.nextInt();
            switch (s1){
                case 1:
                    this.check();
                    int damage = this.getAtk();
                    //Calculating defense to get our new damage
                    int critcheck = rand.nextInt(this.getCrit() + 1);
                    if(critcheck <= this.getCrit()){
                        damage *= 2;
                        System.out.println("Your " + this.getWeapon() + " feels light in your hands");
                    }
                    if(enemy.getDef() != 0){
                        reduction = (enemy.getDef()/100) * this.getAtk();
                        System.out.println("The enemy's defense proves to be formidable");
                        addDelay(1000);
                    }
                        enemy.setHp(enemy.getHp() - (damage - reduction));
                        System.out.println("Player hits enemy for " + damage + " damage");

                    addDelay(1500);
                    if(enemy.getHp() <= 0){
                        System.out.println("Player wins!!!\n " +
                                "Earned " + enemy.getXp() + " xp");
                        enemy.death(this);

                        break outerloop;
                    }
            }
            enemy.checkEnemy();
            System.out.println("Enemy hits player for " + enemy.getAtk() + " damage");
            this.setHp(this.getHp() - enemy.getAtk());
            System.out.println("---------------------------------------------------");
            addDelay(1000);
        }
        addDelay(500);
        System.out.println("Current XP is:"  + this.getXpbar());
    }
}
