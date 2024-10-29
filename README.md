# Description
This repository contains test automation framework based on Java, TestNG, Maven, Selenium and using as design patterns the Page Object Model and Page Factory
and is developed as final project for the Automation QA course of Skillo Academy

Website under test: http://training.skillo-bg.com:4300/posts/all

# Prerequisites
You need to have installed on your machine:
1. JDK - version 22
2. IntelliJ - latest community version

# Overview

- Tests are located in src/test/java/tests.
- Before and after methods for the tests are in src/test/java/tests/BaseTest.
- In case a test fails, a screenshot is taken. All screenshots are located in src/test/resources/screenshots.
- Code for Page Factory model is located in src/main/java/page/factory.
- Code for Page Object Model is located in src/main/java/page/object/model.
- Test suites are located in testng.xml.

# How to run a test with Page Object Model or Page Factory
- Open the desired test class, e.g. LoginTests.
- Check the imports - if they are using "page.object.model.", you will be executing the tests with Page Object Model. If you want to use the Page factory, you need to use to change the imports to "page.factory.".
- Run the whole class or a single test only.
- If a test fails, go to src/test/resources/screenshots and check the screenshot.