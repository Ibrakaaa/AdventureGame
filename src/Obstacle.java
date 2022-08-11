public class Obstacle {
    private int id;
    private int damage;
    private int health;
    private String name;
    private int award;
    private int orijinalHealth;



    public Obstacle(int id,String name, int damage, int health,int award) {
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.name = name;
        this.award = award;
        this.orijinalHealth = health;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public int getOrijinalHealth() {
        return orijinalHealth;
    }

    public void setOrijinalHealth(int orijinalHealth) {
        this.orijinalHealth = orijinalHealth;
    }
}
