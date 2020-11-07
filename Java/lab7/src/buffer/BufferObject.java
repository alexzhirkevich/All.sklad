package buffer;

import java.io.Serializable;

public class BufferObject
{
	private Object object;
	private boolean zipped;

	public BufferObject(){
		object = null;
		zipped = false;
	}

	public BufferObject(Serializable object, boolean zipped){
		this.object = object;
		this.zipped = zipped;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Serializable object) {
		this.object = object;
	}

	public boolean isZipped() {
		return zipped;
	}

	public void setZipped(boolean zipped) {
		this.zipped = zipped;
	}
}