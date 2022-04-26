# Try it out!

[Play Here](https://pixel-war.netlify.app/)

You may need to refresh a few times if no one connected in a while. This is because the server hosting the API shuts down after being inactive.
Thank you for your understanding.

# Using the project

Made with spring boot and Rest-API.
Maven project.
You will need java 11, postgresql, and you might need npm. 

You will also need to set the different environment variables in your IDE, which include:
 - `PSQL_DATABASE_URL` the url (including port) to access your DB
 - `PSQL_DATABASE_USERNAME` the username to access your DB (don't forget to give it permission)
 - `PSQL_DATABASE_PASSWORD` the password of the user you chose
 - `PROTECTED_METHOD_PASSWORD` a String you get to pick, is needed to protect sensitive post methods from random users

## List of Static Endpoints
 - `$URL$/grid/get` GET method, returns the current grid in its entirety, response object structure: `{id (Long), Grid (int[][]), height (int), width (int)}`
 - `$URL$/grid/set` POST method, sets and returns the current grid with a new one, body message structure: `{grid (int[][]), password (String)}`, response object structure: `{id (Long), Grid (int[][]), height (int), width (int)}`
 - `$URL$/grid/resize` POST method, resizes and returns the current grid, body message structure: `{width (int), height (int), password (String)}`, response object structure: `{id (Long), Grid (int[][]), height (int), width (int)}`
 - `$URL$/grid/fill_area` POST method, fills rectangular area with selected color and returns number of pixels overwirtten, body message structure: `{x1 (int), y1 (int), x2 (int), y2 (int), fillColor (int), password (String)}`, response object structure: `int` 

Thank you very muchf
