# Review Checklist (for mentors)

What to check when you review a spec or a code PR. You don't need years of FRC experience to use it: most items are "is it there, and does it match". When something looks wrong and you're not sure, ask the student to explain it. That conversation is a required part of review anyway. Terms are in the [glossary](../glossary.md).

Copilot code review (Lite, mentor-requested, see [D-013](../framework/decisions.md#d-013-copilot-code-review-is-mentor-requested-lite-by-default)) is optional and advisory. A human approval is still required.

## Reviewing a spec

Use the reference spec, [`specs/000-example-roller/spec.md`](../../specs/000-example-roller/spec.md), as the model. Review with the mechanical lead.

- [ ] **Safe state is defined**, and it is what happens when the robot is disabled. For anything gravity can move (arm, elevator, climber), "zero output" may drop it; the spec must say what holds it.
- [ ] **Transition table puts safety rows first** (disabled, faults), and every row has a logged reason.
- [ ] **Every condition has a number and a unit** ("> 40 A for 0.1 s", "below 0.05 m"), not "too high" or "stuck".
- [ ] **Current-based detection has a debounce.** Motors draw a current spike every time they start; a stall check without a time window will trip on every button press.
- [ ] **Limits come from the mech team**, not from Copilot: current limit, soft limits, max speed.
- [ ] **Every sensor and motor has a failure row**: unplugged, reads garbage, or disagrees with another sensor.
- [ ] **Keep-outs are listed** if this mechanism can collide with another one (Robot Behavior section 4).
- [ ] **Controls say what happens on release**, and what happens if two buttons are held at once.
- [ ] **Nothing invented.** CAN IDs, gear ratios, and sensor types the team hasn't decided are marked `TBD`, not made up.
- [ ] **Out of scope is written down** (section 11), so the plan doesn't grow.

When it passes, comment **"Spec approved"** on the draft PR.

## Reviewing code

Open the spec next to the diff. Most of this review is matching one against the other.

**Matches the spec**
- [ ] Every transition-table row appears in `nextState`, **in the same order**.
- [ ] Every row has a test named after it (`t1_...`, `t2_...`), and the test checks what the row says.
- [ ] Constants (volts, amps, limits) match the spec's numbers and have unit-suffixed names (`STALL_CURRENT_AMPS`).
- [ ] Bindings in `RobotContainer` match the spec's Operator controls table.
- [ ] Nothing extra: no features, files, or "improvements" the spec didn't ask for.

**Follows the pattern** (compare with `subsystems/exampleroller/`)
- [ ] `Goal` is public; `State` is not.
- [ ] `nextState` is pure: it uses only its arguments. No hardware calls, no `Timer`, no random numbers, no fields.
- [ ] Commands only call `setGoal(...)`. A command that sets motor voltage is wrong (SysId is the only exception).
- [ ] Every hardware read is in the IO class's `updateInputs`, and every value is a field of the `@AutoLog` inputs class.
- [ ] `periodic()` logs `Goal`, `State`, and `TransitionReason`.
- [ ] One command factory class per subsystem, in `first.robot.commands`.

**2027 correctness** (the mistakes AI makes most)
- [ ] Imports are `org.wpilib.*`. Any `edu.wpi.first` means 2026-era code was pasted.
- [ ] `org.wpilib.command2` (Commands v2), not `command3`.
- [ ] No `SmartDashboard`, `SendableChooser`, or `Shuffleboard`.
- [ ] No `Timer.getFPGATimestamp()`; use `Timer.getTimestamp()`.
- [ ] No `Thread.sleep`, `while` loops waiting on hardware, or other blocking calls. `periodic()` runs every 20 ms and must return quickly.

**Hardware (once real IO exists)**
- [ ] New CAN IDs and ports are in [`docs/robot-io.md`](../robot-io.md), and no ID is used twice on the same bus.
- [ ] Current limits are set on the motor controller, not only in our code.
- [ ] Brake/coast mode and inversion are set on purpose, with a comment saying why.

**Process**
- [ ] CI (`build`) is green.
- [ ] The PR template is filled in honestly: checks actually run, AI assistance disclosed.
- [ ] No test was deleted, skipped, or loosened. If a test changed, the spec changed first and was re-approved.
- [ ] Nothing was deployed or run on a real robot by an agent.
- [ ] The student explained the change to you: which spec row each piece implements, and how they checked it.

## Reviewing a harness change

For changes to `AGENTS.md`, `.github/`, `.specify/`, `docs/framework/`, or the reference example (CODEOWNERS makes these mentor-reviewed):

- [ ] The [phase plan](../framework/phase-plan.md) item it implements has had its trigger fire, or the change is a fix.
- [ ] It doesn't loosen a safety rule, a test, CI, or an approval requirement. Watch for this in particular: Copilot code review reads instructions **from the PR's own branch**, so a PR can weaken its own review.
- [ ] A decision that changed has a new entry in [`decisions.md`](../framework/decisions.md).
- [ ] The [harness guide](../framework/harness-guide.md) and phase plan are updated if the change adds or moves a file.
- [ ] The PR says how the change was tested (for example, "asked Copilot X in a fresh chat; it loaded the new instructions file and did Y").

## Red flags: stop and ask

- A test was deleted or its expected value changed in the same PR as the code it tests.
- `chat.tools.terminal.autoApprove` gained a `true` entry, or a deny rule was removed.
- Code reads a sensor or calls a motor outside the IO class.
- A number in the code has no matching number in the spec.
- The student can't say which spec row a block of code implements.
