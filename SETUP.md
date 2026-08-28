# Setup

You need the same Java toolchain as Lab 1, and nothing new. The property-based
testing library (jqwik) and the coverage tool (JaCoCo) come in through Maven.

## 1. Java and Maven

You installed both for Lab 1. Confirm they are still on your path.

- A JDK, version 21 or newer. Check with `java --version`.
- Maven 3.8 or newer. Check with `mvn --version`.

## 2. Build and run the tests

From this directory:

```
mvn test
```

Everything should be green. The example-based suite and the one provided property both
pass. The first run downloads jqwik and JaCoCo, so it may take a moment.

To see the coverage, open `target/site/jacoco/index.html` after running the tests and
click through to `AvailabilityCalculator`. Notice how high it is. The report covers
everything `mvn test` ran, so it includes the property as well as the example suite.

## 3. Editor

Any editor or IDE that imports a Maven project. This lab assumes you run the tests
from the command line with `mvn test`, which runs both the example-based tests and the
properties, the same tests CI runs. If your editor's/IDE's test runner also picks up the
property-based tests in `AvailabilityProperties`, use it. If it does not, you don't have to
spend time setting it up (unless you really want to).

## 4. Continuous integration

CI is already configured. When you push, `.github/workflows/ci.yml` runs `mvn test` and
reports a green or red gate on the Actions tab of your fork, one run per push. One thing
you do have to do once: GitHub disables workflows on a fresh fork, so open the Actions tab
on your fork and click the button to enable them before your first push. The lab handout's
Setup step 4 has the details.
