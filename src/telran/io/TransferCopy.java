package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TransferCopy extends Copy{

	public TransferCopy(String srcFilepath, String destFilePAth, boolean overwrite) {
		super(srcFilepath, destFilePAth, overwrite);
		
	}

	@Override
	public long copy() throws IOException {
		long size = 0;	
		//TODO impl. with param. overwrite!
		try (InputStream input = new FileInputStream(srcFilepath); 
			OutputStream output = new FileOutputStream(destFilePAth);) {
			long startTime = System.currentTimeMillis();
			size = input.transferTo(output);
			proccesTime = System.currentTimeMillis()-startTime;
		} 
		return size;
	}
	

	@Override
	public DisplayResult getDisplayResult(long copyTime, long fileSize) {
		return new DisplayResult(copyTime, fileSize);
	}
}
