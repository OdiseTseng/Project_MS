package com.ncsist.sstp.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsist.sstp.model.MsgDTO;
import com.ncsist.sstp.model.NettyDTO;
import com.ncsist.sstp.utils.text.NettyCode;

public class NettyClientCommonService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MsgDTO toMsgDTO(String msg){
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

    public NettyDTO toNettyDTO(String msg){
        NettyDTO nettyDTO;
        try {
            nettyDTO = objectMapper.readValue(msg, NettyDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return nettyDTO;
  }

    public MsgDTO treatMsgDTO(int cmd, String from, String msg){
        System.out.println("treatMsgDTO : cmd= " + cmd + " from= " + from + " msg= " + msg);

        MsgDTO msgDTO = new MsgDTO();

        switch (cmd) {
//            case NettyCode.CMD_CONNECT -> {
//
//            }

//            case NettyCode.CMD_DISCONNECT -> {
//
//            }

//            case NettyCode.CMD_LOGIN -> {
//
//            }
//
//            case NettyCode.CMD_LOGOUT -> {
//
//            }

            case NettyCode.CMD_NORMAL_OTHER_MSG -> {

            }

            case NettyCode.CMD_OTHER_CONNECT -> {

            }

            case NettyCode.CMD_OTHER_DISCONNECT -> {

            }

            case NettyCode.CMD_OTHER_LOGIN -> {
                //show from
            }

            case NettyCode.CMD_OTHER_LOGOUT -> {
                //show from
            }

        }

        return msgDTO;
    }


}