package nl.avans.ivh11.a2b.domain.usable;

/**
 * Created by Bram on 1-3-2017.
 */
public class UsableFactoryClient  extends UsableFactory {

    private Usable usable;
    private UsableFactory usableFactory;

    public UsableFactoryClient() {
        usable = null;
        usableFactory = new PotionFactory();
    }

    @Override
    public Usable createUsable(Enum type) {
        if(type.equals(PotionType.HEAL)) {
            usable = usableFactory.createUsable(PotionType.HEAL);
        } else if(type.equals(PotionType.OVERLOAD)) {
            usable = usableFactory.createUsable(PotionType.OVERLOAD);
        }
        return usable;
    }
}
