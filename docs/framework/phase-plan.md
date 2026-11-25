# Phase Plan

Replaces the 10-step Build Piece sequence (archived in [`archive/build-pieces.md`](archive/build-pieces.md)). Decision: [D-016](decisions.md#d-016-phase-0-simplification-build-pieces-replaced-by-phases). Goal: write specs and robot code **now**, and add tooling only when the work needs it.

Kickoff is **January 9, 2027**.

## Phase 0 — Ready to write specs (now)

| # | Step | Status | Where |
|---|------|--------|-------|
| 0.1 | Constitution filled in from AGENTS.md and framework §4 | Done, pending mentor ratification | `.specify/memory/constitution.md` |
| 0.2 | FRC fields in the spec template (Spec Kit override, survives upgrades) | Done | `.specify/templates/overrides/spec-template.md` |
| 0.3 | Reference subsystem: Goal/State/IO, `LoggedRobot`, `@AutoLog`, transition tests | Done | `src/main/java/first/robot/subsystems/exampleroller/`, `specs/000-example-roller/` |
| 0.4 | Terminal safety: never auto-approve deploy, force-push, or NT commands | Done | `.vscode/settings.json` |
| 0.5 | CI: `./gradlew build` (compile + all tests) on every PR | Workflow added | `.github/workflows/build.yml` |
| 0.6 | Make `build` a required status check on `main` | **Needs a repo admin** | Setup guide Part 6, Phase 3 |
| 0.7 | AI policy approved by coach and school (gates **student** use only) | **Needs coach/school** | `docs/ai-policy.md` |
| 0.8 | Confirm the `@Team-663/mentors` team slug in CODEOWNERS | **Needs an org owner** | `.github/CODEOWNERS` |
| 0.9 | First practice spec (roller intake), written by students with the template | Not started | `/speckit-specify` |

**Phase 0 is done when** a mentor-reviewed practice spec exists, CI is required on `main`, and the constitution is ratified.

### Day-to-day workflow (Phase 0)

1. Draft the Robot Behavior section by hand (goals, states, transition table, limits, failure modes). Look at `specs/000-example-roller/spec.md` first.
2. `/speckit-specify` → mentor + mech lead review the spec PR.
3. `/speckit-plan` → `/speckit-tasks`.
4. `/speckit-implement`, **one task per agent session**. Copy the shape of `ExampleRoller`.
5. CI green → student explains the change to a mentor → human approves and merges.

Optional steps: `/speckit-clarify` when the spec has open questions, and `/speckit-converge` when code may have drifted from the spec. Each Spec Kit skill is a large prompt, so skipping optional steps saves student credits.

## Phase 1 — Add when the work needs it (build season)

Pull an item forward only when a real spec or bug needs it. Each one gets its own small PR.

| Item | Trigger to start it | Old piece |
|------|--------------------|-----------|
| Real IO implementations + `robot-io.md` entries | First mechanism plan names its motors | 2 |
| Superstructure class | Second mechanism with a keep-out | 2 |
| `docs/architecture.md`, `docs/debugging.md` | Second subsystem merged | 2 |
| Copilot hooks (deny deploy / NT writes / force-push; compile after edit) | Before students run agent mode unsupervised | 3 |
| Path-specific `*.instructions.md` (subsystems, commands, tests) | Same mistake shows up in 2+ agent PRs | 3 |
| Sim scenario tests in CI | First mechanism with multi-step behavior | 4, 6 |
| Replay regression tests, Git LFS log fixtures, determinism check | First real-robot log worth keeping | 4, 6 |
| `replayWatch` task and git-hash log metadata | First replay investigation | 6 |
| Log tool for agents (D-012) | First log investigation a mentor wants agent help with | 6 |
| Spec Kit `bug` extension wired to fixtures | First field bug | 6 |
| Copilot code review instructions + effort policy check | Review volume makes manual review a bottleneck | 5 |
| `copilot-setup-steps.yml` + cloud agent | A mentor wants to hand off scoped issues | 4, 14 |
| `frc-*` skills and read-only custom agents | The same multi-step prompt has been typed 3+ times | 7 |
| Student onboarding sessions | Before students start agent work | 9 |
| Kickoff playbook | Week before Kickoff | 10 |

## Dropped

- **Commands v3 pilot on a throwaway branch (old Piece 8).** D-003 is decided at Kickoff on criteria 1–2 (official PathPlanner/Choreo and vendor support). Criteria 3–4 are waived unless v3 support lands.
- **Watch-list tools** (Turnal, CadCove-FRC, agent plugins, local automations). Revisit after the season.
- **OpenCode as the team harness.** See [D-017](decisions.md#d-017-opencode-not-adopted-as-the-team-harness).
