

import java.util.Scanner;


public class Game {

    private Scanner input = new Scanner(System.in);



    public void start(){
        System.out.println("Macera Oyununa Hosgeldiniz...");
        System.out.println("Lutfen Karakter Ismini Giriniz: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayin " +player.getName()+ " Bu Korku Dolu Adaya Hosgeldin. Olumden Kacabilecek Misin?");
        System.out.println("Lutfen Oynamak Istedigin Karakteri Sec: ");
        player.selectChar();

        while (true){
            if(player.isWin(player)){
                System.out.println("************************");
                System.out.println("Tebrikler Oyunu Kazandin");
                System.out.println("************************");
                break;
            }

            player.printInfo();
            System.out.println();
            System.out.println("********Bolgeler******** : ");
            System.out.println("1- Guvenli Ev ---> Burada Dusman Yoktur!");
            System.out.println("2- Dukkan ---> Burada Silah ve Zirh Alabilirsin!");
            System.out.println("3- Magara ---> Odul <Yemek> , dikkatli Ol Zombi Cıkabilir.");
            System.out.println("4- Orman ---> Odul <Odun> , dikkatli Ol Vampir Cikabilir.");
            System.out.println("5- Nehir ---> Odul <Su> , dikkatli ol Ayi Cikabilir.");
            System.out.println("6- Maden ---> Odul <Supriz> , dikkatli ol Yilan Cikabilir.");
            System.out.println("0- Cikis Yap");
            System.out.println("Lutfen Gitmek Istediginiz Bolgeyi Seciniz...");
            int selectLoc = input.nextInt();

            Location location = null;
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);


                    break;
                case 4:
                    location = new Forest(player);


                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Lutfen Gecerli Bir Deger Giriniz...");;
            }
            if(location == null){
                System.out.println("Cabuk Vazgectin Korkak");
                break;
            }

            if(!location.onLocation()){
                System.out.println("GAME OVER");
                break;
            }


        }


    }
}
