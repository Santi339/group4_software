package com.example.audiobook_generator.pdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/pdf")
public class PdfController {
    // [Douglas] This basically creates a PDF class and returns the input
    // as a JSON object
    @GetMapping
    public List<Pdf> test(){
        return List.of(new Pdf("test.pdf","the contents of the pdf would be here"));
    }
}
