package nl.avans.ivh11.a2b.domain.usable;

public class PotionFactory extends UsableFactory {

    @Override
    public Usable createUsable(Enum type) {
        Usable usable = null;

        if(type.equals(PotionType.HEAL)) {
            usable = new HealPotion();
        } else if(type.equals(PotionType.OVERLOAD)) {
            usable = new OverloadPotion();
        }
        return usable;
    }

}
