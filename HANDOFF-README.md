# Hand-off: FRC Agentic Development Framework → VS Code + Copilot

This bundle moves the framework planning work (done in Perplexity Computer, October 27, 2026; refreshed November 10, 2026) into your robot repo, so GitHub Copilot in VS Code can continue building it piece by piece.

## What's in the bundle

| File | Purpose | Who reads it |
|---|---|---|
| `AGENTS.md` | Standing rules for any AI agent working in this repo (starter version; Build Piece 3 expands it) | Copilot (auto), code review, other agents |
| `.github/copilot-instructions.md` | Copilot-specific guidance: where the framework lives, how to work credit-smart, how to continue | Copilot (auto), code review |
| `.github/instructions/framework-docs.instructions.md` | Rules for editing the framework and research docs | Copilot, when touching `docs/framework/**` or `docs/research/**` |
| `.github/prompts/build-piece.prompt.md` | `/build-piece` slash command: starts work on a numbered Build Piece | You, in Copilot Chat |
| `.github/prompts/framework-status.prompt.md` | `/framework-status` slash command: summarizes where things stand | You, in Copilot Chat |
| `docs/framework/agentic-dev-framework.md` | **The full framework** (source of truth) | Everyone |
| `docs/framework/setup-guide.md` | **Setup start to finish**: accounts, laptop installs, project, vendordeps, repo, Spec Kit, frc-mcp, rulesets, Systemcore prep, smoke tests | Mentors, then every student |
| `docs/framework/handoff-context.md` | How we got here: the conversation's decisions, reasoning, and what changed along the way | Copilot + new team members |
| `docs/framework/decisions.md` | Decision log (made and open), with rationale | Everyone |
| `docs/framework/build-pieces.md` | Build Piece tracker with inputs, outputs, and done-criteria | Everyone |
| `docs/research/agentic-tooling-sources.md` | Every source behind the framework research, grouped by topic | Copilot + mentors |
| `docs/research/frc-design-build-strategy-resources.md` | General FRC design, build, strategy, and software resources (from the Perplexity project files) | Everyone |

## How to install

Follow `docs/framework/setup-guide.md` Part 5. In short:
1. Create the robot project and the `Team-663/2027` repo (setup guide Parts 3–4).
2. On a `framework-handoff` branch, run `specify init --here --force --integration copilot` and `specify extension add bug` **first** (Spec Kit v1.4.0 installs skills under `.github/skills/speckit-*`; run them as `/speckit-specify`, with a hyphen).
3. Copy the bundle contents into the **repo root**, keeping the folder structure. Merge rather than overwrite if a file already exists, and check `git diff --stat` so no Spec Kit files are removed.
4. Make sure Copilot reads instructions: in VS Code settings, confirm **Chat: Use AGENTS.md File** is on (default), and that `.github/prompts` and `.github/skills` are enabled.
5. Commit on a branch (e.g., `framework-handoff`) and open a PR so mentors review it.

## How to continue

Open Copilot Chat in **Agent** mode and run:

```
/framework-status
```

Then start the next piece:

```
/build-piece 1
```

The recommended order is Pieces 1 → 10. Piece 1 (AI policy + governance) and Piece 2 (constitution + spec templates, tested on the roller intake) come first.

## Notes
- The framework assumes Copilot for everyone (Student plan for students, Pro for mentors). Credits are limited on Student, so the prompt files keep sessions small and scoped.
- Copilot code review is requested by mentors at Lite effort, not automatically (decision D-013).
- Nothing here contains secrets. The frc-mcp server URL is public.
- `AGENTS.md` is intentionally a starter. Don't let it grow into a second copy of the framework; it should link to the docs.
