package com.kbdwn.chat.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// Discard the received data silently.
        //((ByteBuf) msg).release();
        
        try {
            // Do something with msg
        	ByteBuf msgbb=((ByteBuf) msg);
        	/*byte[] bs=new byte[10];
        	StringBuilder sb=new StringBuilder();
        	while(msgbb.isReadable()){
        		msgbb.readBytes(bs);
        		sb.append(IOUtils.toString(bs, "utf-8"));
        	}
        	System.out.println(sb.toString());*/
        	System.out.println(msgbb.toString(io.netty.util.CharsetUtil.UTF_8));
        	
        	//ctx.write(msg); // (1)
            //ctx.flush(); // (2)
        } finally {
            ReferenceCountUtil.release(msg);
        }
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
	
}
