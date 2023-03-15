package com.example.psb_click.services;

import com.example.psb_click.dto.basic.DataDTO;
import com.example.psb_click.dto.request.CheckPaymentReqDTO;
import com.example.psb_click.dto.request.JoydaRequest;
import com.example.psb_click.dto.request.ServicesReqDTO;
import com.example.psb_click.dto.response.*;
import com.example.psb_click.entity.ServicesEntity;
import com.example.psb_click.entity.Transaction;
import com.example.psb_click.exceptions.CustomException;
import com.example.psb_click.repository.ServicesRepository;
import com.example.psb_click.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JoydaService {

    private final TransactionRepository transactionRepository;
    private final ServicesRepository servicesRepository;
    private final ClickService clickService;

    public ResponseEntity<DataDTO<JResInfoDTO>> getInfo(JoydaRequest<?> joydaRequest) {

        return ResponseEntity.ok(new DataDTO<>());
    }

    public ResponseEntity<DataDTO<JResInfoDTO>> prepay(JoydaRequest<?> joydaRequest) {

        return null;
    }

    public ResponseEntity<DataDTO<JPayResponse>> pay(JoydaRequest<?> joydaRequest) {

        Transaction transaction = Transaction.builder()
                .reqId(joydaRequest.getId())
                .status("1")
                .build();

        transactionRepository.save(transaction);

        return null;
    }

    public ResponseEntity<DataDTO> check(JoydaRequest joydaRequest) {

        Optional<ServicesEntity> byId = servicesRepository.findByService_id(joydaRequest.getService_id());
        if (byId.isPresent()){
            //todo logic for check
            return ResponseEntity.ok(new DataDTO(byId.get()));
        }

        throw new CustomException(
                JErrorRes.builder()
                        .message("Service not found")
                        .traceId(joydaRequest.getTrace_id())
                        .build());

    }

    public ResponseEntity<DataDTO<JPayResponse>> getTransaction(JoydaRequest<String> joydaRequest) {
        CheckPaymentResDTO checkPaymentResDTO = clickService.checkPayment(new CheckPaymentReqDTO(joydaRequest.getParams()),joydaRequest.getTrace_id());
        JPayResponse.JPayResponseBuilder status = JPayResponse.builder()
                .transaction_id(checkPaymentResDTO.getPayment_id())
                .status(String.valueOf(checkPaymentResDTO.getPayment_status()))
                .status(checkPaymentResDTO.getStatus_description());
        return ResponseEntity.ok(new DataDTO(status));
    }

    public void initServices(){
        ServicesReqDTO build = ServicesReqDTO.builder().api_version(1).build();
        List<ServicesResDTO> servicesResDTOS = clickService.sendRequestList(build, new ParameterizedTypeReference<>(){}, "service.list", 127,"-1");

        servicesResDTOS.forEach(
                servicesResDTO -> servicesRepository.save(
                        ServicesEntity
                                .builder()
                                .service_id(servicesResDTO.getId())
                                .name(servicesResDTO.getName())
                                .category_id(servicesResDTO.getCategory_id())
                                .status(servicesResDTO.getStatus())
                                .image(servicesResDTO.getImage())
                                .min_limit(servicesResDTO.getMin_limit())
                                .max_limit(servicesResDTO.getMax_limit())
                                .card_types(Arrays.asList(servicesResDTO.getCard_types()))
                                .active(true)
                                .build()
                )
        );
    }
}


