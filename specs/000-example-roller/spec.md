# Feature Specification: Example Roller (reference)

**Feature Branch**: `000-example-roller`

**Created**: 2026-11-25

**Status**: Reference example. Not a real mechanism. Shows how the FRC fields map to code and tests.

**Code**: `src/main/java/first/robot/subsystems/exampleroller/` · **Tests**: `src/test/java/first/robot/subsystems/exampleroller/ExampleRollerTransitionTest.java`

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Intake and hold a game piece (Priority: P1)

The operator holds a button to run the roller inward. When a piece is detected the roller drops to a low holding voltage. Releasing the button returns to idle.

**Independent Test**: Transition tests T6, T7, T4, plus sim with the button held.

**Acceptance Scenarios**:

1. **Given** IDLE and no piece, **When** the intake button is held, **Then** state is INTAKING and output is +8 V.
2. **Given** INTAKING, **When** a piece is detected, **Then** state is HOLDING and output is +1 V.

### User Story 2 - Eject (Priority: P2)

The operator holds a second button to run the roller outward.

**Acceptance Scenarios**:

1. **Given** HOLDING, **When** the eject button is held, **Then** state is EJECTING and output is −8 V.

### Edge Cases

- Jam (current above limit) → STALLED, zero output, stays stalled until the goal returns to IDLE.
- Robot disabled in any state → IDLE, zero output.

## Robot Behavior

### 1. Purpose and match role
Teaching example for the Goal/State pattern. Not on the robot.

### 2. Hardware

| Device | Type / controller | CANPort / channel | Notes |
|--------|-------------------|-------------------|-------|
| Roller motor | TBD (no real IO yet) | — | Sim only |
| Piece sensor | TBD | — | Sim always reports no piece |

### 3. Goals and States

- **Goals**: IDLE, INTAKE, EJECT
- **States**: IDLE, INTAKING, HOLDING, EJECTING, STALLED

| # | From | Condition | To | Transition reason (logged) |
|---|------|-----------|----|----------------------------|
| T1 | any | robot disabled | IDLE | "robot disabled" |
| T2 | STALLED | goal is not IDLE | STALLED | "stalled; waiting for IDLE goal to reset" |
| T3 | any | current > 40 A | STALLED | "current over stall limit" |
| T4 | any | goal IDLE | IDLE | "goal IDLE" |
| T5 | any | goal EJECT | EJECTING | "goal EJECT" |
| T6 | any | goal INTAKE, no piece | INTAKING | "goal INTAKE, no piece" |
| T7 | any | goal INTAKE, piece detected | HOLDING | "piece detected" |

**Output per state**: INTAKING +8 V · HOLDING +1 V · EJECTING −8 V · IDLE 0 V · STALLED 0 V

### 4. Superstructure interactions and keep-outs
None. `isHolding()` is available to a future superstructure.

### 5. Operator controls

| Control | Action | Goal set |
|---------|--------|----------|
| Driver faceRight | while held | INTAKE (IDLE on release) |
| Driver faceDown | while held | EJECT (IDLE on release) |

### 6. Limits
- Current: STALLED above 40 A.
- Voltage: clamped to ±12 V in IO.

### 7. Failure modes and safe states

| Failure | How detected | Safe state / response |
|---------|--------------|-----------------------|
| Jam / stall | `currentAmps` > 40 | STALLED, 0 V, operator releases button to reset |
| Robot disabled | `RobotState.isEnabled()` false | IDLE, 0 V |
| Motor disconnected | `motorConnected` false (logged) | Not handled in this example; a real spec must define it |

### 8. Telemetry
`ExampleRoller/Goal`, `/State`, `/TransitionReason`, `/OutputVolts`, plus all IO inputs.

### 9. Acceptance evidence
- [x] Transition tests T1–T7 and the safe-default test pass in CI
- [ ] Sim: hold faceRight, see INTAKING and +8 V in AdvantageScope

### 10. Bring-up checklist
Not applicable (no hardware).

### 11. Out of scope
Real motor IO, piece sensor, superstructure, closed-loop control.

## Requirements *(mandatory)*

- **FR-001**: The roller MUST output 0 V whenever the robot is disabled.
- **FR-002**: The roller MUST enter STALLED and output 0 V when current exceeds 40 A.
- **FR-003**: Commands MUST only change the roller's goal.
- **FR-004**: Goal, state, and transition reason MUST be logged every loop.

## Success Criteria *(mandatory)*

- **SC-001**: Every transition-table row has a passing unit test.
- **SC-002**: A student can trace any row from this spec to the `nextState` code and its test.
