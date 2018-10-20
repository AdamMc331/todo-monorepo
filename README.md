# Todo Monorepo

## Goal

The purpose of this monorepository is to host a number of todo list applications that each demonstrate a type of architecture or popular library in Android development.

## Modules

The easiest way to demonstrate a specific architecture/library, is to isolate it from other information to limit confusion. For that reason, each todo application will simply be built with a specific architecture, or using a specific library. They will not share any logic between them. They will, however, be expected to look and behave the same between modules to provide consistency and to reinforce the focus on the architecture/library the module is intended for.

Each module will be tested as much as it can be. This may include a mix of unit tests and UI tests.

You can find modules that explain:

* [MVP](/todo-mvp)
* [MVVM](/todo-mvvm)
* [CORE](/core) which contains shared code.

## Sample

While the title of this gif says 'MVP', this is what you can expect the todo list app in each module to look like. A landing page with a list of sample items, a floating action button that leads to an add task screen, where you can enter the task and be sent back to the main screen. 

You can view the sample [here](/images/sample.gif).

## Contributions / Requests

If you find thewre are any architectures or libraries you're interested in that are not listed here, please submit an issue. If you'd like to contribute one yourself, please submit an issue and clarify that you would like to work on it. I will do my best to comment on issues that I am actively working on as well.