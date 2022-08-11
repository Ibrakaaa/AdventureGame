
import java.util.Random;

public class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    public BattleLoc(Player player, String name,Obstacle obstacle,String award,int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }



    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();

        System.out.println("Su an Buradasınız :"+this.getName());
        System.out.println("Dikkatli Ol! Burada "+ obsNumber+ " adet"+this.getObstacle().getName()+" Yasiyor!");
        System.out.println("<S>avas ya da <K>ac");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if(selectCase.equals("S")){
            System.out.println("Kan Dokmeye Hazir Ol");
            if(combat(obsNumber)){
                System.out.println(this.getName()+" alanindaki tüm düsmanlari yendiniz.");
                if(this.getObstacle().getName().equals("Yilan")){
                    snakeAward();
                }
                if(this.award.equals("Yemek")&& !this.getPlayer().getInventory().isFood()){
                    this.getPlayer().getInventory().setFood(true);
                    System.out.println(this.award+" Kazandiniz.");
                }
                if(this.award.equals("Su")&& !this.getPlayer().getInventory().isWater()){
                    this.getPlayer().getInventory().setWater(true);
                    System.out.println(this.award+" Kazandiniz.");
                }
                if(this.award.equals("Odun")&& !this.getPlayer().getInventory().isFirewood()){
                    this.getPlayer().getInventory().setFirewood(true);
                    System.out.println(this.award+" Kazandiniz.");
                }

                this.getPlayer().setAwards(this.getPlayer().getAwards()+this.getAward()+" , ");
                System.out.println("Kazandigin Oduller: "+ this.getPlayer().getAwards());

                return true;
            }
        }
        if(this.getPlayer().getHealth()<=0){
            System.out.println("Oldunuz :(\nGame Over!");
            return false;
        }

        return true;
    }
    public boolean combat(int obsnumber){
        for(int i =1;i<= obsnumber;i++){
            this.getObstacle().setHealth(this.getObstacle().getOrijinalHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth()>0 && this.getObstacle().getHealth()>0){

                    System.out.print("<V>ur veya <K>ac: ");
                    String selectCombat = input.nextLine().toUpperCase();
                    if(selectCombat.equals("V")){
                        System.out.println("Siz Vurdunuz.");
                        this.getObstacle().setHealth(this.getObstacle().getHealth()-this.getPlayer().getTotalDamage());
                        afterHit();

                    if(this.getObstacle().getHealth()>0){
                        System.out.println("Canavar Size Vurdu");
                        int obstacleDAmage = this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDAmage<0){
                            obstacleDAmage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDAmage);
                        afterHit();
                    }
                }else{
                    return false;
                }
            }
            if(this.getObstacle().getHealth()<this.getPlayer().getHealth()){
                System.out.println(i+". Düsmani Yendiniz");
                System.out.println(this.getObstacle().getAward()+" para kazandiniz.");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getObstacle().getAward());
                System.out.println("Guncel Paraniz: "+this.getPlayer().getMoney());


            }else{
                return false;
            }
        }

        return true;
    }
    private void snakeAward(){
        System.out.println("Yilanlari Öldürdünüz ve Hediye İcin Cekilis Hakki Kazandiniz...");
        int awardRand= Mine.random.nextInt(1,101);
        if(awardRand<=8){
            System.out.println("Tabanca Kazandiniz");
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(1));
        }
        if(awardRand>8 && awardRand<=12){
            System.out.println("Kilic Kazandiniz");
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(2));
        }
        if(awardRand>12 && awardRand<=15){
            System.out.println("Tufek Kazandiniz");
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(3));
        }
        if(awardRand>15 && awardRand<=23){
            System.out.println("Hafif Zirh Kazandiniz");
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));
        }
        if(awardRand>23 && awardRand<=27){
            System.out.println("Orta Zirh Kazandiniz");
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));
        }
        if(awardRand>27 && awardRand<=30){
            System.out.println("Agir Zirh Kazandiniz");
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));
        }
        if(awardRand>30 && awardRand<=43){
            System.out.println("1 Lira Kazandiniz");
            this.getPlayer().setAwards(this.getPlayer().getAwards()+1);
        }
        if(awardRand>43 && awardRand<=50){
            System.out.println("5 Lira Kazandiniz");
            this.getPlayer().setAwards(this.getPlayer().getAwards()+5);
        }
        if(awardRand>50 && awardRand<=55){
            System.out.println("10 Lira Kazandiniz");
            this.getPlayer().setAwards(this.getPlayer().getAwards()+10);
        }
        if(awardRand>55){
            System.out.println("Sanssız Günündesin, ödül kazanamadin :(");
        }
    }

    public void afterHit(){
        System.out.println("Caniniz: "+this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName()+" Cani: "+this.getObstacle().getHealth());
        System.out.println();
    }
    public void playerStats(){
        System.out.println("Oyuncu Degerleri: ");
        System.out.println("--------------");
        System.out.println("Saglik: "+this.getPlayer().getHealth());
        System.out.println("Silah: "+this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zirh: "+this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: "+this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar: "+this.getPlayer().getTotalDamage());
        System.out.println("Para: "+this.getPlayer().getMoney());


    }
    public void obstacleStats(int i){
        System.out.println(i+" ."+this.getObstacle().getName()+" Ozellikleri: ");
        System.out.println("--------------");
        System.out.println("Saglik: "+this.getObstacle().getHealth());
        System.out.println("Hasar: "+this.getObstacle().getDamage());
        System.out.println("Odul: "+this.getObstacle().getAward());
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle())+1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
