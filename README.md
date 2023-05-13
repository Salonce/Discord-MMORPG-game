# Discord MMORPG game v1.0

A discord4j-based MMORPG game, where players can interact with the world by fighting monsters, interacting with other players, obtaining new items, managing their inventory and equipment and more!

About the project:
- Written in Java 19
- Uses Discord4j API wrapper for communication with discord.
- Object designing user input calls appropriate function by the use of Observer pattern.
- Database interaction in JDBC with mySQL connection by default.
- Extensively used builder design pattern for various object creation.

The game in current version includes:
- Inventory
- Equipment
- Dungeons
- Monsters
- Fighting system
- Looting system with randomized drops
- Item management (sorting inventory, equipping and dropping items)
- Automatic regeneration system of health and action points

How to run:
- Pass the token of your discord bot as the first CLI argument as you run the program.
- Modify your local database username and password in the first lines of the Model java class.
- If you want to use a database different than MySQL, switch the connector.


All of these are stored in separated java files and new objects are easy to add by following the pattern in existing ones.

![zzMQ2k8](https://github.com/Salonce/discord-rpg-game/assets/27849647/ef95fbf8-9d6a-4c9e-8449-75f5cece3960)
![image](https://user-images.githubusercontent.com/27849647/226178152-44e4d730-730b-43df-b667-979747f6028c.png)
![image](https://user-images.githubusercontent.com/27849647/226177879-a9417650-6f93-40b0-9e75-82f7d4115246.png)

