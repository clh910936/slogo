* Duplication refactoring: none - did not have any duplication.

* General Refactoring:
    * **View/ViewAPI:** Initially we had a ViewAPI as well as a View abstract superclass. The ViewAPI outlined the required methods whereas View created a visual template that a pane could simply be added to such that all the Views were uniform. While both served important purposes, they do not need to be separate. Their functionality was merged such that ViewAPI could be removed.

  * **Parsing/SyntaxHandler:** Originally had a bunch of switch cases for the different syntax symbols (ListStart, Command, ListEnd, etc.). I wasn't sure how to refactor this because there were different operations done for each symbol. We decided to use method reflection so that there was a separate command for each syntax symbol.
  * **Util/ColorUtil**: Had one giant method setting up all the colors that could be referenced by someone inputting RGB values. I realized that that method can just be a private final list of the static class, so I extracted it from the method. That reduced the number of lines in the method from 144 to 0, because I could from then on just reference that list.