package com.embarkx.companyms.company.impl;

import com.embarkx.companyms.company.Company;
import com.embarkx.companyms.company.CompanyRepository;
import com.embarkx.companyms.company.CompanyService;
import com.embarkx.companyms.company.clients.ReviewClient;
import com.embarkx.companyms.company.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
   private CompanyRepository companyRepository;
   private ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void createCompany(Company company){
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompany(Company updatedCompany, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id); //A container object which may or may not contain
        // a non-null value. If a value is present, isPresent() returns true. If no value is present, the object is considered empty
        // and isPresent() returns false.
        if(companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

//    @Override
//    public void updateCompanyRating(ReviewMessage reviewMessage) {
//        System.out.println(reviewMessage.getDescription());
////        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow
////                (() -> new NotFoundException("Company Not Found" + reviewMessage.getCompanyId()));
////        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
////        System.out.println(averageRating);
////        company.setRating(averageRating);
////        companyRepository.save(company);
//    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        try {
            // Log the received message
            System.out.println("Received message: " + reviewMessage);

            // Validate if the message contains valid data
            if (reviewMessage == null || reviewMessage.getCompanyId() == null)
            {
                throw new IllegalArgumentException("Invalid reviewMessage: " + reviewMessage);
            }

            // Fetch the company from the database
            Company company = companyRepository.findById(reviewMessage.getCompanyId())
                    .orElseThrow(() -> new NotFoundException("Company Not Found: " + reviewMessage.getCompanyId()));

            // Call the external service to fetch the average rating
            double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
            System.out.println("Average Rating: " + averageRating);

            // Update the company rating and save
            company.setRating(averageRating);
            companyRepository.save(company);

        } catch (NotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            // Log and skip processing if company is not found
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            // Rethrow or handle the exception to avoid message loss
            throw e;
        }
    }

}
