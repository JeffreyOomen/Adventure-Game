package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.character.EquipmentRepository;
import nl.avans.ivh11.a2b.datastorage.usable.MediaRepository;
import nl.avans.ivh11.a2b.domain.util.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Service("mediaService")
@Repository
public class MediaServiceImpl implements MediaService {

    private MediaRepository mediaRepository;

    /**
     * MediaServiceImpl Constructor
     * @param mediaRepository
     */
    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;

        // Start demo
        persistMediaItems();
    }

    @Transactional
    public void persistMediaItems() {
        ArrayList<Media> mediaList = new ArrayList<>();

        mediaList.add(new Media("rpg-bandit.png"));
        mediaList.add(new Media("rpg-bat.png"));
        mediaList.add(new Media("rpg-dragon.png"));
        mediaList.add(new Media("rpg-dwarf.png"));
        mediaList.add(new Media("rpg-elf.png"));
        mediaList.add(new Media("rpg-ghost.png"));
        mediaList.add(new Media("rpg-giant.png"));
        mediaList.add(new Media("rpg-goblin.png"));
        mediaList.add(new Media("rpg-mummy.png"));
        mediaList.add(new Media("rpg-potion.png"));
        mediaList.add(new Media("rpg-spider.png"));
        mediaList.add(new Media("rpg-sword.png"));
        mediaList.add(new Media("rpg-troll.png"));
        mediaList.add(new Media("rpg-warrior.png"));
        mediaList.add(new Media("rpg-wolf.png"));

        for(Media m : mediaList) {
            mediaRepository.save(m);
        }
    }

    @Transactional
    @Override
    public Media findById(long id) {
        return mediaRepository.findOne(id);
    }

    @Transactional
    @Override
    public Media save(Media media) {
        return null;
    }
}
