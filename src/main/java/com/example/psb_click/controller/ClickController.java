package com.example.psb_click.controller;

import com.example.psb_click.dto.basic.DataDTO;
import com.example.psb_click.dto.request.PaymentReqDTO;
import com.example.psb_click.dto.response.BaseRes;
import com.example.psb_click.dto.response.BaseResList;
import com.example.psb_click.dto.response.CategoryResDTO;
import com.example.psb_click.dto.response.PaymentResDTO;
import com.example.psb_click.services.ClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/click")
public class ClickController {

    private final ClickService clickService;

    @GetMapping("/getCategories")
    public ResponseEntity<DataDTO<List<CategoryResDTO>>> getCategories(){
        return clickService.getCategories();
    }

//    @GetMapping("/getSuppliers")
//    public ResponseEntity<DataDTO<BaseRes>> getSuppliers(){
//        return clickService.getSuppliers();
//    }

    @PostMapping("/paymentToken")
    public ResponseEntity<DataDTO<PaymentResDTO>> paymentWithToken(@RequestBody PaymentReqDTO paymentReqDTO){
        return clickService.paymentWithToken(paymentReqDTO);
    }

}
