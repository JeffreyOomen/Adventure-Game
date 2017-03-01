package nl.avans.ivh11.a2b.domain.usable;

public class UsableDemo {
    public static void main(String[] args) {

        UsableFactory potionFactory = new PotionFactory();
        Usable potion1 = potionFactory.createUsable(PotionType.OVERLOAD);
        System.out.println(potion1.name);

        UsableFactory equipmentFactory = new EquipmentFactory();
        Equipment equipmentBody = (Equipment) equipmentFactory.createUsable(EquipmentType.BODY);
        System.out.println(equipmentBody.getType());

    }
}
