# Add a Mechanism

Step-by-step, from "we want an intake" to merged code. Written for students doing the work and for mentors who want to know what students were asked to do. Unfamiliar words are in the [glossary](../glossary.md).

**Before you start**
- Finish the [quick start](../../README.md#quick-start-about-10-minutes-after-setup) so build, sim, and AdvantageScope work on your laptop.
- Read the reference example end to end. It is short on purpose: [spec](../../specs/000-example-roller/spec.md) → [`ExampleRoller.java`](../../src/main/java/first/robot/subsystems/exampleroller/ExampleRoller.java) → [`ExampleRollerTransitionTest.java`](../../src/test/java/first/robot/subsystems/exampleroller/ExampleRollerTransitionTest.java). Pick any row of the spec's transition table and find it in the code and in the tests.
- Have a mechanical or electrical student ready to answer questions about motors, sensors, and limits.

The whole flow:

| Step | Who | Output | Agent credits |
|---|---|---|---|
| 1. Design the behavior on paper | Students, with mech/elec | Goals, states, transition table, limits | None |
| 2. Write the spec | Student + Copilot | `specs/NNN-name/spec.md`, draft PR | 1 chat |
| 3. Spec review | Mentor + mech lead | "Spec approved" comment on the PR | None |
| 4. Plan and tasks | Student + Copilot | `plan.md`, `tasks.md` | 2 chats |
| 5. Implement, one task per chat | Student + Copilot | Code + tests | 1 chat per task |
| 6. Simulate | Student | Screenshot or note in the PR | None |
| 7. Review and merge | Student explains, mentor approves | Merged PR | None |

---

## 1. Design the behavior on paper

Do this on a whiteboard before you touch Copilot. It is the part that needs your judgment, and it costs no credits. Fill in the seven things below. The reference spec shows each one.

1. **Goals**: what the driver or the rest of the robot can ask for. Example: `IDLE`, `INTAKE`, `EJECT`.
2. **States**: what the mechanism is actually doing. There are usually more states than goals: `HOLDING` and `STALLED` are states nobody asks for directly.
3. **Transition table**: one row per rule, in the format "from state, when condition, go to state, because reason". Rows are checked **top to bottom**, and the first match wins.
   - Put the **safety rows first**: disabled → safe state, then faults (stall, over-limit, sensor lost).
   - Every row gets a short **reason** string. It is logged every loop, and it is the first thing you will read when something goes wrong at an event.
   - Write conditions with **numbers and units** ("current > 40 A for 0.1 s", "position > 1.2 m"), not words like "too high".
4. **Output per state**: voltage, position, or velocity for each state. The safe state outputs zero, or holds position if gravity would drop the mechanism.
5. **Limits**: current limit, soft limits (min/max position), and max speed, with numbers from the mech team.
6. **Failure modes**: for each sensor and motor, ask "what if it is unplugged or reads garbage?" and write down what the mechanism does.
7. **Controls**: which button sets which goal, and what happens when it is released.

## 2. Write the spec with `/speckit-specify`

1. Open **Copilot Chat** in WPILib VS Code, switch to **Agent** mode, and start a **new chat**.
2. Type `/speckit-specify` followed by a one-paragraph description, then paste your paper design. For example:
   > `/speckit-specify` Ground intake: a single roller that pulls a game piece in while the driver holds a button and stops when a beam-break sees the piece. Here is our design: (paste the goals, states, table, limits, failure modes, controls)
3. Copilot creates a branch and `specs/NNN-short-name/spec.md` from the template. The **Robot Behavior** section has the same 11 headings as the reference spec.
4. **Read every line.** Fix anything Copilot guessed. Anything still unknown, such as a CAN ID nobody has picked yet, goes in as `TBD` and in the open questions; don't let Copilot invent it.
5. Commit, push, and open a **draft** pull request titled `Spec: <mechanism>`:
   ```sh
   git add specs/ && git commit -m "Spec: ground intake"
   git push -u origin HEAD
   gh pr create --draft --title "Spec: ground intake" --body "Spec only, for review. Plan and code follow after Spec approved."
   ```

Optional: `/speckit-clarify` asks Copilot to find gaps in the spec. Use it when the spec has open questions. It costs a chat, so skip it when the spec is clear.

## 3. Spec review (mentor + mech lead)

A mentor and the mechanical lead review the spec in the draft PR, using the spec half of the [review checklist](review-checklist.md#reviewing-a-spec). When it is right, the mentor comments **"Spec approved"**. Do not start step 4 before that. Changing code is easy; finding out the arm can't actually reach that angle after the code is written is not.

## 4. Plan and tasks

On the same branch, in a **new chat** each time:

1. `/speckit-plan`: Copilot writes `plan.md`, which lists the files to create, the IO layer, and how each spec row will be tested. It also runs the **constitution check**. Make sure the plan:
   - copies the reference shape (the file list below),
   - has one test per transition-table row,
   - does not add anything the spec doesn't ask for.
2. `/speckit-tasks`: Copilot writes `tasks.md`, a numbered list of small tasks. Good tasks for a new mechanism look like this:
   1. `Goal`/`State` enums, `nextState`, `outputVoltsFor`, and one transition test per row
   2. IO interface and sim IO
   3. Command factory and `RobotContainer` bindings
   4. `docs/robot-io.md` entries (if there is hardware)

   If a task touches more than about three files, ask Copilot to split it.

Commit both files.

### The files a new mechanism needs

For a mechanism called `Intake`, copy the reference files and rename as shown. Copilot does this during `/speckit-implement`, but you should know what to expect.

| Copy from (`exampleroller`) | To | Notes |
|---|---|---|
| `subsystems/exampleroller/ExampleRoller.java` | `subsystems/intake/Intake.java` | Keep `State` package-private (no `public`) |
| `subsystems/exampleroller/ExampleRollerIO.java` | `subsystems/intake/IntakeIO.java` | Inputs class becomes `IntakeIOInputs` |
| `subsystems/exampleroller/ExampleRollerIOSim.java` | `subsystems/intake/IntakeIOSim.java` | Deterministic: no random numbers |
| `commands/ExampleRollerCommands.java` | `commands/IntakeCommands.java` | Commands only call `setGoal` |
| `test/.../exampleroller/ExampleRollerTransitionTest.java` | `test/.../intake/IntakeTransitionTest.java` | Same package as the subsystem, so tests can see `State` |
| `RobotContainer.java` | (edit in place) | Create the subsystem with the right IO for each mode; add bindings |

Also change the log keys (`"ExampleRoller/..."` → `"Intake/..."`).

`IntakeIOInputsAutoLogged` does not exist until you build. AdvantageKit generates it from the `@AutoLog` annotation. A red underline on it before the first `./gradlew build` is normal.

## 5. Implement, one task per chat

For each task in `tasks.md`:

1. Start a **new chat** (this keeps credit use low) and run `/speckit-implement` with the task number, for example `/speckit-implement task 1 only`.
2. When Copilot asks to run a terminal command, **read it before you click Allow**. Allow `./gradlew build` and `./gradlew test`. Never allow `deploy`, anything that writes NetworkTables, or `git push --force`.
3. Run `./gradlew build` yourself and make sure it says `BUILD SUCCESSFUL`.
4. Review the diff in **Source Control** and check it against your spec. You will have to explain every line to a mentor.
5. Commit with a message that names the task: `git commit -m "Intake: task 1, state machine and transition tests"`.

### Writing the transition tests

Each row of the spec's table gets one test, named after the row: `t1_disabledAlwaysGoesIdle`, `t3_stallDetectedGoesStalled`, and so on. The tests call the pure `nextState` function directly, so they need no robot, no simulator, and no timing. Use named constants (`ENABLED`, `NO_PIECE`, ...) instead of bare `true`/`false` so each call reads like its spec row. If you add a debounce or a counter, give it its own small pure function and its own test, as `countOverCurrentLoops` does.

If a test fails, read the message: it shows the expected and actual state. Either the code is wrong (fix it) or the spec is wrong (change the spec, get it re-approved, then change the test). **Never delete or loosen a test to get CI green.**

## 6. Simulate and replay

**Simulate**: see quick-start steps 3 and 4 in the [README](../../README.md#quick-start-about-10-minutes-after-setup). Walk through each user story in the spec and watch `Goal`, `State`, and `TransitionReason` in AdvantageScope. Note what you saw in the PR description.

**Replay** (practice now; it becomes our main debugging tool once the robot runs):

1. Run the simulation, press some buttons, then close it. The log is saved in `logs/`.
2. Open that `.wpilog` in AdvantageScope (**File → Open Log**).
3. In `Constants.java`, change `SIM_MODE = Mode.SIM` to `Mode.REPLAY`.
4. Run **WPILib: Simulate Robot Code** again. AdvantageKit replays the log that is open in AdvantageScope. It prints `Replaying log from AdvantageScope`, or asks for a file path in the terminal.
5. A new log ending in `_sim.wpilog` appears next to the original. Open it: `ReplayOutputs/<Mechanism>/State` should match `RealOutputs/<Mechanism>/State` exactly. If it doesn't, something unlogged is driving a decision. That is a bug.
6. **Set `SIM_MODE` back to `Mode.SIM` before you commit.**

## 7. Review and merge

1. Fill in the PR template: what changed, which checks you ran (and their results), and the AI-assistance box.
2. Click **Ready for review**. CI runs `./gradlew build`; it must be green.
3. Explain the change to a mentor: which spec row each piece of code implements, how you tested it, and what you saw in sim. Then tick "I can explain this change".
4. The mentor reviews with the [review checklist](review-checklist.md) and approves. Squash and merge.

---

## Smaller changes

| Change | What to do |
|---|---|
| **Change a button** | Edit `configureBindings()` in `RobotContainer.java` and the **Operator controls** table in the spec, in the same PR. |
| **Change a limit or threshold** | Change the spec first, then the constant, then the test that covers it. Note the old and new values in the PR. |
| **Add a transition** | Add the row to the spec's table (in the right position: order matters), then the code, then a test named after the new row. |
| **Fix a bug** | Write a test that fails because of the bug, fix the code, and watch the test pass. If the spec was wrong, fix the spec too. |
| **Add an autonomous routine** | Each auto gets its own spec. Add a factory method in `commands/Autos.java`. Autos are built from subsystem goals, like every other command. |

## When real hardware arrives (Phase 1)

The reference roller has no real IO yet. When a mechanism's plan names its motors, add a real IO class, for example `IntakeIOTalonFX`, that implements `IntakeIO`. The checklist below is what experienced FRC programmers check every time; the reviewer will check it too.

- **CAN IDs** recorded in [`docs/robot-io.md`](../robot-io.md) in the same PR, with the `CANPort` enum value.
- **Current limits** configured on the motor controller itself (supply and stator for CTRE), not only in our state machine. The controller limit protects the motor and breaker even if our code is wrong.
- **Neutral mode** chosen on purpose: brake for arms and elevators (they hold under gravity), coast is often fine for rollers.
- **Inversion** checked on the bench, wheels off the ground, by a human, at low voltage.
- **Soft limits** (min/max position) for anything that can hit a hard stop, plus a plan for zeroing (homing) the position.
- **`motorConnected`** and every sensor read in `updateInputs`, so they are logged and replayable. Nothing reads hardware anywhere else.
- **API check**: vendor APIs changed for 2027. Copilot must verify each call with the `frc` MCP server or a compile; don't accept "I think the method is called...".
- **Bring-up checklist** filled in, in spec section 10. A human deploys, the robot is on blocks, and someone has a hand on disable.

## Common problems

| You see | Why | Fix |
|---|---|---|
| `cannot find symbol: class IntakeIOInputsAutoLogged` | Generated code doesn't exist yet | Run `./gradlew build` once |
| `package edu.wpi.first... does not exist` | Code copied from 2026 or older | Use `org.wpilib.*`; ask Copilot to run `frc_migrate` on it |
| `cannot find symbol: method isEnabled()` on `DriverStation` | Moved in 2027 | Use `RobotState.isEnabled()` |
| `SmartDashboard` / `SendableChooser` not found | Removed in 2027 | Log with `Logger.recordOutput`; see AGENTS.md pitfalls |
| `/speckit-specify` fails with `python3: command not found` | Python not installed or not on PATH | [Setup guide 2.6](../framework/setup-guide.md#26-python-runtime-every-programmer) |
| Copilot wants to run `./gradlew deploy` | Agents never deploy | Click **Skip**, and tell a mentor which prompt led to it |
| Test fails in CI but passes on your laptop | Usually uncommitted files | `git status`, commit what's missing |
| Mechanism goes straight to STALLED in sim | Sim current model or limit too tight | Check the logged `currentAmps` in AdvantageScope against the limit and the debounce |
