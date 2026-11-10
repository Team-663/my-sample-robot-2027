# Agentic Tooling Research Sources

Every source used to build the agentic development framework, grouped by topic. Original sources were checked October 27, 2026; the November 10, 2026 refresh is in its own section at the end. Facts about plans, pricing, alphas, and vendor support change quickly; re-verify before relying on them, and update the "Checked" date when you do.

General FRC design, build, strategy, and software resources are in [`frc-design-build-strategy-resources.md`](frc-design-build-strategy-resources.md).

---

## What other FRC teams are doing with AI

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| Chief Delphi: "How has your team used AI in programming?" | Team workflows (4414 AI-first + state machines, 177/429 log analysis, explain-before-merge), AGENTS.md conventions, common complaints, 18+ account issue | https://www.chiefdelphi.com/t/how-has-your-team-used-ai-in-programming/525406 | 2026-10-27 |
| Chief Delphi: "How is your team using AI/LLMs for robot code in 2026?" (page 2) | Automated AI PR review with human sign-off, cost data | https://www.chiefdelphi.com/t/how-is-your-team-using-ai-llms-for-robot-code-in-2026/513448?page=2 | 2026-10-27 |
| Chief Delphi: 254 "The Next Revolution: AI in FRC" (2026 Championship conference) | Agents running sims and reading logs in a closed loop | https://www.chiefdelphi.com/t/2026-championship-conference-presentation-the-next-revolution-ai-in-frc-by-254/519529 | 2026-10-27 |
| Chief Delphi: (Unofficial) Claude AI Toolkit for FRC 2026–2027 | Talon 540 / [un]labs spec-first toolkit targeting WPILib 2027 | https://www.chiefdelphi.com/t/unofficial-claude-ai-toolkit-for-frc-2026-2027/525039 | 2026-10-27 |
| Chief Delphi: Navigating AI Provider Terms of Service | Age and terms-of-service issues for AI tools on FRC teams | https://www.chiefdelphi.com/t/navigating-ai-provider-terms-of-service/525122 | 2026-10-27 |

## Architecture references (state machines vs. commands)

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| Chief Delphi: Team 2910 code release 2026 | Moved to state machines; lost easy PathPlanner use; early state diagrams helped | https://www.chiefdelphi.com/t/team-2910-code-release-2026/521778 | 2026-10-27 |
| Chief Delphi: FRC 360 build thread (Open Alliance) | State machines everywhere except the PathPlanner drivebase | https://www.chiefdelphi.com/t/frc-360-the-revolution-2026-build-thread-open-alliance/510290 | 2026-10-27 |
| Team 10219 REBUILT code | Goal enum + derived State enum; commands only call `setGoal()` | https://github.com/Team10219/REBUILT | 2026-10-27 |
| WPILib: Commands v3 overview | v3 is new for 2027, Java only | https://docs.wpilib.org/en/latest/docs/software/commandbased/commands-v3/index.html | 2026-10-27 |
| WPILib: Commands v3 state machines | Built-in `StateMachine` where states wrap commands | https://docs.wpilib.org/en/latest/docs/software/commandbased/commands-v3/state-machines.html | 2026-10-27 |
| Gray Matter (5712) PathPlanner workshop | Official 2027 PathPlannerLib builds on Commands v2; v3 via community fork | https://www.frc5712.com/pathplanner | 2026-10-27 |
| PathPlanner repo (Actions / PRs) | v3 support in progress as an open PR | https://github.com/mjansen4857/pathplanner/actions | 2026-10-27 |

## 2027 control system and WPILib

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| FIRST: 2026 Systemcore pricing and availability | All FRC teams use Systemcore in 2027 | https://www.firstinspires.org/community-blog/2026-systemcore-pricing-and-availability | 2026-10-27 |
| WPILib releases | 2027 alpha status, breaking changes between alphas | https://github.com/wpilibsuite/allwpilib/releases/ | 2026-10-27 |
| WPILib: New for 2027 | Yearly changelog for Systemcore era | https://docs.wpilib.org/zh-cn/latest/docs/yearly-overview/yearly-changelog.html | 2026-10-27 |
| YAMS: Upgrading 2026 → 2027 | `org.wpilib.*` packages, Java 25, renamed types | https://yams.yassrobotics.com/documentation/upgrading-from-2026-to-2027 | 2026-10-27 |
| YAMS MIGRATION.md | Commands v2 lives at `org.wpilib.command2` | https://github.com/Yet-Another-Software-Suite/YAMS/blob/master/MIGRATION.md | 2026-10-27 |
| WPILib SystemcoreTesting | Quick start, pinned alpha, vendor compatibility, telemetry tools | https://github.com/wpilibsuite/SystemcoreTesting | 2026-10-27 |
| SystemcoreTesting: AdvantageKit notes | AdvantageKit setup on Systemcore | https://github.com/wpilibsuite/SystemcoreTesting/blob/main/AdvantageKit.md | 2026-10-27 |
| SystemcoreTesting issues | Debug mode leaves program unbootable until normal deploy | https://github.com/wpilibsuite/SystemcoreTesting/issues | 2026-10-27 |
| SystemcoreTesting #342 | CAN receive drops at high bus utilization (improved in prerelease) | https://github.com/wpilibsuite/SystemcoreTesting/issues/342 | 2026-10-27 |
| SystemcoreTesting #425 | REV SparkSim MAXMotion doesn't follow profile in sim | https://github.com/wpilibsuite/SystemcoreTesting/issues/425 | 2026-10-27 |

## Grounding and log tools for agents

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| frc-mcp | MCP server covering WPILib 2026/2027, CTRE, REV, PathPlanner, PhotonVision, AdvantageKit; API verification. Endpoint: `https://mrkaynak.com/frc/mcp` | https://github.com/fikretyukselit/frc-mcp | 2026-10-27 |
| ClaudeScope | CLI + skills (`/scope`, `/simulate`, `/replay`) for `.wpilog` and live NetworkTables | https://github.com/rylero/ClaudeScope | 2026-10-27 |
| Triple Helix 2363: wpilog-mcp | MCP server for WPILib logs, built on DataLogReader | https://team2363.org/publications/2026-03-25-wpilog-mcp-model-context-protocol-server-for-wpilib-logs/ | 2026-10-27 |

## Logging, replay, and debugging

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| AdvantageKit: Non-deterministic data sources | Determinism rules (`Timer.getTimestamp()`, IO layers); test replay regularly | https://docs.advantagekit.org/getting-started/common-issues/non-deterministic-data-sources/ | 2026-10-27 |
| AdvantageKit: Replay Watch | `replayWatch` re-runs replay on save | https://docs.advantagekit.org/getting-started/replay-watch/ | 2026-10-27 |
| AdvantageKit: Traditional replay | Replay setup; modified outputs can't affect replayed inputs | https://docs.advantagekit.org/getting-started/traditional-replay/ | 2026-10-27 |
| AdvantageKit: Log replay comparison | Deterministic replay is ~50x real time | https://docs.advantagekit.org/theory/log-replay-comparison/ | 2026-10-27 |
| AdvantageScope Lite | Browser-based log viewer on Systemcore's web interface | https://docs.advantagescope.org/more-features/advantagescope-lite/ | 2026-10-27 |
| WPILib: Downloading data logs | DataLogTool SFTP from Systemcore | https://docs.wpilib.org/en/latest/_sources/docs/software/telemetry/datalog-download.rst.txt | 2026-10-27 |
| CTRE Phoenix 6: Signal logging | High-rate `.hoot` CAN signal logging | https://v6.docs.ctr-electronics.com/en/latest/docs/api-reference/api-usage/signal-logging.html | 2026-10-27 |

## Spec-driven development

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| GitHub Spec Kit (repo) | Install (`uv tool install specify-cli`), Copilot integration | https://github.com/github/spec-kit | 2026-10-27 |
| Spec Kit docs | Overview of the workflow | https://github.github.com/spec-kit/ | 2026-10-27 |
| Spec Kit quickstart | Constitution → Specify → Clarify → Plan → Tasks → Analyze → Implement → Converge | https://github.github.com/spec-kit/quickstart.html | 2026-10-27 |
| Spec Kit: Spec of specs | Roadmap pattern for ordering features | https://github.github.com/spec-kit/concepts/spec-of-specs.html | 2026-10-27 |

## GitHub Copilot: plans, credits, and eligibility

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| GitHub Docs: Plans for Copilot | Free/Student/Pro/Pro+/Business features, credits, auto model selection, Student excludes third-party agents, teacher free Pro, CLI in all plans | https://docs.github.com/en/copilot/get-started/plans | 2026-10-27 |
| GitHub: Copilot plans & pricing | What consumes AI credits (agent mode, review, cloud, CLI) | https://github.com/features/copilot/plans | 2026-10-27 |
| GitHub Docs: Usage-based billing for individuals | Student = unlimited completions + credit allowance, auto model only | https://docs.github.com/en/copilot/concepts/billing-and-usage/individuals/billing | 2026-10-27 |
| GitHub Docs: Models and pricing | Code review billed in AI credits plus Actions minutes | https://docs.github.com/en/copilot/reference/copilot-billing/models-and-pricing | 2026-10-27 |
| roboin.io report | Student allowance reported as ~200 AI credits/month (GitHub doesn't publish the number) | https://roboin.io/article/en/2026/06/25/github-copilot-free-and-student-plans-limited-to-auto-model-selection/ | 2026-10-27 |
| GitHub Education: Students | Copilot Student for verified students | https://github.com/education/students | 2026-10-27 |
| GitHub Docs: Copilot for students | How students activate Copilot Student | https://docs.github.com/en/copilot/how-tos/copilot-on-github/set-up-copilot/enable-copilot/set-up-for-students | 2026-10-27 |

## GitHub Copilot: customization and review

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| VS Code: Agent customization overview | Instructions, skills, prompt files, custom agents, hooks, MCP | https://code.visualstudio.com/docs/agent-customization/overview | 2026-10-27 |
| VS Code: Prompt files | `.prompt.md` slash commands | https://code.visualstudio.com/docs/agent-customization/prompt-files.md | 2026-10-27 |
| VS Code: Agent skills | `SKILL.md` in `.github/skills/` (also reads `.claude/skills/`); open standard | https://code.visualstudio.com/docs/agent-customization/agent-skills | 2026-10-27 |
| VS Code: Custom agents | `.agent.md` role-limited agents | https://code.visualstudio.com/docs/agent-customization/custom-agents.md | 2026-10-27 |
| VS Code: Hooks | `preToolUse` / `postToolUse` lifecycle hooks | https://code.visualstudio.com/docs/agent-customization/hooks.md | 2026-10-27 |
| VS Code: Hooks reference | Hook config format, allow/ask/deny decisions | https://code.visualstudio.com/docs/agents/reference/hooks-reference.md | 2026-10-27 |
| VS Code: Agent best practices | `/init` to generate starter instructions | https://code.visualstudio.com/docs/agents/best-practices.md | 2026-10-27 |
| GitHub Docs: Copilot code review concepts | Which instruction files review uses | https://docs.github.com/en/copilot/concepts/agent-capabilities/agent-powered-workflows-and-operations/code-review | 2026-10-27 |
| GitHub Docs: Using Copilot code review | Review reads instructions from the PR head branch | https://docs.github.com/en/copilot/how-tos/copilot-on-github/use-copilot-agents/copilot-code-review | 2026-10-27 |
| GitHub Docs: Configure code review | Ruleset option for automatic review (we keep it off, D-013) | https://docs.github.com/en/copilot/how-tos/copilot-on-github/set-up-copilot/configure-code-review | 2026-10-27 |
| GitHub Docs: Customize the agent environment | `copilot-setup-steps.yml` for cloud agent and code review | https://docs.github.com/en/copilot/how-tos/copilot-on-github/customize-copilot/customize-cloud-agent/customize-the-agent-environment | 2026-10-27 |

## Policy

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| FIRST: Submitted awards / AI guidance | AI use allowed with proper credit, attribution, and IP respect | https://www.firstinspires.org/resources/library/frc/submitted-awards | 2026-10-27 |

## Deferred: Claude (for a possible later mentor upgrade)

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| Claude Help Center: Get started | Claude consumer services require users 18+ | https://support.claude.com/en/articles/8114491-get-started-with-claude | 2026-10-27 |
| Anthropic: Guidelines for organizations serving minors | Safeguards required when minors use API-based products | https://support.claude.com/en/articles/9307344-responsible-use-of-anthropic-s-models-guidelines-for-organizations-serving-minors | 2026-10-27 |
| Claude Code: GitHub Actions | `/install-github-app`, PR review workflow | https://code.claude.com/docs/en/github-actions | 2026-10-27 |


## November 10, 2026 refresh

| Source | What it tells us | URL | Checked |
|---|---|---|---|
| FIRST: 2026 Systemcore pricing and availability | Sales open Nov 18, 2026; 4 GB $550, one per team; one in every Kickoff Kit | https://www.firstinspires.org/community-blog/2026-systemcore-pricing-and-availability | 2026-11-10 |
| FIRST: Game and season | BIOCORE presented by Haas; Kickoff Jan 9, 2027 | https://www.firstinspires.org/programs/frc/game-and-season | 2026-11-10 |
| Chief Delphi: 2027 Event & Robot Rules Preview Part 2 | Relays removed, servo power devices, new legal devices; rules not final; no software rules previewed | https://www.chiefdelphi.com/t/frc-blog-2027-event-robot-rules-preview-part-2/525524 | 2026-11-10 |
| WPILib Alpha 8 release | Installers, Systemcore image 14+, DS Alpha 7+, Beta 1 will break vendordeps, cmdv3 robot mode scopes | https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-8 | 2026-11-10 |
| WPILib Alpha 7 release | Telemetry/Tunables replace SmartDashboard; ALL_CAPS constants; CANPort; nanosecond timestamps | https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-7 | 2026-11-10 |
| Limelight: Systemcore beta lockup report | Fixed by Systemcore OS image 17+ | https://docs.limelightvision.io/reports/systemcore-beta-lockup | 2026-11-10 |
| Limelight: Systemcore eMMC failure report | Keep a USB recovery drive | https://docs.limelightvision.io/reports/systemcore-beta-emmc-failure | 2026-11-10 |
| SystemcoreTesting #439 | Phoenix logging crash on USB filesystem errors | https://github.com/wpilibsuite/SystemcoreTesting/issues/439 | 2026-11-10 |
| SystemcoreTesting #443 | Brownout logging issue | https://github.com/wpilibsuite/SystemcoreTesting/issues/443 | 2026-11-10 |
| SystemcoreTesting AdvantageKit notes | alpha-7 logs lack event name/match number; LoggedOpModeRobot | https://github.com/wpilibsuite/SystemcoreTesting/blob/main/AdvantageKit.md | 2026-11-10 |
| SystemcoreTesting AdvantageScope notes | v27.0.0-alpha-6 | https://github.com/wpilibsuite/SystemcoreTesting/blob/main/AdvantageScope.md | 2026-11-10 |
| PathPlanner PR #1183 (Commands v3) | Open, Java only, untested; v2/v3 vendordep split discussed | https://github.com/mjansen4857/pathplanner/pull/1183 | 2026-11-10 |
| Spec Kit: Copilot integration | Skills mode default; `/speckit-*` invocation; `--commands` option | https://github.github.io/spec-kit/integrations/copilot.html | 2026-11-10 |
| Spec Kit: Existing projects | `specify init --here --force --integration copilot` | https://github.github.io/spec-kit/how-to/existing-projects.html | 2026-11-10 |
| Spec Kit: Installation | Pinned install from tag; Python 3.11+ | https://github.github.io/spec-kit/installation.html | 2026-11-10 |
| GitHub Docs: Copilot plans | Student, Pro, Pro+, Max, Business; $0.01/credit overage | https://docs.github.com/en/copilot/get-started/plans | 2026-11-10 |
| GitHub Docs: Copilot code review concepts | Lite/Balanced/Max effort tiers and costs; billing to PR author or requester | https://docs.github.com/en/copilot/concepts/agent-capabilities/agent-powered-workflows-and-operations/code-review | 2026-11-10 |
| GitHub Docs: Using Copilot code review | Pick effort under Reviewers, then Request; VS Code local review | https://docs.github.com/en/copilot/how-tos/use-copilot-agents/use-code-review | 2026-11-10 |
| GitHub changelog: code review API and overview (Nov 6) | Credits shown per review session; default effort for automatic reviews | https://github.blog/changelog/2026-11-06-copilot-code-review-api-management-and-review-overview-updates/ | 2026-11-10 |
| GitHub changelog: individual sign-ups reopening (Jun 17) | Gradual reopening after the April pause | https://github.blog/changelog/2026-06-17-copilot-individual-plan-sign-ups-are-reopening/ | 2026-11-10 |
| GitHub changelog: model deprecations (mid-November) | Selected models deprecated Nov 16, 2026 | https://github.blog/changelog/2026-10-15-upcoming-deprecation-of-selected-github-copilot-models-in-mid-november/ | 2026-11-10 |
| GitHub changelog: weekly releases Nov 2 | Agent Merge, assisted approval, cron local automations | https://github.blog/changelog/2026-11-06-github-copilot-weekly-releases-november-2/ | 2026-11-10 |
| heise: Copilot Business registrations reopened | Business/Enterprise self-serve sign-up allowed again | https://www.heise.de/en/news/New-registrations-for-GitHub-Copilot-Business-and-Enterprise-allowed-again-11435256.html | 2026-11-10 |
| VS Code: Hooks | Preview; `.github/hooks/*.json`; events; deny output; not forwarded to remote sessions | https://code.visualstudio.com/docs/agent-customization/hooks | 2026-11-10 |
| VS Code: Agent customization overview | Agent plugins bundle skills, agents, hooks, MCP | https://code.visualstudio.com/docs/agent-customization/overview | 2026-11-10 |
| frc-mcp | v0.1.4; hosted HTTP server; frc_migrate with 238 rules | https://github.com/fikretyukselit/frc-mcp | 2026-11-10 |
| Chief Delphi: Turnal | Per-turn AI attribution; supports Copilot CLI; early | https://www.chiefdelphi.com/t/introducing-turnal-accountability-for-frc-s-ai-agent-era/523194 | 2026-11-10 |
| Chief Delphi: CadCove-FRC | CAD + code + physics sim in VS Code for agents | https://www.chiefdelphi.com/t/introducing-cadcove-frc-open-source-cad-code-and-simulation-in-vs-code/525377 | 2026-11-10 |
| Chief Delphi: WIP MCP for wpilog and NT4 | Another log-tool candidate for D-012 | https://www.chiefdelphi.com/t/wip-model-context-protocol-for-frc-wpilog-and-nt4/517780 | 2026-11-10 |
| Chief Delphi: ChiefDelphi and Artificial Intelligence | Credited AI use permitted; community norms | https://www.chiefdelphi.com/t/chiefdelphi-and-artificial-intelligence/523988 | 2026-11-10 |
| Chief Delphi: Introducing Doing It the Hard Way | Teams choosing to work without AI | https://www.chiefdelphi.com/t/introducing-doing-it-the-hard-way/525785 | 2026-11-10 |
