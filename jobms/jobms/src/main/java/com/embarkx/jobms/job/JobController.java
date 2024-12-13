package com.embarkx.jobms.job;

import com.embarkx.jobms.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs") // when request mapping is applied at class level, it sets a "baseurl path" for all the methods that are handling request in that particular Controller(class).
public class JobController {
    private JobService jobService;

    // Loose Coupling
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
      return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobDTO = jobService.getJobById(id);
        if(jobDTO != null)
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
    boolean deleted = jobService.deleteJobById(id);
    if(deleted)
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    return new ResponseEntity<>("Job doesn't exists!!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
//    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
            boolean updated = jobService.updateJob(id, updatedJob);
            if(updated)
                return  new ResponseEntity<>("Job Updated successfully", HttpStatus.OK);
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }

}

//Endpoints
// GET /jobs: get all jobs
// GET /jobs/{id}: get a specific job by id
// POST /jobs: Create a new job(request body should contain the job details)
// DELETE /jobs/{id}: delete a specific job by id
// PUT /jobs/{id}: update a specific job by id(request body should contain the updated job details)
// GET /jobs/{id}/company: get the company, associated with the specific job[by id]
//


// Example API urls:
// GET {base_url}/jobs
// GET {base_url}/jobs/1
// POST {base_url}/jobs
// DELETE {base_url}/jobs/1
// PUT {base_url}/jobs/1
// GET {base_url}/jobs/1/company
