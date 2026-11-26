# Team 663 Robot Code, 2027

Robot code for FRC Team 663's 2027 robot (game: BIOCORE, Kickoff January 9, 2027). It is Java on **WPILib 2027** and the **Systemcore** controller. We design each mechanism in a written **spec** first, then write the code with help from **GitHub Copilot**. Every change is reviewed by a person before it merges.

This repository is also where we build and test that workflow, which we call the **harness**: the instructions, templates, examples, and checks that keep AI-assisted code correct and safe.

> **Current phase: Phase 0, writing specs and code.** What is done and what is next: [`docs/framework/phase-plan.md`](docs/framework/phase-plan.md).

New to FRC code or to this repo? Read the [glossary](docs/glossary.md) alongside this page.

## Start here

| You are... | Read, in this order |
|---|---|
| **A student programmer** | This page → [Add a mechanism](docs/guides/add-a-mechanism.md) → the reference example ([spec](specs/000-example-roller/spec.md), [code](src/main/java/first/robot/subsystems/exampleroller/ExampleRoller.java)) → [`AGENTS.md`](AGENTS.md) (the rules Copilot follows; they apply to you too) |
| **A mentor reviewing PRs** | This page → [Review checklist](docs/guides/review-checklist.md) → [Add a mechanism](docs/guides/add-a-mechanism.md) (so you know what students were asked to do) |
| **Working on the harness itself** (instructions, CI, Spec Kit, hooks) | [Harness guide](docs/framework/harness-guide.md) → [phase plan](docs/framework/phase-plan.md) → [decision log](docs/framework/decisions.md) |
| **Setting up a new laptop** | [Setup guide](docs/framework/setup-guide.md), Parts 1, 2, and 9 |

You do **not** need to read [`agentic-dev-framework.md`](docs/framework/agentic-dev-framework.md) to get started. It is background on why the harness looks the way it does.

## Quick start (about 10 minutes, after setup)

You need the **2027 WPILib VS Code** installed (setup guide Part 2). Always open this folder in the 2027 WPILib VS Code, not your normal VS Code and not the 2026 one.

1. **Get the code.**
   ```sh
   gh repo clone Team-663/my-sample-robot-2027
   cd my-sample-robot-2027
   ```
2. **Build and run the tests.** Open a terminal in WPILib VS Code (**Terminal → New Terminal**):
   ```sh
   ./gradlew build        # Windows: .\gradlew build
   ```
   `BUILD SUCCESSFUL` means the code compiles and every unit test passed. If a test fails, the output names the test and shows what it expected. CI runs this exact command on every pull request.
3. **Run the robot in simulation.** Command palette (Ctrl+Shift+P, or Cmd+Shift+P on Mac) → **WPILib: Simulate Robot Code** → pick **Sim GUI**.
   - In the Sim GUI's **Robot State** panel, click **Teleoperated** to enable the robot.
   - A gamepad plugged into the laptop should appear as joystick 0. If it doesn't, drag it from **System Joysticks** onto slot 0 in the **Joysticks** panel.
   - Hold the **right face button** (B on Xbox-style pads) to intake and the **bottom face button** (A) to eject.
4. **Watch it in AdvantageScope.** Open AdvantageScope → **File → Connect to Simulator**. In the sidebar, open `RealOutputs` → `ExampleRoller` (it may be inside an `AdvantageKit` folder) and watch `Goal`, `State`, and `TransitionReason` change as you press buttons.
5. **Stop the simulation** by closing the Sim GUI window. A log file of the run is saved in `logs/` (Git ignores this folder). To replay it, see [Simulate and replay](docs/guides/add-a-mechanism.md#6-simulate-and-replay).

**Never deploy to a robot from an agent chat**, and never let Copilot run a `deploy` task. Humans deploy, with the robot on blocks, following the mechanism's bring-up checklist.

## How a feature gets built

```
 idea ──► spec ──► plan ──► tasks ──► code + tests ──► PR ──► CI green ──► student explains ──► mentor approves ──► merge
          (student,        (Copilot     (Copilot, one        (./gradlew     to a mentor
           mentor +         drafts,      task per chat)        build)
           mech review)     you edit)
```

The full walkthrough, including which files to copy and what to check at each step, is [Add a mechanism](docs/guides/add-a-mechanism.md).

## What is where

| Path | What it is |
|---|---|
| `src/main/java/first/robot/` | Robot code. `Robot.java` starts logging; `RobotContainer.java` creates subsystems and button bindings. |
| `src/main/java/first/robot/subsystems/<name>/` | One folder per mechanism: subsystem, IO interface, sim IO (and real IO once hardware exists). |
| `src/main/java/first/robot/commands/` | One command factory per subsystem, plus `Autos`. |
| `src/test/java/first/robot/subsystems/<name>/` | Unit tests, one per transition-table row in the spec. |
| `specs/` | One folder per feature: `spec.md`, then `plan.md` and `tasks.md`. `000-example-roller` is the reference. |
| `docs/robot-io.md` | Every CAN ID, sensor port, and controller port. Update it in the same PR as the code. |
| `docs/guides/` | How-to guides for students and mentors. |
| `docs/framework/` | Harness docs: phase plan, decisions, setup guide, harness guide. |
| `AGENTS.md`, `.github/` | Instructions Copilot reads, CI, PR template, code owners. |
| `.specify/` | GitHub Spec Kit: constitution, templates (FRC fields live in `templates/overrides/`), scripts. |
| `vendordeps/` | Vendor libraries (CTRE, REV, AdvantageKit, PathPlanner, ...). Install them through WPILib VS Code, never by hand. |

## Rules everyone follows

- **Spec first.** Behavior that isn't in an approved spec doesn't go in the code.
- **Agents never move hardware.** No deploys and no NetworkTables writes from an agent. A human reads and approves every terminal command Copilot wants to run.
- **Tests are never weakened** to make CI pass. A failing test means fix the code, or change the spec first and get it re-reviewed.
- **Disclose AI use** in every PR (the template has a checkbox). This is a FIRST rule.
- **You can explain it.** A student explains each change and how it was checked to a mentor before it merges.

Full rules: [`AGENTS.md`](AGENTS.md) and the [constitution](.specify/memory/constitution.md). Team AI policy (draft): [`docs/ai-policy.md`](docs/ai-policy.md).
