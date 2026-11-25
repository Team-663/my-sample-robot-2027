# Copilot Credit Review Ledger

**Status:** Active governance record
**Owner:** Software mentors
**Last reviewed:** November 24, 2026

## Review policy

- Automatic Copilot code review remains off.
- Mentors request Copilot review only for ready, CI-green pull requests.
- Use **Lite** review by default.
- Use **Balanced** only for elevator, arm, climber, superstructure, or keep-out changes.
- Do not use **Max** review.
- A human mentor still approves the pull request; AI review is advisory.
- Record every requested Copilot review in the ledger below, whether or not GitHub reports an error or timeout.

## Ledger

| Date | Pull request | Reviewer | Effort | Credits used | Safety-critical area? | Outcome / notes |
|---|---|---|---|---:|---|---|
|  |  |  | Lite / Balanced |  | Yes / No |  |

## Recording guidance

For each review, record:

1. The pull request number and title.
2. The GitHub or team name of the mentor who requested it.
3. Lite or Balanced effort.
4. Credits shown or reported for the review.
5. Whether the change touched a safety-critical area.
6. A short outcome, including whether the review found a safety or architecture issue.

If the exact credit amount is unavailable, enter `unavailable` rather than guessing. Add the actual amount when it becomes visible.

This ledger implements the review-cost requirement in [decision D-013](framework/decisions.md#d-013-copilot-code-review-is-mentor-requested-lite-by-default). Credit estimates and billing rules should be rechecked in GitHub's Copilot billing documentation before spending decisions.
