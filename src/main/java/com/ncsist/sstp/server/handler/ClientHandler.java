package com.ncsist.sstp.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
//import org.simulation.scene.MainScene;
//import org.simulation.service.session.SessionUser;
//import org.simulation.util.func.CommonFunction;
//import org.simulation.util.log.LogDataHelper;
//import org.simulation.util.text.CommonString;

public class ClientHandler extends ChannelInboundHandlerAdapter {

	private static ChannelHandlerContext ctx;

	@Override
	public void channelActive( ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive : " + ctx.channel().remoteAddress().toString());
//		LogDataHelper.writeLog("channelActive()");
		//發送訊息到server
//		ctx.writeAndFlush( Unpooled.copiedBuffer(CommonString.INFO_CLIENT_CONNECTED, CharsetUtil.UTF_8));
		ctx.writeAndFlush( Unpooled.copiedBuffer("INFO_CLIENT_CONNECTED", CharsetUtil.UTF_8));
		this.ctx = ctx;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("channelRead : " + ctx.channel().remoteAddress().toString());
//		LogDataHelper.writeLog("channelRead()");
		//接收Server回傳的訊息
		ByteBuf byteBuf = (ByteBuf) msg;
		String message = byteBuf.toString(CharsetUtil.UTF_8);
//		LogDataHelper.writeLog("收到Server " + ctx.channel().remoteAddress() + " 的訊息：" + byteBuf.toString(CharsetUtil.UTF_8));

//		switch ( CommonFunction.getResponseInfo( message ) ){
//			case CommonString.CMD_SESSION_GET:
//				SessionUser.setSession( CommonFunction.getResponseMsg( message ) );
//				LogDataHelper.writeLog( "channelRead() and save " + CommonString.CMD_SESSION_GET, CommonFunction.getResponseMsg( message ) );
//				message = "[SESSION_GET]";
//				break;
//			case CommonString.CMD_SESSION_CLEAR:
//				SessionUser.clearSession();
//				LogDataHelper.writeLog( "channelRead() and clear " + CommonString.CMD_SESSION_CLEAR, CommonFunction.getResponseMsg( message ) );
//				message = "[SESSION_CLEAR]";
//				break;
//			case CommonString.INFO_LOGIN_REMIND:
//			case CommonString.INFO_LOGOUT_REMIND:
//				message = "[INFO] " + CommonFunction.getResponseMsg(message);
//				break;
//			default:
//				break;
//		}
//
//
//		MainScene.appendText(message);

		//ctx.channel().eventLoop().execute( () -> {
		//	try {
		//		ByteBuf byteBuf = (ByteBuf) msg;
		//		System.out.println("收到Server" + ctx.channel().remoteAddress() + "的訊息：" + byteBuf.toString(CharsetUtil.UTF_8));
		//		//长时间操作，不至于长时间的业务操作导致Handler阻塞
		//		Thread.sleep(1000);
		//		System.out.println("长时间的业务处理");
		//	} catch (Exception e) {
		//		e.printStackTrace();
		//	}
		//} );
	}

	@Override
	public void channelInactive( ChannelHandlerContext ctx ) throws Exception {
		System.out.println("channelInactive : " + ctx.channel().remoteAddress().toString());
//		LogDataHelper.writeLog("channelInactive()");
		//super.channelInactive( ctx );
		sendCMD(1, "disconnect");
		ctx.channel().close();

	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelUnregistered : " + ctx.channel().remoteAddress().toString());
		super.channelUnregistered(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("userEventTriggered : " + ctx.channel().remoteAddress().toString());
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("exceptionCaught : " + ctx.channel().remoteAddress().toString());
		super.exceptionCaught(ctx, cause);
	}

	public static void sendMsg(String msg){
		System.out.println("sendMsg : " + ctx.channel().remoteAddress().toString() + " ;;; " + msg);
//		LogDataHelper.writeLog("sendMsg() : " + msg);
		ctx.writeAndFlush( Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));

	}

	public static void sendCMD(int cmdType, String msg){
		System.out.println("sendCMD() cmdType : " + cmdType + "; msg : " + msg);
		String cmdMsg = "";
//		LogDataHelper.writeLog("sendCMD()","cmdType : " + cmdType + "; msg : " + msg);
//		switch(cmdType){
//		case CommonString.CMD_CONNECTED_INT:
//				cmdMsg = CommonFunction.setResponse(CommonString.CMD_CONNECTED, msg);
//				break;
//		case CommonString.CMD_DISCONNECT_INT:
//				cmdMsg = CommonFunction.setResponse(CommonString.CMD_DISCONNECT , msg);
//				break;
//		case CommonString.CMD_LOGIN_INT:
//				cmdMsg = CommonFunction.setResponse(CommonString.CMD_LOGIN, msg);
//				break;
//		case CommonString.CMD_LOGOUT_INT:
//				cmdMsg = CommonFunction.setResponse(CommonString.CMD_LOGOUT, msg);
//				break;
//		}

		ctx.writeAndFlush( Unpooled.copiedBuffer( cmdMsg, CharsetUtil.UTF_8));
	}
}