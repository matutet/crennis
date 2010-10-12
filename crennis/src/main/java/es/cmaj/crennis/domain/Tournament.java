package es.cmaj.crennis.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import es.cmaj.crennis.domain.algorithm.CyclicRoundRobin;
import es.cmaj.crennis.domain.algorithm.ITournamentSchedule;

public class Tournament {
	private String name;
	private List<Team> enrollments = new LinkedList<Team>();
	private List<Round> rounds = new LinkedList<Round>();
	private ITournamentSchedule tournamentSchedule;

	public Tournament() {
		this(null);
	}

	public Tournament(String name) {
		super();
		this.name = name;
		tournamentSchedule = new CyclicRoundRobin();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void enroll(Team team) {
		enrollments.add(team);
	}

	public List<Round> getRounds() {
		return Collections.unmodifiableList(rounds);
	}

	public void setTournamentSchedule(ITournamentSchedule tournamentSchedule) {
		this.tournamentSchedule = tournamentSchedule;
	}

	public ITournamentSchedule getTournamentSchedule() {
		return tournamentSchedule;
	}

	public void calculateRounds() {
        // Shuffle enrollments ...
        List<Team> shuffled = new LinkedList<Team>();
        shuffled.addAll(enrollments);
        Collections.shuffle(shuffled);
        
		tournamentSchedule.calculateRounds(shuffled, rounds);
		
		// Shuffle rounds
        Collections.shuffle(rounds);
        
        int i = 1;
        for (Iterator<Round> it = rounds.iterator(); it.hasNext();) {
            Round round = it.next();
            round.setName("" + i++);
        }

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tournament [name=");
		builder.append(name);
		builder.append(", enrollments=");
		builder.append(enrollments);
		builder.append(", rounds=");
		builder.append(rounds);
		builder.append("]");
		return builder.toString();
	}
}
