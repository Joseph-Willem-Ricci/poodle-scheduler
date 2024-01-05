package com.poodle.backend;

import com.poodle.utils.Schedule;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProcessorController {

    @PostMapping("/process")
    public Schedule processFrontEndInput(@RequestBody ScheduleRequest request) {
        Processor processor = new Processor(request.getEmployeeList(), request.getNumberOfDays());
        Schedule schedule = processor.run();
        return schedule;
    }
}
