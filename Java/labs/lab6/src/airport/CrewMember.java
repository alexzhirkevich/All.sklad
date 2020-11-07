package airport;

import locale.MyLocale;
import java.io.Serializable;
import java.util.Objects;

public class CrewMember extends  CreationDateHolder implements Serializable {

	public enum Role {pilot, navigator, operator, steward}

	private static final long serialVersionUID = 1L;

	private String name;
	private Role role;

	public CrewMember(){
		name = "unnamed";
		role = Role.steward;
	}

	public CrewMember(String name, Role role){
		this.name = name;
		this.role = role;
	}

	public CrewMember(final CrewMember c) {
		this.name = c.name;
		this.role = c.role;
	}

	public Role getRole() {
		return role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CrewMember that = (CrewMember) o;
		return Objects.equals(name, that.name) &&
				role == that.role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, role);
	}

	@Override
	public String toString() {
		return  name + " (" + MyLocale.getString(role.toString())+ ") " + getCreationDateString();
	}
}
