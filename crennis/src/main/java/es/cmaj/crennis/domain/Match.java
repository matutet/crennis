package es.cmaj.crennis.domain;

import java.util.Date;

public class Match {

	private Team homeTeam;
	private Team awayTeam;
	private Date date;

	public Match() {
		this(null, null, null);
	}

	public Match(Team homeTeam, Team awayTeam, Date date) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.date = date;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Match [homeTeam=");
		builder.append(homeTeam);
		builder.append(", awayTeam=");
		builder.append(awayTeam);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

}

