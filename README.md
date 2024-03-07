During the UPenn MOSA Fall 2022 Hackathon, my teammate Benjamin Lam and I developed a terminal application in Java that schedules a team of employees based on each person's availability and desired number of shifts.

Since then, I have been working on turning this project into a full-stack web application using Vue and Spring, and hosted at www.poodlescheduler.com

Poodle is inspired by Simon, a young man with muscular dystrophy whom I had the privilege of working as a caregiver for, over two years in Copenhagen, DK. In Denmark, the disabled receive government-funded care, ensuring them equal opportunity, mobility, and agency. While just, and equitable, it became clear that the program also inadvertently imposed certain disadvantaged.

Each month, Simon spent hours marking a calendar with colored pencils, trying to find a schedule that aligned with each caregiver's availability and quota; an effort that is not required by those who do not receive care.

I realized that this represents a classic Constraint Satisfaction Problem during my Artificial Intelligence class in the semester after the Hackathon. In this context, one caregiver can be assigned to any day, and since the shifts are 24 hours long, no one can work consecutive days. I was excited to discover that the solution Benjamin and I came up with was essentially the AC-3 algorithm with forward checking and constraint propagation.

Moving forward, I hope to:
- build a log-in system to for asynchronous availability entry
- generalize the algorithm and interface to allow for flexible, custom scheduling beyond the current one-employee-per-day model
- improve search efficiency through heuristic variable selection and constraint learning.

Moreover, I believe there might also be novel improvements to be made in turning CSP solvers into informed search algorithms by using search states as inputs and outcomes as labels for ML models to derive heuristic functions that guide search down promising branches.During the UPenn MOSA F
