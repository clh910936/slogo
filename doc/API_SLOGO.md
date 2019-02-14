1) Parsing takes place when the command is entered. Needs correct syntax (be checked)

2) Separate the pieces of the command and call front-end internal APIs

3) Errors are detected while parsing (check for valid command, then check for right parameters, and check syntax). Throw error, then pass to front-end to display error message

4) After we parse, commands know parameter values, and know which methods to call in the front-end

5) Update per method call
