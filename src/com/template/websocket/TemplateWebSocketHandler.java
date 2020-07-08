package com.template.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TemplateWebSocketHandler extends TextWebSocketHandler{
	
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        
		
		System.out.println(message.getPayload());
		
    }
	
	/**
	    * 建立连接后触发的回调
	    */
	   public void afterConnectionEstablished(WebSocketSession session) throws Exception
	   {
		   session.getId();
		   
		   System.out.println("afterConnectionEstablished");
		   
		   TextMessage text = new TextMessage ("你好");
		   session.sendMessage(text);
	   }

	   /**
	    * 收到消息时触发的回调
	    */
	   public  void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception
	   {
		   System.out.println("handleMessage");
	   }

	   /**
	    * 传输消息出错时触发的回调
	    */
	   public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception
	   {
		   System.out.println("handleTransportError");
	   }

	   /**
	    * 断开连接后触发的回调
	    */
	   public  void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception
	   {
		   System.out.println("afterConnectionClosed");
	   }

	   /**
	    * 是否处理分片消息
	    */
	   public boolean supportsPartialMessages()
	   {
		   return false;
	   }
}
