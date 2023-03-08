package telran.io;

import java.io.IOException;

public class BufferCopy extends Copy {

	public BufferCopy(String srcFilepath, String destFilePAth, boolean overwrite) {
		super(srcFilepath, destFilePAth, overwrite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long copy() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DisplayResult getDisplayResult(long copyTime, long fileSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
