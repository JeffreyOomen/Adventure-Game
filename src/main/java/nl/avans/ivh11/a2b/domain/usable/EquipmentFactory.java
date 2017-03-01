package nl.avans.ivh11.a2b.domain.usable;

import nl.avans.ivh11.a2b.domain.character.CharacterStats;

public class EquipmentFactory extends UsableFactory {

    @Override
    public Usable createUsable(Enum type) {
        Usable usable = null;

        if(type.equals(EquipmentType.BODY)) {
            usable = new Equipment(EquipmentType.BODY, new CharacterStats());
        } else if(type.equals(EquipmentType.HELMET)) {
            usable = new Equipment(EquipmentType.HELMET, new CharacterStats());
        }
        return usable;
    }

}
