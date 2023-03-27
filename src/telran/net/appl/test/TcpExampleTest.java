package telran.net.appl.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import telran.net.NetworkClient;
import telran.net.UdpClient;

public class TcpExampleTest {

	static NetworkClient client; 
	@BeforeAll
	static void connection() throws Exception {
		client = new UdpClient("localhost", 4000);
	}

	@Test
	void test() {
		assertEquals("olleH", client.send("reverse", "Hello"));
		Integer expected = 5;
		assertEquals(expected, client.send("length", "Hello"));
	}
	@AfterAll
	static void disconnection() throws IOException {
		client.close();
}
}
