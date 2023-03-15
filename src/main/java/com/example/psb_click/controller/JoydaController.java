package com.example.psb_click.controller;

import com.example.psb_click.dto.basic.DataDTO;
import com.example.psb_click.dto.request.JoydaRequest;
import com.example.psb_click.dto.response.JPayResponse;
import com.example.psb_click.dto.response.JResInfoDTO;
import com.example.psb_click.services.JoydaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Click service", description = "Service integrated with Click for JOYDA application")
@RequestMapping("/v1/joyda")
public class JoydaController {

    private final JoydaService joydaService;

    @PostMapping("/getInfo")
    public ResponseEntity<DataDTO<JResInfoDTO>> getInfo(@Validated @RequestBody JoydaRequest<?> joydaRequest){
        return joydaService.getInfo(joydaRequest);
    }

    @PostMapping("/prepay")
    public ResponseEntity<DataDTO<JResInfoDTO>> prepay(@Validated @RequestBody JoydaRequest<?> joydaRequest){
        return joydaService.prepay(joydaRequest);
    }

    @PostMapping("/pay")
    public ResponseEntity<DataDTO<JPayResponse>> pay(@Validated @RequestBody JoydaRequest<?> joydaRequest){
        return joydaService.pay(joydaRequest);
    }

    @GetMapping("/check")
    public ResponseEntity<DataDTO> check(@Validated @RequestBody JoydaRequest<Integer> joydaRequest){
        return joydaService.check(joydaRequest);
    }

    @PostMapping("/getTransaction")
    public ResponseEntity<DataDTO<JPayResponse>> getTransaction(@Validated @RequestBody JoydaRequest<String> joydaRequest){
        return joydaService.getTransaction(joydaRequest);
    }

}
