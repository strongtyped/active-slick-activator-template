NOTE!!!!
========
Evolutions script must be applied in order to create the schema. 
You must first access http://localhost:9000 and click on "Apply this script now!" 
to have it applied to the in-memory DB.

adding new players
==================
    POST to http://localhost:9000/player 
    with payload { "name": "player-name" }


getting a player
=================
    GET on http://localhost:9000/player/$id
    where $id correspond to the player's id


getting all players
===================
    GET on http://localhost:9000/player    


updating a player
=================
    PUT on http://localhost:9000/player/$id
    with payload { "name": "player-name", "id": $id }
    where $id correspond to the player's id
    

deleting new players
====================
    DELETE on http://localhost:9000/player/$id
    where $id correspond to the player's id    