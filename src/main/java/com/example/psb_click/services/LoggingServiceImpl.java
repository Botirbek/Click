package com.example.psb_click.services;

import com.example.psb_click.dto.request.JoydaRequest;
import com.example.psb_click.dto.response.JPayResponse;
import com.example.psb_click.entity.Logs;
import com.example.psb_click.enums.LogType;
import com.example.psb_click.exceptions.CustomException;
import com.example.psb_click.repository.LogsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoggingServiceImpl implements  LoggingService{

    private final ObjectMapper objectMapper;
    private final LogsRepository logsRepository;

    Logger logger = LoggerFactory.getLogger("LoggingServiceImpl");

    @Override
    public void displayReq(HttpServletRequest request, Object body) {
        if (request.getRequestURI().startsWith("/v3/api-docs")) return;

//        StringBuilder reqMessage = new StringBuilder();
//        reqMessage.append("REQUEST ");
//        reqMessage.append("method = [").append(request.getMethod()).append("]");
//        reqMessage.append(" path = [").append(request.getRequestURI()).append("] ");

        try {
            String reqBody = null;
            String traceId = null;

            if (!Objects.isNull(body)) {
                reqBody=objectMapper.writeValueAsString(body);
      //        reqMessage.append(" body = ").append(reqBody);
                ObjectNode node = objectMapper.readValue(reqBody, ObjectNode.class);
                if (node.has("trace_id")) {
                    traceId = node.get("trace_id").asText();
                }
            }

            Logs build = Logs.builder()
                    .body(reqBody)
                    .path(request.getRequestURI())
                    .traceId(traceId)
                    .type(LogType.JoydaRequest)
                    .time(LocalDateTime.now())
                    .build();

            logsRepository.save(build);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//        logger.info("log Request: {}", reqMessage);
    }

    @Override
    public void displayResp(HttpServletRequest request, HttpServletResponse response, Object body) {
        if (request.getRequestURI().startsWith("/v3/api-docs")) return;

//        StringBuilder respMessage = new StringBuilder();
//        respMessage.append("RESPONSE ");
//        respMessage.append(" method = [").append(request.getMethod()).append("]");

        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        try {
            String resBody = objectMapper.writeValueAsString(body);
//            respMessage.append(" responseBody = ").append(resBody);
             String traceId = null;

             ObjectNode node = objectMapper.readValue(resBody, ObjectNode.class);
             if (node.has("trace_id")) {
                 traceId = node.get("trace_id").asText();
             }

             Logs build = Logs.builder()
                    .body(resBody)
                    .traceId(traceId)
                    .path(request.getRequestURI())
                    .type(LogType.JoydaResponse)
                    .time(LocalDateTime.now())
                    .build();

            logsRepository.save(build);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        logger.info("log Response: {}",respMessage);
    }

//    private Map<String,String> getHeaders(HttpServletResponse response) {
//        Map<String,String> headers = new HashMap<>();
//        Collection<String> headerMap = response.getHeaderNames();
//        for(String str : headerMap) {
//            headers.put(str,response.getHeader(str));
//        }
//        return headers;
//    }
//
//    private Map<String,String> getParameters(HttpServletRequest request) {
//        Map<String,String> parameters = new HashMap<>();
//        Enumeration<String> params = request.getParameterNames();
//        while(params.hasMoreElements()) {
//            String paramName = params.nextElement();
//            String paramValue = request.getParameter(paramName);
//            parameters.put(paramName,paramValue);
//        }
//        return parameters;
//    }

}
