# Copilot Instructions

Follow the root `AGENTS.md` for all robot-code rules (2027 APIs, architecture, tests, safety). This file adds Copilot-specific guidance.

## Current phase
- We are in **Phase 0: writing specs and code.** The plan and the next steps are in `docs/framework/phase-plan.md`. Do not build Phase 1 tooling (hooks, sim/replay CI, `frc-*` skills, custom agents) unless the user asks for that specific item.
- For new robot behavior, use the Spec Kit skills in order: `/speckit-specify`, `/speckit-plan`, `/speckit-tasks`, `/speckit-implement`. Follow `.specify/memory/constitution.md`.
- Use `src/main/java/first/robot/subsystems/exampleroller/` and `specs/000-example-roller/spec.md` as the pattern for new subsystems and specs.

## Working credit-smart
- One Spec Kit task per agent session. Read only the files the task names; do not search the whole repo or read the full framework document.
- Let `./gradlew build` (and CI) find compile and test errors before reasoning about them.
- If a spec is vague, ask the user rather than guessing behavior, limits, or CAN ports.

## Safety
- Never deploy to the robot, write NetworkTables values on a live robot, or run actions that can move hardware. Do not run Gradle deploy tasks.
- Never commit secrets. Do not weaken safety rules, tests, or approval requirements.

## Review expectations
- Explain the purpose and scope of changes, identify assumptions, and report the checks actually run (with results).
- Do not claim a phase step is complete unless its "done" condition in the phase plan is met; distinguish completed work from approvals that are still outstanding.
