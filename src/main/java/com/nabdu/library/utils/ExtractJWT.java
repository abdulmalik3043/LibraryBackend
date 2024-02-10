package com.nabdu.library.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ExtractJWT {

    public static String payloadJWTExtraction(String jwt, String key) {
        try {
            String[] jwtParts = jwt.split("\\.");

            // Decode the payload (second part of the JWT)
            String payload = new String(Base64.getDecoder().decode(jwtParts[1]), StandardCharsets.UTF_8);

            // Extract the value inside the "sub" key
            return extractSubject(payload, key);
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }
     

	private static String extractSubject(String payload, String key) {
        // You may use a JSON parser here to parse the payload as JSON
        // For simplicity, we assume a simple format and use string manipulation
//		System.out.printf("key",key);
        String subKey = "\"" + key + "\":\"";
        int subIndex = payload.indexOf(subKey);

        if (subIndex != -1) {
            int startIndex = subIndex + subKey.length();
            int endIndex = payload.indexOf("\"", startIndex);
            if (endIndex != -1) {
            	System.out.println(payload.substring(startIndex, endIndex));
                return payload.substring(startIndex, endIndex);
            }
        }

        return null; // Subject not found
    }
}
