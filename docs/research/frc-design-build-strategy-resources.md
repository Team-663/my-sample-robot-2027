# FRC Design, Build & Strategy Resources

Reference guide for researching robot specs, design ideas, strategy, and execution. Compiled October 27, 2026, between the 2026 REBUILT season and the 2027 BIOCORE kickoff.

## How to Use This File

- Start with the **Official Sources** for any rule question. Community sites interpret rules; they do not set them.
- For "how did top teams solve this?" questions, go to **Robot Specs & Reveals**, then **CAD Releases**, then **Technical Binders**.
- For "how should we play this game?" questions, use **Strategy & Scouting** and **Data & Analytics**.
- For "how do we build or code it?" questions, use **Mechanism Design**, **Vendors**, and **Software & Controls**.
- Rule of thumb: copy methods, not robots. Study why a design worked for that team's strategy and resources.

---

## Official Sources (Rules & Season Info)

| Resource | What it's for | URL |
|---|---|---|
| FIRST Season Materials | Game manual, team updates, field drawings, KitBot, kickoff assets. As of late October 2026 it lists REBUILT Team Update 22. | https://www.firstinspires.org/resources/library/frc/season-materials |
| Official Q&A System | Official rule rulings. Anyone can read or search; one account per team can ask questions. | Linked from Season Materials page above |
| 2026 Game Manual (PDF) | Authoritative REBUILT rules | https://firstfrc.blob.core.windows.net/frc2026/Manual/2026GameManual.pdf |
| FRCManual.com | Searchable, hyperlinked version of the manual (G, R, I, T, E rule sections, glossary). Unofficial, so check the PDF for anything that matters. | https://www.frcmanual.com/2026/game-overview |
| FRC Events API | Official FMS match and event data, synced from event fields | https://frc-api-docs.firstinspires.org/ |
| SOLIDWORKS FIRST KOP downloads | CAD models of the Kit of Parts, drive base configurations, and KitBot (SOLIDWORKS, STEP, 3DXML) | https://www.solidworks.com/product/students/download-first-kit-parts-frc-and-ftc |

Notes:
- FRCManual.com's 2026 game overview says autonomous lasts the **first 20 seconds** of the match. Check this against the official PDF, since some older notes say 10 seconds.
- **2027 heads-up:** the 2027 game, BIOCORE, kicks off January 9, 2027. SystemCore, a Limelight-designed controller built on a Raspberry Pi Compute Module 5, replaces the roboRIO. Processor, OS, Driver Station, Java package names, and much of the wiring all change ([LearnFRC SystemCore explainer](https://learnfrc.com/blog/frc-systemcore)). Plan offseason training around it.

---

## Community Hubs (Where Ideas Get Shared)

| Resource | Best for | URL |
|---|---|---|
| Chief Delphi | Main FRC forum: technical threads, CAD and code releases, build blogs, whitepapers (CD-Media). Search before you ask. | https://www.chiefdelphi.com/ |
| FRC Open Alliance | Teams that publish CAD, code, and media publicly and post weekly build-thread updates on Chief Delphi. Posts are tagged `#openalliance` and `#frc[teamNumber]`. | https://theopenalliance.org/frc |
| Open Alliance guidelines (GitHub) | How the program works and how to join | https://github.com/OpenAllianceFRC/info |
| David's Design Server (Discord) | Design-focused Discord with robot photo and mechanism example channels (e.g., `robot-pics-2026`, `mechanism-examples`) | Find the invite via FRCDesign.org or Chief Delphi |
| Unofficial FRC Discord | General Q&A and real-time help | discord.gg/frc |
| r/FRC | Casual discussion, quick questions | https://www.reddit.com/r/FRC/ |

How to search Chief Delphi well:
- Search `[team number] [year] CAD release`, `build thread`, `technical binder`, or `[mechanism] prototype`.
- Filter by the **Technical** category and tags like `robot`, `cad`, `2026`.

---

## Robot Specs, Reveals & Teardowns

| Resource | Best for | URL |
|---|---|---|
| FUN Robotics Network, "Behind the Bumpers" | Video walkthroughs of top robots: mechanisms, motors, ratios, design reasoning. Example: 316 LUNATECS' 2026 drum-shooter robot. | https://funroboticsnetwork.com/ · Example: https://www.youtube.com/watch?v=8WyrDVajhXY |
| FRC 2026 REBUILT Robot Study Dashboard | Community dashboard of 2026 robots with public CAD, binders, or code, sorted by EPA, with subsystem notes and links | https://dmeglan.github.io/frc2026-rebuilt-dashboard/ |
| Robot in 3 Days (Ri3D) | Builds a robot from kickoff in 3 days. Strong for early-season ideas and showing the design process. | https://www.youtube.com/watch?v=Nqsh2xfR6wE (2026 strategy video) |
| Ri3D teams (e.g., Unqualified Quokkas) | Daily build vlogs during kickoff week | https://www.youtube.com/watch?v=PBHYWGQ7CJM |
| Team robot pages | Season recaps with awards and results (example: 4099) | https://www.team4099.com/about/robots/ |

Top 2026 robots worth studying, by EPA on the [Robot Study Dashboard](https://dmeglan.github.io/frc2026-rebuilt-dashboard/): 4414 HighTide, 254 Cheesy Poofs, 7769 The CREW, 2231 OnyxTronix, 1323 MadTown, 2056 OP Robotics, 1690 Orbit, 27 RUSH, 1114 Simbotics, 2481 Roboteers, 5687 Outliers.

---

## CAD Releases & Design Learning

| Resource | Best for | URL |
|---|---|---|
| FRCDesign.org Learning Course | Free Onshape course that takes students from zero to modeling a full robot, using FRC-specific projects | https://frcdesign.org/learning-course/ |
| FRCDesignApp / FRCDesignLib | Onshape app for inserting COTS FRC parts (newer replacement for the MKCad workflow) | https://www.chiefdelphi.com/t/introducing-the-new-frcdesignapp/507335 |
| Chief Delphi CAD releases | Full robot CAD from top teams, usually Onshape links (e.g., 1706's 2026 Champs-version CAD) | https://www.chiefdelphi.com/ (search "2026 CAD release") |
| Spectrum 3847 Resources | Curated FRC CAD collection, mechanism explorations, training modules, presentations, a $2,000 purchase guide, a young-team guide, inexpensive build tips | https://www.spectrum3847.org/resources/ |
| Spectrum Swerve course | Interactive course on swerve: module internals, kinematics, odometry, gyros, field-centric drive | https://spectrum3847.org/training/swerve/ |

---

## Technical Binders & Whitepapers

| Resource | Best for | URL |
|---|---|---|
| Team 254 Documents & Code | Tech binders that walk from game analysis to robot requirements to subsystems. A benchmark for design documentation. | https://www.team254.com/resources/ · Example: https://media.team254.com/2022/10/8f2407a5-Team_254_Tech_Binder_2022.pdf |
| Chief Delphi CD-Media Papers | Classic engineering papers, such as Ether's swerve kinematics derivation | https://www.chiefdelphi.com/t/paper-4-wheel-independent-drive-independent-steering-swerve/107383 |
| Team 971 Spartan Robotics | Workshops, resources, and a team handbook (useful for safety and program structure) | https://www.spartanrobotics.org/governance |
| NASA Robotics Alliance FRC Resources | Directory of team-published resources, sorted by team number | https://robotics.nasa.gov/frc-resources/ |

---

## Strategy & Scouting

| Resource | Best for | URL |
|---|---|---|
| Karthik's "Effective FIRST Strategies" (1114) | Core strategic-design talk: game analysis, chokehold strategies, cost-benefit, task priority, tradeoffs, scouting. Watch it every kickoff. | 2019: https://www.youtube.com/watch?v=ZI6QJpVO4Eo · 2012: https://www.youtube.com/watch?v=Apk_X-maRf8 |
| 1678 Citrus Circuits 2026 Scouting & Strategy Release | Scouting whitepaper, kickoff README, and process docs. Explains how they estimated FUEL scored per robot from shooting time and scoreboard changes. | https://www.chiefdelphi.com/t/1678-citrus-circuits-2026-scouting-and-strategy-release/521537 |
| 1678 older scouting whitepaper | How an electronic scouting system evolves year to year | https://www.chiefdelphi.com/uploads/default/original/3X/4/6/46f2c2033e56a0d56493d74131f53fffbbeffee4.pdf |
| FRC Scouting app (836 RoboBees) | Ready-made Android scouting app with CSV export | https://play.google.com/store/apps/details?id=org.growingstems.scouting |
| LearnFRC | Plain-language explainers on Championship structure, divisions, Einstein, qualification | https://learnfrc.com/blog/frc-world-championship-explained |

---

## Data & Analytics

| Resource | Best for | URL |
|---|---|---|
| The Blue Alliance (TBA) | Match results, rankings, videos, event history; has an API and mobile app | https://www.thebluealliance.com/ |
| Statbotics | EPA (Expected Points Added) ratings, a moving-average model of each team's scoring contribution, including component EPAs. Use it for pre-event scouting and match prediction. | https://www.statbotics.io/blog/intro |
| Statbotics source code | Open-source EPA model if you want to run your own analysis | https://github.com/avgupta456/statbotics |
| FRC Events API | Official raw data | https://frc-api-docs.firstinspires.org/ |

Note: as of mid-October 2026, the [Robot Study Dashboard](https://dmeglan.github.io/frc2026-rebuilt-dashboard/) said the Statbotics data API was offline, so it calculated some EPAs locally from TBA data.

---

## Mechanism Design Calculators

| Resource | Best for | URL |
|---|---|---|
| ReCalc | Calculators for linear mechanisms (elevators, climbers), arms, flywheels, belts, chains, pneumatics, and more, with motor models (e.g., Kraken X60 FOC), current limits, and feedforward/LQR gain estimates | https://www.reca.lc/ |
| JVN Design Calculator | Classic spreadsheet for mechanism forces and speeds with standard FRC motors (Google Sheets version from Pearce Robotics) | https://team.pearcerobotics.com/training/jvn-calculator |

---

## Starter / Low-Resource Robot Designs

| Resource | Best for | URL |
|---|---|---|
| FIRST KitBot | Official baseline robot each season (instructions on Season Materials page) | https://www.firstinspires.org/resources/library/frc/season-materials |
| 118 Everybot | About $1,500 robot built with basic tools, designed to compete in district finals. Good for rookies and as a second robot. | https://www.118everybot.org/ |
| REV ION FRC Starter Bot | REBUILT starter bot built from the REV ION system, with MAXSwerve or EasySwerve options | https://www.revrobotics.com/ion/frc-starter-bot/ |

---

## Vendors (Specs, User Guides, CAD)

Vendor product pages often have the most reliable dimensions, ratios, and STEP files.

| Vendor | Known for | URL |
|---|---|---|
| WestCoast Products (WCP) | Kraken X60/X44 motors, drivetrain and swerve parts, gearboxes, field elements | https://wcproducts.com/collections/viewall |
| Swerve Drive Specialties (SDS) | MK4i/MK4n/MK5n swerve modules and spare parts | https://www.swervedrivespecialties.com/collections/all |
| REV Robotics | NEO motors, SPARK controllers, MAXSwerve, ION build system | https://www.revrobotics.com/ |
| CTR Electronics (CTRE) | TalonFX, CANcoder, Pigeon 2, CANivore | https://store.ctr-electronics.com/ |
| The Thrifty Bot | Lower-cost common parts, including a cycloidal gearbox for high-torque arms designed with 118 | https://www.thethriftybot.com/ |
| AndyMark | KOP parts, field elements, practice perimeter | https://www.andymark.com/ |

---

## Software & Controls

| Resource | Best for | URL |
|---|---|---|
| WPILib Docs | Official FRC software docs: command-based programming, control theory, simulation, kinematics, data logging | https://docs.wpilib.org/ |
| CTRE Phoenix 6 Docs | TalonFX/Kraken config, Tuner X, Motion Magic, swerve generator; Java/C++/Python APIs | https://v6.docs.ctr-electronics.com/ |
| PathPlanner (3015) | Bézier path planning, event markers, auto builder, hot reload, on-the-fly pathfinding | https://pathplanner.dev/home.html |
| Choreo | Time-optimal trajectory planner for swerve and differential drive, with ChoreoLib | https://github.com/SleipnirGroup/Choreo |
| YAGSL | JSON-configured generic swerve library. The 2027 version targets WPILib 2027 and SystemCore. | https://yagsl.yassrobotics.com/ |
| AdvantageKit / AdvantageScope (6328) | Logging, replay-in-simulation, and telemetry visualization. AdvantageScope works without AdvantageKit. | https://github.com/Mechanical-Advantage/advantagekit |
| PhotonVision | Free vision: AprilTag pose estimation, object detection, simulation, code examples | https://docs.photonvision.org/en/latest/index.html |
| Limelight Docs | Limelight setup, networking (static IP 10.TE.AM.11), MegaTag localization, neural detectors | https://docs.limelightvision.io/ |
| Team GitHub orgs | Real competition code from top teams (6328, 254, 1678, and others) | https://github.com/Mechanical-Advantage/ |

---

## Suggested Research Workflow

1. **Kickoff (Days 1–3):** Read the manual. Watch Karthik's strategy talk. Do game analysis: list scoring actions with point values, time per action, and RP thresholds. Follow Ri3D builds.
2. **Week 1:** Read Open Alliance build threads and Chief Delphi prototype threads. Prototype the scoring mechanism and size it with ReCalc.
3. **Weeks 2–3:** CAD in Onshape with FRCDesignLib parts, using prior-season CAD releases of similar mechanisms as references. Hold design reviews.
4. **Weeks 4–8:** Follow other teams' builds. Track the Q&A and Team Updates. Build autos with PathPlanner or Choreo and log everything with AdvantageKit.
5. **Competition season:** Watch Behind the Bumpers and reveals. Scout with Statbotics, TBA, and your own app. Use the 1678 whitepaper as a model.
6. **Offseason:** Study tech binders and CAD from top teams. Run Spectrum and FRCDesign training. Prepare for the next control-system or rule changes (SystemCore in 2027).

## Safety Reminder

Community CAD and designs do not replace your own safety review. Before copying any mechanism, check pinch points, stored energy (springs, flywheels, pneumatics), and electrical protection.
