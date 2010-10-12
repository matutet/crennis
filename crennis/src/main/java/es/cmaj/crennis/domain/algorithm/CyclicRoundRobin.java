package es.cmaj.crennis.domain.algorithm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.cmaj.crennis.domain.Round;
import es.cmaj.crennis.domain.Team;

/**
 * One item is locked while the others rotate. At each step of the rotation the round is planned by pairing
 * items.
 * <ul>
 * <li>round 1</li>
 * 
 * <pre>
 *        3  4  5  6
 *  1  2
 *       10  9  8  7
 * </pre>
 * 
 * <li>round 2</li>
 * 
 * <pre>
 *        2  3  4  5
 *  1 10
 *        9  8  7  6
 * </pre>
 * 
 * <li>round 3</li>
 * 
 * <pre>
 *       10  2  3  4
 *  1  9
 *        8  7  6  5
 * </pre>
 * 
 * <p>
 * ...
 * </p>
 * <p>
 * ...
 * </p>
 * <li>round 9</li>
 * 
 * <pre>
 *        4  5  6  7
 *  1  3
 *        2 10  9  8
 * </pre>
 * 
 * </ul>
 * 
 * If we have an odd number of players, invite a ghost to play. Whoever plays the ghost gets a bye.
 * 
 * @author matutet
 * @see http://www.devenezia.com/downloads/round-robin/index.html
 */
public class CyclicRoundRobin implements ITournamentSchedule {
    private static final Logger log = LoggerFactory.getLogger(CyclicRoundRobin.class);

    public void calculateRounds(final List<Team> players, final List<Round> rounds) {
        if (log.isDebugEnabled()) {
            log.debug("enroll(Team) - start");
        }

        // Calculate number of rounds and games per round
        int cntTeams = players.size();
        int cntGamesPerRound = (int) Math.ceil(cntTeams / 2.0);
        int cntRounds = (cntGamesPerRound * 2) - 1;
        if (log.isInfoEnabled()) {
            log.info("Tournament has {} teams, so there are {} rounds of {} games per round", new Integer[] {
                    cntTeams, cntRounds, cntGamesPerRound });
        }

        // Empty previous rounds
        rounds.clear();

        // Dump teams in temporary list ...
        if (log.isDebugEnabled()) {
            log.debug("calculateRounds() - players {}", players);
        }

        // If there are an odd number of players, the last one will be null
        Team[] teams = new Team[cntGamesPerRound * 2];
        players.toArray(teams);

        for (int i = 0; i < cntRounds; i++) {
            rounds.add(buildRound(teams));
            rotateToLeft(teams);
        }

        if (log.isDebugEnabled()) {
            log.debug("enroll(Team) - end");
        }
    }

    /**
     * Fold teams array and choose couples
     * 
     * @return
     */
    private Round buildRound(Team[] teams) {
        Round round = new Round();

        for (int i = 0; i < teams.length / 2; i++) {
            Team home = teams[i];
            Team away = teams[teams.length - 1 - i];
            if (log.isDebugEnabled()) {
                log.debug("buildRound() - {} vs {}", home, away);
            }
            round.addMatch(home, away);
        }
        round.shuffle();
        
        if (log.isDebugEnabled()) {
            log.debug("buildRound() - round: {}", round);
        }
        return round;
    }

    /**
     * Rotate teams from second item to the end. Element at the first position is always the same
     * 
     * @param teams
     */
    private void rotateToLeft(Team[] teams) {
        if (log.isDebugEnabled()) {
            log.debug("rotateTeams(Team[]) - start - teams[{}]: {}", teams.length, teams);
        }

        Team firstTeam = teams[1];
        // Team at the first position must be always the same, so iteration index begin at item 1
        for (int i = 1; i < teams.length - 1; i++) {
            teams[i] = teams[i + 1];
        }
        teams[teams.length - 1] = firstTeam;

        if (log.isDebugEnabled()) {
            log.debug("rotateTeams(Team[]) - end - teams[{}]: {}", teams.length, teams);
        }
    }

    /**
     * Rotate teams from second item to the end. Element at the first position is always the same
     * 
     * @param teams
     */
    @SuppressWarnings("unused")
    private void rotateToRight(Team[] teams) {
        if (log.isDebugEnabled()) {
            log.debug("rotateTeams(Team[]) - start - teams[{}]: {}", teams.length, teams);
        }

        Team lastTeam = teams[teams.length - 1];
        // Team at the first position must be always the same, so iteration index begin at item 1
        for (int i = teams.length - 1; i > 1; i--) {
            teams[i] = teams[i - 1];
        }
        teams[1] = lastTeam;

        if (log.isDebugEnabled()) {
            log.debug("rotateTeams(Team[]) - end - teams[{}]: {}", teams.length, teams);
        }
    }
}
