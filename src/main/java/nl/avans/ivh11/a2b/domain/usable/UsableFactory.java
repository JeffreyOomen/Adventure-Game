package nl.avans.ivh11.a2b.domain.usable;

import nl.avans.ivh11.a2b.domain.character.CharacterStats;

public class UsableFactory {

    public  Usable createUsable(String type) {
        Usable usable = null;

        switch(type) {
            case"EQUIPMENT_BODY":
                usable = new Equipment(UsableType.EQUIPMENT_BODY, new CharacterStats());
                break;
        }

        return usable;
    }

}
