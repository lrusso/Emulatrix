# Emulatrix

JavaScript and WebAssembly Emulator - Sega Genesis, Nintendo, Super Nintendo, GameBoy, GameBoy Color, GameBoy Advance, MAME32, DOSBox and Virtual Machines.

![alt screenshot](https://raw.githubusercontent.com/lrusso/Emulatrix/master/Emulatrix1.png)

## Web

https://www.emulatrix.com

## How does it work?

It's a Web project that is 50% in JavaScript and 50% in WebAssembly. It uses the HTML5 File API for reading a file from the computer, so the End User must select the ROM file from his computer. After that, it uses BrowserFS, which creates a virtual filesystem on the client side where the ROM file is uploaded. When using the DOS emulator, there is a Web File Manager that allows the End User to upload and download files to/from that virtual filesystem. The emulators are in WebAssembly and the content is rendered on a Canvas and for audio it uses an AudioContext. There is a JavaScript logic that handles the AudioContext (for mute/unmute) and another logic for pausing and resuming the emulation when the window is on blur or on focus.

## IMPORTANT - After clicking on the upload icon

| PLATFORM  | HOW TO PLAY A GAME?  | FILE FORMAT |
| :------------ |:---------------:| :-----:|
| Nintendo | Select the game file from your device. | NES |
| Super Nintendo | Select the game file from your device. | SMC, SFC, SRM |
| GameBoy | Select the game file from your device. | GB |
| GameBoy Color | Select the game file from your device. | GBC |
| GameBoy Advance | Select the game file from your device. | GBA |
| Sega Genesis | Select the game file from your device. | BIN, SMD, MD |
| MAME32 | Select the game file from your device. | ZIP |
| DOS | Select the game file from your device. | ZIP |
| Virtual Machines | Select the disk file from your device. | ZIP |

- When running DOS, Emulatrix will try to run ```AUTORUN.BAT``` from the ZIP file after booting.
- Regarding virtual machines, the ZIP file must contain a ```c.img``` file that will be mounted and booted.

## DOSBox useful commands

| TYPE IN THE PROMPT  | RESULT  |
| :------------ |:---------------:|
| mount d . -t cdrom | Mounting a path as a CD-ROM |
| imgmount d cd1.cue cd2.cue -t cdrom | Mounting two CD-ROM images |
| config -set "cycles=4000" | Default emulation speed |
| config -set "cycles=10000" | Faster emulation speed |
| config -set "cycles=15000" | Faster emulation speed |
| config -set "cycles=20000" | Fastest emulation speed |
| config -set "sbtype=none" | Sound Blaster disabled |
| pkzip folder.zip c:\myfolder\\*.\* -rp | Creates folder.zip with myfolder content |

- For switching CD-ROM images you must press ```Ctrl+F4``` (Windows) or ```Fn + Control + Option + F4``` (Mac). This also can be used for refreshing the filesystem after uploading a file.

## Original core files

| PLATFORM  | URL  | CORE
| :------------ |:---------------:| :-----:|
| Nintendo | https://buildbot.libretro.com/stable | fceumm
| Super Nintendo | https://buildbot.libretro.com/stable | snes9x2010
| GameBoy | https://buildbot.libretro.com/stable | gambatte
| GameBoy Color | https://buildbot.libretro.com/stable | gambatte
| GameBoy Advance | https://buildbot.libretro.com/stable | vba_next
| Sega Genesis | https://buildbot.libretro.com/stable | genesis_plus_gx
| MAME32 | https://buildbot.libretro.com/stable | mame2003_plus
| MAME32 | https://buildbot.libretro.com/stable | fbalpha2012
| DOS | https://github.com/dreamlayers/em-dosbox | dosbox

## Core files modifications

| PLATFORM  | TYPE  | DETAILS |
| :------------ |:--------------- |:---------------|
| Libretro cores | Bugfix | Content resized to canvas |
| Libretro cores | Bugfix | Delayed compilation workaround |
| Libretro cores | Feature | Mute/unmute implementation |
| Libretro cores | Feature | Load/save state implementation |
| Libretro cores | Feature | Paused/resumed emulation on blur/focus |
| DOSBox core | Bugfix | Launching emulator on blur |
| DOSBox core | Bugfix | SimulateInfiniteLoop in 16 bits systems |
| DOSBox core | Bugfix | Removed black margin when trying fullscreen |
| DOSBox core | Feature | PKZip implementation |
| DOSBox core | Feature | Mute/unmute implementation |
| DOSBox core | Feature | Load/save file implementation |
| DOSBox core | Feature | Paused/resumed emulation on blur/focus |

## MAME 2003 Plus notes:

- Playing Killer Instinct: Create a zip file named ```kinst-chd.zip``` that must contain ```kinst.zip``` and ```kinst.chd```. Emulatrix will unzip those files in order to run the game (takes several seconds).
- Saving and loading states: In some games (Killer Instinct, Mortal Kombat 1, 2 and Ultimate) the saving function doesn't work (core issue).

## Final Burn Alpha 2012 note:

- Playing Samurai Shodown III: You must use the ```samsho3h.zip``` file.

## Emulatrix has mobile compatibility (Android only)

![alt screenshot](https://raw.githubusercontent.com/lrusso/Emulatrix/master/Emulatrix2.png)

## Playing Emulatrix on Android with a USB Keyboard:

Press ```C``` on the Main Menu in order to enable or disable the mobile controls.

## Virtual joystick code

https://github.com/lrusso/VirtualJoystick

## Banner generators

https://www.emulatrix.com/Banner1.htm

https://www.emulatrix.com/Banner2.htm

https://www.emulatrix.com/Banner3.htm

https://www.emulatrix.com/Banner4.htm

https://www.emulatrix.com/Banner5.htm

https://www.emulatrix.com/Banner6.htm

https://www.emulatrix.com/BannerTitle.htm
