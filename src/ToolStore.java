public class ToolStore extends NormalLoc{
    public ToolStore(Player player){
        super(player,"Magaza");
    }
    @Override
    public boolean onLocation() {
        System.out.println("--------Magazaya Hosgeldiniz-------- !");
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1- Silahlar-");
            System.out.println("2- Zirhlar");
            System.out.println("3- Cikis Yap");
            System.out.print("Seciminiz: ");

            int selectCase = input.nextInt();
            while (selectCase<1 || selectCase>3){
                System.out.print("Hatalı Deger Girdiniz. Lutfen Tekrar Giriniz.");
                selectCase = input.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;

                case 2:
                    printArmor();
                    buyArmor();
                    break;

                case 3:
                    System.out.println("Tekrar Bekleriz...");
                    showMenu = false;
                    break;
        }
        }
        return true;
    }
    public void printWeapon(){
        System.out.println("----Silahlar----");
        for(Weapon w: Weapon.weapons()){
            System.out.println(w.getId()+"--" +w.getName()+
                    "|| Para: "+w.getPrice()+" Hasar: "+w.getDamage()+" ||");
        }System.out.println("0 - Cikis Yap");

    }
    public void buyWeapon(){
        System.out.println("Bir Silah Seciniz: ");

        int selectWeaponID = input.nextInt();
        while (selectWeaponID<0 || selectWeaponID> Weapon.weapons().length){
            System.out.println("Hatali Deger. Tekrar Giriniz...");
            selectWeaponID = input.nextInt();
        }
        if(selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponID);
            if(selectedWeapon != null){
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yetersiz Bakiye :(");
                }else{
                    System.out.println(selectedWeapon.getName()+" Silahini Satin Aldiniz.");
                    int balance = this.getPlayer().getMoney()-selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Mevcut Bakiye: "+ balance);
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);


                }
            }
        }


    }
    public void printArmor(){

        System.out.println("-------Zırhlar-------");
        for(Armor a:Armor.armors()){
            System.out.println(a.getId()+ " -  < "+a.getName()+ " Zirh :"+"Blok Gucu: "+a.getBlock()+" Para: "+a.getPrice()+" >");
        }
        System.out.println("0 - Cikis Yap");
    }
    public void buyArmor(){
        System.out.println("Bir Zirh Seciniz: ");
        int selectArmorID = input.nextInt();
        while (selectArmorID<0 || selectArmorID> Armor.armors().length){
            System.out.println("Hatali Deger. Tekrar Giriniz...");
            selectArmorID = input.nextInt();
        }
        if(selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorID);

            if(selectedArmor != null){
                if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yetersiz Bakiye :(");
                }else{
                    System.out.println(selectedArmor.getName()+" zirhini Satin Aldiniz.");
                    int balance = this.getPlayer().getMoney()- selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Mevcut Bakiye: "+ balance);


                }
            }
        }

    }
}
