package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.resume.ResumeDetailDto;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Resume;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.ResumeRepository;
import uz.napa.my_career.repository.SkillRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private SkillRepository skillRepository;

    public ResumeDetailDto getDetail(Long resumeId) {

        Resume resumeEntity = getEntityById(resumeId);
        ResumeDetailDto dto = new ResumeDetailDto();

        dto.setFirstName(resumeEntity.getUser().getFirstname());
        dto.setLastName(resumeEntity.getUser().getLastname());
        dto.setEmail(resumeEntity.getUser().getEmail());
        dto.setPhone(resumeEntity.getUser().getPhone());
        dto.setAddress(resumeEntity.getUser().getAddress());
        dto.setCratedDate(LocalDateTime.now());
        dto.setAboutMe(resumeEntity.getAboutMe());
        dto.setCoverLater(resumeEntity.getCoverLetter());
        dto.setExperienceList(resumeEntity.getExperienceSet());
        dto.setEducationList(resumeEntity.getEducationSet());
        dto.setSkillsList(resumeEntity.getSkills());
        return dto;
    }


    public Resume getEntityById(Long id) {
        Optional<Resume> optional = resumeRepository.findById(id);
        if (optional.isEmpty()){
            throw new ServerBadRequestException("REsume not found");
        }
        return optional.get();
    }

}
