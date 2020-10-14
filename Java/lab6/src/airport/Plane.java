package airport;

import locale.MyLocale;
import java.io.Serializable;
import java.util.Objects;

public class Plane extends CreationDateHolder implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String model;
	private Double capacity;
	private Double flyRange;

	public Plane() {
		model = "unknown";
		capacity = 0.0;
		flyRange = 0.0;
	}

	public Plane(Double capacity, Double flyRange, String model){
		this.model = model;
		this.capacity = capacity;
		this.flyRange = flyRange;
	}

	public Plane(final Plane p) {
		this(p.capacity,p.flyRange,p.model);
	}

	public Double getCapacity() {
		return capacity;
	}

	public Double getFlyRange() {
		return flyRange;
	}

	public String getModel() {
		return model;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public void setFlyRange(Double flyRange) {
		this.flyRange = flyRange;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Plane plane = (Plane) o;
		return Objects.equals(model, plane.model) &&
				Objects.equals(capacity, plane.capacity) &&
				Objects.equals(flyRange, plane.flyRange);
	}

	@Override
	public int hashCode() {
		return Objects.hash(model, capacity, flyRange);
	}

	@Override
	public String toString() {
		return "[" + MyLocale.getString(MyLocale.model) + model + " | " +
				MyLocale.getString(MyLocale.capacity) + capacity + " | " +
				MyLocale.getString(MyLocale.fly_range) + flyRange + " | " +
				getCreationDateString() + "]";
	}
}
