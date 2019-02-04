# Emulatrix

JavaScript Emulator based on RetroArch - Sega Genesis, Nintendo, Super Nintendo, GameBoy, GameBoy Color, GameBoy Advance, MAME32 and DOSBox.

## Web:

https://emulatrix.lrusso.com.ar

## Important: After clicking in 'Open ROM'

| PLATFORM  | HOW TO PLAY A GAME?  | FILE FORMAT | NOTE |
| :------------ |:---------------:| :-----:| :-----:|
| Nintendo | Select the game file from your hard drive. | NES | --- |
| Super Nintendo | Select the game file from your hard drive. | SMC | --- |
| GameBoy | Select the game file from your hard drive. | GB | --- |
| GameBoy Color | Select the game file from your hard drive. | GBC | --- |
| GameBoy Advance | Select the game file from your hard drive. | GBA | --- |
| Sega Genesis | Select the game file from your hard drive. | BIN | --- |
| MAME32 | Select the game file from your hard drive. | ZIP | Do not rename the file. |
| DOS | Select the game file from your hard drive. | ZIP | Will try to run AUTORUN.BAT |

## DOSBox useful commands:

| TYPE IN THE PROMPT  | SPEED EMULATION  |
| :------------ |:---------------:|
| config -set "cycles=4000" | Default |
| config -set "cycles=10000" | Faster |
| config -set "cycles=15000" | Faster |
| config -set "cycles=20000" | Fastest (suggested max value) |


| config -set "sbtype=none" | Sound Blaster disabled |

## Core files:

https://buildbot.libretro.com/stable/
