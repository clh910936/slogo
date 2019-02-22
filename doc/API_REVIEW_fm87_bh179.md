fm87, bh179

Part 1
What about your API/design is intended to be flexible?
- He created separate classes for separate displays, and an API for every class. They implemented a few methods that allow the displays to communicate with each other.

How is your API/design encapsulating your implementation decisions?
- The API allows for the main view window to handle all the subwindows and add more subwindows as needed.

What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
- The front-end will incorporate a try-catch system that will display an error message once catching an exception, like a syntax error in a command.

Why do you think your API/design is good (also define what your measure of good is)?
- They think their API is very flexible for new commands/classes and powerful in that the backend can communicate a lot/act a lot on the frontend with the provided interface methods.

Both people should complete Part 1 before continuing on to Part 2.

Part 1
What about your API/design is intended to be flexible?
- One internal API is implmemented by all the subclasses. Adding more classes would be easy because methods are added to the interfaces.

How is your API/design encapsulating your implementation decisions?
- The back end can make commands on the turtle, but doesn't know how the commands are implmemented. The front end handles that aspect. 

What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
- When there is a bad command, a popup box will show up and display a general syntax error.

Why do you think your API/design is good (also define what your measure of good is)?
- The API is very flexible and enables future implementation of subclasses that easily extend functionality.

Both people should complete Part 1 before continuing on to Part 2.

*We had a long discussion about our design decisions and didn't get to complete this last part*

Part 2

How do you think Design Patterns are currently represented in the design or could be used to help improve the design?

What feature/design problem are you most excited to work on?

What feature/design problem are you most worried about working on?

Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).