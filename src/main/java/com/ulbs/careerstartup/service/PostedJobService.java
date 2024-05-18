package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.PostedJobDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.PostedJobRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.NOT_FOUND;

@Service
@AllArgsConstructor
public class PostedJobService {

    private PostedJobRepository postedJobRepository;

    private Mapper mapper;

    public Collection<PostedJobDTO> findAllPostedJobs() {
        return postedJobRepository.findAll().stream().map(mapper::postedJobToPostedJobDTO).toList();
    }

    public PostedJobDTO findPostedJobById(UUID id) {
        return postedJobRepository.findById(id).map(mapper::postedJobToPostedJobDTO).orElseThrow(() -> new EntityNotFoundException("PostedJob with id " + id + NOT_FOUND));
    }

    public Collection<PostedJobDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return postedJobRepository.findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10)).map(mapper::postedJobToPostedJobDTO).toList();
    }

    public PostedJobDTO savePostedJob(PostedJobDTO postedJobDTO) {
        return mapper.postedJobToPostedJobDTO(postedJobRepository.save(mapper.postedJobDTOToPostedJob(postedJobDTO)));
    }

    public PostedJobDTO updatePostedJob(PostedJobDTO postedJobDTO) {
        if (postedJobRepository.existsById(postedJobDTO.getId()))
            return mapper.postedJobToPostedJobDTO(postedJobRepository.save(mapper.postedJobDTOToPostedJob(postedJobDTO)));
        else throw new EntityNotFoundException("PostedJob with id " + postedJobDTO.getId() + NOT_FOUND);
    }

    @Transactional
    public void deletePostedJob(PostedJobDTO postedJobDTO) {
        if (postedJobRepository.existsById(postedJobDTO.getId())) {
            postedJobRepository.deleteById(postedJobDTO.getId());
        } else {
            throw new EntityNotFoundException("PostedJob with id " + postedJobDTO.getId() + NOT_FOUND);
        }
    }
}
