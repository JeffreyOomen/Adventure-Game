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
import nl.avans.ivh11.a2b.domain.util.Equipment;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
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
        Equipment helmet = new Equipment("Bronze helm", EquipmentEnum.HELMET);
        Equipment torso = new Equipment("Bronze torso", EquipmentEnum.TORSO);
        Equipment legs = new Equipment("Silver legs", EquipmentEnum.LEGS);
        Equipment boots = new Equipment("Silver boots", EquipmentEnum.BOOTS);
        Equipment gloves = new Equipment("Bronze gloves", EquipmentEnum.GLOVES);
        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);
        equipmentRepository.save(helmet);
        equipmentRepository.save(torso);
        equipmentRepository.save(legs);
        equipmentRepository.save(boots);
        equipmentRepository.save(gloves);
        equipmentRepository.save(sword);

        Equipment helm1 = equipmentRepository.findOne(1L);
        Equipment torso1 = equipmentRepository.findOne(2L);
        Equipment legs1 = equipmentRepository.findOne(3L);
        Equipment boots1 = equipmentRepository.findOne(4L);
        Equipment gloves1 = equipmentRepository.findOne(5L);
        Equipment sword1 = equipmentRepository.findOne(6L);

        // Make a normal character:
        Character ch = new Dwarf("Jeffrey Oomen", new Stats());
        ch.setActionBehavior(new NormalAttack());
        characterRepository.save(ch);

        // Make a decorator character with equipment:
        Character ch2 = new Mage(new Elf("Matthijs Toverboom", new Stats()));
        ch2.mountEquipment(EquipmentEnum.HELMET, helm1);
        ch2.mountEquipment(EquipmentEnum.TORSO, torso1);
        ch2.mountEquipment(EquipmentEnum.LEGS, legs1);
        ch2.mountEquipment(EquipmentEnum.BOOTS, boots1);
        ch2.mountEquipment(EquipmentEnum.GLOVES, gloves1);
        ch2.mountEquipment(EquipmentEnum.SWORD, sword1);
        characterRepository.save(ch2);

        // Find the decorator character
        CharacterDecorator decoratedCharacter = (CharacterDecorator) characterRepository.findOne(3L);

        // print the name:
        System.out.println("=================================================================");
        System.out.println("====================="+ "Character's Name" + "===================");
        System.out.println("==============" + decoratedCharacter.getCharacter().getName() + "==============");
        System.out.println("=================================================================");

        // print the equipment:
        System.out.println("=================================================================");
        System.out.println("====================="+ "Character's Equipment" + "===================");
        for (Map.Entry<EquipmentEnum, Equipment> entry : decoratedCharacter.getEquipment().entrySet()) {
            System.out.println("======= TYPE: " + entry.getKey() + "======= NAME: " + entry.getValue().getName());
        }
        System.out.println("=================================================================");
    }
}
