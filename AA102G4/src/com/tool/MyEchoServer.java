package com.tool;
import java.io.*;
import java.util.*;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.joinactivity.model.JoinactivityService;
import com.joinactivity.model.JoinactivityVO;


@ServerEndpoint("/MyEchoServer")
public class MyEchoServer {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 已連線123");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		Integer i=null;
		for (Session session : allSessions) {
			if (session.isOpen()){
				Gson gson = new Gson();
				JsonObject jsonObj = gson.fromJson(message,JsonObject.class );
				Integer rel_mem_no = jsonObj.get("mem").getAsInt();
				Integer act_no = jsonObj.get("act").getAsInt();
				Integer rel_invite = jsonObj.get("rel_invite").getAsInt();
				System.out.println("mem:"+rel_mem_no);
				System.out.println("act:"+act_no);
				JoinactivityService jactSvc = new JoinactivityService();
				JoinactivityVO jactVO=jactSvc.getIsINActByMem_no(act_no, rel_mem_no);
				if(rel_invite==1){
					if(jactVO!=null){
						jactSvc.updateJoinAct(act_no, rel_mem_no, 0, 0, 0.0, 0.0);
					}
					else{//該成員未參加時
						jactSvc.addJoinAct(act_no, rel_mem_no, 0, 0, 0.0, 0.0);
					}
				}
				else if(rel_invite==0){
						jactSvc.updateJoinAct(act_no, rel_mem_no,3, 0, 0.0, 0.0);
				}
				int cnt = jactSvc.getCntNoSureByMem(rel_mem_no);
				System.out.println("cnt: " +cnt );
				JsonObject str = new JsonObject();
				str.addProperty("cnt", cnt);
				str.addProperty("mem", rel_mem_no);
				session.getAsyncRemote().sendText(str.toString());
			}
		}
		System.out.println("Message received: " +message );
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
