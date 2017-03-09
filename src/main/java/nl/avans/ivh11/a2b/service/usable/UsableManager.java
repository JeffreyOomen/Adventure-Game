package nl.avans.ivh11.a2b.service.usable;

import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
import nl.avans.ivh11.a2b.domain.usable.Equipment;
import nl.avans.ivh11.a2b.domain.usable.EquipmentFactory;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
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
        Usable usable = ef.createUsable(UsableType.EQUIPMENT_BODY , "Adamant Body", "Strong", new Stats());
        Equipment savedEquipment =  (Equipment) usableRepository.save(usable);

        System.out.println("=================================================================");
        System.out.println("====================="+ "NEW USABLE CREATED" + "===================");
        System.out.println("============== NAME: " + savedEquipment.getName() + "==============");
        System.out.println("============== DESCRIPTION: " + savedEquipment.getDescription() + "==============");
        System.out.println("============== TYPE: " + savedEquipment.getType() + "==============");
        System.out.println("=================================================================");

    }
}
