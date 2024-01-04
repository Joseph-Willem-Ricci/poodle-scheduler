package com.poodle.backend;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProcessorController {

    @PostMapping("/process")
    public String processInput(@RequestBody String input) {
        // Processor processor = new Processor(input);  //TODO: parse input into expected params
        // return processor.run();
        return "Hello World!";
    }
}
