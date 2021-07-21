# Emulatrix

JavaScript Emulator - Sega Genesis, Nintendo, Super Nintendo, GameBoy, GameBoy Color, GameBoy Advance, MAME32, DOSBox and Virtual Machines.

![alt screenshot](https://raw.githubusercontent.com/lrusso/Emulatrix/master/Emulatrix.png)

## Web

https://www.emulatrix.com

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
| pkzip folder.zip c:\myfolder\\*.\* -rp | Creates folder.zip with myfolder content |

## Original core files

| PLATFORM  | URL  | CORE
| :------------ |:---------------:| :-----:|
| Nintendo | https://buildbot.libretro.com/stable | fceumm
| Super Nintendo | https://buildbot.libretro.com/stable | snes9x2010
| GameBoy | https://buildbot.libretro.com/stable | gambatte
| GameBoy Color | https://buildbot.libretro.com/stable | gambatte
| GameBoy Advance | https://buildbot.libretro.com/stable | vba_next
| Sega Genesis | https://buildbot.libretro.com/stable | genesis_plus_gx
| MAME32 | https://buildbot.libretro.com/stable | fbalpha2012
| DOS | https://github.com/dreamlayers/em-dosbox | dosbox

## Core files modifications

| PLATFORM  | TYPE  | DETAILS |
| :------------ |:--------------- |:---------------|
| Libretro cores | Bugfix | Delayed compilation workaround |
| Libretro cores | Bugfix | Content resized to canvas |
| Libretro cores | Feature | Paused/resumed emulation on blur/focus |
| Libretro cores | Feature | Load/save state implementation |
| DOSBox core | Bugfix | Launching emulator on blur |
| DOSBox core | Bugfix | Removed black margin when trying fullscreen |
| DOSBox core | Bugfix | SimulateInfiniteLoop in 16 bits systems |
| DOSBox core | Feature | Paused/resumed emulation on blur/focus |
| DOSBox core | Feature | Load/save file implementation |
| DOSBox core | Feature | PKZip implementation |

## Virtual joystick code

https://github.com/lrusso/VirtualJoystick

## Banner generators

https://www.emulatrix.com/Banners/Banner1.htm

https://www.emulatrix.com/Banners/Banner2.htm

https://www.emulatrix.com/Banners/Banner3.htm

https://www.emulatrix.com/Banners/Banner4.htm

https://www.emulatrix.com/Banners/Banner5.htm

https://www.emulatrix.com/Banners/Banner6.htm
