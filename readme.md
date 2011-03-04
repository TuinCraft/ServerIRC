[INFO/CHAT] ServerIRC 0.3 - Minecraft server bot for IRC channels
=============================================================

Creates a bot for the server that joins an IRC channel.
This is a Bukkit plugin.
Inspired by (and works great in combination with) [IRCTransport](http://forums.bukkit.org/threads/chat-irctransport-0-5-minecraft-chat-irc-integration.3412/). (Thanks hef!)

Available settings:
------------------
Put these in Minecraft's server.properties file with appropriate values.
    irc.server=
    irc.autojoin=
    irc.verbose=false
    irc.serverbot=MineCraft

The irc.server setting is mandatory. All other settings are optional.
Set irc.autojoin to a channel to have your bot autojoin that channel.
Use irc.serverbot to set the bot's name.


Downloads:
----------
[ServerIRC v0.3](https://github.com/downloads/TuinCraft/ServerIRC/ServerIRC-0.3.jar)
[Source](https://github.com/TuinCraft/ServerIRC)

Features:
---------
  * Let a bot join an IRC channel.
  * Listen to custom events.

Optional Addons:
----------------
  * Use [HeroicDeath 1.4.2+](http://forums.bukkit.org/threads/info-heroicdeath-v1-4-3-customizable-server-broadcasts-on-player-death-211-450.3255/) for death events.

TODO:
-----
  * [Issues](https://github.com/TuinCraft/ServerIRC/issues)

Changelog:
----------
### Version 0.3
  * Removed debug stuff from the previous version
  * Fixed an error if HeroicDeath was missing.
### Version 0.2
  * Working Colors
### Version 0.1
  * Basic bot features.
  * HeroicDeath events.
