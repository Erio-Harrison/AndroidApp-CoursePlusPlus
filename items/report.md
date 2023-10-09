# [G01 - Course++] Report

The following is a report template to help your team successfully provide all the details necessary for your report in a structured and organised manner. Please give a straightforward and concise report that best demonstrates your project. Note that a good report will give a better impression of your project to the reviewers.

Note that you should have removed ALL TEMPLATE/INSTRUCTION textes in your submission (like the current sentence), otherwise it hampers the professionality in your documentation.

*Here are some tips to write a good report:*

* `Bullet points` are allowed and strongly encouraged for this report. Try to summarise and list the highlights of your project (rather than give long paragraphs).*

* *Try to create `diagrams` for parts that could greatly benefit from it.*

* *Try to make your report `well structured`, which is easier for the reviewers to capture the necessary information.*

*We give instructions enclosed in square brackets [...] and examples for each sections to demonstrate what are expected for your project report. Note that they only provide part of the skeleton and your description should be more content-rich. Quick references about markdown by [CommonMark](https://commonmark.org/help/)*

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#application-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#team-meetings)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)

## Administrative
- Firebase Repository Link: https://console.firebase.google.com/project/courses-and-comments/overview

## Team Members and Roles
The key area(s) of responsibilities for each member

| UID   |  Name  |   Role |
|:------|:------:|-------:|
| u7541840 | Haoren Hu | database design, data generation, action simulation |
| u7565753 | Min Su Park | authentication, posting comment, android activity design |
| u7618313 | Porntipa Poonpolsub | data load, android activity design |
| u7582380 | Yuki Misumi | architecture design, refactoring, search functionality |


## Summary of Individual Contributions

Specific details of individual contribution of each member to the project.

Each team member is responsible for writing **their own subsection**.

A generic summary will not be acceptable and may result in a significant lose of marks.

*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in pecentage of the contribution of each member to the whole code implementation, e.g. 30%.]*

*you should ALSO provide links to the specified classes and/or functions*
Note that the core criteria of contribution is based on `code contribution` (the technical developing of the App).

*Here is an example: (Note that you should remove the entire section (e.g. "others") if it is not applicable)*

0. **UID1, Name1**  I have 30% contribution, as follows: <br>
  - **Code Contribution in the final App**
    - Feature A1, A2, A3 - class Dummy: [Dummy.java](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java)
    - XYZ Design Pattern -  class AnotherClass: [functionOne()](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43), [function2()](the-URL)
    - ... (any other contribution in the code, including UI and data files) ... [Student class](../src/path/to/class/Student.java), ..., etc.*, [LanguageTranslator class](../src/path/to/class/LanguageTranslator.java): function1(), function2(), ... <br><br>

  - **Code and App Design** 
    - [What design patterns, data structures, did the involved member propose?]*
    - [UI Design. Specify what design did the involved member propose? What tools were used for the design?]* <br><br>

  - **Others**: (only if significant and significantly different from an "average contribution") 
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* <br><br>

1. **u7541840, Haoren Hu**  I have 25% contribution, as follows: <br>
  - **Code Contribution in the final App**
  - **Code and App Design** 
  - **Others**: (only if significant and significantly different from an "average contribution") 

2. **u7565753, Min Su Park**  I have 25% contribution, as follows: <br>
  - **Code Contribution in the final App**
  - **Code and App Design** 
  - **Others**: (only if significant and significantly different from an "average contribution") 

3. **u7618313, Porntipa Poonpolsub**  I have 25% contribution, as follows: <br>
  - **Code Contribution in the final App**
  - **Code and App Design** 
  - **Others**: (only if significant and significantly different from an "average contribution") 

4. **u7582380, Yuki Misumi**  I have 25% contribution, as follows: <br>
  - **Code Contribution in the final App**
  - **Code and App Design** 
  - **Others**: (only if significant and significantly different from an "average contribution") 
    - Designed the overall architecture

## Application Description

*[What is your application, what does it do? Include photos or diagrams if necessary]*

*Here is a pet specific application example*

*PetBook is a social media application specifically targetting pet owners... it provides... certified practitioners, such as veterians are indicated by a label next to their profile...*

### Application Use Cases and or Examples

*[Provide use cases and examples of people using your application. Who are the target users of your application? How do the users use your application?]*

*Here is a pet training application example*

*Molly wants to inquiry about her cat, McPurr's recent troublesome behaviour*
1. *Molly notices that McPurr has been hostile since...*
2. *She makes a post about... with the tag...*
3. *Lachlan, a vet, writes a reply to Molly's post...*
4. ...
5. *Molly gives Lachlan's reply a 'tick' response*

*Here is a map navigation application example*

*Targets Users: Drivers*

* *Users can use it to navigate in order to reach the destinations.*
* *Users can learn the traffic conditions*
* ...

*Target Users: Those who want to find some good restaurants*

* *Users can find nearby restaurants and the application can give recommendations*
* ...

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

<hr> 

### Application UML

![ClassDiagram](media/architecture.png)

<hr>

## Code Design and Decisions

This is an important section of your report and should include all technical decisions made. Well-written justifications will increase your marks for both the report as well as for the relevant parts (e.g., data structure). This includes, for example,

- Details about the parser (describe the formal grammar and language used)

- Decisions made (e.g., explain why you chose one or another data structure, why you used a specific data model, etc.)

- Details about the design patterns used (where in the code, justification of the choice, etc)

*Please give clear and concise descriptions for each subsections of this part. It would be better to list all the concrete items for each subsection and give no more than `5` concise, crucial reasons of your design.

<hr>

### Data Structures

*[What data structures did your team utilise? Where and why?]*

Here is a partial (short) example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. *LinkedList*
   * *Objective: used for storing xxxx for xxx feature.*
   * *Code Locations: defined in [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
   * *Reasons:*
      * *It is more efficient than Arraylist for insertion with a time complexity O(1)*
      * *We don't need to access the item by index for xxx feature because...*
      * For the (part), the data ... (characteristics) ...

1. *AVLTree*
   * *Objective: used for storing various keys of comments for [Search], [Search-Filter] feature and caching comments fetched from Firebase Realtime Database (hereafter FirebaseDB).*
   * *Code Locations: defined in [AVLTree](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/data/avl/AVLTree.java) and [Node](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/data/avl/Node.java); processed using [CommentFindingStrategy#find](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/data/comment/CommentFindingStrategy.java#L12) and the implementation classes. 
   * *Reasons:*
      * *It is more efficient than arrays for searching with a time complexity O(log N)*
   * *Other outstandings:*
      * For the comment filter, it supports finding comments in a given range.

<hr>

### Design Patterns
*[What design patterns did your team utilise? Where and why?]*

1. *Singleton Pattern*
   * *Objective: used for ensuring a class object is created only once.*
   * *Code Locations: defined in [CommentDatasource](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/data/comment/CommentDatasource.java) and all the other classes implementing a repository interface at `data` package.
   * *Reasons:*
      * to prevent from data fetching by multiple instances which may result in unwanted behaviours, including reading an incorrect state of the cache.

2. *Factory Pattern*
   * *Objective: used for providing a state object creation capability out of the box.*
   * *Code Locations: defined in [StateFactory](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/model/query/tokenizer/state/StateFactory.java); processed using [StateMachine#getNextState](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/model/query/tokenizer/StateMachine.java#L80) and [StateMachine#findStateToTransition](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/model/query/tokenizer/StateMachine.java#L89)
   * *Reasons:*
      * It encapsulates nitty gritty details of [State](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/model/query/tokenizer/state/State.java) class, so that the responsibility of other classes no longer is polluted by them.

3. *DAO Pattern*
   * *Objective: used for segregating a concern of data layer out of domain and service layer.*
   * *Code Locations: defined in [CommentRepository](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/model/comment/CommentRepository.java) and [CommentDatasource](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/data/comment/CommentDatasource.java); processed using [CommentService](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/service/comment/CommentService.java). In addition, all the other repository interfaces defined at `domain` layer and the corresponding implementations and service classes.
   * *Reasons:*
      * It encapsulates nitty gritty details of [State](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/model/query/tokenizer/state/State.java) class, so that the responsibility of other classes no longer is polluted by them.

4. *Strategy Pattern*
   * *Objective: used for addomg extensibility of the branching at the comment finder.* 
   * *Code Locations: defined in [CommentFindingStrategy](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/data/comment/CommentFindingStrategy.java) and the implementations; processed using [CommentFinder#processComparison](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/main/java/com/example/couseplusplus/data/comment/CommentFinder.java#L36).
   * *Reasons:*
      * It encapsulates nitty gritty details of each comment finding logic per token type.
      * It gives us a modularity to add/remove/update any of the logics without touching anything on CommentFinder. This minimises the area of change and the associated risk to a bug.


<hr>

### Parser

### <u>Grammar(s)</u>
*[How do you design the grammar? What are the advantages of your designs?]*
*If there are several grammars, list them all under this section and what they relate to.*

Production Rules:
    <query> ::= <expression> | <expression> <condition> <query>
    <expression> ::= helpful <operator> <number> | enrol <operator> <enroldate> | posted <operator> <date> | text <fuzzy> <string> | ( <query> )
    <condition> ::= & | |
    <operator> ::= < | > | = | <= | >=
    <fuzzy> ::= ~
    <number> ::= <digit\0> <number> | <digit> | -<digit\0> <number> | -<digit>
    <digit> ::= [0-9]
    <string> ::= "<characters>"
    <characters> ::= <character> <characters> | <character>
    <character> ::= // anything except "
    <date> ::= <digit><digit><digit><digit>-<digit><digit>-<digit><digit>
    <enroldate> ::= <digit><digit><digit><digit><semester>
    <semester> ::= S1 | S2

### <u>Tokenizers and Parsers</u>

*[Where do you use tokenisers and parsers? How are they built? What are the advantages of the designs?]*
- [Tokenizer]() and [Parser]() are used at [QueryParseTreeCreator#create]() to parse [Query]() to find the matching [Comment]()s later.
- Tokenizer is build using [StateMachine]() based on the state diagram below. The main advantage is its modularity - we can add/remove/update any states by only changing the very state class without polluting the actual tokenizer class.
![state-diagram](media/state-diagram.png)
- We also added the graceful variants [GracefulTokenizer](), [GracefulParser]() to allow the user type an incomplete query to still search the matching comments at its best effort.

<hr>

### Others

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

<br>
<hr>

## Implemented Features
*[What features have you implemented? where, how, and why?]* <br>
*List all features you have completed in their separate categories with their featureId. THe features must be one of the basic/custom features, or an approved feature from Voice Four Feature.*

### Basic Features
1. [LogIn]. Description of the feature ... (easy)
   * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * Description of feature: ... <br>
   * Description of your implementation: ... <br>

2. [DataFiles]. Description  ... ... (...)
   * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
   * Link to the Firebase repo: ...

3. [LoadShowData].
4. [Search].

   <br>

### Custom Features
Feature Category: Search-related features <br>
1. [Search-Invalid]. Description of the feature  (easy)
   * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * Description of your implementation: ... <br>
     <br>

2. [Search-Filter]. Description ... ... (medium)
   ... ...
   <br><br>

Feature Category: Firebase Integration <br>
3. [FB-Auth] Description of the feature (easy)
   * Code: [Class X, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * [Class B](../src/path/to/class/file.java#L30-85): methods A, B, C, lines of code: 30 to 85
   * Description of your implementation: ... <br>

4. [FB-Persist].

<hr>

### Surprised Features

- If implemented, explain how your solution addresses the task (any detail requirements will be released with the surprised feature specifications).
- State that "Suprised feature is not implemented" otherwise.

Suprised feature is not implemented

<br> <hr>

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*
*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

*Here is an example:*

1. *Bug 1:*
   - *A space bar (' ') in the sign in email will crash the application.*
   - ...

2. *Bug 2:*
3. ...

<br> <hr>


## Testing Summary

*[What features have you tested? What is your testing coverage?]*
*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

*Here is an example:*

1. Tests for Search
   - Code: [TokenizerTest Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java) for the [Tokenizer Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43)
   - *Number of test cases: ...*
   - *Code coverage: ...*
   - *Types of tests created and descriptions: ...*

2. Tests for AVL Tree
   - Code: [AVLTreeTest](https://gitlab.cecs.anu.edu.au/u7582380/ga-23s2/-/blob/main/src/app/src/test/java/com/example/couseplusplus/data/avl/AVLTreeTest.java) for [AVLTree]()
   - *Number of test cases: ...*
   - *Code coverage: ...*
   - *Types of tests created and descriptions: ...*

...

...

<br> <hr>


## Team Management

### Meetings Records
* Link to the minutes of your meetings like above. There must be at least 4 team meetings.
  (each commited within 2 days aftre the meeting)
* Your meetings should also have a reasonable date spanning across Week 6 to 11.*


- *[Team Meeting 1](meeting1.md)*
- ...
- ...
- [Team Meeting 4](link_to_md_file.md)
- ... (Add any descriptions if needed) ...

<hr>

### Conflict Resolution Protocol
*[Write a well defined protocol your team can use to handle conflicts. That is, if your group has problems, what is the procedure for reaching consensus or solving a problem?
(If you choose to make this an external document, link to it here)]*

This shall include an agreed procedure for situations including (but not limited to):
- e.g., if a member fails to meet the initial plan and/or deadlines
- e.g., if your group has issues, how will your group reach consensus or solve the problem?
- e.g., if a member gets sick, what is the solution? Alternatively, what is your plan to mitigate the impact of unforeseen incidents for this 6-to-8-week project? 
