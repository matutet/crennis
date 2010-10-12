package es.cmaj.crennis;

import java.util.Iterator;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.cmaj.crennis.domain.Match;
import es.cmaj.crennis.domain.Player;
import es.cmaj.crennis.domain.Round;
import es.cmaj.crennis.domain.Team;
import es.cmaj.crennis.domain.Tournament;

/**
 * Hello world!
 * 
 */
public class Crennis {
	/**
	 * Logger for this class
	 */
	private static final Logger log = LoggerFactory.getLogger(Crennis.class);

	private static final int NUM_PLAYERS = 15;

	public static void main(String[] args) {
		// Set up a simple configuration that logs on the console.
		BasicConfigurator.configure();

		if (log.isDebugEnabled()) {
			log.debug("main(String[]) - start");
		}

		Tournament tournament = new Tournament("Liga CREV");
		for (int n = 0; n < NUM_PLAYERS; n++) {
			String name = "Player " + (n + 1);

			Team team = new Team(name);
			Player player = new Player(name);
			team.addPlayer(player);
			tournament.enroll(team);
		}

		tournament.calculateRounds();
		
		for (Iterator<Round> it = tournament.getRounds().iterator(); it.hasNext();) {
			Round round= it.next();
			dumpRound(round);
		}


		if (log.isDebugEnabled()) {
			log.debug("main(String[]) - end");
		}
	}
	
	private static void dumpRound(Round round) {
		System.out.println("Round: " + round.getName());
		System.out.println("==============================");
		
		
		for (Match match : round.getMatches() ) {
			System.out.print("\t");
			System.out.print(match.getHomeTeam() != null ? match.getHomeTeam().getName() : null);
			System.out.print(" vs ");
			System.out.print(match.getAwayTeam() != null ? match.getAwayTeam().getName() : null);
			System.out.print("\n");
		}
		System.out.println();
		
	}
}
