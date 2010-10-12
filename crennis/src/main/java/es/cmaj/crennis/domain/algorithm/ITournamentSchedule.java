package es.cmaj.crennis.domain.algorithm;

import java.util.List;

import es.cmaj.crennis.domain.Round;
import es.cmaj.crennis.domain.Team;

public interface ITournamentSchedule {

	public abstract void calculateRounds(final List<Team> enrollments,
			final List<Round> rounds);

}