package telran.io.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class InputOutputStreamTest {

	final static String fileName = "test.txt";
	final static String fileNameCopy = "test-copy.txt";
	String hello = "Hello World";
		
	@BeforeAll
		static void setUp() throws Exception {
			Files.deleteIfExists(Path.of(fileName));
		}
		
		@Test
		@Order(1)
		void OutputStreamTest() throws Exception {
			try(OutputStream output = new FileOutputStream(fileName)) {
				
				byte[] helloBytes = hello.getBytes();
				output.write(helloBytes);
			}
		}
		@Test
		
		void InputStreamTest() throws Exception {
			String file = fileName;
			readFileTest(file);
		}

		private void readFileTest(String file) throws IOException, FileNotFoundException {
			try (InputStream input = new FileInputStream(file)) {
				byte[] buffer = input.readAllBytes();
				String str = new String(buffer);
				assertEquals(hello, str);
			}
		}
		@Test
		@Order(2)
		void transferToTest() throws Exception{
			try (InputStream input = new FileInputStream(fileName); 
					OutputStream output = new FileOutputStream(fileNameCopy);) {
				input.transferTo(output);
			}
		}
		@Test
		void copyTest() throws Exception{
			readFileTest(fileNameCopy);
		}
		
}
