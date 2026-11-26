package first.robot.subsystems.exampleroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import first.robot.subsystems.exampleroller.ExampleRoller.Goal;
import first.robot.subsystems.exampleroller.ExampleRoller.State;
import org.junit.jupiter.api.Test;

/**
 * One test per row of the transition table in specs/000-example-roller/spec.md (T1-T7), plus the
 * safe-default and stall-debounce rules. Pure-function tests: no HAL, no hardware, no timing.
 *
 * <p>Name each test after its spec row (t3_...) so a reviewer can check the table row by row.
 */
class ExampleRollerTransitionTest {
  // Named arguments, so a call reads like a spec row instead of "true, false, true".
  private static final boolean ENABLED = true;
  private static final boolean DISABLED = false;
  private static final boolean PIECE = true;
  private static final boolean NO_PIECE = false;
  private static final boolean STALL = true;
  private static final boolean NO_STALL = false;

  private static State next(
      State current, Goal goal, boolean enabled, boolean hasPiece, boolean stallDetected) {
    return ExampleRoller.nextState(current, goal, enabled, hasPiece, stallDetected).state();
  }

  @Test
  void t1_disabledAlwaysGoesIdle() {
    for (State s : State.values()) {
      for (Goal g : Goal.values()) {
        assertEquals(State.IDLE, next(s, g, DISABLED, PIECE, STALL), s + "/" + g);
      }
    }
  }

  @Test
  void t2_stalledStaysStalledUntilGoalIdle() {
    assertEquals(State.STALLED, next(State.STALLED, Goal.INTAKE, ENABLED, NO_PIECE, NO_STALL));
    assertEquals(State.STALLED, next(State.STALLED, Goal.EJECT, ENABLED, NO_PIECE, NO_STALL));
  }

  @Test
  void t3_stallDetectedGoesStalled() {
    assertEquals(State.STALLED, next(State.INTAKING, Goal.INTAKE, ENABLED, NO_PIECE, STALL));
    assertEquals(State.STALLED, next(State.EJECTING, Goal.EJECT, ENABLED, NO_PIECE, STALL));
  }

  @Test
  void t4_goalIdleGoesIdleAndResetsStall() {
    assertEquals(State.IDLE, next(State.INTAKING, Goal.IDLE, ENABLED, NO_PIECE, NO_STALL));
    assertEquals(State.IDLE, next(State.STALLED, Goal.IDLE, ENABLED, NO_PIECE, NO_STALL));
  }

  @Test
  void t5_goalEjectGoesEjecting() {
    assertEquals(State.EJECTING, next(State.HOLDING, Goal.EJECT, ENABLED, PIECE, NO_STALL));
  }

  @Test
  void t6_goalIntakeWithoutPieceGoesIntaking() {
    assertEquals(State.INTAKING, next(State.IDLE, Goal.INTAKE, ENABLED, NO_PIECE, NO_STALL));
  }

  @Test
  void t7_goalIntakeWithPieceGoesHolding() {
    assertEquals(State.HOLDING, next(State.INTAKING, Goal.INTAKE, ENABLED, PIECE, NO_STALL));
  }

  @Test
  void safeDefault_idleAndStalledOutputZero() {
    assertEquals(0.0, ExampleRoller.outputVoltsFor(State.IDLE));
    assertEquals(0.0, ExampleRoller.outputVoltsFor(State.STALLED));
  }

  @Test
  void stallDebounce_startupSpikeShorterThanDebounceIsIgnored() {
    double overAmps = ExampleRoller.STALL_CURRENT_AMPS + 1.0;
    int loops = 0;
    for (int i = 0; i < ExampleRoller.STALL_DEBOUNCE_LOOPS - 1; i++) {
      loops = ExampleRoller.countOverCurrentLoops(loops, overAmps);
    }
    assertFalse(loops >= ExampleRoller.STALL_DEBOUNCE_LOOPS, "spike one loop too short");

    loops = ExampleRoller.countOverCurrentLoops(loops, overAmps);
    assertTrue(loops >= ExampleRoller.STALL_DEBOUNCE_LOOPS, "sustained over-current is a stall");
  }

  @Test
  void stallDebounce_dropUnderLimitResetsCount() {
    double overAmps = ExampleRoller.STALL_CURRENT_AMPS + 1.0;
    double normalAmps = ExampleRoller.STALL_CURRENT_AMPS - 1.0;
    int loops = ExampleRoller.countOverCurrentLoops(3, overAmps);
    assertEquals(4, loops);
    assertEquals(0, ExampleRoller.countOverCurrentLoops(loops, normalAmps));
  }
}
