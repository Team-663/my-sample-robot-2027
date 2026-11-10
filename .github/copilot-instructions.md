# Copilot Instructions

Use this file as the Copilot-specific guide for work in this repository. The framework design and decisions live in `docs/framework/`; consult the relevant spec and decision log before changing robot architecture or framework policy.

## Working on the framework
- Treat `docs/framework/agentic-dev-framework.md` as the framework source of truth, `docs/framework/decisions.md` as the decision log, and `docs/framework/build-pieces.md` as the status tracker.
- Work on one numbered Build Piece at a time. Follow its outputs and done-criteria in the tracker and framework. Keep changes scoped; do not generate later pieces preemptively.
- Use the `/framework-status` prompt to summarize current progress and `/build-piece` to start a numbered piece. Review the current diff and update the tracker when a piece's status changes.
- Cite or link existing research in `docs/research/` when adding factual claims. Mark uncertain or time-sensitive facts for re-verification.

## Robot-code and safety rules
- Follow the root `AGENTS.md` for project-wide robot-code rules, including 2027 APIs, architecture, tests, and safety.
- Never deploy to the robot, write NetworkTables values on a live robot, or run actions that can move hardware. Do not run Gradle deploy tasks.
- Never commit secrets. Do not weaken safety or approval rules as part of a framework change.
- When adding robot code that uses WPILib or vendor APIs, verify it against the repository's pinned 2027 season and build/test it as appropriate.

## Review expectations
- Explain the purpose and scope of changes, identify any assumptions, and report verification performed.
- Do not claim a Build Piece is complete unless its documented done-criteria are met; distinguish completed work from follow-up decisions or external approvals.
