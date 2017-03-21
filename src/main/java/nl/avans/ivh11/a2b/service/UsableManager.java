package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
/**
 * UsableManager (service)
 * is used for test purposes only at this stage.
 */
public class UsableManager {

    @Autowired
    private UsableRepository usableRepository;

    public UsableManager(UsableRepository usableRepository) {
        this.usableRepository = usableRepository;
        runDemo();
    }

    /**
     * Test
     * Test equipment items can be created and assign to equipment list. increase given accuracies.
     */
    public void runDemo() {
        EquipmentFactory ef = new EquipmentFactory();
//        Usable usable = ef.createUsable(UsableType.EQUIPMENT_BODY , "Adamant Body", "Strong", new Stats());
//        Equipment savedEquipment =  (Equipment) usableRepository.save(usable);

        List<Equipment> equipmentList = new ArrayList<>();

        equipmentList.add( (Equipment) ef.createUsable(UsableType.EQUIPMENT_BODY, 10));
        equipmentList.add( (Equipment) ef.createUsable(UsableType.EQUIPMENT_BOOTS, 10));
        equipmentList.add( (Equipment) ef.createUsable(UsableType.EQUIPMENT_LEGS, 10));

        int totalStrengthAccurancy = 0;
        int totalDefAccurancy = 0;
        int totalMagicAccurancy = 0;
        int totalArcheryAccurancy = 0;

        for(Equipment e : equipmentList) {
            totalStrengthAccurancy += e.getStrengthAccuracy();
            totalDefAccurancy += e.getDefenseAccuracy();
            totalMagicAccurancy += e.getMagicAccuracy();
            totalArcheryAccurancy += e.getArcheryAccuracy();
        }


        PotionFactory pf = new PotionFactory();
        Usable healPotion = pf.createUsable(UsableType.POTION_HEAL, 10);
        usableRepository.save(healPotion);

        System.out.println("=================================================================");
        System.out.println("====================="+ "NEW USABLE CREATED" + "===================");
        System.out.println("=================================================================");


    }
}
