package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.usable.MediaRepository;
import nl.avans.ivh11.a2b.domain.util.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mediaService")
@Repository
@Transactional
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    /**
     * MediaServiceImpl Constructor
     * @param mediaRepository
     */
    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public Media findById(Long id) {
        return mediaRepository.findOne(id);
    }

    @Override
    public Media findByName(String name) {
        return mediaRepository.findByImageName(name);
    }

    @Override
    public Media save(Media media) {

//        if(mediaRepository.findByImageName(media.getImageName()) != null && media.getId() != null) {
//            return media;
//        }
        return mediaRepository.save(media);
    }
}
