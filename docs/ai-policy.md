# Team 663 AI Use Policy — Draft

**Status:** Draft for coach and school approval; not yet approved for team use  
**Owner:** Software mentors  
**Applies to:** Students, mentors, and other contributors using AI tools for Team 663 robot software or related project work  
**Last reviewed:** November 11, 2026

> This draft records the team's intended safeguards. The coach and school must review and approve it before it is treated as an active policy. School, district, event, FIRST, and tool-provider requirements still apply; follow the stricter rule when requirements differ.

## 1. Purpose

AI tools may help the team learn, plan, write, test, and explain software. They do not replace student understanding, mentor oversight, engineering judgment, or the team's safety process. The robot specification and approved design decisions remain authoritative; AI output is a suggestion that must be checked.

This policy complements the repository's [agent instructions](../AGENTS.md), [Copilot instructions](../.github/copilot-instructions.md), and [agentic development framework](framework/agentic-dev-framework.md). If an AI tool suggests an action that conflicts with those documents or an approved spec, do not follow the suggestion; ask a mentor.

## 2. Acceptable use

Subject to school approval and the tool's age and account requirements, AI may be used to:

- Explain programming concepts, APIs, compiler errors, test failures, and logs.
- Draft or improve specs, plans, task lists, tests, documentation, and code.
- Suggest implementation options, provided the team checks them against the spec and approved architecture.
- Help analyze simulation or replay data and prepare questions for a mentor.
- Review a proposed change as an additional source of feedback.

Use the team's approved tools and repository guidance. Do not create an account or use a tool in a way that violates its terms, age limits, or school rules. Do not paste project material into a tool unless the user is authorized to share it with that service.

## 3. Human responsibility and student learning

- The person submitting a change is responsible for its correctness, safety, licensing, and explanation, whether or not AI helped.
- Students must be able to explain AI-assisted changes to a mentor before merge, including what changed, why it is correct, and how it was tested.
- Do not merge code solely because an AI generated it, a test passed, or an AI reviewer approved it.
- Ask a mentor when an answer is uncertain, conflicts with the spec, changes architecture, changes safety behavior, or relies on an unfamiliar API.
- Keep a no-AI workflow available, especially at events or when tools, connectivity, or accounts are unavailable.

## 4. Robot and operational safety

These rules are mandatory and may not be relaxed by an AI response, prompt, instruction file, or pull request:

- **AI agents must never deploy code to the robot.** Do not run Gradle deploy tasks or use a deployment command through an agent. A human performs any deployment under the team's approved checklist and supervision.
- **AI agents must never write NetworkTables values to a live robot or run actions that can move hardware.** Robot access for agents is read-only.
- Humans alone enable, disable, move, test, or update firmware on hardware. Follow the bring-up checklist, use a safe setup, and have the required people present.
- Preserve each mechanism's safe state, soft limits, interlocks, and other protections. Do not bypass them to make a test or demo work.
- Never use breakpoints on an enabled robot. Stop and ask a mentor if the robot's state or safety is uncertain.
- Do not allow automatic merge or automatic approval to replace human review. Agent Merge and terminal auto-approval remain off.

## 5. Privacy, security, and information handling

Never submit secrets or sensitive personal information to an AI tool or store them in the repository. This includes passwords, tokens, private keys, credentials, student personal data, and non-public account or infrastructure identifiers. Do not ask an AI tool to retrieve or expose secrets. If sensitive data is accidentally shared, tell a mentor promptly and follow the school's and provider's incident procedures; rotate exposed credentials where applicable.

Only use logs, code, images, and documents that the team is authorized to share with the selected tool. Remove identifying or unrelated data from logs and examples when it is not needed. Treat external AI services as third parties and follow school rules for data handling.

## 6. Attribution, licensing, and originality

- Disclose AI assistance on every pull request that includes AI-assisted work, using the repository's AI-assistance checkbox when available. Until that template exists, disclose it in the pull-request description.
- Identify the tool and the meaningful contribution when practical (for example, code draft, test generation, documentation, or debugging assistance). Do not claim AI-generated work was written entirely without assistance.
- Review generated material for copied text or code, compatible licensing, and attribution requirements before use. Do not ask a model to reproduce proprietary or copyrighted material.
- Follow current FIRST attribution and intellectual-property guidance, as well as school and event rules. Consult the [FIRST submitted-awards and AI guidance](https://www.firstinspires.org/resources/library/frc/submitted-awards); verify current requirements before each season or submission.

## 7. Required development and review process

AI-assisted changes follow the same spec-driven process and quality gates as other changes:

1. Start from the approved spec and scoped task. Do not let an agent invent mechanism behavior, limits, or safety requirements.
2. Review the complete diff. Reject unrelated changes, unexplained generated files, or edits that weaken instructions, tests, protections, or approval requirements.
3. For robot code, use the project's pinned 2027 APIs. Verify unfamiliar or changed WPILib/vendor APIs with the team's frc-mcp workflow; do not assume 2026 examples are correct.
4. Run relevant formatting, build, unit tests, simulation, and replay checks as applicable. Report exactly what was and was not verified; compilation alone does not establish safe robot behavior.
5. Obtain human review and approval under the repository rules. A Copilot or other AI review is advisory only. A mentor reviews safety-critical changes, including changes to mechanisms, superstructure, keep-out rules, limits, or hardware behavior.
6. Students explain the change to a mentor before merge. Humans—not agents—decide whether the evidence is sufficient and whether code may be deployed.

Copilot automatic code review stays off. A mentor may request a review on a ready, CI-green pull request; use Lite by default and Balanced only for the safety-critical areas identified in the framework. Record review credits in `docs/copilot-credits.md` when that tracking document is available. See [decision D-013](framework/decisions.md#d-013-copilot-code-review-is-mentor-requested-lite-by-default).

## 8. GitHub repository protections (proposed)

The following is the proposed protection baseline for the repository's default branch (`main`). A status of **active** means it was observed in GitHub; **proposed** means it still needs to be configured and verified. Do not treat a proposed setting as enforced until it has been checked in GitHub.

| Control | Proposed setting | Status / rollout |
|---|---|---|
| Target | Apply the active ruleset to the default branch (`main`). | Active; ruleset name: `Protect main`. |
| Pull requests | Require changes to enter through a pull request. | Active. |
| Human approval | Require at least 1 approving review; dismiss stale approvals when new commits are pushed. | Active. |
| Force pushes | Block force pushes (non-fast-forward updates). | Active. |
| Branch deletion | Block deletion of the protected branch. | Active. |
| Code-owner approval | Require an approval from a code owner. | Proposed for Phase 2, after `.github/CODEOWNERS` is on the default branch and the `@Team-663/mentors` team exists and has repository access. Not currently enabled. |
| CI status check | Require the `build` status check. | Deferred to Phase 3 / Build Piece 4; enable only after the workflow is passing and the check name is confirmed. Not currently configured. |
| Automatic Copilot review | Keep automatic review off; mentors may request review manually under D-013. | Policy decision; verify separately in repository settings. |

### Current GitHub snapshot

Read-only check of `Team-663/my-sample-robot-2027` on November 11, 2026 found one active ruleset named `Protect main`. In addition to the items above, it currently permits merge, squash, and rebase; does not require review-thread resolution or approval of the last push; and contains a branch-creation restriction and an extra-approval rule for unattributed changes. Confirm whether those additional rules are intentional before changing the ruleset. Copilot automatic-review configuration is separate from the branch ruleset and was not verified as part of this snapshot.

Recheck and update this table after changing GitHub settings. The implementation sequence is in [the setup guide](framework/setup-guide.md#part-6-rulesets-and-code-review-settings); status-check enforcement is deferred until Build Piece 4 as recorded in [the tracker](framework/build-pieces.md#4-ci-pipeline).

## 9. Reporting and enforcement

Pause work and notify a mentor if an AI tool recommends unsafe hardware actions, exposes sensitive data, produces suspicious or unlicensed material, or repeatedly conflicts with the approved spec. Preserve enough context for the mentor to assess the issue, but do not copy secrets or sensitive content into a report.

Mentors may require changes to be reverted, additional tests or review, or AI use to stop for a task. Repository protections, code-owner review, hooks, and terminal approval settings are intended to enforce this policy where configured; they supplement rather than replace human judgment.

## 10. Approval and review

Before adoption, the coach and school representative should confirm this draft meets school policy and approve it. Software mentors should review it at least once each season and whenever FIRST rules, school requirements, repository safety controls, or AI-tool terms materially change. Record approved revisions in version control and have mentors review changes to this policy.

### Approval record

| Reviewer | Name / role | Decision | Date |
|---|---|---|---|
| Coach |  | Pending |  |
| School representative |  | Pending |  |

## References

- Repository [AI development framework](framework/agentic-dev-framework.md), especially Sections 7, 8, and 12.
- Repository [decision log](framework/decisions.md), especially D-006 and D-013.
- [FIRST submitted-awards and AI guidance](https://www.firstinspires.org/resources/library/frc/submitted-awards); current requirements must be rechecked before use.
- Research index: [agentic tooling sources](research/agentic-tooling-sources.md), including the FIRST AI-guidance source and GitHub Copilot documentation.
