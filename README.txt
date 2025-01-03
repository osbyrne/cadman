Scala is a programing language. It enforces immutable state, pure functions and consistent types.

This project aims to provide a way to write CAD-as-code, but more flexible than writing CAD source code by hand. Basically a 1000x worse version of https://zoo.dev/docs/kcl

project instructions : https://moodle.myefrei.fr/pluginfile.php/586349/mod_resource/content/1/DSL.md
grading will be based on :
- proper behavior of the implementation
- adherence to functional programming principles
- test coverage
- project organization
- quality and completeness of the documentation

We will write an *internal DSL* because it's easier, especially in Scala

our tasks : 

# 1. Identify a problem domain and explain why a DSL is suitable for it.

I tried to make a CAD model, but all viewers I tried at the time (before I discovered KCL) were mouse-driven and not suited for my use.


# 2. Define the goal of your DSL and its expected usage.

goals of the DSL : make it easy to print objects based on known dimensions, and some variable dimensions. Sometimes it's OK if width >= 5cm, but height has to be 4cm±5mm
So it should be used as such : 
- collect all specs of your piece
- ask chatGPT o3 to write the CAD source code
- profit

I have no idea how to make this fr gonna purchase o3 for this project specificially


# 3. Create an internal DSL using **Scala 3**, following functional programming principles.

Fair enough



# 4. Provide comprehensive examples and documentation.

o3 ftw
also vitepress :fire_emoji: