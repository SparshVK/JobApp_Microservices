package com.embarkx.jobms.job;

import org.springframework.data.jpa.repository.JpaRepository;
                                    //JpaRepository<Entity type, type of Primary key>
public interface JobRepository extends JpaRepository<Job, Long> { // JobRepository interface is what we call a SpringDataJPA
    // repository. It's not a concrete class that we can instantiate with the new keyword instead the SpringDataJPA will create an
    // implementation of this interface for us to use.
}
