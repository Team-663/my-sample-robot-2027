# Glossary

Short definitions for words used in this repo. FRC terms first, then the words for our workflow and harness.

## Robot and FRC

| Term | Meaning |
|---|---|
| **FRC** | FIRST Robotics Competition. Each season's game is revealed at **Kickoff** (January 9, 2027; the game is BIOCORE). |
| **WPILib** | The official FRC software library and VS Code install. We use **WPILib 2027** (still alpha). Its Java packages start with `org.wpilib`; older years used `edu.wpi.first`. |
| **Systemcore** | The new 2027 robot controller. It replaces the roboRIO, which runs 2026-and-older code. |
| **Vendordep** | A vendor library (CTRE Phoenix 6, REVLib, AdvantageKit, PathPlanner, ...), described by a JSON file in `vendordeps/`. Install through WPILib VS Code. |
| **CAN, CAN ID, `CANPort`** | The wire network motor controllers and sensors share. Each device has an ID. In 2027, constructors take a `CANPort` enum value. All IDs are listed in `docs/robot-io.md`. |
| **Loop / periodic** | Robot code runs every 20 ms (50 times a second). Each subsystem's `periodic()` runs once per loop and must return quickly. |
| **Subsystem** | One mechanism (intake, arm, drivetrain) as a Java class. It owns its motors and sensors. |
| **Command** | Something the robot does over time, usually started by a button. In our code, commands only set a subsystem's goal. |
| **Command factory** | A class of static methods that build commands for one subsystem, such as `ExampleRollerCommands.intake(roller)`. |
| **Commands v2 / v3** | WPILib's command frameworks. We use v2 (`org.wpilib.command2`). Switching to v3 is decision D-003, decided at Kickoff. |
| **Binding** | Connects a controller button to a command, in `RobotContainer.configureBindings()`. |
| **Enabled / disabled** | Whether the robot is allowed to move. The Driver Station (or the Sim GUI) enables it. Disabled always means zero output. |
| **Teleop / autonomous** | Match periods: drivers control the robot in teleop; code runs alone in autonomous ("auto"). |
| **Stall** | A motor that is powered but can't turn (jammed). Its current goes very high; it can overheat or trip a breaker. |
| **Current limit** | Maximum current (amps) a motor may draw. Set on the motor controller and checked in our state machine. |
| **Debounce** | Requiring a condition to stay true for a while before acting on it. This filters out short spikes, such as the current surge when a motor starts. |
| **Soft limit** | A software position limit that stops a mechanism before it reaches a hard stop. |
| **Brake / coast** | What a motor does at zero output: brake resists motion (holds arms), coast spins freely. |
| **Bring-up** | The first time a mechanism runs on the real robot: on blocks, low power, a hand on disable. Humans only. |
| **Deploy** | Copying code onto the robot. Only humans deploy; agents never do. |
| **NetworkTables (NT)** | Live key/value data shared between the robot and laptops. Agents may read it, never write it on a live robot. |
| **SysId** | WPILib tool that measures a mechanism to tune its control constants. |
| **PathPlanner / Choreo** | Tools for planning autonomous drive paths. |

## Our architecture

| Term | Meaning |
|---|---|
| **Goal** | What the rest of the robot asks a subsystem to do (`INTAKE`). A public enum; commands set it. |
| **State** | What the subsystem is actually doing (`HOLDING`, `STALLED`). A non-public enum, computed every loop. |
| **Transition table** | The spec's list of rules for moving between states. Checked top to bottom; first match wins. Each row has a logged reason. |
| **`nextState`** | The pure function that implements the transition table. It uses only its inputs, so each row can be unit-tested. |
| **Safe state / safe default** | What a mechanism does when anything is wrong (disabled, stalled, sensor lost). Usually zero output. |
| **Superstructure** | One class that coordinates several mechanisms and enforces **keep-outs** (positions where they'd collide). We'll add it when a second mechanism with a keep-out exists. |
| **IO layer** | The interface between a subsystem and hardware: `XxxIO` (interface), `XxxIOSim` (simulation), and later `XxxIOTalonFX` or similar (real). The subsystem never touches hardware directly. |
| **AdvantageKit** | Logging library. Records every input and output so a match can be **replayed** exactly. `@AutoLog` generates the logging code for an inputs class. |
| **Replay** | Re-running the robot code on a laptop with inputs from a log file, to see exactly why it did something. |
| **`.wpilog`** | A log file. Open it in **AdvantageScope**, the log viewer. |
| **Simulation (sim)** | Running the robot code on a laptop with simulated hardware. The **Sim GUI** lets you enable the robot and use a gamepad. |

## Workflow and harness

| Term | Meaning |
|---|---|
| **Harness** | Everything that guides and checks AI-assisted work here: instructions, templates, the reference example, CI, and review rules. See the [harness guide](framework/harness-guide.md). |
| **Spec** | `specs/NNN-name/spec.md`: what a feature does, written before the code. The source of truth. |
| **Spec Kit** | GitHub's spec-driven toolkit. Gives Copilot the `/speckit-*` commands (`specify`, `plan`, `tasks`, `implement`). |
| **Plan / tasks** | `plan.md` (how we'll build the spec) and `tasks.md` (small numbered steps), written by Copilot and checked by a student. |
| **Constitution** | `.specify/memory/constitution.md`: the principles every spec and plan must follow. Mentors own it. |
| **Agent mode** | Copilot Chat mode where Copilot edits files and proposes terminal commands. You approve each command. |
| **Credits** | Copilot usage allowance. Student plans have few, so: one task per chat, short prompts. |
| **Skill** | A folder with a `SKILL.md` that teaches Copilot a multi-step procedure, run with `/name` in chat. The `speckit-*` skills come from Spec Kit. |
| **Instructions file** | Text Copilot reads automatically: `AGENTS.md` and `.github/copilot-instructions.md` every time; `.github/instructions/*.instructions.md` only for matching files. |
| **MCP server** | A tool server Copilot can call. Ours is `frc` (frc-mcp), which checks 2027 WPILib APIs. |
| **Hook** | A script VS Code runs before or after an agent's tool call; it can block it. Planned for Phase 1 to hard-block deploys. |
| **CI** | Continuous integration: GitHub Actions runs `./gradlew build` on every PR. The check is called `build`. |
| **Required check** | A CI check that must pass before a PR can merge. |
| **CODEOWNERS** | File that makes mentors required reviewers for harness files. |
| **Phase 0 / Phase 1** | Phase 0: the minimum needed to write specs and code (now). Phase 1: tooling added only when a real need (trigger) appears. See the [phase plan](framework/phase-plan.md). |
| **Decision (D-0NN)** | An entry in [`decisions.md`](framework/decisions.md) recording what we chose and why. |
