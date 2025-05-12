package com.toiletgo.ToiletGo.api;

import com.toiletgo.ToiletGo.entity.Toilet;
import com.toiletgo.ToiletGo.repository.ToiletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToiletController {
    @Autowired
    ToiletRepository toiletRepository;
    @GetMapping("/api/toilets")
    public ResponseEntity<List<Toilet>> getToilet(){
        List<Toilet> toilets = toiletRepository.findAll();
        if (toilets.size()!= 0){
            return ResponseEntity.status(HttpStatus.OK).body(toilets);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    }
}
