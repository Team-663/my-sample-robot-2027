# AGENTS.md

Standing rules for AI agents (GitHub Copilot and others) working in this repository. This is the **starter version**; Build Piece 3 expands it. The full framework is in [`docs/framework/agentic-dev-framework.md`](docs/framework/agentic-dev-framework.md).

## Project
- FIRST Robotics Competition robot code, **2027 season** (game: BIOCORE, kickoff January 9, 2027).
- Java, **WPILib 2027** on the **Systemcore** controller. WPILib 2027 is alpha; pin to the version in `build.gradle`.
- We follow **spec-driven development** with GitHub Spec Kit. Specs in `specs/` are the source of truth.

## Hard rules
1. **2027 APIs only.** Packages are `org.wpilib.*`, not `edu.wpi.first.*`. Java 25. Verify unfamiliar APIs with the `frc` MCP server before using them. If you can't verify an API, say so instead of guessing.
2. **Commands v2 only** (`org.wpilib.command2`). Do not import Commands v3 (`command3`) unless `docs/framework/decisions.md` says we've switched.
3. **Architecture** (details in the framework, Section 4):
   - Each subsystem has a public `Goal` enum and a private `State` enum. `periodic()` computes State from Goal + sensor inputs, applies **safe defaults first**, then sets outputs.
   - A single **superstructure** owns robot-level state, legal transitions, and keep-out rules.
   - **Commands only set goals.** They never drive motors directly (SysId routines are the only exception).
   - Subsystem logic and IO classes never import command classes.
   - All hardware reads go through AdvantageKit `@AutoLog` IO inputs (real / sim / replay implementations).
4. **Determinism:** use `Timer.getTimestamp()`, never FPGA timestamps; no randomness in logic; nothing unlogged may drive decisions.
5. **Logging:** every subsystem logs goal, state, and transition reason every loop.
6. **Tests:** every transition-table row in a spec gets a unit test in `src/test/java/frc/robot/transitions/`.
7. **Units everywhere.** Use WPILib units types or unit-suffixed names (`positionMeters`, `velocityRps`).
8. **2027 API pitfalls** (Alpha 7/8 changes; models often get these wrong):
   - No SmartDashboard, SendableChooser, Sendable, or Shuffleboard. Use AdvantageKit `Logger`, WPILib Telemetry/Tunables, and `Selectable` (or AdvantageKit's chooser).
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
- Never enable Agent Merge or auto-approve terminal commands.

## How to work
- **Spec Kit commands are skills:** `/speckit-specify`, `/speckit-plan`, `/speckit-tasks`, `/speckit-implement`, `/speckit-converge`, and `/speckit-bug-*` for bugs (hyphen, not dot).
- **Stay scoped.** Work one Spec Kit task (or one Build Piece step) at a time. Credits are limited for students.
- **Don't dump code.** When designing framework pieces, propose the structure and get agreement before generating large files.
- **Read before writing.** Check `docs/framework/decisions.md` and the relevant spec before changing anything.
- **Cite sources** when you add research or rule claims to docs, using links from `docs/research/` where possible.
- **Mark AI-assisted work.** PRs with AI-generated code must tick the AI-assistance box in the PR template (FIRST attribution policy).

## Key docs
- Framework: `docs/framework/agentic-dev-framework.md`
- Decisions: `docs/framework/decisions.md`
- Build Piece tracker: `docs/framework/build-pieces.md`
- How we got here: `docs/framework/handoff-context.md`
- Research sources: `docs/research/`
