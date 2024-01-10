package com.poodle.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProcessorController {

    @CrossOrigin(origins = "https://www.poodlescheduler.com")  // TODO: Update this to the frontend URL
    @PostMapping("/process")
    public String processFrontEndInput(@RequestBody ScheduleRequest request) {
        Processor processor = new Processor(request.getEmployeeList(), request.getNumberOfDays());
        String schedule = processor.run();
        return schedule;
    }
}
