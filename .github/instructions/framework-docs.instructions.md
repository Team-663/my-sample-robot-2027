---
name: Framework documentation rules
description: Use when editing docs/framework/** or docs/research/**; preserve the framework's decisions, build-piece tracking, citations, and hand-off accuracy.
applyTo:
  - "docs/framework/**"
  - "docs/research/**"
---

# Framework and research documentation

- Read `docs/framework/decisions.md` and the relevant section of `docs/framework/agentic-dev-framework.md` before changing framework guidance. Add a new decision entry when a decision changes; do not silently rewrite history.
- Keep `docs/framework/build-pieces.md` aligned with the framework's Build Piece list. Update status only when the documented work actually changes status.
- Keep instructions operational and consistent with files and commands present in the repository. Do not advertise absent prompt files, scripts, setup steps, or slash commands.
- Cite authoritative sources for technical, policy, and time-sensitive claims. Prefer the links already collected in `docs/research/`; mark facts that need periodic re-verification.
- Preserve the distinction between accepted decisions and open decisions. Do not invent approvals, test results, or hardware availability.
- Keep setup steps scoped, reproducible, and safe. Never recommend deploying to or writing to a live robot.
- Use relative Markdown links for repository documents where useful, and check that referenced files exist before adding links.
