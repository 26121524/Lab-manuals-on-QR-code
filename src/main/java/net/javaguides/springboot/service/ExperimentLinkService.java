package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.ExperimentLink;

public interface ExperimentLinkService {
    List<ExperimentLink> getAllExperimentLinks();

    ExperimentLink saveExperimentLink(ExperimentLink experimentLink);

    ExperimentLink getExperimentLinkById(Long id);

    ExperimentLink updateExperimentLink(ExperimentLink experimentLink);

    void deleteExperimentLinkById(Long id);
}
