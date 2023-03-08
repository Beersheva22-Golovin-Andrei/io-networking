package telran.io;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FilesCopy extends Copy {
	
	public FilesCopy(String srcFilepath, String destFilePAth, boolean overwrite) {
		super(srcFilepath, destFilePAth, overwrite);
	}

	@Override
	public long copy() throws IOException{
		long size = 0;
		try {
			CopyOption copyOption = overwrite ?  StandardCopyOption.REPLACE_EXISTING : null ;
			Path distPath = Path.of(destFilePAth);
			long startTime = System.currentTimeMillis();
			Files.copy(Path.of(srcFilepath), distPath, copyOption);
			proccesTime = System.currentTimeMillis()-startTime;
			size = Files.size(distPath);
		} catch (FileAlreadyExistsException e1) {
			System.out.println("File already exists and cannot be overwritten!");
		}
		return size;
	}

	@Override
	public DisplayResult getDisplayResult(long copyTime, long fileSize) {
		return new DisplayResult(copyTime, fileSize);
	}
}
