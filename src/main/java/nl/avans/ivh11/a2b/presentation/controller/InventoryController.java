package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.usable.Equipment;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.presentation.model.BattleModel;
import nl.avans.ivh11.a2b.service.CharacterService;
import nl.avans.ivh11.a2b.service.OpponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class InventoryController {

    private CharacterService characterService;
    private CharacterRepository characterRepository;
    private Character character;

    @Autowired
    public InventoryController(CharacterService characterService, CharacterRepository characterRepository) {
        this.characterService = characterService;
        this.characterRepository = characterRepository;

        // TODO temp  character setted
        this.character = characterRepository.findOne(1L);

    }

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public String inventory(Model uiModel) {

        uiModel.addAttribute("helmet",  character.getEquipment().get(UsableType.EQUIPMENT_HELMET));
        uiModel.addAttribute("weapon",  character.getEquipment().get(UsableType.EQUIPMENT_WEAPON));
        uiModel.addAttribute("body",  character.getEquipment().get(UsableType.EQUIPMENT_BODY));
        uiModel.addAttribute("legs",   character.getEquipment().get(UsableType.EQUIPMENT_LEGS));
        uiModel.addAttribute("gloves",   character.getEquipment().get(UsableType.EQUIPMENT_GLOVES));
        uiModel.addAttribute("boots",   character.getEquipment().get(UsableType.EQUIPMENT_BOOTS));

        uiModel.addAttribute("inventoryUsables", character.getInventory().getUsables());

        return "inventory";
    }

    /**
     * Executes a battle event with a special attack for the Character
     */
    @RequestMapping(value = "/inventory", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@RequestBody  String usableId) {
        long id = Long.parseLong(usableId);

        if(id > 0) {
            // Drop item
            this.characterService.dropInventoryItem(character, id);
        }
    }

    /**
     * Executes a battle event with a special attack for the Character
     */
    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    @ResponseBody
    public void useItem(@RequestBody  String usableId) {

        long id = Long.parseLong(usableId);

        // Validate usable found based on given id
        if(id > 0){
            // Use item
            this.characterService.useInventoryItem(character, id);
        }

    }
}
