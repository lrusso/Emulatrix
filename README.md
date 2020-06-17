# Emulatrix

JavaScript Emulator - Sega Genesis, Nintendo, Super Nintendo, GameBoy, GameBoy Color, GameBoy Advance, MAME32, DOSBox and Virtual Machines.

![alt screenshot](https://raw.githubusercontent.com/lrusso/EmulatrixWeb/master/Emulatrix.png)

## Web version

https://www.emulatrix.net

## Android version

https://play.google.com/store/apps/details?id=net.emulatrix

## IMPORTANT - After selecting the upload icon

| PLATFORM  | HOW TO PLAY A GAME?  | FILE FORMAT | NOTE |
| :------------ |:---------------:| :-----:| :-----:|
| Nintendo | Select the game file from your hard drive. | NES | --- | 
| Super Nintendo | Select the game file from your hard drive. | SMC, SFC, SRM | --- |
| GameBoy | Select the game file from your hard drive. | GB | --- |
| GameBoy Color | Select the game file from your hard drive. | GBC | --- |
| GameBoy Advance | Select the game file from your hard drive. | GBA | --- |
| Sega Genesis | Select the game file from your hard drive. | BIN, SMD, MD | --- |
| MAME32 | Select the game file from your hard drive. | ZIP | Do not rename the ZIP file. |
| DOS | Select the game file from your hard drive. | ZIP | Will try to run AUTORUN.BAT |
| Virtual Machines | Select the disk file from your hard drive. | ZIP | Must contain a c.img file.<br/>It will be mounted and booted. |

## DOSBox useful commands

| TYPE IN THE PROMPT  | RESULT  |
| :------------ |:---------------:|
| config -set "cycles=4000" | Default emulation speed |
| config -set "cycles=10000" | Faster emulation speed |
| config -set "cycles=15000" | Faster emulation speed |
| config -set "cycles=20000" | Fastest emulation speed |
| config -set "sbtype=none" | Sound Blaster disabled |

## Core files

| PLATFORM  | URL  | CORE |
| :------------ |:---------------:| :-----:|
| Nintendo | https://buildbot.libretro.com/stable | fceumm |
| Super Nintendo | https://buildbot.libretro.com/stable | snes9x2010 |
| GameBoy | https://buildbot.libretro.com/stable | gambatte |
| GameBoy Color | https://buildbot.libretro.com/stable | gambatte |
| GameBoy Advance | https://buildbot.libretro.com/stable | vba_next |
| Sega Genesis | https://buildbot.libretro.com/stable | genesis_plus_gx |
| MAME32 | https://buildbot.libretro.com/stable | fbalpha2012 |
| DOS | https://github.com/dreamlayers/em-dosbox | dosbox |

## Virtual joystick code

https://github.com/lrusso/VirtualJoystick
