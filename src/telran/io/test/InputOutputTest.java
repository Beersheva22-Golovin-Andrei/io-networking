package telran.io.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class InputOutputTest {

	String fileName = "myFile";
	String directoryName = "myDirectory1/myDirectory2";
		@BeforeEach
		void setUp() throws Exception {
			new File(fileName).delete();
			new File(directoryName).delete();
		}

		@Test
		@Disabled
		void testFile() throws IOException {
			File f1 = new File(fileName);
			assertTrue(f1.createNewFile());
			File dir1 = new File(directoryName);
			assertTrue(dir1.mkdirs());
			System.out.println(dir1.getAbsolutePath());
			
			
		}
		@Test
		void printDirectoryFileTest() throws IOException {
			 new File ("main_dir/folder1/folder2/folder3/folder4").mkdirs(); 
			 new File("main_dir/folder1/file1").createNewFile();
			 new File ("main_dir/folder5/folder6").mkdirs(); 
			 new File("main_dir/file0").createNewFile();
			 new File ("main_dir/folder1/folder2/folder3/file3-1").createNewFile();
			 new File ("main_dir/folder1/folder2/folder3/file3-2").createNewFile();
			printDirectoryFile("main_dir", -1);
			new File ("simple_file").createNewFile();
			printDirectoryFile("simple_file", 2);	
		}
		
		void printDirectoryFile(String path, int maxLevel) {	
			File file = new File(path);
			System.out.println(file.getName());
			if (file.isDirectory()) {
				checkDirectory(file, 0, maxLevel);
			}
		}
			
		private void checkDirectory (File directory, int level, int maxLevel) {
			if (maxLevel<0 || level<maxLevel) {
				File[] dirArr = directory.listFiles();
				if (dirArr.length!=0) {
					for (int i=0; i<dirArr.length; i++) {
						System.out.println("	".repeat(level+1) + dirArr[i].getName());
						if (dirArr[i].isDirectory()) {
							checkDirectory (dirArr[i],level+1, maxLevel);
						}
					}
				}
			}
		}
		
		@Test
		@Disabled
		void testFiles() {
			Path path = Path.of(".");
			System.out.println(path.toAbsolutePath().getNameCount());
			
			
		}
		@Test
		void printDirectoryFilesTest1() {
			//printDirectoryFiles("main_dir/folder1/folder2/folder3/folder4", 3);
			printDirectoryFiles("main_dir/folder1/folder2/folder3/folder4", -3);
		}
		
		void printDirectoryFiles(String path, int maxLevel) {
			Path direct = Path.of(path);
			if (maxLevel<0) maxLevel = direct.getNameCount();
			System.out.println(direct.subpath(0, 1));
			checkDir(direct, maxLevel, 0);
			
			
		}
		
		
		private void checkDir (Path path, int maxLevel, int level) {
			if (level<maxLevel) {
				File file = new File (path.subpath(0, level+1).toString());
				Arrays.stream(file.listFiles()).filter(f -> {
					System.out.println("	".repeat(level+1)+f.getName());
					return f.isDirectory();
					})
				.forEach(f -> checkDir(f.toPath(), maxLevel, level+1));  
			}
		}		
}