package es.cmaj.crennis.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Team {

	private String name;
	private List<Player> players = new LinkedList<Player>();

	public Team() {
		this(null);
	}

	public Team(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	protected void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Team addPlayer(Player player) {
		players.add(player);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Team [name=");
		builder.append(name);
		//builder.append(", players=");
		//builder.append(players);
		builder.append("]");
		return builder.toString();
	}

}
