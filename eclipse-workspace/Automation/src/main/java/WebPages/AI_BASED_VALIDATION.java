package WebPages;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AI_BASED_VALIDATION {

    private static final String API_KEY = "AIzaSyB6GOefTcQWux5zWLm413IAUHWb2XnjApA";
    private static final String ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

    public static String classifyDreamName(String dreamName) {
        try {
            String prompt = "Classify this dream name as Good or Bad:\nDream name: \"" + dreamName + "\"\nAnswer:";
            String response = callAPI(prompt);
            return parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    private static String callAPI(String prompt) throws IOException {
        JSONObject requestBody = new JSONObject();
        JSONObject part = new JSONObject().put("text", prompt);
        JSONObject content = new JSONObject().put("parts", new JSONArray().put(part));
        requestBody.put("contents", new JSONArray().put(content));

        URL url = new URL(ENDPOINT);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            os.write(requestBody.toString().getBytes("utf-8"));
        }

        if (con.getResponseCode() != 200) {
            throw new IOException("HTTP Error " + con.getResponseCode());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line.trim());
            }
            JSONObject json = new JSONObject(response.toString());
            return json.getJSONArray("candidates")
                       .getJSONObject(0)
                       .getJSONObject("content")
                       .getJSONArray("parts")
                       .getJSONObject(0)
                       .getString("text");
        }
    }

    private static String parseResponse(String response) {
        response = response.toLowerCase();
        if (response.contains("good")) return "Good";
        if (response.contains("bad")) return "Bad";
        return "Unknown";
    }
}
