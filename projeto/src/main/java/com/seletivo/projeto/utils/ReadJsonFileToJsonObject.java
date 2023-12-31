package com.seletivo.projeto.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class ReadJsonFileToJsonObject {
  
  public JSONObject read() throws IOException {
    String file = "projeto/src/main/resources/open-api/response-api.json";
    String content = new String(Files.readAllBytes(Paths.get(file)));
    return new JSONObject(content);
  }
}