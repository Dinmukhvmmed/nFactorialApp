package application.controller;

import application.dto.BigramMapper;
import application.dto.DtoBigram;
import application.model.Bigram;
import application.repository.BigramRepository;
import application.service.CreateBigram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ApiController {
    @Autowired
    private BigramRepository bigramRepository;

    @GetMapping("/start")
    public ResponseEntity startIndexing() {
        HashMap<String, Integer> bigrams = CreateBigram.create();
        for (Map.Entry<String, Integer> entry : bigrams.entrySet()) {
            Bigram bigram = new Bigram();
            bigram.setLetters(entry.getKey());
            bigram.setFrequency(entry.getValue());
            bigramRepository.save(bigram);
        }
        return new ResponseEntity(bigrams, HttpStatus.OK);
    }

    @GetMapping("/data")
    public ResponseEntity showData() {
        List<DtoBigram> bigrams = bigramRepository.findAll()
                .stream().map(BigramMapper::mapper)
                .collect(Collectors.toList());
        return new ResponseEntity(bigrams, HttpStatus.OK);
    }

    @PostMapping("/generatedName")
    public ResponseEntity generate(@RequestParam String letter) {
        Bigram bigram = bigramRepository.findMaxFrequencyByLetter(letter);
        return new ResponseEntity(bigram.getLetters(), HttpStatus.OK);
    }

    @PostMapping("/nextMaxLetter")
    public ResponseEntity nextMaxFrequencyLetter(@RequestParam String letter) {
        List<DtoBigram> bigramList = bigramRepository.findFiveMaxFrequencyByLetter(letter)
                .stream().map(BigramMapper::mapper)
                .collect(Collectors.toList());
        return new ResponseEntity(bigramList, HttpStatus.OK);
    }

    @PostMapping("/nextMinLetter")
    public ResponseEntity nextMinFrequencyLetter(@RequestParam String letter) {
        List<DtoBigram> bigramList = bigramRepository.findFiveMinFrequencyByLetter(letter)
                .stream().map(BigramMapper::mapper)
                .collect(Collectors.toList());
        return new ResponseEntity(HttpStatus.OK);
    }

}
