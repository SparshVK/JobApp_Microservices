package com.embarkx.reviewms.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId); // we don't need to define any implementation for this because its an interface
    //Just by declaring it here(in Review Repository interface) we are telling Jpa that it needs to generate an implementation of this, on runtime.
    // And with this declaration SpringDataJPA automatically generates implementation for this method at runtime,

}
