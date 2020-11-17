# jSicko Tutorials

This repository contains basic tutorials to use jSicko - Java Simple Contract 
Checker, updated to version `1.0.0-M4`. This version of the tutorials includes a few
examples related to the support of exceptional behaviors and inherited (weakened) preconditions,
that are now supported by jSicko.

## Contents

This repository contains a few classes and tests that show how to use jSicko.

* `ch.usi.si.codelounge.jsicko.tutorials.simple.Collections`, that contains a few static methods boxing Java utility methods for collections;
* `ch.usi.si.codelounge.jsicko.tutorials.simple.Math`, with a few methods manipulating doubles; 
* `ch.usi.si.codelounge.jsicko.tutorials.stack`, containing the contract for a `Stack` interface and two implementing classes, `impl.GoodStack` and `impl.BadStack`.
* `ch.usi.si.codelounge.jsicko.tutorials.inheritance`, containing the contract for an abstract collection, an abstract list, and two classes (`ArrayList` and `HashSet`).

In `src/test`, you will find jUnit 5 tests that exercise a few behaviors of the classes above.
In most cases, the test exercise normal behavior, exceptional behavior, and some violated preconditions.
In the case of `BadStack` and `BadLists` implementations, the classes violate their contracts,
and thus the tests also cover those exceptional cases (i.e., violated postconditions).

## Use with maven

If you want to run the jSicko tutorials with maven, just clone this
repository and run `mvn clean compile test`. The output will show compilation with a few
detailed note of how jSicko operates (including the rewriting of methods to
add pre/post-condition and invariant checks), and eventually the correct
execution of test cases.

## Use with IntelliJ IDEA

To run and play with this tutorial project with IntelliJ IDEA, clone this repository and import it. 
Most of the maven settings will be imported, except the compiler option that enables the jSicko
instrumentation. 

To enable it, open the IntelliJ IDEA Preference window, and look for `Builder, Execution, Deployment / Compiler / Java Compiler` on the settings tree. In the bottom of the settings panel, you can add a project-specific option by adding `-Xplugin:JSickoContractCompiler` as a compilation option for the `jSicko-tutorials` module.



