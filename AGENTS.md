# AGENTS.md

Standing rules for AI agents (GitHub Copilot and others) working in this repository. We are in **Phase 0: writing specs and code** ([`docs/framework/phase-plan.md`](docs/framework/phase-plan.md)). Principles are in the [constitution](.specify/memory/constitution.md); background design is in [`docs/framework/agentic-dev-framework.md`](docs/framework/agentic-dev-framework.md) (read only the section you need).

## Project
- FIRST Robotics Competition robot code, **2027 season** (game: BIOCORE, kickoff January 9, 2027).
- Java, **WPILib 2027** on the **Systemcore** controller. WPILib 2027 is alpha; pin to the version in `build.gradle`.
- Robot code package is `first.robot` (`src/main/java/first/robot/`).
- We follow **spec-driven development** with GitHub Spec Kit. Specs in `specs/` are the source of truth.

## Hard rules
1. **2027 APIs only.** Packages are `org.wpilib.*`, not `edu.wpi.first.*`. Java 25. Verify unfamiliar APIs with the `frc` MCP server or by compiling (`./gradlew build`) before relying on them. If you can't verify an API, say so instead of guessing.
2. **Commands v2 only** (`org.wpilib.command2`). Do not import Commands v3 (`command3`) unless `docs/framework/decisions.md` says we've switched.
3. **Architecture.** Copy the shape of the reference subsystem in `src/main/java/first/robot/subsystems/exampleroller/` (spec: `specs/000-example-roller/spec.md`). File list and steps: `docs/guides/add-a-mechanism.md`.
   - Each subsystem has a public `Goal` enum and a non-public `State` enum. `periodic()` reads inputs, computes State from Goal + logged inputs with a **pure `nextState` function**, applies **safe defaults first**, then sets outputs.
   - Current- or sensor-based fault checks are **debounced** with a small pure counter function (see `countOverCurrentLoops`), never a raw threshold.
   - A single **superstructure** owns robot-level state, legal transitions, and keep-out rules. It doesn't exist yet; it's added when a second mechanism has a keep-out.
   - **Commands only set goals.** One command factory class per subsystem in `first.robot.commands`. Commands never drive motors directly (SysId routines are the only exception).
   - Subsystem logic and IO classes never import command classes.
   - All hardware reads go through AdvantageKit `@AutoLog` IO inputs (real / sim / replay implementations). The robot class is `LoggedRobot`.
4. **Determinism:** use `Timer.getTimestamp()`, never FPGA timestamps; no randomness in logic; nothing unlogged may drive decisions.
5. **Logging:** every subsystem logs goal, state, and transition reason every loop.
6. **Tests:** every transition-table row in a spec gets a unit test in the subsystem's test package, `src/test/java/first/robot/subsystems/<name>/`. Never delete or weaken a test to make CI pass.
7. **Units everywhere.** Use WPILib units types or unit-suffixed names (`positionMeters`, `velocityRps`, `currentAmps`).
8. **2027 API pitfalls** (Alpha 7/8 changes; models often get these wrong):
   - No SmartDashboard, SendableChooser, Sendable, or Shuffleboard. Use AdvantageKit `Logger`, WPILib Telemetry/Tunables, and `Selectable` (or AdvantageKit's chooser).
   - Enable and mode queries (`isEnabled`, `isAutonomous`, `isTeleop`, ...) are on `org.wpilib.driverstation.RobotState`, not `DriverStation`.
   - Constants and enum values are ALL_CAPS.
   - CAN device constructors take a `CANPort` enum. Record the port in `docs/robot-io.md`.
   - Raw integer timestamps are nanoseconds (DataLog/NT files still use microseconds).
   - AprilTag and CameraServer are vendordeps; `AprilTagFields` is now part of `Fields`. `Alert` lives in wpiutil.
   - Gamepad face buttons use directional names (`faceUp/Down/Left/Right`) and have a default deadband.
   - Phoenix 5 does not exist on Systemcore. Phoenix 6 only.
   - Run `frc_migrate` (frc-mcp) on any code copied from 2026 or older.

## Safety (non-negotiable)
- **Never deploy to the robot.** Do not run `deploy` Gradle tasks or WPILib "Deploy Robot Code."
- **Never write NetworkTables values** on a live robot. Reading is fine.
- Every mechanism has a **safe state** and **soft limits** from its spec. Elevators, arms, and climbers get extra care.
- Never commit secrets.
- Never enable Agent Merge or auto-approve terminal commands. Do not add `true` entries to `chat.tools.terminal.autoApprove`.

## How to work
- **Workflow:** `/speckit-specify` → `/speckit-plan` → `/speckit-tasks` → `/speckit-implement` (hyphen, not dot). `/speckit-clarify` and `/speckit-converge` are optional. Details: [`phase-plan.md`](docs/framework/phase-plan.md#day-to-day-workflow-phase-0).
- **Stay scoped.** One Spec Kit task per agent session. Credits are limited for students.
- **Build, then stop.** Run `./gradlew build` after code changes and report the result. CI runs the same command.
- **Don't build Phase 1 tooling early.** Hooks, sim/replay CI, skills, and agents wait for their trigger in the phase plan.
- **Don't dump code.** For framework changes, propose the structure and get agreement before generating large files.
- **Read before writing.** Check the relevant spec and `docs/framework/decisions.md` before changing behavior.
- **Cite sources** when you add research or rule claims to docs, using links from `docs/research/` where possible.
- **Mark AI-assisted work.** PRs with AI-generated code must tick the AI-assistance box in the PR template (FIRST attribution policy).

## Key docs
- Start page: `README.md`
- Phase plan (what to do now): `docs/framework/phase-plan.md`
- How to add a mechanism: `docs/guides/add-a-mechanism.md`
- How to change the harness: `docs/framework/harness-guide.md`
- Constitution: `.specify/memory/constitution.md`
- Decisions: `docs/framework/decisions.md`
- IO map: `docs/robot-io.md`
- Framework background: `docs/framework/agentic-dev-framework.md`
- Research sources: `docs/research/`
