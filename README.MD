
Asteria 
=======

Co-operative Multiplayer Space Warfare Simulator

Current status
--------------

Development phase: Pre-Alpha.

Target deadline for Alpha: 25.7.2015


License
-------

An open source license, exact license to be determined.


Development
-----------

Target platform: 
 * Desktop (with both webstart and downloadable package / .exe).
 * Eventually (android & ios) tablets as well (not planned for alpha).
 * Support for separate server.

Used tech:
 * Java version 7 (balance between modern features and bugs fixed, and availability on user machines)
 * Maven for builds
 * Issues maintained in github issue database

Used libraries:
 * LibGdx for Open GL access, sound, controller integration, etc.
 * Netty for networking?  (Alternatively Kryo-net?)
 * Entity framework for server architecture 
     (entities with zero data, 
      data resides in pluggable components that can be added/removed to/from entities, 
      processors process some subset of entities at suitable intervals to update simulation data).

Small tasks maintained in code using the following tags 
   (in caps, followed by a colon, so that they are easy to search for or highlight in an IDE):
 * IMPLEMENT: Implementation missing, code compiles, but program will not work properly without it being completed.
 * TODO: Medium level todo, code compiles and program executes even without it being fixed, but needs to be done for future version
 * FIXME: Bug in the code, comments how to fix it if known.  Should be fixed for releases.
 * NOTE: Something about an implementation that should be taken into account when maintaining the code.
 * IDEA: Feature or improvement idea, idea for optimization, etc.  Can be removed when irrelevant or deemed not implementable / worth the time to implement.

Communication channels:
 * IRC: #asteria-spacewar on irc.freenode.org (asteria was already reserved)
 * Github: https://github.com/irah-000/asteria


Roadmap
-------

Rough roadmap of features and versions planned for Alpha.
Features may be removed or moved between versions.

* Version 0.1: First Contact
  * The client has the following screens: 
     * Connection screen (with IP/URL or server), username field, password field, Login button, Register button (and quit button), and some label or similar showing status when trying to connect to the server
     * Chat screen with chat window, chat line entry field, and disconnect button.
  * Server has support for the following commands (and response notifications):
     * CreateAccount(desiredUsername, desiredPassword) command message to create an account on the server
     * AccountCreatedOk / InvalidAccountCreation notification messages
     * Login(username, password) command message to log in to the server
     * LoginOk / InvalidLogin notification messages
     * SendChatMessage(channel, message) command message to say something in a chat channel
     * ReceiveChatMessage(channel, user, message) notification message for the client when someone said something in a chat channel
     * Disconnect command message for disconnecting a client from the server
     * Disconnected notification message to the client when it got disconnected
  * Server can store account and authentication information in memory for now.
  * NOTE: We'll need at least one chat channel for each faction, so we can as well add preliminary support for it now.
    Initially the players could just be autojoined to the "universal" channel, later we can add commands to join
    and part different channels, and a list of the channels that are available to a specific user.
    
* Version 0.2: Visible Universe
  * Entities are visible in a 3D universum, and move in simple orbits or are stationary
  * Client shows the entities (and overlays the chat screen on top of the view)
  * Command & notification messages:
    * EntityPerceptionUpdate(list of visible entity updates) notification message from server to client, with updates to visible entities
      * A perception update has one or more of the following:
        * Some id for the entity (long, need not be same from time to time, always present)
        * Name of the entity (String)
        * Type of the entity (String)
        * Faction of the entity (String)
        * Movement and position update class, with the following fields:
          * Location of the entity
          * Heading of the entity
          * Velocity of the entity
          * Rotational speed of the entity
          * Current acceleration of the entity
          * Current rotational acceleration of the entity
        * Current visual appearance of the entity (if not provided, the entity is not visible)
          * The appearance consists of a model/appearance name (String), and a String key -> float value map of parameters for it.
        * Current sound of the entity (if not provided, the entity makes no sound)
          * Consists of sound name (String) and a String key -> float value map with sound parameters
    * PerceptionLost(long entityId) notification message when an entity is no longer visible (or audible)
  * Normally the client updates entities when the server sends updates, but if the client can lose connection
    without the server noticing, the following commands may be needed (they may be subject to max caps later by the server to minimize DDoS potential):
    * RequestPerceivedEntities() command message to get a list of all the visible or perceived entity ids.
    * PerceivedEntityIds(list of entity ids) notification message with all entity ids visible or perceived by the client.
    * RequestPerceptionData(list of entity ids) command message to request all the perception data for the specified entity ids from the server.
  * Stress-test server and client with large universe filled with lots of entities and many automated clients.  

* Version 0.3: Take Control
  * The player should be able to control his ship and fly around in it
  * Each player is automatically assigned control of a small one-crew ship when logged in or destroyed
  * Command & notification messages:
    * ShipAssignmentChanged(long entity id of ship the player is now stationed on,
                            String name of the console on the ship the player is assigned to,
                            Ship information) notification message 
      Tells the client what ship the player is stationed on and what console they are manning (helm, engineering, captain, etc).
      Entity perception updates will normally follow with the ships position etc.
      Ship information contains:
        * String shipName - name of the ship
        * String shipClass - model of the ship
        * String shipFaction - name of the team the ship is on
    * ControlsUpdated(list of the controls currently available to the player) notification message from the server
      Used e.g. when the player is assigned to a new ship, changes control terminals within the ship,
      or if some module or weapon was added or detached from the ship.
      (If some module is damaged the control is still there, it just may not be doing anything).
    * InputControl(String controlName, float newControlValue (in range 0..1 normally)) command message to server, 
      when the user modifies the value of some user interface control (e.g. thrust, pitch, yaw, roll, weapon1fire, etc).
  * TODO: Determine how to send status and sensor information about the ship, e.g. damage, speed and other readings, and weapon / module specific statuses.

* Version 0.4: Hostile Confrontation
  * Players should be able to fight in the game, shooting and damaging each other, 
    respawning on destruction, collect achievements, and be listed on the high score table
  * Add summary view of your recent achievements after your ship is destroyed
    (on top of a death cam view showing the wreck)
  * Add high score view e.g. after summary view (or integrated with it) (and/or somewhere else)
  * Gather some feedback from players on usability, playability, etc.
   
* Version 0.5: Pimp My Spaceship
  * Add more weapon types, bonuses that can be picked up, improve graphics, sound and gameplay

* Version 0.6: Fill The Space
  * Add resupply at bases, destroyable turrets, collision with planets & asteroids, procedural asteroid fields
  
* Version 0.7 (Alpha): Assemble the Squadrons
  * Create fast-paced scenario in gas planet rings with two (or maybe three?) factions, for 5-50 players.
  * Polish game to playable, usable, and pretty state.
  * If the game is acceptable, release for Assembly 2015 GameDev compo.
  * Gather feedback from many players on usability, playability, etc. (Built in feedback form?  Anonymous usage statistics?)
 






