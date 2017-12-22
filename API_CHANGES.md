The Second paragraph outlining API for commands was changed. Now the front end no longer receives information to change the front end. All change are carried out by the commands, which are executed by the backend. This makes much more sense as you are no longer rerouting a lot of information depending on whether or not a command is front end or backend, so that distinction doesn’t need to be made. Philosophically, it makes much more sense to have all actions done in one place, in our case this place would be the respective command classes.