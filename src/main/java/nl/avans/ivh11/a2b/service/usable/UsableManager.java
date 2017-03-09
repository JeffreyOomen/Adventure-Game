package nl.avans.ivh11.a2b.service.usable;

import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
     */
    public void runDemo() {
        EquipmentFactory ef = new EquipmentFactory();
//        Usable usable = ef.createUsable(UsableType.EQUIPMENT_BODY , "Adamant Body", "Strong", new Stats());
//        Equipment savedEquipment =  (Equipment) usableRepository.save(usable);

        Usable testEquipment = (Equipment) ef.createUsable(UsableType.EQUIPMENT_BODY, 10);


//        PotionFactory pf = new PotionFactory();
//        Usable healPotion = pf.createUsable(UsableType.POTION_HEAL, "Heal berry", "Tasteful");
//        usableRepository.save(healPotion);

        System.out.println("=================================================================");
        System.out.println("====================="+ "NEW USABLE CREATED" + "===================");
        System.out.println("============== NAME: " + testEquipment.getName() + "==============");
        System.out.println("============== DESCRIPTION: " + testEquipment.getDescription() + "==============");
        System.out.println("============== TYPE: " + testEquipment.getType() + "==============");
        System.out.println("=================================================================");


    }
}
