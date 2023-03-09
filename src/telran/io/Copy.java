package telran.io;

import java.io.IOException;

public abstract class Copy {

	protected String srcFilepath;
	
	protected String destFilePAth;
	
	protected boolean overwrite = false;
	
	protected long proccesTime;

	public Copy(String srcFilepath, String destFilePAth, boolean overwrite) {
		this.srcFilepath = srcFilepath;
		this.destFilePAth = destFilePAth;
		this.overwrite = overwrite;
	}
	
	public abstract long copy() throws IOException;
	
	public abstract DisplayResult getDisplayResult (long copyTime, long fileSize);

	public void copyRun() {
		try {	
			long fileSize = copy();
			getDisplayResult(proccesTime, fileSize).toString();
		} catch (IOException e2) {
			System.out.println("File cannot be copied!");
			e2.printStackTrace();
		}
	}
}
