package com.example.aisParsing.Processor;

import com.example.aisParsing.Service.JsonConvertService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonConverter{

    public String jsonConverter(Map<String, String> calculatedMessage){
        String jsonType1 = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonType1 = objectMapper.writeValueAsString(calculatedMessage);
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonType1;
    }
}
