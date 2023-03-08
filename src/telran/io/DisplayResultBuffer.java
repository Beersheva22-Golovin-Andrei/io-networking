package telran.io;

public class DisplayResultBuffer extends DisplayResult {

	private long bufferSize;
	
	public DisplayResultBuffer(long fileSize, long copyTime, long bufferSize) {
		super(fileSize, copyTime);
		this.bufferSize = bufferSize;
	}

	@Override
	public String toString() {
		return "DisplayResultBuffer [bufferSize=" + bufferSize + "]";
	}
}
