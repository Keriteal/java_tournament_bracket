package com.tournament.managerment.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tournament.managerment.dto.RoundInfo;

@Component
public class BracketSocketEventHandler {
	private final static Logger logger = LoggerFactory.getLogger(BracketSocketEventHandler.class);
	private static Map<String, SocketIOClient> clientsMap = new HashMap<String, SocketIOClient>();
	public final static String EVENT_UPDATED = "updated";

	private final SocketIOServer server;

	@Autowired
	public BracketSocketEventHandler(SocketIOServer server) {
		this.server = server;
	}

	@OnConnect
	public void onConnect(SocketIOClient client) {
		String uuid = client.getSessionId().toString();
		clientsMap.put(uuid, client);
		logger.info("Client " + client.getSessionId() + "connected");
	}

	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		String uuid = client.getSessionId().toString();
		clientsMap.remove(uuid);
		logger.info("Client " + client.getSessionId() + "disconnected");
	}

	@OnEvent("subscribe")
	public void onSubscribe(SocketIOClient client) {
		logger.info("Client " + client.getSessionId() + "disconnected");
	}

	public SocketIOServer getServer() {
		return server;
	}

	public void sendUpdatedToAll(List<RoundInfo> rounds) {
		ObjectMapper mapper = new ObjectMapper();
		String message = "";
		try {
			message = mapper.writeValueAsString(rounds);
		} catch (Exception ex) {
			logger.error(rounds.toString());
		}

		this.sendMessageToAll(EVENT_UPDATED, message);
	}

	public void sendMessageToAll(String eventType, String message) {
		Collection<SocketIOClient> clients = server.getAllClients();
		for (SocketIOClient client : clients) {
			client.sendEvent(eventType, message);
		}
	}

}
