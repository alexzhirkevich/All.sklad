package airport;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.EnumMap;

public class Stuff implements Serializable {

	private static final long serialVersionUID = 1L;
	EnumMap<CrewMember.Role,ArrayDeque<CrewMember>> stuff = new EnumMap<>(CrewMember.Role.class);

	public Stuff() {
		stuff.put(CrewMember.Role.pilot,new ArrayDeque<>());
		stuff.put(CrewMember.Role.navigator,new ArrayDeque<>());
		stuff.put(CrewMember.Role.operator,new ArrayDeque<>());
		stuff.put(CrewMember.Role.steward,new ArrayDeque<>());
	}

	public Stuff(CrewMember[] pilots, CrewMember[] navigators, CrewMember[] operators, CrewMember[] stewards) {
		this();
		if (pilots != null) {
			for (CrewMember p:pilots){
				addStuff(p);
			}
		}
		if (navigators != null) {
			for (CrewMember p:navigators){
				addStuff(p);
			}
		}
		if (operators != null) {
			for (CrewMember p:operators){
				addStuff(p);
			}
		}
		if (stewards != null) {
			for (CrewMember p:stewards){
				addStuff(p);
			}
		}
	}

	public Stuff(final Stuff s){
		stuff = new EnumMap<>(s.stuff);
	}

	public CrewMember getCrew(CrewMember.Role role) {
		ArrayDeque<CrewMember> c = stuff.get(role);

		if (c.size() == 0)
			return null;

		c.push(c.pop());
		stuff.put(c.getLast().getRole(), c);

		return c.getLast();
	}

	public CrewMember[] getTeam() {
		ArrayDeque<CrewMember> team = new ArrayDeque<>();

		CrewMember c = getCrew(CrewMember.Role.pilot);
		if (c == null) { return null; }
		team.push(new CrewMember(c));

		c = getCrew(CrewMember.Role.navigator);
		if (c == null) { return null; }
		team.push(new CrewMember(c));

		c = getCrew(CrewMember.Role.operator);
		if (c == null) { return null; }
		team.push(new CrewMember(c));

		c = getCrew(CrewMember.Role.steward);
		if (c == null) { return null; }
		team.push(new CrewMember(c));

		return team.toArray(new CrewMember[team.size()]);
	}

	public void addStuff(CrewMember person) {
		ArrayDeque<CrewMember> c = stuff.get(person.getRole());
		c.push(new CrewMember(person));
		stuff.put(person.getRole(),c);
	}

	public boolean dissmiss(CrewMember person){
		boolean found = false;
		ArrayDeque<CrewMember> c = stuff.get(person.getRole());

		if (c.size() == 0)
			return false;

		CrewMember first = c.getFirst();
		if (first.equals(person)){
			c.removeFirst();
			stuff.put(person.getRole(),c);
			found = true;
		}
		else {
			c.push(c.pop());
			while (!c.getFirst().equals(first))  {
				if (c.getFirst().equals(person)) {
					c.removeFirst();
					stuff.put(person.getRole(), c);
					found = true;
				}
				c.push(c.pop());
			}
		}
		return found;
	}
}
