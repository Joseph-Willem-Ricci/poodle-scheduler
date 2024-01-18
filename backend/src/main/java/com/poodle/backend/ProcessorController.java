package com.poodle.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProcessorController {

    @CrossOrigin(origins = {"https://www.poodlescheduler.com", "http://www.poodlescheduler.com", "https://poodlescheduler.com", "http://poodlescheduler.com", "http://localhost:8081"})
    @PostMapping("/process")
    public ScheduleResponse processFrontEndInput(@RequestBody ScheduleRequest request) {
        Processor processor = new Processor(request.getEmployeeList(), request.getNumberOfDays());
        ScheduleResponse scheduleRsp = processor.run();
        return scheduleRsp;
    }
}
