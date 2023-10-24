package com.ncsist.sstp.server.controller;

import com.ncsist.sstp.model.MsgDTO;
import com.ncsist.sstp.model.NettyDTO;
import com.ncsist.sstp.server.service.NettyClientLoginService;
import com.ncsist.sstp.utils.text.NettyCode;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import lombok.Setter;
import org.python.antlr.ast.Str;

public class NettyClientMsgController {


    @Getter
    @Setter
    private static String clientCtxId = "";

    @Getter
    private static NettyDTO nettyDTO = null;

    public ChannelHandlerContext ctx;
    public NettyClientLoginService nettyClientLoginService = new NettyClientLoginService();

    public void setCtx(ChannelHandlerContext sCtx){
        ctx = sCtx;
    }

    public MsgDTO parseToMsgDTO(String msg){

        return nettyClientLoginService.getClientMsg(msg);
    }

    public MsgDTO parseMsgDTO(int cmdCode,
                                 String msg,
                                 String name,
                                 long level,
                                 int team){

        return nettyClientLoginService.createMsgDTO(cmdCode, msg, name, level, team);
    }



    public void sendMsg(String msg){
        System.out.println("sendMsg : " + msg);

        nettyClientLoginService.createMsgDTO(NettyCode.CMD_NORMAL_MSG, msg, clientCtxId, 0L, 0);
        ctx.writeAndFlush( Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    public void sendCMD(String msg){
        System.out.println("sendCMD() msg");
        String cmdMsg = "";

        ctx.writeAndFlush( Unpooled.copiedBuffer( cmdMsg, CharsetUtil.UTF_8));
    }

    public void treatMsg(String msg){
        MsgDTO msgDTO = nettyClientLoginService.getClientMsg(msg);

        String treatMsg = msgDTO.getMsg();
        String from = msgDTO.getFrom();
        Long level = msgDTO.getLevel();
        int team = msgDTO.getTeam();


        int msgCode = msgDTO.getCmd();
        int msgType = msgCode / 1000;

        switch (msgType) {
            case 89 -> {
                if(msgCode == NettyCode.CMD_NORMAL_MSG){

                }
            }
            case 90 -> {
                if(msgCode == NettyCode.CMD_CONNECT){
                    setClientCtxId(treatMsg);
                }
                if(msgCode == NettyCode.CMD_LOGIN){
                    nettyDTO = nettyClientLoginService.msgToNettyDTO(treatMsg);
                }
            }
            case 91 -> {

            }

            case 92 -> {

            }
        }
    }
}
