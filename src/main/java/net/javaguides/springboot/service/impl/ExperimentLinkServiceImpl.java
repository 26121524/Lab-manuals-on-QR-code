package net.javaguides.springboot.service.impl;

import java.util.List;

import net.javaguides.springboot.model.ExperimentLink;
import net.javaguides.springboot.repository.ExperimentLinkRepository;
import net.javaguides.springboot.service.ExperimentLinkService;
import org.springframework.stereotype.Service;

//implementation of all methods declared in ExperimentLinkService
@Service
public class ExperimentLinkServiceImpl implements ExperimentLinkService{

    private ExperimentLinkRepository experimentLinkRepository;

    public ExperimentLinkServiceImpl(ExperimentLinkRepository experimentLinkRepository) {
        super();
        this.experimentLinkRepository = experimentLinkRepository;
    }

    @Override
    public List<ExperimentLink> getAllExperimentLinks() {

        return experimentLinkRepository.findAll();
    }

    @Override
    public ExperimentLink saveExperimentLink(ExperimentLink experimentLink) {
        return experimentLinkRepository.save(experimentLink);
    }

    @Override
    public ExperimentLink getExperimentLinkById(Long exp_id) {
        return experimentLinkRepository.findById(exp_id).get();
    }

    @Override
    public ExperimentLink updateExperimentLink(ExperimentLink experimentLink) {
        return experimentLinkRepository.save(experimentLink);
    }

    @Override
    public void deleteExperimentLinkById(Long exp_id) {
        experimentLinkRepository.deleteById(exp_id);
    }

}
