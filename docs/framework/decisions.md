# Decision Log

Record every framework decision here with a date and rationale. When a decision changes, add a new entry that supersedes the old one rather than deleting it, and update the framework doc to match.

Status values: **Accepted**, **Open**, **Superseded**.

---

## D-001: Spec-driven development with GitHub Spec Kit
- **Status:** Accepted, 2026-10-27
- **Decision:** Use Spec Kit (Constitution, Specify, Clarify, Plan, Tasks, Implement, Converge) adapted with FRC-specific spec fields.
- **Why:** Specs give the agent a clear target, give students ownership of behavior design, and give reviewers something to check code against.

## D-002: Commands v2 + Goal/State state machines
- **Status:** Accepted, 2026-10-27
- **Decision:** Subsystems use Goal/State enums with safe defaults; one superstructure coordinates; commands only set goals; IO layers for hardware.
- **Why:**
  - Specs map one-to-one to code.
  - Transitions can be unit-tested.
  - Fewer agent-introduced command bugs.
  - Official PathPlanner and SysId support.
- **See:** Framework Section 4.

## D-003: Commands v3 switch — decide at Kickoff
- **Status:** Open (decision date: Kickoff weekend, January 9–10, 2027)
- **Owner:** Software mentor + programming lead
- **Switch only if all hold:**
  1. Official PathPlannerLib (and Choreo, if used) supports v3.
  2. CTRE, REV, AdvantageKit, and vision libraries work with v3 on our pinned WPILib.
  3. The v3 pilot needed no more agent rework than v2, and students read it as easily.
  4. Copilot's v3 code passes frc-mcp verification without repeated fixes.
- **Evidence source:** Build Piece 8 pilot.
- **Status check 2026-11-10:** PathPlanner PR #1183 (v3, Java only) is open and active but untested; maintainers discussing separate v2/v3 vendordeps. Criterion 1 not met. https://github.com/mjansen4857/pathplanner/pull/1183

## D-004: AdvantageKit logging and replay from day one
- **Status:** Accepted, 2026-10-27
- **Decision:** AdvantageKit for all logging; deterministic replay as the main real-robot debugging tool; replay regression tests in CI; curated log fixtures in Git LFS.
- **Why:** Replay turns field bugs into reproducible, testable cases, and it's where agents add the most value with real hardware.

## D-005: GitHub Copilot for everyone (to start)
- **Status:** Accepted, 2026-10-27. Supersedes the earlier Claude Code (mentors) + Copilot Student (students) split.
- **Decision:** Students on Copilot Student; mentors on Copilot Pro (free for verified teachers, otherwise $10/month). Copilot agent mode in VS Code, Copilot code review on PRs.
- **Why:** One tool, one set of config files, covers students 13+; keeps setup simple.
- **Trade-offs accepted:** limited student credits, auto model selection only on Student.
- **Revisit if:** students regularly run out of credits (consider Copilot Business), or mentors need capabilities Copilot lacks (consider adding Claude Code for mentors, see Framework Section 14).

## D-013: Copilot code review is mentor-requested, Lite by default
- **Status:** Accepted, 2026-11-10. Supersedes the "automatically request Copilot review" ruleset setting in D-005 and the October framework.
- **Decision:** Automatic review stays off. A mentor requests a review on ready, CI-green PRs at Lite effort (~$0.25). Balanced (~$1.50) only for elevator, arm, climber, superstructure, or keep-out changes. Max is not used. Credits per review are logged in `docs/copilot-credits.md`.
- **Why:** Review now has effort tiers; automatic reviews bill the PR author and manual requests bill the requester. One Balanced review would use most of a Copilot Student's monthly allowance.
- **Revisit if:** Team-663 moves to Copilot Business (org-billed review for members).
- **Sources:** https://docs.github.com/en/copilot/concepts/agent-capabilities/agent-powered-workflows-and-operations/code-review , https://docs.github.com/en/copilot/how-tos/use-copilot-agents/use-code-review

## D-014: Pin Spec Kit v1.4.0 (skills mode) and add the bug extension
- **Status:** Accepted, 2026-11-10
- **Decision:** Install with `uv tool install specify-cli --from git+https://github.com/github/spec-kit.git@v1.4.0`. Copilot integration in skills mode (`.github/skills/speckit-*`, invoked as `/speckit-specify`). Add `specify extension add bug` for `/speckit-bug-assess|fix|test`, with reports in `.specify/bugs/<slug>/`.
- **Why:** Skills are the Copilot default in v1.4.0; pinning avoids surprise changes mid-season; the bug extension fits our bug-to-fixture loop.

## D-006: Agents never move hardware
- **Status:** Accepted, 2026-10-27
- **Decision:** No agent deploys code, writes NetworkTables values, or runs anything that moves the robot. Enforced with hooks and terminal approval rules, not only instructions.

---

## Open decisions
| ID | Question | Notes |
|---|---|---|
| D-003 | Commands v3 switch | See above |
| D-007 | Repo visibility: public or private? | Recommended public (matches past Team-663 repos, no Actions-minute limits). Check the 2027 manual on Jan 9 for any pre-Kickoff code rule. |
| D-008 | Mentor Copilot plans | Who qualifies for free teacher Pro; who needs a paid seat |
| D-009 | Credit escalation threshold | What usage level triggers Copilot Business or sponsored seats |
| D-010 | Systemcore hardware before Kickoff? | **Recommended: buy one when sales open Nov 18, 2026** ($550, 4 GB, one per team). Image 17+, keep a USB recovery drive. |
| D-011 | Keep-out rule ownership | Who in mechanical signs off on superstructure keep-outs |
| D-015 | `LoggedOpModeRobot` vs. `LoggedRobot` | AdvantageKit alpha-7 added `LoggedOpModeRobot`; decide at Kickoff with D-003 |
| D-012 | Log tool for agents | wpilog-mcp (MCP, native to Copilot) or ClaudeScope CLI + skills; decide in Piece 6 |
