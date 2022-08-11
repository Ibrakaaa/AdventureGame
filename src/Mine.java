
import java.util.Random;

public class Mine extends BattleLoc{
    static Random random = new Random();


    public Mine(Player player) {

        super(player,"Maden",new Snake(random.nextInt((4)+4)),"",5);
    }

}
