---
name: build-piece
description: Start or continue one numbered Build Piece from the FRC Agentic Development Framework tracker.
argument-hint: "Build Piece number, for example 1"
agent: agent
---

Work on the numbered Build Piece supplied by the user. If no number was supplied, ask which piece to work on before making changes.

1. Read `AGENTS.md`, `.github/copilot-instructions.md`, `docs/framework/build-pieces.md`, and the relevant Build Piece section in `docs/framework/agentic-dev-framework.md`. Read `docs/framework/decisions.md` and any relevant specs or setup docs as needed.
2. Check the current git status and existing changes. Preserve unrelated work; do not overwrite or revert it.
3. Confirm dependencies and prerequisites for the selected piece. If a prerequisite is unmet or the scope is ambiguous, explain the blocker and ask before expanding scope.
4. Implement only work for the selected piece, using its outputs and done-criteria as the acceptance criteria. Keep changes small and reviewable. Do not deploy or operate hardware.
5. Run relevant safe checks, inspect the final diff, and report files changed, checks performed, remaining work, and whether the done-criteria are actually met.
6. Update the piece's status in `docs/framework/build-pieces.md` only when the evidence supports a status change. Do not mark work Done if external approval, configuration, or validation remains outstanding.
