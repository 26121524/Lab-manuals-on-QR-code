package net.javaguides.springboot.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

//entity to create table of experiment_link to fetch experiment information
@Entity
@Table(name = "experiment_links")
public class ExperimentLink {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id", nullable = false)
    private Long exp_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="subject",nullable = false)
    private String subject;

    @Column(name="experiment",nullable = false)
    private String experiment;

    @Column(name="link",nullable = false)
    private String link;

    public ExperimentLink() {
    }

    public Long getExp_id() {
        return exp_id;
    }

    public void setExp_id(Long exp_id) {
        this.exp_id = exp_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExperiment() {
        return experiment;
    }

    public void setExperiment(String experiment) {
        this.experiment = experiment;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    // Constructors, getters, and setters
}
