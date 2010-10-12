package es.cmaj.crennis.domain;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Round {
	private String name;
	private Date date;
	private List<Match> matches = new LinkedList<Match>();

	public Round() {
		this(null);
	}

	public Round(String name) {
		this(name,null);
	}

	public Round(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Match> getMatches() {
		return Collections.unmodifiableList(matches);
	}
	
	protected void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
	public void addMatch(Team home, Team away) {
		addMatch(home, away, null);
	}

	public void addMatch(Team home, Team away, Date date) {
		matches.add(new Match(home, away, date));
	}
	
	public void shuffle() {
	    Collections.shuffle(matches);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Round [date=");
		builder.append(date);
		builder.append(", matches=");
		builder.append(matches);
		builder.append("]");
		return builder.toString();
	}
}
