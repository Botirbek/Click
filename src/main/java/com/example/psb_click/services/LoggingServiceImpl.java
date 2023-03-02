package com.example.psb_click.services;

import com.example.psb_click.entity.Logs;
import com.example.psb_click.util.enums.LogType;
import com.example.psb_click.repository.LogsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        StringBuilder reqMessage = new StringBuilder();
        reqMessage.append("REQUEST ");
        reqMessage.append("method = [").append(request.getMethod()).append("]");
        reqMessage.append(" path = [").append(request.getRequestURI()).append("] ");

        try {
            String reqBody = null;
            if (!Objects.isNull(body)) {
                reqBody=objectMapper.writeValueAsString(body);
                reqMessage.append(" body = ").append(reqBody);
            }
            Logs build = Logs.builder()
                    .body(reqBody)
                    .path(request.getRequestURI())
                    .type(LogType.JoydaRequest)
                    .time(LocalDateTime.now())
                    .build();


            logsRepository.save(build);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.info("log Request: {}", reqMessage);
    }

    @Override
    public void displayResp(HttpServletRequest request, HttpServletResponse response, Object body) {
        StringBuilder respMessage = new StringBuilder();
        respMessage.append("RESPONSE ");
        respMessage.append(" method = [").append(request.getMethod()).append("]");

        try {
            String resBody = objectMapper.writeValueAsString(body);
            respMessage.append(" responseBody = ").append(resBody);

            Logs build = Logs.builder()
                    .body(resBody)
                    .path(request.getRequestURI())
                    .type(LogType.JoydaResponse)
                    .time(LocalDateTime.now())
                    .build();

            logsRepository.save(build);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        logger.info("log Response: {}",respMessage);
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
