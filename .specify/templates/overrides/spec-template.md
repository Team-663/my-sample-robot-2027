# Feature Specification: [FEATURE NAME]

**Feature Branch**: `[###-feature-name]`

**Created**: [DATE]

**Status**: Draft

**Input**: User description: "$ARGUMENTS"

## User Scenarios & Testing *(mandatory)*

<!--
  IMPORTANT: User stories should be PRIORITIZED as user journeys ordered by importance.
  Each user story/journey must be INDEPENDENTLY TESTABLE - meaning if you implement just ONE of them,
  you should still have a viable MVP (Minimum Viable Product) that delivers value.

  Assign priorities (P1, P2, P3, etc.) to each story, where P1 is the most critical.
  Think of each story as a standalone slice of functionality that can be:
  - Developed independently
  - Tested independently
  - Deployed independently
  - Demonstrated to users independently
-->

### User Story 1 - [Brief Title] (Priority: P1)

[Describe this user journey in plain language]

**Why this priority**: [Explain the value and why it has this priority level]

**Independent Test**: [Describe how this can be tested independently - e.g., "Can be fully tested by [specific action] and delivers [specific value]"]

**Acceptance Scenarios**:

1. **Given** [initial state], **When** [action], **Then** [expected outcome]
2. **Given** [initial state], **When** [action], **Then** [expected outcome]

---

### User Story 2 - [Brief Title] (Priority: P2)

[Describe this user journey in plain language]

**Why this priority**: [Explain the value and why it has this priority level]

**Independent Test**: [Describe how this can be tested independently]

**Acceptance Scenarios**:

1. **Given** [initial state], **When** [action], **Then** [expected outcome]

---

### User Story 3 - [Brief Title] (Priority: P3)

[Describe this user journey in plain language]

**Why this priority**: [Explain the value and why it has this priority level]

**Independent Test**: [Describe how this can be tested independently]

**Acceptance Scenarios**:

1. **Given** [initial state], **When** [action], **Then** [expected outcome]

---

[Add more user stories as needed, each with an assigned priority]

### Edge Cases

<!--
  ACTION REQUIRED: The content in this section represents placeholders.
  Fill them out with the right edge cases.
-->

- What happens when [boundary condition]?
- How does system handle [error scenario]?

## Robot Behavior *(mandatory for mechanisms, autos, and superstructure changes)*

<!--
  Team 663 FRC fields. Students draft these by hand with mech/elec before running an agent.
  Describe behavior (goals, states, transitions, limits), not command classes or code.
  Every transition-table row becomes a unit test. Example: specs/000-example-roller/spec.md
  Mark unknowns as [NEEDS CLARIFICATION: ...] rather than guessing numbers.
-->

### 1. Purpose and match role

[What this does for the robot in a match, and when it is used]

### 2. Hardware

| Device | Type / controller | CANPort / channel | Notes |
|--------|-------------------|-------------------|-------|
| [motor/sensor] | [e.g., Kraken X60 / TalonFX] | [port] | [ratio, inversion] |

Record every CAN device in `docs/robot-io.md`.

### 3. Goals and States

- **Goals** (what commands ask for): [IDLE, ...]
- **States** (what is actually happening): [IDLE, ..., safe/fault states]

**Transition table** (checked top to bottom; safety rows first):

| # | From | Condition | To | Transition reason (logged) |
|---|------|-----------|----|----------------------------|
| T1 | any | robot disabled | [safe state] | "robot disabled" |
| T2 | [state] | [condition] | [state] | "[reason]" |

**Output per state**: [state → output; every non-moving state outputs zero]

### 4. Superstructure interactions and keep-outs

[Other mechanisms this depends on or must avoid; keep-out rules and who signs off (D-011)]

### 5. Operator controls

| Control | Action | Goal set |
|---------|--------|----------|
| [button] | [while held / on press] | [GOAL] |

### 6. Limits

- Soft limits: [positionMeters / degrees range]
- Current limits: [amps]
- Speed caps: [units]

### 7. Failure modes and safe states

| Failure | How detected | Safe state / response |
|---------|--------------|-----------------------|
| [sensor fails, stall, CAN loss] | [logged signal] | [state] |

### 8. Telemetry

Minimum: goal, state, and transition reason every loop. Plus: [inputs/outputs needed for debugging]

### 9. Acceptance evidence

- [ ] Transition tests T1–Tn pass in CI
- [ ] [Sim behavior to demonstrate]
- [ ] [On-robot check, after bring-up]

### 10. Bring-up checklist

- [ ] CAN ID and firmware verified
- [ ] Low-voltage jog: direction correct
- [ ] Encoder direction and ratio verified
- [ ] Soft limits checked with robot disabled
- [ ] Closed loop at reduced speed

### 11. Out of scope

- [What this spec deliberately does not cover]

## Requirements *(mandatory)*

<!--
  ACTION REQUIRED: The content in this section represents placeholders.
  Fill them out with the right functional requirements.
-->

### Functional Requirements

- **FR-001**: System MUST [specific capability, e.g., "allow users to create accounts"]
- **FR-002**: System MUST [specific capability, e.g., "validate email addresses"]
- **FR-003**: Users MUST be able to [key interaction, e.g., "reset their password"]
- **FR-004**: System MUST [data requirement, e.g., "persist user preferences"]
- **FR-005**: System MUST [behavior, e.g., "log all security events"]

*Example of marking unclear requirements:*

- **FR-006**: System MUST authenticate users via [NEEDS CLARIFICATION: auth method not specified - email/password, SSO, OAuth?]
- **FR-007**: System MUST retain user data for [NEEDS CLARIFICATION: retention period not specified]

### Key Entities *(include if feature involves data)*

- **[Entity 1]**: [What it represents, key attributes without implementation]
- **[Entity 2]**: [What it represents, relationships to other entities]

## Success Criteria *(mandatory)*

<!--
  ACTION REQUIRED: Define measurable success criteria.
  These must be technology-agnostic and measurable.
-->

### Measurable Outcomes

- **SC-001**: [Measurable metric, e.g., "Users can complete account creation in under 2 minutes"]
- **SC-002**: [Measurable metric, e.g., "System handles 1000 concurrent users without degradation"]
- **SC-003**: [User satisfaction metric, e.g., "90% of users successfully complete primary task on first attempt"]
- **SC-004**: [Business metric, e.g., "Reduce support tickets related to [X] by 50%"]

## Assumptions

<!--
  ACTION REQUIRED: The content in this section represents placeholders.
  Fill them out with the right assumptions based on reasonable defaults
  chosen when the feature description did not specify certain details.
-->

- [Assumption about target users, e.g., "Users have stable internet connectivity"]
- [Assumption about scope boundaries, e.g., "Mobile support is out of scope for v1"]
- [Assumption about data/environment, e.g., "Existing authentication system will be reused"]
- [Dependency on existing system/service, e.g., "Requires access to the existing user profile API"]
