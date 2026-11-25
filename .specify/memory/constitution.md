# Team 663 Robot Code Constitution (2027)

Every spec, plan, task list, and change in this repository must comply with these principles. `/speckit-plan` checks them in its Constitution Check gate. `AGENTS.md` holds the day-to-day agent rules and 2027 API pitfalls; this file holds the principles those rules come from.

## Core Principles

### I. Spec Is the Source of Truth
Behavior is designed in `specs/<###-feature>/spec.md` before code is written. Code that disagrees with an approved spec is a bug, unless the spec is changed first and re-reviewed. Specs describe goals, states, transitions, limits, and failure modes, not command classes or implementation detail. A mentor and the mechanical lead review each mechanism spec before planning starts.

### II. Safety First (NON-NEGOTIABLE)
- Every mechanism has a documented **safe state** and **soft limits** in its spec. The safe state is applied by default every loop, before any other output.
- Disabled robot means zero output; over-limit (current, position, speed) means safe state.
- Elevators, arms, climbers, and superstructure keep-outs get extra review.
- AI agents **never deploy** to the robot, **never write** NetworkTables values on a live robot, and never run anything that can move hardware. Humans deploy, following the bring-up checklist.

### III. Explicit State Machines on Commands v2
- Each subsystem has a public `Goal` enum and a non-public `State` enum. `periodic()` computes State from Goal plus logged inputs, applies safe defaults, then sets outputs.
- The transition logic is a pure function (no hardware, no time, no randomness) so every row can be unit-tested.
- One superstructure owns robot-level state, legal transitions, and keep-out rules.
- Commands only set goals and never drive motors (SysId is the only exception). Each subsystem has one command factory class. Subsystem logic and IO classes never import command classes.
- Commands v2 (`org.wpilib.command2`) only, until decision D-003 says otherwise.
- Reference implementation: `src/main/java/first/robot/subsystems/exampleroller/`.

### IV. Logged, Deterministic, Replayable
- AdvantageKit from day one (D-004). All hardware reads go through `@AutoLog` IO inputs with real, sim, and replay implementations.
- Every subsystem logs goal, state, and transition reason every loop.
- Use `Timer.getTimestamp()`, never FPGA timestamps. No randomness in logic. Nothing unlogged drives a decision.

### V. Tested Before Merge
- Every transition-table row in a spec has a unit test next to its subsystem under `src/test/java/first/robot/subsystems/<name>/`.
- CI (`./gradlew build`) must pass before merge. Tests are never deleted or weakened to make CI pass; a failing test means fix the code or change the spec first.
- Sim and replay evidence are added as Phase 1 tooling lands.

### VI. Current APIs and Units
- WPILib 2027 (`org.wpilib.*`, Java 25) and the vendordeps pinned in `vendordeps/`. Verify unfamiliar APIs with frc-mcp or a compile; never guess.
- Units everywhere: WPILib units types or unit-suffixed names (`positionMeters`, `velocityRps`, `currentAmps`).
- CAN devices are recorded in `docs/robot-io.md` with their `CANPort`.

## Constraints

- **Credits are scarce.** Students write specs and transition tables by hand, then use one agent pass to tighten them. One agent session per task.
- **Simplicity.** Only build what the current spec needs. New framework tooling waits for its phase in `docs/framework/phase-plan.md`.
- **No secrets** in the repository. AI assistance is disclosed in every PR (FIRST attribution).

## Development Workflow

1. `/speckit-specify` (student-drafted, with mech/elec input) → mentor + mech lead review.
2. `/speckit-plan` → plan review.
3. `/speckit-tasks` → small, ordered tasks.
4. `/speckit-implement`, one task per session → CI green.
5. Student explains the change to a mentor → human approval → merge.

`/speckit-clarify`, `/speckit-analyze`, `/speckit-checklist`, and `/speckit-converge` are optional; use them when a spec is unclear or code may have drifted from it.

## Governance

This constitution overrides other practices in this repository. Mentors own it (CODEOWNERS). Amendments go through a PR with mentor approval, a version bump, and a matching entry in `docs/framework/decisions.md`. Versioning: MAJOR for removed or redefined principles, MINOR for added principles, PATCH for wording.

**Version**: 1.0.0 | **Ratified**: TODO(RATIFICATION_DATE): set when mentors approve this PR | **Last Amended**: 2026-11-25
