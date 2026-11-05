# Emulatrix

Emulator with mobile compatibility designed for running in pure JavaScript pre-ECMAScript 2015 (no WebAssembly). Supports Sega Genesis, 32x, Nintendo, Super Nintendo, GameBoy, GameBoy Color, GameBoy Advance, MAME and DOS.

Simply open the link below, click the red icon, and select a ROM file in `BIN`, `SMD`, `MD`, `32X`, `NES`, `SMC`, `SFC`, `SRM`, `GB`, `GBC`, `GBA` or `ZIP` format from your computer; it will be loaded and booted automatically.

![alt screenshot](https://raw.githubusercontent.com/lrusso/Emulatrix/master/README.png)

## Website:

https://www.emulatrix.com

## Special keys:

| Action          | macOS Shortcut | Windows Shortcut | Safari Shortcut |
| :-------------- | :------------: | :--------------: | :-------------: |
| Save state      |  Command + 1   |     Ctrl + 1     |    Ctrl + 1     |
| Load state      |  Command + 2   |     Ctrl + 2     |    Ctrl + 2     |
| Toggle sound    |  Command + 3   |     Ctrl + 3     |    Ctrl + 3     |
| Fullscreen mode |  Command + F   |     Ctrl + F     |    Ctrl + F     |
| Reset game      |  Command + R   |     Ctrl + R     |    Ctrl + R     |

## How to play on mobile devices using a USB keyboard:

Press `C` on the Main Menu to enable or disable mobile controls.

## Author's note:

Emulatrix is compatible with both Android and iOS devices. However, WebKit on iOS has historically lagged behind; for instance, it took nearly a decade for Apple to allow developers to set a custom download filename for an `a` tag. This feature was implemented recently on iOS, so you can now download the game state. Another three iOS quirks: 1) if a slow connection causes the script to take several seconds to load, WebKit may fail to initialize the AudioContext; 2) if you send Safari to the background and return to it, there will be no audio; 3) if you click on the file selector and it takes you several seconds to choose a ROM file, there will be no audio. In any case, a manual tap on the screen is required to enable or re-enable the audio.

## Original core files:

| PLATFORM        | URL                                      |
| :-------------- | :--------------------------------------- |
| Sega Genesis    | https://github.com/lrusso/Genesis        |
| 32x             | https://github.com/lrusso/Genesis        |
| Nintendo        | https://github.com/lrusso/Nintendo       |
| Super Nintendo  | https://github.com/lrusso/SuperNintendo  |
| GameBoy         | https://github.com/lrusso/GameBoy        |
| GameBoy Color   | https://github.com/lrusso/GameBoy        |
| GameBoy Advance | https://github.com/lrusso/GameBoyAdvance |
| MAME            | https://github.com/lrusso/MAME           |
| DOS             | https://github.com/lrusso/DOS            |

## Banner generators:

https://www.emulatrix.com/BannerLaptop.htm

https://www.emulatrix.com/BannerName.htm

https://www.emulatrix.com/BannerPlatforms.htm

https://www.emulatrix.com/BannerReadme.htm

https://www.emulatrix.com/BannerResources.htm
