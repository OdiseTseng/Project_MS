package com.ncsist.sstp.server.handler;

import com.ncsist.sstp.server.controller.NettyClientMsgController;
import com.ncsist.sstp.model.MsgDTO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
//import org.simulation.scene.MainScene;
//import org.simulation.service.session.SessionUser;
//import org.simulation.util.func.CommonFunction;
//import org.simulation.util.log.LogDataHelper;
//import org.simulation.util.text.CommonString;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	NettyClientMsgController nettyClientMsgController = new NettyClientMsgController();

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelRegistered : ");
//		System.out.println("channelRegistered : " + ctx.channel().remoteAddress().toString());
//		super.channelRegistered(ctx);
		nettyClientMsgController.setCtx(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete : ");
//		System.out.println("channelReadComplete : " + ctx.channel().remoteAddress().toString());
//		super.channelReadComplete(ctx);
	}

	@Override
	public void channelActive( ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive : ");
//		System.out.println("channelActive : " + ctx.channel().remoteAddress().toString());
//		LogDataHelper.writeLog("channelActive()");
		//發送訊息到server
//		ctx.writeAndFlush( Unpooled.copiedBuffer(CommonString.INFO_CLIENT_CONNECTED, CharsetUtil.UTF_8));
//		ctx.writeAndFlush( Unpooled.copiedBuffer("INFO_CLIENT_CONNECTED", CharsetUtil.UTF_8));
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("channelRead : ");
//		System.out.println("channelRead : " + ctx.channel().remoteAddress().toString());
//		LogDataHelper.writeLog("channelRead()");
		//接收Server回傳的訊息
		ByteBuf byteBuf = (ByteBuf) msg;
		String message = byteBuf.toString(CharsetUtil.UTF_8);
		System.out.println("收到Server " + ctx.channel().remoteAddress() + " 的訊息：" + message);

//		MsgDTO msgDTO = nettyClientController.getClientMsg(message);
//		MsgDTO msgDTO = nettyClientMsgController.parseToMsgDTO(message);
		nettyClientMsgController.treatMsg(message);
//
//		clientCtxId = msgDTO.getMsg();



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
		System.out.println("channelInactive : ");
//		System.out.println("channelInactive : " + ctx.channel().remoteAddress().toString());
//		LogDataHelper.writeLog("channelInactive()");
		//super.channelInactive( ctx );
//		sendCMD(1, "disconnect");
		ctx.channel().close();

	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelUnregistered : ");
//		System.out.println("channelUnregistered : " + ctx.channel().remoteAddress().toString());
//		super.channelUnregistered(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("userEventTriggered : " + ctx.channel().remoteAddress().toString());
//		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("exceptionCaught : ");
//		System.out.println("exceptionCaught : " + ctx.channel().remoteAddress().toString());
//		super.exceptionCaught(ctx, cause);
	}
}