package com.tournament.managerment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

@Configuration
public class WebSocketConfig {
	@Value("${tbm.socket.host}")
	private String host;

	@Value("${tbm.socket.port}")
	private Integer port;

	@Bean
	public SocketIOServer socketIOServer() {
		com.corundumstudio.socketio.Configuration conf = new com.corundumstudio.socketio.Configuration();

		conf.setHostname(host);
		conf.setPort(port);

		return new SocketIOServer(conf);
	}

	@Bean
	public SpringAnnotationScanner springAnnotationScanner() {
		return new SpringAnnotationScanner(socketIOServer());
	}
}
