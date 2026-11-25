# Hand-off Context: How We Got Here

Summary of the planning conversation (Perplexity Computer, FIRST Robotics project, October 27, 2026) that produced this framework. Read this to understand why the framework looks the way it does.

## Goal
Build agentic tools for **coding, testing, and managing** our FRC robot code, using GitHub, VS Code, and an AI assistant, following **spec-driven development**. Build it as a framework first, then one piece at a time, without generating large amounts of code up front.

## Timeline of decisions

| # | Step | Outcome |
|---|---|---|
| 1 | Initial research: what other teams use, which harness, setup, next steps | Framework v1 recommended **Claude Code** in VS Code, **GitHub Spec Kit**, **frc-mcp** for 2027 API grounding, sim/log verification, Claude PR review in GitHub Actions. Found that Claude consumer accounts require 18+, so v1 had a split model: Claude for mentors, Copilot Student for under-18 students. |
| 2 | State machine vs. command-based | Compared pure state machine, Commands v2 + state machines (hybrid), and Commands v3. **Chose the hybrid on Commands v2.** v3 is new, still changing, has little training data, and official PathPlanner doesn't support it yet. |
| 3 | Framework update | Added Section 4 (architecture) with Goal/State subsystems, superstructure, thin commands, IO layers. Set **Kickoff weekend** as the v3 go/no-go date, with four criteria. |
| 4 | Debugging across stages | Defined debugging for development, simulation, mechanical bring-up, and real hardware. Agent never moves hardware; replay is the backbone for real-robot debugging. |
| 5 | AdvantageKit from day one | **Decided.** Added determinism rules, replay regression tests in CI, log fixtures in Git LFS, bring-up checklists. |
| 6 | Switch to Copilot for everyone | **Decided, to keep it simple.** Rewrote the framework around Copilot: AGENTS.md + Copilot instructions, prompt files, skills, custom agents, hooks, MCP in `.vscode/mcp.json`, Copilot code review via rulesets. Added a credit budget playbook, because Copilot Student has a small AI-credit allowance. Claude Code moved to "later upgrades." |
| 7 | Hand-off to VS Code | This bundle. |

## Key reasoning to preserve

- **2027 is a breaking-change year.** Systemcore replaces the roboRIO, packages move to `org.wpilib.*`, Java 25. AI models default to 2026-era code, so **grounding with frc-mcp is required**, and every agent must verify APIs.
- **Why the hybrid architecture is AI-friendly:**
  - Spec state tables map directly to enums.
  - Transitions are unit-testable.
  - Goal/state logs make behavior checkable.
  - Thin commands avoid the command-interruption bugs AI tends to introduce.
  - Commands v2 keeps PathPlanner and SysId working.
- **Why replay matters:** AdvantageKit replay lets us re-run a real match against new code about 50x faster than real time, add logging after the fact, and turn field bugs into regression tests. That's where agents add the most value on real hardware.
- **Why the credit playbook exists:**
  - Copilot Student has auto model selection only and a small credit allowance (reported as about 200 credits a month).
  - Completions are free; chat, agent mode, and review cost credits.
  - So students write specs by hand and use completions, mentors run long agent sessions, and PR review runs when a PR is ready.
- **Why mentors own agent config:** Copilot code review reads instructions from the PR's branch, so a PR could weaken its own review. CODEOWNERS protects `AGENTS.md` and `.github/`.
- **Safety principles:**
  - Agents never deploy and never write NetworkTables values.
  - No breakpoints on an enabled robot.
  - Bring-up follows a checklist with the robot on blocks.
  - Humans approve every merge.

## Things that were considered and set aside
- **Pure state machine (no commands):** loses easy PathPlanner/SysId integration (2910's experience).
- **Commands v3 now:** too early; revisit at Kickoff.
- **Claude Code as primary:** strong tool (most FRC agentic examples use it), but the 18+ restriction split the team. Deferred; our files are portable if we add it for mentors later.
- **WPILib-native logging instead of AdvantageKit:** rejected because replay is central to the debugging plan.

## Where we are now
- Framework written. **No Build Pieces started.**
- Next: Build Piece 1 (AI policy + governance) and Build Piece 2 (constitution + spec templates, tested on a roller intake spec with its transition table and bring-up checklist).
- Off-season. As of November 10, 2026, about 8.5 weeks to Kickoff (January 9, 2027).
- November 10 refresh: Copilot code review moved to mentor-requested Lite reviews (D-013), Spec Kit pinned to v1.4.0 in skills mode (D-014), buy Systemcore Nov 18 (D-010), Alpha 8 baseline with Beta 1 upgrade day planned, setup moved to `setup-guide.md`.

## Facts to re-verify periodically
These change often; check before relying on them for a decision.
- Copilot plan features, AI-credit allowances, and which features Copilot Student includes
- Current WPILib 2027 alpha/beta version and vendor compatibility
- PathPlanner Commands v3 support status
- frc-mcp coverage of the current WPILib release
- Systemcore known issues (debug mode, CAN)
