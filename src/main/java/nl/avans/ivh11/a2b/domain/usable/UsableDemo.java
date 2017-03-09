package nl.avans.ivh11.a2b.domain.usable;

import nl.avans.ivh11.a2b.domain.util.Stats;

public class UsableDemo {
    public static void main(String[] args) {
        System.out.println("Hallo");

        EquipmentFactory ef = new EquipmentFactory();
        Usable usable = ef.createUsable(UsableType.EQUIPMENT_BODY , "Adamant Body", "Strong", new Stats());
        System.out.println(usable);

    }
//    public static void main(String[] args) {
//
//        UsableFactory potionFactory = new PotionFactory();
//        Usable potion1 = potionFactory.createUsable(PotionType.OVERLOAD);
//        System.out.println(potion1.name);
//
//        UsableFactory equipmentFactory = new EquipmentFactory();
//        Equipment equipmentBody = (Equipment) equipmentFactory.createUsable(EquipmentType.BODY);
//        System.out.println(equipmentBody.getType());
//
//    }
}
