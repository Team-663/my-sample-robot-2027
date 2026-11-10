# FRC Agentic Development Framework (2027 Season)

Team 663 Power Sharks · Version 3 · Updated November 10, 2026

This is the design for our agentic tools for writing, testing, and managing robot code. We use **GitHub, VS Code, and GitHub Copilot**, and we follow spec-driven development. Each Build Piece in Section 11 gets built in its own follow-up session.

Setup instructions are in a separate document: **[FRC 663 Agentic Dev Setup Guide (2027)](frc-setup-guide.md)**.

**Assumptions**
- Java, WPILib 2027 (Alpha 8 today, Beta 1 next) on Systemcore.
- GitHub Copilot for everyone. Students use Copilot Student and mentors use Copilot Pro.
- Commands v2 with explicit state machines (Section 4). A switch to Commands v3 stays open if official support lands in time (Section 4.3).
- AdvantageKit logging and replay from day one, with AdvantageScope as the viewer (Section 9).
- Repo `Team-663/2027`, with the robot project at the repo root.
- Everything we write for Copilot (AGENTS.md, skills, specs) uses open formats, so another agent can be added later (Section 14).

Today is November 10, 2026. Kickoff is January 9, 2027, which leaves about 8.5 weeks.

---

## What Changed in This Version

| Area | Change | Why |
|---|---|---|
| Copilot code review | **No more automatic review on every PR.** Mentors request reviews by hand at Lite effort. Balanced effort is only for safety-critical PRs. | Reviews now have effort tiers that cost about $0.25 (Lite), $1.50 (Balanced), or $10 (Max) each. Automatic reviews are billed to the PR author ([GitHub Docs](https://docs.github.com/en/copilot/concepts/agent-capabilities/agent-powered-workflows-and-operations/code-review)). One Balanced review would use most of a student's month. |
| Spec Kit | Pinned to **v1.4.0**. Commands now install as **skills** and run as `/speckit-specify`, with a hyphen instead of a dot. The `bug` extension is added for debugging. | Skills became the default for the Copilot integration ([Spec Kit Copilot docs](https://github.github.io/spec-kit/integrations/copilot.html)). |
| Systemcore | Buy one on **November 18**. Run OS image 17 or newer. Keep a USB recovery drive. | Sales open November 18 at $550 for the 4 GB unit, one per team ([FIRST](https://www.firstinspires.org/community-blog/2026-systemcore-pricing-and-availability)). Image 17 fixes a lockup ([Limelight report](https://docs.limelightvision.io/reports/systemcore-beta-lockup)). Pre-production units have a storage failure risk ([Limelight report](https://docs.limelightvision.io/reports/systemcore-beta-emmc-failure)). |
| WPILib | Alpha 8 is the baseline. Plan one team-wide upgrade day for Beta 1. AGENTS.md gets a list of 2027 API pitfalls. | Alpha 8 is a quality-of-life release, and Beta 1 will break vendordeps ([Alpha 8 notes](https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-8)). Alpha 7 replaced SmartDashboard with Telemetry and Tunables, among other changes ([Alpha 7 notes](https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-7)). |
| Commands v3 | Still on v2. PathPlanner's v3 pull request is active but untested. | See Section 4.3 ([PR #1183](https://github.com/mjansen4857/pathplanner/pull/1183)). |
| Copilot features | Agent Merge stays off. Assisted approval stays off for anything that touches the robot. Model choices are re-checked after the November 16 deprecations. | New features in the [November 2 release](https://github.blog/changelog/2026-11-06-github-copilot-weekly-releases-november-2/) and the [deprecation notice](https://github.blog/changelog/2026-10-15-upcoming-deprecation-of-selected-github-copilot-models-in-mid-november/). |
| Watch list | Added Turnal (tracks which AI turn wrote each line) and CadCove-FRC. | Possible tools for attribution and simulation (Section 14). |

---

## 1. The Recommendation

| Layer | Choice | Why |
|---|---|---|
| Harness | **GitHub Copilot agent mode** in the WPILib VS Code | One tool for everyone. Verified students 13+ get it free ([GitHub Docs](https://docs.github.com/en/copilot/how-tos/copilot-on-github/set-up-copilot/enable-copilot/set-up-for-students)). |
| Agent rules | **`AGENTS.md`** + `.github/copilot-instructions.md` + path-specific `*.instructions.md` | Copilot reads all three in VS Code and in PR review. AGENTS.md also works with other AI tools. |
| Robot architecture | **Commands v2 + state machines.** Each subsystem has Goal/State enums, one superstructure coordinates, and commands only set goals. | Specs map directly to enums and transitions. Keeps official PathPlanner support. Fewer hidden scheduler bugs. |
| Spec-driven process | **GitHub Spec Kit v1.4.0** in skills mode | `/speckit-specify`, `/speckit-plan`, `/speckit-tasks`, `/speckit-implement`, `/speckit-converge`, each with a review gate ([Spec Kit](https://github.github.io/spec-kit/integrations/copilot.html)) |
| Grounding (current APIs) | **frc-mcp** server in `.vscode/mcp.json` | Knows the 2027 alpha APIs and includes `frc_migrate`, which has 238 rules for moving 2026 code to 2027 ([frc-mcp](https://github.com/fikretyukselit/frc-mcp)) |
| Verification | Gradle tests, WPILib sim, **AdvantageKit replay**, CTRE Signal Logger, a log tool (chosen in Piece 6) | Proves the code works in sim and against real match data, not just that it compiles |
| Code management | GitHub PRs, rulesets, CI, **mentor-requested Copilot code review** | A human approves every merge. AI review costs credits, so we use it selectively. |
| Enforcement | Copilot **hooks** (`.github/hooks/`, currently Preview) + VS Code terminal approval rules | Hard blocks (for example, no deploy) instead of trusting the model ([VS Code hooks](https://code.visualstudio.com/docs/agent-customization/hooks)) |

One rule ties it together: **the spec is the source of truth, the agent works from the spec, and evidence (tests, sim, logs) decides when it's done.**

---

## 2. What Other Teams Are Doing

| Team / source | What they do | Takeaway for us |
|---|---|---|
| 254 Cheesy Poofs | Agents run simulations and read logs in a closed loop ([Chief Delphi](https://www.chiefdelphi.com/t/2026-championship-conference-presentation-the-next-revolution-ai-in-frc-by-254/519529)) | Sim-in-the-loop is the bar to aim for |
| 4414 | AI-first development. They use state machines because AI reasons about them better, plus skill files for autos and log parsing ([Chief Delphi](https://www.chiefdelphi.com/t/how-has-your-team-used-ai-in-programming/525406)) | Architecture choice affects agent quality |
| 2910 / 360 / 10219 | 2910 dropped commands and lost easy PathPlanner use ([Chief Delphi](https://www.chiefdelphi.com/t/team-2910-code-release-2026/521778)). 360 uses state machines everywhere except the PathPlanner drivebase ([Chief Delphi](https://www.chiefdelphi.com/t/frc-360-the-revolution-2026-build-thread-open-alliance/510290)). 10219 pairs a Goal enum with a derived State, and its commands only call `setGoal()` ([GitHub](https://github.com/Team10219/REBUILT)). | The basis for our hybrid architecture |
| 177 / 429 | AI mainly for log analysis. Students walk a mentor through every AI change ([Chief Delphi](https://www.chiefdelphi.com/t/how-has-your-team-used-ai-in-programming/525406)). | Log analysis is the best place to start |
| Teams on Copilot Student | Report that the lower student usage limits cut back how much they could use the cloud agent ([Chief Delphi](https://www.chiefdelphi.com/t/how-has-your-team-used-ai-in-programming/525406)) | Plan around scarce student credits (Section 8) |
| Several teams | One central `AGENTS.md`, with a `CLAUDE.md` symlink if other tools are used. Custom skills work better than downloaded ones ([Chief Delphi](https://www.chiefdelphi.com/t/how-has-your-team-used-ai-in-programming/525406)). | Adopt this convention |
| Community debate | Some teams deliberately write everything by hand ([Doing It the Hard Way](https://www.chiefdelphi.com/t/introducing-doing-it-the-hard-way/525785)). FIRST allows AI if it's credited ([Chief Delphi](https://www.chiefdelphi.com/t/chiefdelphi-and-artificial-intelligence/523988)). | Keep the "students explain before merge" rule and a no-AI fallback at events |
| Log tools | 2363 wpilog-mcp, ClaudeScope, and an in-progress wpilog/NT4 MCP ([Triple Helix](https://team2363.org/publications/2026-03-25-wpilog-mcp-model-context-protocol-server-for-wpilib-logs/), [ClaudeScope](https://github.com/rylero/ClaudeScope), [Chief Delphi](https://www.chiefdelphi.com/t/wip-model-context-protocol-for-frc-wpilog-and-nt4/517780)) | Pick one in Piece 6 |

---

## 3. Constraints That Shape the Design

### 3.1 2027 is a breaking-change year
- Every team must use Systemcore in 2027, and every team gets a 4 GB unit in the Kickoff Kit. Extra units go on sale November 18, limited to one per team ([FIRST](https://www.firstinspires.org/community-blog/2026-systemcore-pricing-and-availability)).
- WPILib 2027 is still in alpha. Beta 1 will break vendordeps ([Alpha 8 notes](https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-8)).
- Big API changes in Alpha 7 ([Alpha 7 notes](https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-7)):
  - Packages moved to `org.wpilib.*`, and Java 25 is required.
  - SmartDashboard, SendableChooser, and Sendable were replaced by Telemetry and Tunables. The new chooser is called `Selectable`.
  - Constants and enum values are now ALL_CAPS.
  - CAN devices take a `CANPort` enum.
  - Raw timestamps are in nanoseconds.
  - AprilTag and CameraServer became vendordeps.
- Phoenix 5 isn't available on Systemcore ([CTRE notes](https://github.com/wpilibsuite/SystemcoreTesting/blob/main/CTR-Phoenix.md)).
- **What this means for us:** AI models were trained mostly on 2026-style code and will write it confidently. Grounding with frc-mcp, a list of pitfalls in AGENTS.md, and a "verify against the 2027 API" step are all required.

### 3.2 Copilot plans and AI credits
Code completions don't use credits. Chat, agent mode, code review, and the cloud agent do. Extra usage costs $0.01 per credit ([GitHub Docs](https://docs.github.com/en/copilot/get-started/plans)).

| Who | Plan | What it gives us |
|---|---|---|
| Students | **Copilot Student** (free) | Unlimited completions, a small credit allowance (GitHub doesn't publish it; reported as about 200 a month), automatic model choice only, no third-party agents ([GitHub Docs](https://docs.github.com/en/copilot/get-started/plans)) |
| Mentors who are verified teachers | Copilot Pro, free | 1,500 credits a month |
| Other mentors | Copilot Pro, $10/month | 1,500 credits a month, model choice |
| Heavy mentor use | Pro+ ($39, 7,000 credits) or Max ($100) | Only if a mentor runs long agent sessions all the time |
| Option later | Copilot Business on Team-663, $19/seat | 1,900 pooled credits per seat and central policy. Lets unlicensed org members get code review billed to the org. Self-serve sign-up has reopened ([heise](https://www.heise.de/en/news/New-registrations-for-GitHub-Copilot-Business-and-Enterprise-allowed-again-11435256.html)). |

**What this means for us:**
- **Student agent time is scarce.** Students lean on completions and save agent mode for well-scoped spec tasks. Mentors run the long sessions: refactors, sim investigations, and replay triage.
- **Code review is now a budget item.** Lite reviews cost about 25 credits and Balanced about 150. Automatic reviews are billed to the PR author, and manual requests to whoever asks ([GitHub Docs](https://docs.github.com/en/copilot/how-tos/use-copilot-agents/use-code-review)). Section 8 covers the review policy.
- **Students can't pick a model.** Auto selection decides, so good specs and strong instructions matter even more.
- **Sign-up delays happen.** Individual sign-ups were paused this spring and reopened gradually from June 17 ([GitHub changelog](https://github.blog/changelog/2026-06-17-copilot-individual-plan-sign-ups-are-reopening/)). The student benefit can take several days after verification. Get every student verified now.

### 3.3 Age and accounts
- Everyone needs their own GitHub account, and GitHub requires users to be 13 or older. Students verify through GitHub Education.
- Confirm the AI use policy with the school or host org before rollout.

### 3.4 FIRST policy and rules
- FIRST allows AI help on robot code as long as it's credited and IP and licenses are respected. We use PR labels, a PR-template checkbox, and a README statement.
- The 2027 rules previews cover hardware only so far (relays removed, servo power, new devices like SPARK Lite and Thrifty Power Hub). Rules aren't final ([Rules Preview Part 2](https://www.chiefdelphi.com/t/frc-blog-2027-event-robot-rules-preview-part-2/525524)). Software rules, including any rule about code written before Kickoff, are unverified until the manual comes out January 9.

---

## 4. Robot Code Architecture

### 4.1 Decision: Commands v2 + state machines
Commands v2 is still in WPILib 2027 as `org.wpilib.command2`. It's the backbone, and the behavior lives in explicit state machines.

| Element | Rule |
|---|---|
| Subsystem | Public `Goal` enum (what we want) and private `State` enum (what's actually happening). `periodic()` works out the state from the goal and sensors, applies **safe defaults first**, then sets the outputs. |
| Superstructure | One coordinator holds the robot-level state (IDLE, INTAKE, SCORE, CLIMB...). It owns the legal transitions and keep-out rules. |
| Commands | Thin wrappers that set goals, used for button bindings, PathPlanner named commands, and SysId. **Commands never drive motors** (SysId is the only exception). |
| Hardware layer | One IO interface per subsystem, with real, sim, and replay versions. Every hardware read goes through `@AutoLog` inputs. |
| Telemetry | AdvantageKit `Logger` logs goal, state, and transition reason for every subsystem every loop. Tunable values use AdvantageKit or WPILib Tunables, not SmartDashboard, which is gone. |
| Determinism | Use `Timer.getTimestamp()`, keep randomness out of logic, and never let unlogged data drive decisions. |
| Robot base | `LoggedRobot` with the Commands v2 scheduler. AdvantageKit alpha-7 adds `LoggedOpModeRobot`. Consider it at Kickoff along with the v3 decision. |
| Drivetrain | Command-driven so PathPlanner works. The superstructure supplies its goals (aim-lock or free drive). |

### 4.2 Why this fits the agentic workflow
- **Spec to code is one-to-one.** The state table becomes the enum, transitions become rules, and failure modes become safe states. Converge can check each one.
- **Fewer agent-introduced bugs.** Thin commands remove most interruption bugs.
- **Verifiable.** Transition rules can be unit-tested without hardware, and goal/state logs confirm behavior in sim and robot logs.

### 4.3 Open decision: switch to Commands v3?
Commands v3 adds coroutine commands and a built-in `StateMachine`. Alpha 8 added robot mode scopes ([Alpha 8 notes](https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-8)). PathPlanner's v3 pull request is open and active but untested, and it's Java only. As of November 9, maintainers were discussing separate v2 and v3 vendordeps ([PR #1183](https://github.com/mjansen4857/pathplanner/pull/1183)).

**Decision date: Kickoff weekend.** Switch only if **all** of these hold:
1. An official PathPlannerLib release (and Choreo, if we use it) supports v3. **Status as of November 10: not met.**
2. CTRE, REV, AdvantageKit, and our vision library work with v3 on our pinned WPILib.
3. The v3 pilot (Build Piece 8) needed no more agent rework than v2, and students read it at least as easily.
4. Copilot's v3 code passes frc-mcp verification without repeated fixes.

If any of these fails, stay on v2 for 2027. To keep a switch cheap, subsystem logic and IO never import command classes, every command comes from one factory per subsystem, and specs describe states, not command types.

---

## 5. Framework Architecture

```
 ┌────────────────────────────────────────────────────────────┐
 │ 6. GOVERNANCE  rulesets · CODEOWNERS · AI policy · credits  │
 ├────────────────────────────────────────────────────────────┤
 │ 5. CI / REVIEW Actions: build, tests, sim, replay;          │
 │                mentor-requested Copilot review (Lite)       │
 ├────────────────────────────────────────────────────────────┤
 │ 4. VERIFY      gradle test · WPILib sim · AdvantageKit      │
 │                replay · log tool                            │
 ├────────────────────────────────────────────────────────────┤
 │ 3. GROUND      frc-mcp (2027 API, vendordeps, migration)    │
 ├────────────────────────────────────────────────────────────┤
 │ 2. HARNESS     Copilot agent mode · hooks · custom agents · │
 │                skills · prompt files · terminal approvals   │
 ├────────────────────────────────────────────────────────────┤
 │ 1. INTENT      constitution · specs · plans · tasks ·       │
 │                AGENTS.md + Copilot instructions             │
 └────────────────────────────────────────────────────────────┘
```

### Target repo layout (`Team-663/2027`, skeleton only)

```
2027/
├── AGENTS.md                         # rules for any AI agent (architecture, 2027 pitfalls, safety)
├── .github/
│   ├── copilot-instructions.md       # Copilot-specific rules + links to docs/
│   ├── instructions/                 # path-specific rules (applyTo globs)
│   │   ├── subsystems.instructions.md
│   │   ├── commands.instructions.md
│   │   ├── tests.instructions.md
│   │   └── review.instructions.md    # Copilot code review checklist
│   ├── agents/                       # read-only custom agents (*.agent.md)
│   │   ├── spec-reviewer.agent.md
│   │   ├── api-verifier.agent.md
│   │   └── log-investigator.agent.md
│   ├── skills/
│   │   ├── speckit-*/                # installed by Spec Kit (don't hand-edit)
│   │   └── frc-*/                    # team skills: frc-new-subsystem, frc-add-transition,
│   │                                 #   frc-tune-mechanism, frc-build-auto, frc-log-triage,
│   │                                 #   frc-replay-investigate, frc-bug-to-fixture
│   ├── prompts/                      # small team prompts (build-piece, framework-status)
│   ├── hooks/                        # deny deploy / NT writes / force-push; compile after edit
│   ├── workflows/
│   │   ├── build.yml                 # build, tests, sim scenarios, replay regression
│   │   └── copilot-setup-steps.yml   # Java 25 + WPILib for code review / cloud agent
│   ├── pull_request_template.md      # includes AI-assistance checkbox
│   └── CODEOWNERS                    # mentors own AGENTS.md, .github/, .specify/, docs/architecture.md
├── .vscode/
│   ├── mcp.json                      # frc-mcp (+ log tool in Piece 6)
│   └── settings.json                 # terminal approval rules
├── .specify/
│   ├── memory/constitution.md
│   └── bugs/<slug>/                  # Spec Kit bug extension reports
├── specs/
│   ├── 000-roadmap.md
│   └── 001-intake/                   # spec.md · plan.md · tasks.md · evidence.md
├── docs/
│   ├── framework/                    # this document, setup guide, decisions, build pieces
│   ├── robot-io.md                   # CAN IDs, CANPort, sensors (single source)
│   ├── architecture.md · debugging.md · ai-policy.md · copilot-credits.md
│   └── bringup/<mechanism>.md
├── logs/fixtures/                    # curated .wpilog/.hoot + notes (Git LFS)
├── src/test/java/frc/robot/{transitions,sim,replay}/
└── src/main/java/frc/robot/{subsystems,superstructure,commands,autos}/
```

Team skills use an `frc-` prefix so they never collide with Spec Kit's `speckit-` skills.

---

## 6. Copilot Customization Map

| Copilot feature | File / location | We use it for | Notes |
|---|---|---|---|
| Agent instructions | `AGENTS.md` | Architecture, 2027 pitfalls, safety, how we work | Read in VS Code and in code review |
| Repo instructions | `.github/copilot-instructions.md` | Copilot-specific guidance, current phase | Same |
| Path instructions | `.github/instructions/*.instructions.md` | Separate rules for subsystems, commands, tests, and review | Same |
| Skills | `.github/skills/<name>/SKILL.md` | Spec Kit commands (`speckit-*`) and team workflows (`frc-*`) | Invoked with `/name` in chat |
| Prompt files | `.github/prompts/*.prompt.md` | Small team commands | |
| Custom agents | `.github/agents/*.agent.md` | Read-only reviewers and log investigator | Can also run as local automations later |
| Hooks (Preview) | `.github/hooks/*.json` | `preToolUse`: block deploy, force-push, NT writes. `postToolUse`: format and compile. | Deny by returning `permissionDecision: "deny"`. Repo hooks aren't sent to remote sessions ([VS Code hooks](https://code.visualstudio.com/docs/agent-customization/hooks)). |
| MCP servers | `.vscode/mcp.json` | frc-mcp, plus the log tool | |
| Setup steps | `.github/workflows/copilot-setup-steps.yml` | Java 25 + WPILib for code review and the cloud agent | |
| Code review | Manual request, with the default effort set to Lite | AI review on ready PRs | Section 8 |

**Important:** Copilot code review reads instructions and skills **from the PR's branch**, so a PR could weaken its own review. CODEOWNERS makes mentors required reviewers for `AGENTS.md`, `.github/`, `.specify/`, and `docs/architecture.md`.

**Features we keep off:**
- **Agent Merge**, which lets Copilot merge a PR when it's ready ([GitHub changelog](https://github.blog/changelog/2026-11-06-github-copilot-weekly-releases-november-2/)). Merging stays a human decision.
- **Assisted-approval personal instructions** for any tool that runs Gradle tasks, terminal commands, or anything touching the network or robot.
- **Auto-approve** for the terminal.

---

## 7. Spec-Driven Workflow, Adapted for FRC

The Spec Kit loop is Constitution (once), then Specify, Clarify, Plan, Tasks, Analyze, Implement, Converge. In Copilot each step is a skill: `/speckit-constitution`, `/speckit-specify`, `/speckit-clarify`, `/speckit-plan`, `/speckit-tasks`, `/speckit-analyze`, `/speckit-implement`, `/speckit-converge` ([Spec Kit Copilot docs](https://github.github.io/spec-kit/integrations/copilot.html)).

| Step | Who leads | Robot-specific content | Gate | Credit cost |
|---|---|---|---|---|
| Constitution (once) | Mentors + leads | Section 4 rules, 2027 API only, units, safe states, no agent deploys, tests required, determinism | Team sign-off | Mentor |
| Roadmap | Leads | Ordered features with dependencies | — | Low |
| Specify | **Students** with mech/elec | Goals, states, transitions, controls, limits, failure modes, telemetry, evidence, bring-up checklist. No code. | Mentor + mech lead review | Low. Draft by hand, then one pass. |
| Clarify | Agent asks, students answer | What if the sensor fails? Max extension? Brake or coast? | — | Low |
| Plan | Students + agent | Motors, vendordeps, `CANPort`, control approach, IO interfaces, enums, sim model | Plan review PR | Medium |
| Tasks | Agent | Small ordered tasks, one agent session each | — | Low |
| Implement | Student (completions + agent per task) or mentor | Code + transition tests + sim, checked with frc-mcp | CI green | **Highest** |
| Converge | Agent | Compare code to spec and add tasks for any gaps | "Converged" | Medium |
| Evidence | Students | Sim run, log screenshots, robot checklist in `evidence.md` | Student explains to a mentor | None |

**Bugs** use the Spec Kit `bug` extension: `/speckit-bug-assess`, then `/speckit-bug-fix` and `/speckit-bug-test`. Reports go in `.specify/bugs/<slug>/` and link to the log fixture (Section 9.7).

### FRC spec template fields (built in Piece 2)
1. Purpose and match role
2. Hardware: motors, controllers, sensors, CAN IDs, and CAN port (link to `robot-io.md`)
3. Goals and States, with a transition table (from, to, condition)
4. Superstructure interactions and keep-outs
5. Operator controls
6. Limits: soft limits, current limits, speed caps
7. Failure modes and the safe state for each
8. Telemetry (goal, state, and transition reason at minimum)
9. Acceptance evidence
10. Bring-up checklist
11. Out of scope

---

## 8. Credit Budget and Review Policy

### 8.1 Habits
| Do | Don't |
|---|---|
| Write specs and transition tables by hand, then use one agent pass to tighten them | Ask the agent to "write the intake" from one line |
| Keep each task small enough for one agent session | Let an agent loop on a vague task |
| Use completions (free) for routine code | Use chat for things the docs answer |
| Point the agent at specific files and skills | Make it search the whole repo every time |
| Let CI catch compile and test failures first | Use agent mode to find a typo the build log shows |
| Hand long investigations to a mentor | Spend a student's month on a log dig |

### 8.2 Copilot code review policy

| Setting | Our choice | Why |
|---|---|---|
| Automatic review ruleset | **Off** | It bills the PR author. A Balanced review costs about 150 credits, which is most of a student's month ([GitHub Docs](https://docs.github.com/en/copilot/concepts/agent-capabilities/agent-powered-workflows-and-operations/code-review)). |
| Who requests a review | A **mentor**, once the PR is ready and CI is green | Manual requests are billed to the requester, so it comes from the mentor's Pro credits |
| Default effort | **Lite** (about $0.25) | Roughly 60 Lite reviews fit in 1,500 credits if nothing else is used |
| When to use Balanced | Elevator, arm, climber, superstructure, or keep-out changes | Worth the cost where a bug could break a mechanism |
| Max | Not used | About $10 per review |
| Re-review on new pushes | Off | Avoids paying again for small fixes |

Review sessions now show how many credits they used ([GitHub changelog](https://github.blog/changelog/2026-11-06-copilot-code-review-api-management-and-review-overview-updates/)). Record them in `docs/copilot-credits.md`.

### 8.3 Tracking and escalation
Each month, mentors check usage and note in `docs/copilot-credits.md` where the credits went. If students regularly run out, or review volume outgrows mentor credits, evaluate Copilot Business on Team-663. That needs an org owner (D-009).

**Actions minutes:** CI and code review use Actions minutes on private repos. A public repo avoids that limit (D-007).

---

## 9. Debugging Across Stages

**Core principle:** debugging means finding the first point where the **spec, the intended behavior, and the actual behavior** stop matching. Every subsystem logs the same signals at every stage: IO inputs, goal, state, transition reason, setpoints, and outputs.

### 9.1 Stages at a glance
| Stage | Typical bugs | Main tools | Copilot's role | Who can move hardware |
|---|---|---|---|---|
| Development | Compile errors, 2026 APIs, wrong transitions, units | Gradle, unit tests, frc-mcp, hooks | Fix-and-test loop | Nobody |
| Simulation | Sequencing, interlocks, control behavior, autos | WPILib physics sim, AdvantageScope, headless sim tests | Runs sim tests, reads the logs | Nobody |
| Mechanical bring-up | Inversions, gear ratios, encoder offsets, CAN IDs, binding | Phoenix Tuner X Preview, REV Hardware Client 2, SysId, checklist | Read-only: compares spec to config and logs | Humans only |
| Real robot / events | Intermittent faults, brownouts, CAN, vision, loop overruns | AdvantageKit logs and replay, `.hoot`, DS logs, AdvantageScope Lite | `log-investigator` triage and replay (mentor-run) | Humans only |

### 9.2 Development
- Each transition-table row becomes a unit test.
- The agent loop is edit, compile (via hook), test, fix. frc-mcp catches old APIs.
- Breakpoints in unit tests are safe and cost no credits.

### 9.3 Simulation
- Sim tests assert behavior, for example "intake for 2 s, expect HOLDING and current under the limit." They run in CI.
- Sim proves logic, not tuning. REV MAXMotion still doesn't follow its profile in sim ([SystemcoreTesting #425](https://github.com/wpilibsuite/SystemcoreTesting/issues/425)).

### 9.4 Mechanical bring-up
- Work through the checklist one motor at a time: CAN ID and firmware, low-voltage jog and direction, encoder direction and ratio, soft limits with the robot disabled, then closed-loop at reduced speed.
- Feed SysId results back into the sim model.
- Never use breakpoints on an enabled robot.

### 9.5 Real robot and events
- Log everything: AdvantageKit (with the git hash), CTRE Signal Logger `.hoot` files, and DS logs. Alpha 8 no longer deletes match logs and moves logs to a USB drive automatically if it's plugged in late ([Alpha 8 notes](https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-8)).
- **Current alpha issues to plan around:**
  - AdvantageKit alpha-7 logs are missing the event name and match number, so record both in the fixture note ([AdvantageKit notes](https://github.com/wpilibsuite/SystemcoreTesting/blob/main/AdvantageKit.md)).
  - Phoenix logging can crash robot code when the USB filesystem has errors ([#439](https://github.com/wpilibsuite/SystemcoreTesting/issues/439)).
  - Brownout events may not be logged correctly ([#443](https://github.com/wpilibsuite/SystemcoreTesting/issues/443)).
  - Use USB 2 drives formatted as MBR.
- **Replay is the main tool:** check out the commit from the log, add outputs, replay, and use `replayWatch` while you iterate.
- **Watch CAN health.** Log bus utilization and put heavy buses on a CANivore.
- **Event fixes still go through the stages:** fix, test, replay the failing match, then a human deploys. Keep a no-AI fallback.

### 9.6 Layer triage
1. **Wrong state?** It's a logic bug. Add a transition test.
2. **Right state, wrong output?** Check tuning, units, and sign.
3. **Right output, wrong motion?** Check mechanical, wiring, inversion, and ratio.
4. **Did what the spec said, and that was wrong?** Fix the spec first.

### 9.7 Every bug improves the system
| Bug source | Permanent fix |
|---|---|
| Logic | Regression test for that transition |
| Sim gave the wrong answer | Update the sim model with measured constants |
| Field or bring-up | Log fixture in `logs/fixtures/` (LFS), a `.specify/bugs/<slug>/` report, and a replay regression test |
| Spec gap | Update the spec and re-run converge |

### 9.8 Replay regression tests
- A fixture is a `.wpilog` plus a note: what happened, the commit hash, the event and match, and the expected behavior.
- Tests assert on decisions, for example "never enters SCORING below keep-out height." Replay can't feed changed outputs back into the inputs, so closed-loop behavior is checked in sim.
- Keep fixtures curated rather than saving every match log.

---

## 10. Setup

Setup is now a separate document: **[FRC 663 Agentic Dev Setup Guide (2027)](frc-setup-guide.md)**. It runs start to finish:

accounts and Copilot → laptop installs → WPILib project and vendordeps → `Team-663/2027` repo → hand-off bundle and Spec Kit → frc-mcp → rulesets in phases → code review settings → Systemcore prep → smoke tests

---

## 11. Build Pieces (Follow-up Sessions)

Target dates are adjusted for November 10.

| # | Piece | Output | Target |
|---|---|---|---|
| 1 | **AI policy + governance** | `docs/ai-policy.md`, CODEOWNERS, PR template with AI checkbox, ruleset phase 2, `docs/copilot-credits.md` with the review policy | Nov 14 |
| 2 | **Constitution + FRC spec templates** | `/speckit-constitution` run, `docs/architecture.md`, FRC spec template, `robot-io.md` format (with CANPort), `docs/debugging.md` | Nov 21 |
| 3 | **Copilot instructions + guardrails** | Full `AGENTS.md` (2027 pitfalls), path instructions, hooks (deny deploy, NT writes, force-push; compile after edit), terminal approval rules | Nov 21 |
| 4 | **CI pipeline** | `build.yml` (build, transition tests, sim scenarios, replay regression with LFS, determinism check), `copilot-setup-steps.yml` | Nov 26 |
| 5 | **Copilot code review setup** | `review.instructions.md`, default effort set to Lite, the manual-request process, 3 test PRs with the credits each used | Dec 3 |
| 6 | **Verification and debugging loop** | Sim scenario format, replay and `replayWatch` workflow, log tool chosen (D-012), fixture format, Spec Kit `bug` extension wired to fixtures, SysId-to-sim process | Dec 10 |
| 7 | **Skills + custom agents** | `frc-*` skills and read-only agents | Dec 17 |
| 8 | **Pilot feature + v3 comparison** | Roller intake from Specify to Converged on v2 with credits recorded, the same spec on a throwaway v3 branch, the D-003 scorecard. On Systemcore hardware if we have it. | Dec 23 |
| 9 | **Student onboarding** | 2 sessions: Copilot Student, credit habits, specs, reading diffs, AdvantageScope and replay, bring-up safety | Jan 6 |
| 10 | **Kickoff playbook** | Week 1 process, v2/v3 go/no-go, LoggedOpModeRobot decision, build-season credit plan, Beta 1 upgrade plan | Jan 6 |

**Beta 1 upgrade day:** whenever Beta 1 ships, one person upgrades WPILib and all vendordeps on a branch, CI has to pass, and then everyone updates the same day.

---

## 12. Guardrails (Non-negotiable)

- **Agents never deploy to the robot.** Enforced by a hook and terminal approval rules. Humans deploy with the robot on blocks, a hand on disable, and an "enabling" call-out.
- **Agents get read-only access to the robot.** They never write NetworkTables values.
- **Every mechanism has a safe state and soft limits,** applied as the default every loop. Elevators, arms, and climbers get extra review.
- **Students explain before merge.** Every AI-assisted change gets walked through with a mentor.
- **Copilot review is advice, not approval.** A human approval is always required, and Agent Merge stays off.
- **Mentors own the agent rulebook** through CODEOWNERS.
- **No breakpoints on an enabled robot.**
- **Bring-up follows the checklist:** low voltage first, one mechanism at a time, with both mechanical and programming people present.
- **Firmware updates happen on the bench** with the wheels off the ground, and settings get re-checked afterward.
- **No secrets in the repo.** Attribution follows FIRST policy.
- **Field fallback:** the team can fix and redeploy without AI at events.

---

## 13. Decisions

### Made
- D-001 Spec-driven development with Spec Kit (now v1.4.0 in skills mode).
- D-002 Commands v2 + Goal/State state machines.
- D-004 AdvantageKit from day one, with replay regression tests in CI. AdvantageScope is the viewer.
- D-005 Copilot for everyone (Student and Pro).
- D-006 Agents never move hardware.
- **D-013 (new)** Copilot code review is mentor-requested at Lite effort, Balanced for safety-critical PRs, with automatic review off.
- **D-014 (new)** Pin Spec Kit v1.4.0 and use the `bug` extension for debugging.

### Open
| ID | Question | Recommendation |
|---|---|---|
| D-003 | Commands v3 switch | Decide at Kickoff. Criterion 1 is not met yet. |
| D-007 | Repo visibility | Public, to match past repos and avoid Actions-minute limits. Check the 2027 manual on January 9 for any rule about code written before Kickoff. |
| D-008 | Mentor plans | Verify teacher eligibility for free Pro |
| D-009 | Credit escalation | Org owner to evaluate Copilot Business if students run out or review volume grows |
| D-010 | Systemcore before Kickoff | **Buy one on November 18** to test Pieces 6 and 8 on real hardware |
| D-011 | Keep-out rule ownership | Mechanical lead signs off |
| D-012 | Log tool | Pick in Piece 6 |
| D-015 | `LoggedOpModeRobot` vs. `LoggedRobot` | Decide at Kickoff with D-003 |

---

## 14. Later Upgrades and Watch List

None of these are needed to start.
- **Copilot cloud agent:** assign a scoped issue and get a PR back. Mentor-only, once `copilot-setup-steps.yml` works.
- **Local automations:** VS Code can now run custom agents on a cron schedule. One possible use is a nightly read-only "spec drift" report.
- **Agent plugins:** package our skills, agents, hooks, and MCP into one installable team plugin ([VS Code docs](https://code.visualstudio.com/docs/agent-customization/overview)).
- **Turnal:** records which agent turn wrote which lines, which could help with attribution. Supports Copilot CLI and is still early, so it's on the watch list ([Chief Delphi](https://www.chiefdelphi.com/t/introducing-turnal-accountability-for-frc-s-ai-agent-era/523194)).
- **CadCove-FRC:** a VS Code extension that combines CAD, code, and physics sim for agents, aimed at CTRE ([Chief Delphi](https://www.chiefdelphi.com/t/introducing-cadcove-frc-open-source-cad-code-and-simulation-in-vs-code/525377)). Try it in Piece 6 if the sim fidelity holds up.
- **Another agent for mentors** (for example Claude Code): AGENTS.md, skills, specs, and MCP config carry over.

## Next Question to Ask
"What does a great spec look like for one mechanism?" Start Piece 2 with the roller intake spec, including its transition table and bring-up checklist, and record how many credits the pilot uses.
