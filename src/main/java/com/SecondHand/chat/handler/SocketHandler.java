package com.SecondHand.chat.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;

@Component
public class SocketHandler extends TextWebSocketHandler {

    // 웹 소켓 세션을 담아둘 맵
    HashMap<String, WebSocketSession> sessionMap = new HashMap<>();

    @Override   // 메시지 전송
    public void handleTextMessage(WebSocketSession session, TextMessage message){
        String msg = message.getPayload();
        for(String key : sessionMap.keySet()){
            WebSocketSession wss = sessionMap.get(key);
            try {
                wss.sendMessage(new TextMessage(msg));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override   // 소켓 연결
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session);
    }

    @Override   // 소켓 종료
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }

}
