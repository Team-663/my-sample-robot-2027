# FRC 663 Agentic Dev Setup Guide (2027)

Team 663 Power Sharks · Checked November 10, 2026 · Companion to the [FRC Agentic Development Framework (2027 Season)](agentic-dev-framework.md)

This guide goes from nothing installed to a working `Team-663/2027` repo with Copilot, Spec Kit, frc-mcp, and AdvantageKit, ready for Build Piece 1. Do the parts in order. Everything is still alpha software, so re-check versions against [SystemcoreTesting](https://github.com/wpilibsuite/SystemcoreTesting) before every install day.

| Part | Who | Time |
|---|---|---|
| 1. Accounts and Copilot | Everyone | 15 min, plus several days for student verification |
| 2. Laptop installs | Everyone | 45–60 min |
| 3. Create the robot project | One mentor | 30 min |
| 4. Create the repo on Team-663 | One mentor | 15 min |
| 5. Framework hand-off and Spec Kit | One mentor | 30 min |
| 6. Rulesets and code review settings | Repo admin (and an org owner for teams) | 20 min |
| 7. Systemcore prep | Mentor + electrical lead | 1 hour, once hardware arrives |
| 8. Smoke tests | One mentor, then each student | 30 min |
| 9. Each student's first day | Every programmer | 20 min |

---

## Part 1: Accounts and Copilot

### 1.1 GitHub accounts
1. Every programmer needs their own GitHub account, and GitHub requires users to be 13 or older. Use a personal email the student will keep, not one that expires at graduation.
2. An **org owner** of Team-663 invites everyone who isn't a member yet and adds programmers to the `robot-developers` team.
3. Ask the org owner to create a **`mentors`** team. CODEOWNERS will need it in Part 6.

### 1.2 Copilot plans
| Who | Plan | How |
|---|---|---|
| Students | Copilot Student (free) | Verify at [GitHub Education](https://github.com/education/students) with a school email or ID, then activate Copilot Student ([GitHub Docs](https://docs.github.com/en/copilot/how-tos/copilot-on-github/set-up-copilot/enable-copilot/set-up-for-students)). The benefit can take several days to appear. |
| Mentors who teach | Copilot Pro, free for verified teachers | Verify as a teacher through GitHub Education |
| Other mentors | Copilot Pro, $10/month, 1,500 credits | Sign up on the [Copilot plans page](https://docs.github.com/en/copilot/get-started/plans) |

**Start student verification today.** Individual sign-ups were paused this spring and reopened gradually ([GitHub changelog](https://github.blog/changelog/2026-06-17-copilot-individual-plan-sign-ups-are-reopening/)). Some students still report activation trouble, so give yourself time to fix problems before onboarding.

### 1.3 AI use policy
Write a one-page draft of the policy (Build Piece 1 finalizes it) and get sign-off from the coach and the school. It should include FIRST attribution, "students explain before merge," and the rule that agents never deploy.

---

## Part 2: Laptop Installs (Every Programmer)

### 2.1 Check the laptop
| Requirement | Detail |
|---|---|
| Operating system | 64-bit Windows 11, macOS 15 or later, or Ubuntu 26.04 ([Alpha 8 notes](https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-8)) |
| Mac only | Run `xcode-select --install` first |
| Disk | About 3 GB to download, plus room to install |
| 2026 install | Leave it in place. Each WPILib year installs separately, and 2026 robots still need it. |

### 2.2 WPILib 2027 Alpha 8
Download the installer for your machine from the [Alpha 8 release](https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-8):
- [Windows x64](https://packages.wpilib.workers.dev/installer/v2027.0.0-alpha-8/WPILib_Windows-x64-2027.0.0-alpha-8.iso) · [Windows Arm64](https://packages.wpilib.workers.dev/installer/v2027.0.0-alpha-8/WPILib_Windows-Arm64-2027.0.0-alpha-8.iso)
- [macOS Apple Silicon](https://packages.wpilib.workers.dev/installer/v2027.0.0-alpha-8/WPILib_macOS-Arm64-2027.0.0-alpha-8.dmg) · [macOS Intel](https://packages.wpilib.workers.dev/installer/v2027.0.0-alpha-8/WPILib_macOS-Intel-2027.0.0-alpha-8.dmg)
- [Linux x64](https://packages.wpilib.workers.dev/installer/v2027.0.0-alpha-8/WPILib_Linux-x64-2027.0.0-alpha-8.tar.gz) · [Linux Arm64](https://packages.wpilib.workers.dev/installer/v2027.0.0-alpha-8/WPILib_Linux-Arm64-2027.0.0-alpha-8.tar.gz)

**Windows**
1. Right-click the `.iso` and choose **Mount** (or extract it with 7-Zip).
2. Run `WPILibInstaller.exe`. If you see "Windows protected your PC," choose **More info**, then **Run anyway**.
3. Choose **Start**, then **Everything**, then **Install for this User**. Use **all Users** instead on shared team laptops with admin rights.
4. Choose **Download for this computer only**, click **Next**, then **Finish**.

**macOS**
1. Open the `.dmg`, then open `WPILibInstaller`.
2. Choose **Start**, then **Everything**, then **Download for this computer only**, then **Next**, then **Finish**.
3. Drag the 2027 WPILib VS Code to the Dock, then eject the disk.

**Linux**
```sh
tar -xf WPILib_Linux-x64-2027.0.0-alpha-8.tar.gz
cd WPILib_Linux-x64-2027.0.0-alpha-8/
./WPILibInstaller
```

**Shared laptops:** on the first machine, choose **Create VS Code archives** so the others can install offline from a USB drive.

Always open the **2027 WPILib VS Code**, not your regular VS Code and not the 2026 one.

### 2.3 Git, Git LFS, and GitHub CLI
| OS | Command |
|---|---|
| Windows | `winget install --id Git.Git -e` then `winget install --id GitHub.GitLFS -e` then `winget install --id GitHub.cli -e` |
| macOS | `brew install git git-lfs gh` |
| Ubuntu | `sudo apt install git git-lfs gh` |

Then, on every OS:
```sh
git lfs install
gh auth login          # choose GitHub.com, HTTPS, log in with a browser
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

### 2.4 GitHub Copilot in WPILib VS Code
1. Open the 2027 WPILib VS Code.
2. In Extensions, install **GitHub Copilot Chat** if it isn't already there.
3. Click the Accounts icon (bottom left) and sign in with GitHub. Use the same account that has Copilot Student or Pro.
4. Open Chat (Ctrl+Alt+I, or Cmd+Ctrl+I on Mac) and confirm that **Agent** mode is available.

### 2.5 Tuning and log tools
| Tool | Version | Where | Who |
|---|---|---|---|
| AdvantageScope | v27.0.0-alpha-6 or newer | [AdvantageScope releases](https://github.com/Mechanical-Advantage/AdvantageScope/releases), first `v27.0.0-alpha-X` ([notes](https://github.com/wpilibsuite/SystemcoreTesting/blob/main/AdvantageScope.md)) | Everyone |
| Phoenix Tuner X **Preview** | Latest preview | Required to put 26.70.X firmware on CTRE devices ([CTRE notes](https://github.com/wpilibsuite/SystemcoreTesting/blob/main/CTR-Phoenix.md)) | Electrical + one programmer |
| REV Hardware Client 2 | 1.4.4 or newer | [REV notes](https://github.com/wpilibsuite/SystemcoreTesting/blob/main/REV.md). SPARK firmware 27.0.0 for YAMS. | Electrical + one programmer |
| FIRST Driver Station | 2027 Alpha 7 or newer | Linked from [SystemcoreTesting](https://github.com/wpilibsuite/SystemcoreTesting) | Drive laptop |
| Elastic (optional) | 2027 alpha | [Elastic releases](https://github.com/Gold872/elastic-dashboard/releases). Now also runs in a browser. | Drive team |

### 2.6 Python runtime (every programmer)
The checked-in Spec Kit skills run Python scripts directly; for example, `/speckit-specify` invokes `python3`. Every programmer must install Python 3.11 or newer and confirm that the exact `python3` command works in the terminal inside WPILib VS Code. This is separate from the Spec Kit CLI, which students do not need.

| OS | Install Python 3.11+ |
|---|---|
| Windows 11 | Install the [Python install manager](https://www.python.org/downloads/) with `winget install 9NQ7512CXL7T -e`, then install a runtime with `py install 3.11`. When prompted, add the global aliases directory (default `%LocalAppData%\Python\bin`) to `PATH`, then open a new terminal. |
| macOS | `brew install python` |
| Ubuntu | `sudo apt install python3` |

Verify in a new terminal:
```sh
python3 --version
```
It must report Python 3.11 or newer. On Windows, if `python3` is not recognized, add `%LocalAppData%\Python\bin` to your user `PATH`, reopen the terminal, and retry. Do not proceed until this exact command works; the skills invoke `python3` directly. See the [official Windows Python setup guide](https://docs.python.org/3/using/windows.html) for Python install manager details.

### 2.7 Spec Kit CLI (mentors and software leads; optional for students)
The CLI is for initializing/updating Spec Kit, not for running the checked-in Copilot skills. Students need Python from Part 2.6 but do not need `uv` or the `specify` CLI.

```sh
# Windows (PowerShell)
powershell -ExecutionPolicy ByPass -c "irm https://astral.sh/uv/install.ps1 | iex"
# macOS / Linux
curl -LsSf https://astral.sh/uv/install.sh | sh

# All: pin Spec Kit v1.4.0
uv tool install specify-cli --from git+https://github.com/github/spec-kit.git@v1.4.0
specify --version
```
Installation details are in the [Spec Kit docs](https://github.github.io/spec-kit/installation.html). Students don't need the CLI, because the slash commands live in the repo.

---

## Part 3: Create the Robot Project (One Mentor)

1. In the 2027 WPILib VS Code, open the command palette (Ctrl+Shift+P) and run **WPILib: Create a new project**.
2. Choose **Template**, then **Java**, then **Command Robot** (Commands v2). Set the base folder to wherever you keep repos, the project name to **`2027`**, and the team number to **663**. Turn on **Desktop Support**.
3. Run **WPILib: Build Robot Code**, then **WPILib: Simulate Robot Code**. Both should succeed before you go on.
4. Open `build.gradle` and confirm the GradleRIO version is `2027.0.0-alpha-8`.

### 3.1 Vendordeps
Install them from the **WPILib sidebar → Vendor Dependencies** list, or run **WPILib: Manage Vendor Libraries → Install new libraries (online)** and paste the URL. Build while you're online, then commit everything in `vendordeps/`.

**Core (install now)**
| Library | Version | URL | Notes |
|---|---|---|---|
| CTRE Phoenix 6 | 26.70.0-alpha-2 | VS Code list ("CTRE-Phoenix (v6)"), or `https://raw.githubusercontent.com/wpilibsuite/vendor-json-repo/main/2027_alpha7/Phoenix6-26.70.0-alpha-2.json` | Choose the normal entry, not Phoenix Replay. Phoenix 5 isn't available. |
| REVLib | 2027.0.0-alpha-9 | `https://software-metadata.revrobotics.com/REVLib-2027.json` | Newer than the compatibility table and fixes read timeouts ([REV notes](https://github.com/wpilibsuite/SystemcoreTesting/blob/main/REV.md)) |
| AdvantageKit | 27.0.0-alpha-7 | `https://github.com/Mechanical-Advantage/AdvantageKit/releases/download/v27.0.0-alpha-7/AdvantageKit.json` | Follow the 2027 skeleton for both `build.gradle` and runtime setup: use `LoggedRobot`, record build metadata, configure data receivers and replay, and call `Logger.start()`. Add a `WPILOGWriter` in SIM mode for smoke test 5 ([AdvantageKit notes](https://github.com/wpilibsuite/SystemcoreTesting/blob/main/AdvantageKit.md)). |
| PathPlannerLib | 2027.0.0-alpha-4.1 | `https://3015rangerrobotics.github.io/pathplannerlib/PathplannerLibSystemCoreAlpha.json` | Commands v2 ([notes](https://github.com/wpilibsuite/SystemcoreTesting/blob/main/PathPlannerLib.md)) |

**Install only if the robot uses them**
| Library | Version | URL | Notes |
|---|---|---|---|
| YAGSL (Commands v2) | 2026.11.06 | `https://cdn.yassrobotics.com/yagsl_commands2.json` | Regenerate the swerve config at config.yagsl.com. Supports NavX3 through StudicaLib ([YASS notes](https://github.com/wpilibsuite/SystemcoreTesting/blob/main/YASS.md)). |
| YAMS (Commands v2) | 2026.11.06 | `https://cdn.yassrobotics.com/yams_commands2.json` | Needs REVLib alpha-9, ThriftyLib alpha-6, and SPARK firmware 27.0.0 |
| LimelightLib 2 | 2.0.0-beta9-alpha7 | `https://limelightvision.github.io/limelightlib-public/LimelightLib-alpha7.json` | Replaces `LimelightHelpers.java` |
| StudicaLib | 2027.0.0-alpha-1 | `https://dev.studica.com/maven/release/2027/json/StudicaLib.json` | navX |
| ThriftyLib | 2027.0.0-alpha-6 | `https://software.thethriftybot.com/frcvendor/ThriftyLib-2027.json` | `ThriftyNova` is now `Nova` |
| ChoreoLib | 2027.0.0-alpha-3 | `https://choreo.autos/lib/ChoreoLib2027Alpha.json` | Pick either Choreo or PathPlanner |
| ReduxLib | 2027.0.0-alpha-7p1 | `https://frcsdk.reduxrobotics.com/ReduxLib_2027.json` | Canandmag / Canandgyro |
| PhotonLib | dev build | [PhotonVision Dev release](https://github.com/PhotonVision/photonvision/releases/tag/Dev) | Only if you move away from Limelight |

Never copy 2026 vendordep files into this project. When Beta 1 ships, plan one upgrade day for WPILib and every vendordep together ([Alpha 8 notes](https://github.com/wpilibsuite/allwpilib/releases/tag/v2027.0.0-alpha-8)).

Build and simulate again after adding the vendordeps.

---

## Part 4: Create the Repo on Team-663 (One Mentor)

Your account can create repos in Team-663 and will be admin of the new one. Public matches past years (D-007); swap in `--private` if you'd rather wait.

```sh
cd 2027
git init -b main
git lfs track "*.wpilog" "*.hoot" "*.revlog"
cat >> .gitignore <<'EOF'
.turnal/
logs/*.wpilog
!logs/fixtures/**
EOF
git add .gitattributes .gitignore .
git commit -m "2027 Commands v2 project with 2027 alpha vendordeps"
gh repo create Team-663/2027 --public --source . --push
gh api -X PUT orgs/Team-663/teams/robot-developers/repos/Team-663/2027 -f permission=push
```

Then confirm on GitHub that `.gitattributes` lists the LFS patterns and that the `robot-developers` team has Write access.

---

## Part 5: Framework Hand-off and Spec Kit (One Mentor)

### 5.1 Work on a branch
```sh
git switch -c framework-handoff
```

### 5.2 Initialize Spec Kit first
```sh
specify init --here --force --integration copilot
git status        # review what it added before committing
git add -A && git commit -m "Add Spec Kit v1.4.0 (Copilot, skills mode)"
```
This installs skills in `.github/skills/speckit-*/` and the `.specify/` folder ([existing projects guide](https://github.github.io/spec-kit/how-to/existing-projects.html)). Commands run in Copilot Chat as `/speckit-specify` and so on, with a hyphen. Running Spec Kit before unpacking our bundle means our files win any conflict.

Add the bug-workflow extension:
```sh
specify extension add bug
git add -A && git commit -m "Add Spec Kit bug extension"
```

### 5.3 Unpack the hand-off bundle
Unzip **FRC Copilot Hand-off Bundle** and copy the *contents* of `frc-copilot-handoff/` into the repo root:
- `AGENTS.md`, `HANDOFF-README.md`
- `.github/copilot-instructions.md`, `.github/instructions/`, `.github/prompts/`
- `docs/framework/` (framework, this guide, decisions, build pieces, hand-off context)
- `docs/research/`

```sh
git diff --stat           # Spec Kit files should not be removed
git add -A && git commit -m "Add agentic framework hand-off docs and starter Copilot instructions"
```

### 5.4 frc-mcp grounding
Create `.vscode/mcp.json`:
```json
{
  "servers": {
    "frc": { "type": "http", "url": "https://mrkaynak.com/frc/mcp" }
  }
}
```
This is the hosted [frc-mcp](https://github.com/fikretyukselit/frc-mcp) server. Teams that want offline use can install it locally with brew or scoop and point to it instead.

### 5.5 Interim terminal safety (until Piece 3 hooks)
Add this to `.vscode/settings.json` so Copilot always asks before deploy-type commands, and never runs them automatically:
```json
{
  "chat.tools.terminal.autoApprove": {
    "/deploy/i": false,
    "/git push --force/": false,
    "/nt4|networktables/i": false
  }
}
```
These rules only govern auto-approval. The real block comes from the `preToolUse` hooks in Build Piece 3 ([VS Code hooks](https://code.visualstudio.com/docs/agent-customization/hooks)). Until then, every terminal command needs a human click, and nobody approves a deploy from chat.

### 5.6 Push and open the PR
```sh
git add .vscode && git commit -m "Add frc-mcp and interim terminal safety settings"
git push -u origin framework-handoff
gh pr create --title "Framework hand-off: Spec Kit, Copilot instructions, frc-mcp" --body "Starter agentic framework. See docs/framework/ and HANDOFF-README.md."
```
Have a second person (a mentor or programming lead) review and merge it.

---

## Part 6: Rulesets and Code Review Settings

Turn rules on in phases so the repo stays usable before CI exists.

| Phase | When | Rules on `main` |
|---|---|---|
| 1 | Now | Require a PR with 1 approval, block force pushes, block deletion |
| 2 | During Piece 1, once `.github/CODEOWNERS` exists and the `mentors` team has been created | Add **Require review from Code Owners**; this is required before Piece 1 can be marked Done |
| 3 | During Piece 4, once the `build` workflow is passing | Add **Require status checks**: the `build` job; this is required before Piece 4 can be marked Done |
| — | Never | "Automatically request Copilot code review" stays **off** (D-013) |

Piece dependencies in the tracker are completion gates: start a dependent piece only after its listed prerequisites are Done. Ruleset phases are separate operational milestones within those pieces. Piece 1 does not wait for CI; the required `build` status check is deferred to Piece 4, and Piece 4 cannot be marked Done until that check is enabled and verified.

**Phase 1 from the command line** (repo admin):
```sh
gh api -X POST repos/Team-663/2027/rulesets --input - <<'EOF'
{
  "name": "main-protection",
  "target": "branch",
  "enforcement": "active",
  "conditions": { "ref_name": { "include": ["~DEFAULT_BRANCH"], "exclude": [] } },
  "rules": [
    { "type": "pull_request", "parameters": {
        "required_approving_review_count": 1,
        "dismiss_stale_reviews_on_push": true,
        "require_code_owner_review": false,
        "require_last_push_approval": false,
        "required_review_thread_resolution": false } },
    { "type": "non_fast_forward" },
    { "type": "deletion" }
  ]
}
EOF
```
In the web UI, the same settings are under **Settings → Rules → Rulesets → New branch ruleset**.

**Code review settings:**
- Mentors set their personal default review effort to **Lite**.
- To review a ready PR with CI green, a mentor picks the effort under **Reviewers**, next to Copilot, then clicks **Request** ([GitHub Docs](https://docs.github.com/en/copilot/how-tos/use-copilot-agents/use-code-review)). Use **Balanced** only for elevator, arm, climber, superstructure, or keep-out changes.
- Students can do a quick local check in VS Code with **Source Control → Copilot Code Review - Uncommitted Changes**, but it uses their credits. Save it for small diffs.
- Note the credits from each review session in `docs/copilot-credits.md`.

**Keep these off:** Agent Merge, assisted-approval instructions for terminal or robot tools, and global auto-approve.

---

## Part 7: Systemcore Prep

1. **Buy:** the FIRST Storefront opens Systemcore sales on **November 18, 2026**: 4 GB for $550, one per team. Every team also gets one in the Kickoff Kit ([FIRST](https://www.firstinspires.org/community-blog/2026-systemcore-pricing-and-availability)).
2. **Image:** run Systemcore OS image **17 or newer**, which fixes the beta lockup ([Limelight report](https://docs.limelightvision.io/reports/systemcore-beta-lockup)). Alpha 8 needs at least image 14 and DS Alpha 7. On image 17, the Wi-Fi network is `SYSTEMCORE-[random]`, and the password is shown on the unit's display.
3. **Recovery drive:** pre-production units have a storage (eMMC) failure risk. Keep a USB recovery drive made with the latest imaging tool, and label it ([Limelight report](https://docs.limelightvision.io/reports/systemcore-beta-emmc-failure)).
4. **Log drive:** use a USB 2 drive formatted as MBR. Phoenix logging can crash robot code when the USB filesystem has errors ([#439](https://github.com/wpilibsuite/SystemcoreTesting/issues/439)).
5. **Firmware on the bench:** update CTRE devices to 26.70.X with Tuner X Preview, and SPARKs with REV Hardware Client 2. Keep the wheels off the ground, and re-check brake mode and current limits afterward.
6. **Label everything:** any controller or robot that runs 2026 code (roboRIO) gets a label, so nobody deploys 2027 code to the wrong robot.

---

## Part 8: Smoke Tests

| # | Test | Pass looks like |
|---|---|---|
| 1 | In Copilot Chat (Agent mode), ask: "Use the frc MCP to confirm this project's WPILib version and list our vendordeps." | It reports 2027, `org.wpilib`, and `command2`. If you see `edu.wpi.first`, grounding isn't working. |
| 2 | Ask: "Use frc_migrate to check whether this project has any 2026 patterns." | A clean report, or a short list of fixes |
| 3 | Type `/speckit-` in chat | Spec Kit skills show up (constitution, specify, plan, tasks, implement, converge, bug-*) |
| 4 | Ask Copilot to run `./gradlew deploy` | It stops and asks you. Click **Skip**. (Piece 3 turns this into a hard block.) |
| 5 | **Deferred until robot-side AdvantageKit is configured:** simulate, open the generated log in AdvantageScope, and run replay | The robot initializes AdvantageKit logging, a replay source is configured, and `ReplayOutputs` matches `RealOutputs` |
| 6 | Open a tiny PR and request a **Lite** Copilot review | Copilot comments, follows `copilot-instructions.md`, and the session shows credits used |
| 7 | **Deferred until robot-side logging and metadata are configured, and Systemcore is available:** with the robot on blocks, a human deploys by hand and pulls the log with AdvantageScope Lite | The log opens and includes the git hash. Event name and match number may be missing on AdvantageKit alpha-7, which is expected. |

Tests 5 and 7 are not pass criteria for the initial project/setup smoke test. Installing the AdvantageKit vendordep and Gradle integration alone does not configure robot startup, logger initialization, replay inputs, or git-hash metadata. Run test 5 only after the pilot robot code has those logging/replay pieces; run test 7 only after git-hash metadata is also included and hardware is available. Until then, leave both deferred rather than reporting them as passed.

---

## Part 9: Each Student's First Day

1. Confirm Copilot Student is active at github.com/settings/copilot.
2. Complete Part 2, sections 2.1–2.6, including the Python runtime and `python3 --version` check. The Spec Kit CLI in section 2.7 is not needed for students.
3. Clone the repo:
   ```sh
   gh repo clone Team-663/2027
   cd 2027 && git lfs pull
   ```
4. Open the folder in the 2027 WPILib VS Code. When prompted, trust the workspace and start the `frc` MCP server.
5. Run **Build** and **Simulate**.
6. Run smoke test 1.
7. Read `AGENTS.md` and Section 8 of the framework (credit habits).

---

## Safety Notes
- Agents never deploy and never write NetworkTables values. Until the Piece 3 hooks exist, a human reads every terminal command before approving it.
- The first enable on Systemcore happens with the robot on blocks, a hand on disable, and a spoken "enabling" call-out.
- Do firmware updates on the bench, and re-check settings afterward.
- Keep the Systemcore recovery drive with the robot cart.

## Next Question to Ask
Who will be the org owner contact for creating the `mentors` team and deciding on Copilot Business later? Phase 2 of the ruleset and D-009 both depend on it.
