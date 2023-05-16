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

New items and monsters are easy to add in java classes by following the building pattern of existing ones.

How to run:
- Pass the token of your discord bot as the first CLI argument as you run the program.
- Modify your local database username and password in the first lines of the Model java class.
- If you want to use a database different than MySQL, switch the connector.

![zzMQ2k8](https://github.com/Salonce/discord-rpg-game/assets/27849647/ef95fbf8-9d6a-4c9e-8449-75f5cece3960) <br>
![game1](https://github.com/Salonce/Discord-MMORPG-game/assets/27849647/2582bf26-87ac-444a-ade1-b44d3eeb22ab) <br>
![game2](https://github.com/Salonce/Discord-MMORPG-game/assets/27849647/7d5288f4-72c5-4741-a4c7-7847328a39b9) <br>
![image](https://user-images.githubusercontent.com/27849647/226177879-a9417650-6f93-40b0-9e75-82f7d4115246.png) <br>

