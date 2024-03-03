package Spring_HW.sem11.config_serv;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SuppressWarnings("UnresolvedClassReferenceRepair")
@EnableConfigServer
@SpringBootApplication
public class ConfigServApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
	}
}