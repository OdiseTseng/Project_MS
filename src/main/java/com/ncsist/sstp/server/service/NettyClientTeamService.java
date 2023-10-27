package com.ncsist.sstp.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ncsist.sstp.model.MsgDTO;
import com.ncsist.sstp.model.TeamDTO;
import com.ncsist.sstp.utils.func.DTOParser;
import com.ncsist.sstp.utils.text.NettyCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NettyClientTeamService {


    @Getter
    @Setter
    private List<TeamDTO> teamDTOList = new ArrayList<>();


    public MsgDTO treatMsgDTO(int cmd, String from, String msg){
        MsgDTO msgDTO = new MsgDTO();
        switch(cmd){
            case NettyCode.TEAM_WAITING_UPDATE:                   //00
                msgDTO.setCmd(NettyCode.TEAM_WAITING_UPDATE);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_WAIT:                   //01
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_WAIT_TIMEOUT:           //02
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT_TIMEOUT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_JOIN:                   //03
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_JOIN_FAIL:              //04
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_GET_ALL:         //05
//                TeamDTO[] teamDTOS = DTOParser.parseStringToDTOs(msg, TeamDTO[].class);
                TeamDTO[] teamDTOS;
                try {
                    teamDTOS = (TeamDTO[])DTOParser.parseStringToDTO(msg, TeamDTO[].class);

                } catch (JsonProcessingException e) {
                    System.out.println("JsonProcessingException : " + e.getMessage());
                    throw new RuntimeException(e);
                }

                teamDTOList = Arrays.stream(teamDTOS).sorted().toList();
                for(TeamDTO teamDTO : teamDTOList){
                    System.out.println("teamDTO : " + teamDTO);
                }

                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_GET_ALL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_DISPATCH:         //06
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL:    //07
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_NEXT:                   //08
                msgDTO.setCmd(NettyCode.TEAM_WAITING_NEXT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_STEP_WAITING:            //09
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_WAITING);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_STEP_STARTING:           //10
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_STARTING);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_STEP_FINISH:             //11
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_FINISH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_ALL_FINISH:              //12
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FINISH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_ALL_FAIL:                //13
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_DEL_TEAM:                //14
                msgDTO.setCmd(NettyCode.TEAM_COURSE_DEL_TEAM);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;
        }

        return msgDTO;
    }
}
