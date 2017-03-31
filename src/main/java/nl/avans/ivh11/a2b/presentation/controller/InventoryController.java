package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.service.CharacterService;
import nl.avans.ivh11.a2b.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("isAuthenticated()")
public class InventoryController {

    private CharacterService characterService;

    @Autowired
    public InventoryController(CharacterService characterService, SecurityService securityService) {
        this.characterService = characterService;
        this.securityService = securityService;

    }
    private SecurityService securityService;

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public String inventory(Model uiModel) {
        User user = this.securityService.findLoggedInUser();
        Character character = user.getCharacter();

        uiModel.addAttribute("helmet", character.getEquipment().get(UsableType.EQUIPMENT_HELMET));
        uiModel.addAttribute("weapon", character.getEquipment().get(UsableType.EQUIPMENT_WEAPON));
        uiModel.addAttribute("body", character.getEquipment().get(UsableType.EQUIPMENT_BODY));
        uiModel.addAttribute("legs", character.getEquipment().get(UsableType.EQUIPMENT_LEGS));
        uiModel.addAttribute("gloves", character.getEquipment().get(UsableType.EQUIPMENT_GLOVES));
        uiModel.addAttribute("boots", character.getEquipment().get(UsableType.EQUIPMENT_BOOTS));


        Inventory inv  = character.getInventory();



        // Validate inventory isn't empty
        if(character.getInventory() != null) {
            uiModel.addAttribute("inventoryUsables", character.getInventory().getUsables().values());
        }

        return "inventory";
    }

    /**
     * Executes a battle event with a special attack for the Character
     */
    @RequestMapping(value = "/inventory", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@RequestBody String usableId) {
        long id = Long.parseLong(usableId);

        User user = this.securityService.findLoggedInUser();
        Character character = user.getCharacter();

        if (id > 0) {
            // Drop item
            this.characterService.dropInventoryItem(character, id);
        }
    }

    /**
     * Executes a battle event with a special attack for the Character
     */
    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    @ResponseBody
    public void useItem(@RequestBody String usableId) {

        long id = Long.parseLong(usableId);

        User user = this.securityService.findLoggedInUser();
        Character character = user.getCharacter();

        // Validate usable found based on given id
        if (id > 0) {
            // Use item
            this.characterService.useInventoryItem(character, id);
        }

    }
}
