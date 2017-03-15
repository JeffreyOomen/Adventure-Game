package nl.avans.ivh11.a2b.service.character;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.character.EquipmentRepository;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Dwarf;
import nl.avans.ivh11.a2b.domain.character.Elf;
import nl.avans.ivh11.a2b.domain.character.decoration.CharacterDecorator;
import nl.avans.ivh11.a2b.domain.character.decoration.Mage;
import nl.avans.ivh11.a2b.domain.usable.EquipmentFactory;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.usable.Equipment;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Map;

@Controller
@RequestMapping("/")
public class CharacterManager
{
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    public CharacterManager(CharacterRepository characterRepository,
                            EquipmentRepository equipmentRepository) {
        this.characterRepository = characterRepository;
        this.equipmentRepository = equipmentRepository;
        this.example();
    }

    /**
     * Just for JPA Hibernate test purposes
     */
    public void example() {
//
//        Equipment helm1 = equipmentRepository.findOne(1L);
//        Equipment torso1 = equipmentRepository.findOne(2L);
//        Equipment legs1 = equipmentRepository.findOne(3L);
//        Equipment boots1 = equipmentRepository.findOne(4L);
//        Equipment gloves1 = equipmentRepository.findOne(5L);
//        Equipment sword1 = equipmentRepository.findOne(6L);
        EquipmentFactory equipmentFactory = new EquipmentFactory();
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_HELMET, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_BODY, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_LEGS, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_BOOTS, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_GLOVES, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_WEAPON_SWORD, 10));

        // Make a normal character:
        Character ch = new Dwarf("Jeffrey Oomen", new Stats());
        ch.getStats().setStrength(100);
        ch.getStats().setStrengthAccuracy(100);
        ch.setActionBehavior(new NormalAttack());
        characterRepository.save(ch);

        // Make a decorator character with equipment:
//        Character ch2 = new Mage(new Elf("Matthijs Toverboom", new Stats()));
//        ch.mountEquipment(UsableType.EQUIPMENT_HELMET, equipmentRepository.findOne(1L));
//        ch.mountEquipment(UsableType.EQUIPMENT_BODY, equipmentRepository.findOne(2L));
//        ch.mountEquipment(UsableType.EQUIPMENT_LEGS, equipmentRepository.findOne(3L));
//        ch.mountEquipment(UsableType.EQUIPMENT_BOOTS, equipmentRepository.findOne(4L));
//        ch.mountEquipment(UsableType.EQUIPMENT_GLOVES, equipmentRepository.findOne(5L));
//        ch.mountEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, equipmentRepository.findOne(6L));
//        characterRepository.save(ch2);

        // Find the decorator character
//        CharacterDecorator decoratedCharacter = (CharacterDecorator) characterRepository.findOne(3L);
//
//        // print the name:
//        System.out.println("=================================================================");
//        System.out.println("====================="+ "Character's Name" + "===================");
//        System.out.println("==============" + decoratedCharacter.getCharacter().getName() + "==============");
//        System.out.println("=================================================================");
//
//        // print the equipment:
//        System.out.println("=================================================================");
//        System.out.println("====================="+ "Character's Equipment" + "===================");
//        for (Map.Entry<UsableType, Equipment> entry : decoratedCharacter.getEquipment().entrySet()) {
//            System.out.println("======= TYPE: " + entry.getKey() + "======= NAME: " + entry.getValue().getName());
//        }
//        System.out.println("=================================================================");
    }
}
