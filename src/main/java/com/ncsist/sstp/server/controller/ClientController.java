package com.ncsist.sstp.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsist.sstp.vo.MsgDTO;

public class ClientController {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static MsgDTO getClientMsg(String msg){
        MsgDTO msgDTO;
        try {
            msgDTO = objectMapper.readValue(msg, MsgDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return msgDTO;
    }
}
