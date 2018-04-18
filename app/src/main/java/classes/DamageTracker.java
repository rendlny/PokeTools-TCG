package classes;

/**
 * Created by Ren on 21/03/2018.
 */

public class DamageTracker {
    int damage;

    public DamageTracker(){
        damage = 0;
    }

    public DamageTracker(int damage){
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int increase(){
        if(damage<400) {
            damage = damage + 10;
            return damage;
        }else{
            return -1;
        }
    }

    public int decrease(){
        if(damage>=10){
            damage = damage - 10;
            return damage;
        }else{
            return -1;
        }
    }
}
