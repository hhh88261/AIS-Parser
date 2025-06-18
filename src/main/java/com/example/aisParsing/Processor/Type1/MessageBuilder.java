package com.example.aisParsing.Processor.Type1;

import com.example.aisParsing.Service.ConvertService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MessageBuilder implements ConvertService {

    @Override
    public Map<String, String> messageBuilder(String type1Message){

        Map<String, String> map = new HashMap<>();
        type1Message = type1Message.substring(1, type1Message.length() - 1);
        type1Message = convertPos(type1Message);

        String[] pairs = type1Message.split(",\\s*");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=");

            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim().replaceAll("^\"|\"$", "");
                map.put(key, value);
            }
        }
        return map;
    }

    public String convertPos(String stringType1Message) {

        String parseMessage = stringType1Message;

        Pattern pattern = Pattern.compile("pos=\\((\\d+),(\\d+)\\)\\s*=\\s*\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(stringType1Message);

        if (matcher.find()) {
            String lon = matcher.group(1);
            String lat = matcher.group(2);
            String replacement = "pos=" + lon + "/" + lat;
            parseMessage = matcher.replaceFirst(replacement);
        }

        return parseMessage;
    }
}
