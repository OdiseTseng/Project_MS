package com.ncsist.sstp.server.controller;

import com.ncsist.sstp.model.MsgDTO;
import com.ncsist.sstp.model.NettyDTO;
import com.ncsist.sstp.server.service.NettyClientCommonService;
import com.ncsist.sstp.server.service.NettyClientTeamService;
import com.ncsist.sstp.utils.text.NettyCode;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import lombok.Setter;

public class NettyClientMsgController {

    private static NettyClientMsgController controller;


    @Getter
    @Setter
    private static String clientCtxId = "";

    @Getter
    @Setter
    private static NettyDTO nettyDTO;

    public ChannelHandlerContext ctx;
    public NettyClientCommonService nettyClientCommonService;
    public NettyClientTeamService nettyClientTeamService;

    private NettyClientMsgController(){
        nettyClientCommonService = new NettyClientCommonService();
        nettyClientTeamService = new NettyClientTeamService();
    }

    public static NettyClientMsgController getInstance(){
        if(controller == null){
            controller = new NettyClientMsgController();
        }
        return controller;
    }

    public void setCtx(ChannelHandlerContext sCtx){
        ctx = sCtx;
    }

    public MsgDTO parseToMsgDTO(String msg){

        return nettyClientCommonService.toMsgDTO(msg);
    }

    public MsgDTO parseMsgDTO(int cmdCode,
                                 String msg,
                                 String name,
                                 long level,
                                 int team){

        return nettyClientCommonService.createMsgDTO(cmdCode, msg, name, level, team);
    }



    public void sendMsg(String msg){
        System.out.println("sendMsg : " + msg);

        nettyClientCommonService.createMsgDTO(NettyCode.CMD_NORMAL_MSG, msg, clientCtxId, 0L, 0);
        ctx.writeAndFlush( Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    public void sendCMD(int cmdCode){
        System.out.println("sendCMD() cmdCode : " + cmdCode);
        MsgDTO msgDTO = nettyClientCommonService.createMsgDTO(cmdCode);
        String cmdMsg = nettyClientCommonService.parseDTOToString(msgDTO);
        System.out.println("sendCMD() cmdMsg : " + cmdMsg);

        ctx.writeAndFlush( Unpooled.copiedBuffer( cmdMsg, CharsetUtil.UTF_8));
    }

    public void treatMsg(String sourceMsg){
        System.out.println("treatMsg : " + sourceMsg);
        MsgDTO msgDTO = nettyClientCommonService.toMsgDTO(sourceMsg);

        int cmd = msgDTO.getCmd();
        int cmdType = cmd / 10000;
        String msg = msgDTO.getMsg();
        String from = msgDTO.getFrom();
        long level = msgDTO.getLevel();
        int team = msgDTO.getTeam();
        System.out.println("cmd : "  + cmd);
        System.out.println("cmdType : "  + cmdType);
        System.out.println("msg : "  + msg);
        System.out.println("from : "  + from);
        System.out.println("level : "  + level);
        System.out.println("team : "  + team);


        switch (cmdType) {
//            case 89 -> {
//                if(cmd == NettyCode.CMD_NORMAL_MSG){
//                    //顯示訊息
//                }
//            }

            case NettyCode.CMD_NORMAL,NettyCode.CMD -> {
                if(cmd == NettyCode.CMD_CONNECT){
                    setClientCtxId(msg);
                    System.out.println("check set clientCtxId : " + getClientCtxId());
                }else{
                    nettyClientCommonService.treatMsgDTO(cmd, from, msg);
                }

//                if(cmd == NettyCode.CMD_LOGIN){
//                    if(clientCtxId.equals(msg)){
//                        nettyDTO = nettyClientCommonService.toNettyDTO(msg);
//                    }else{
//                        //提示別的使用者登入
//                    }
//                }

            }

            case NettyCode.TEAM -> {
                nettyClientTeamService.treatMsgDTO(cmd, from, msg);
            }
        }
    }
}
