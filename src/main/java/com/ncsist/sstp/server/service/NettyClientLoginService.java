package com.ncsist.sstp.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsist.sstp.model.MsgDTO;
import com.ncsist.sstp.model.NettyDTO;

public class NettyClientLoginService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MsgDTO getClientMsg(String msg){
        MsgDTO msgDTO;
        try {
            msgDTO = objectMapper.readValue(msg, MsgDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return msgDTO;
    }

    public MsgDTO createMsgDTO(int cmdCode, String msg, String name, long level, int team){
        MsgDTO msgDTO = new MsgDTO();
        msgDTO.setCmd(cmdCode);
        msgDTO.setMsg(msg);
        msgDTO.setFrom(name);
        msgDTO.setLevel(level);
        msgDTO.setTeam(team);

        return msgDTO;
    }

    public NettyDTO msgToNettyDTO(String msg){
        NettyDTO nettyDTO;
        try {
            nettyDTO = objectMapper.readValue(msg, NettyDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return nettyDTO;
  }


}
