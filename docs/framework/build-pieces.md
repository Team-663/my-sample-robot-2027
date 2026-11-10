# Build Piece Tracker

Status values: **Not started**, **In progress**, **Done**, **Blocked**. Update this file whenever a piece changes status. Details for each piece are in the framework, Section 11.

| # | Piece | Status | Target | Depends on |
|---|---|---|---|---|
| 1 | AI policy + governance | Not started | Early Nov | — |
| 2 | Constitution + FRC spec templates | Not started | Early Nov | 1 |
| 3 | Copilot instructions + guardrails | Not started | Mid Nov | 2 |
| 4 | CI pipeline | Not started | Mid Nov | 3 |
| 5 | Copilot code review setup | Not started | Late Nov | 3, 4 |
| 6 | Verification and debugging loop | Not started | Late Nov–Dec | 4 |
| 7 | Skills + custom agents | Not started | Dec | 3, 6 |
| 8 | Pilot feature + v3 comparison | Not started | Dec | 2–7 |
| 9 | Student onboarding | Not started | Before Kickoff | 8 |
| 10 | Kickoff playbook | Not started | Before Kickoff | 8 |

---

## Piece details

### 1. AI policy + governance
- **Outputs:**
  - `docs/ai-policy.md`
  - `.github/CODEOWNERS` (mentors own `AGENTS.md`, `.github/`, `docs/architecture.md`)
  - `.github/pull_request_template.md` with an AI-assistance checkbox
  - Ruleset settings written down
  - `docs/copilot-credits.md`
- **Done when:**
  - Coach/school has approved the policy.
  - The ruleset is active on `main`: PR + 1 approval, no force push, and Code Owners. Required CI checks are added after Piece 4, per the setup guide. Copilot automatic review stays off (D-013).
  - A test PR shows the template.

### 2. Constitution + FRC spec templates
- **Outputs:**
  - `.specify/memory/constitution.md`
  - `docs/architecture.md`
  - Spec Kit spec/plan/tasks templates with the FRC fields (transition table, bring-up checklist)
  - `docs/robot-io.md` format
  - `docs/debugging.md`
- **Done when:**
  - The roller intake spec is written with the template.
  - A mentor and the mech lead have reviewed it.
  - Template fixes are folded back in.

### 3. Copilot instructions + guardrails
- **Outputs:**
  - Full `AGENTS.md` and `.github/copilot-instructions.md`
  - Path-specific `*.instructions.md` for subsystems, commands, tests, review
  - `.github/hooks/` (deny deploy, force-push, NT writes; format + compile after edits)
  - Terminal approval rules in `.vscode/settings.json`
  - frc-mcp in `.vscode/mcp.json`
- **Done when:**
  - Asking Copilot to deploy is blocked by the hook.
  - Edits trigger format + compile.
  - The smoke test reports 2027 / `org.wpilib` / `command2`.

### 4. CI pipeline
- **Outputs:**
  - `.github/workflows/build.yml`: build, transition tests, headless sim scenarios, replay regression with LFS checkout, replay determinism check
  - `.github/workflows/copilot-setup-steps.yml` (Java 25 + WPILib)
- **Done when:**
  - CI is green on an empty project.
  - A deliberately broken transition test fails CI.
  - The replay determinism check passes.

### 5. Copilot code review setup
- **Outputs:**
  - `.github/instructions/review.instructions.md`
  - Default review effort set to Lite; manual-request process documented (D-013)
  - 3 test PRs (good, safety violation, architecture violation), with credits used per review recorded
- **Done when:** a Lite review flags the safety and architecture violations in the test PRs, and the cost per review is logged.

### 6. Verification and debugging loop
- **Outputs:**
  - Sim scenario format
  - Replay + `replayWatch` workflow doc
  - Log MCP chosen and configured (D-012)
  - Fixture format (include event name and match number in the note; AdvantageKit alpha-7 logs omit them)
  - Spec Kit `bug` extension wired to fixtures (`.specify/bugs/<slug>/`)
  - Bring-up checklist generator
  - SysId-to-sim process
- **Done when:**
  - One sim scenario and one replay regression test run in CI.
  - Copilot answers a question about a sample log through the log tool.

### 7. Skills + custom agents
- **Outputs:**
  - Skills in `.github/skills/` with an `frc-` prefix (so they don't collide with Spec Kit's `speckit-*`): `frc-new-subsystem`, `frc-add-transition`, `frc-tune-mechanism`, `frc-build-auto`, `frc-log-triage`, `frc-replay-investigate`, `frc-bug-to-fixture`
  - Read-only agents in `.github/agents/`: `spec-reviewer`, `api-verifier`, `log-investigator`
- **Done when:** each skill has been run once successfully, and the read-only agents can't edit files.

### 8. Pilot feature + v3 comparison
- **Outputs:**
  - Roller intake taken from Specify to Converged on v2, with credits used recorded
  - The same spec on a throwaway v3 branch
  - A D-003 scorecard
- **Done when:** the intake passes tests, sim, and (if hardware exists) the bring-up checklist, and the scorecard is filled in.

### 9. Student onboarding
- **Outputs:** 2-session workshop covering:
  - Copilot Student setup and credit-smart habits
  - Writing specs
  - Reading agent diffs and explaining changes
  - AdvantageScope and replay
  - Bring-up safety
- **Done when:** every programming student has Copilot Student active and has written one spec section.

### 10. Kickoff playbook
- **Outputs:**
  - Week-1 process: game analysis, then roadmap, then first specs
  - v2/v3 go/no-go checklist
  - Build-season credit plan
- **Done when:** mentors have reviewed it and it's scheduled for Kickoff weekend.
