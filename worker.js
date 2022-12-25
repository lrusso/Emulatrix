const filesToCache = [
	"/",
	"Emulatrix.css",
	"Emulatrix.htm",
	"Emulatrix.json",
	"Emulatrix1.png",
	"Emulatrix2.png",
	"EmulatrixFavIcon_16x16.png",
	"EmulatrixFavIcon_192x192.png",
	"EmulatrixFavIcon_192x192b.png",
	"EmulatrixFavIcon_32x32.png",
	"EmulatrixFavIcon_48x48.png",
	"EmulatrixFavIcon_512x512.png",
	"EmulatrixFavIcon_512x512b.png",
	"EmulatrixFavIcon.svg",
	"EmulatrixShare.png",
	"Emulatrix_DOSBox.htm",
	"Emulatrix_DOSBox.js",
	"Emulatrix_DOSBox.wasm",
	"Emulatrix_GameBoy.htm",
	"Emulatrix_GameBoy.js",
	"Emulatrix_GameBoy.wasm",
	"Emulatrix_GameBoyAdvance.htm",
	"Emulatrix_GameBoyAdvance.js",
	"Emulatrix_GameBoyAdvance.wasm",
	"Emulatrix_MAME32.htm",
	"Emulatrix_MAME32.js",
	"Emulatrix_MAME32.wasm",
	"Emulatrix_MAME2003.htm",
	"Emulatrix_MAME2003.js",
	"Emulatrix_MAME2003.wasm",
	"Emulatrix_Nintendo.htm",
	"Emulatrix_Nintendo.js",
	"Emulatrix_Nintendo.wasm",
	"Emulatrix_SegaGenesis.htm",
	"Emulatrix_SegaGenesis.js",
	"Emulatrix_SegaGenesis.wasm",
	"Emulatrix_SuperNintendo.htm",
	"Emulatrix_SuperNintendo.js",
	"Emulatrix_SuperNintendo.wasm",
	"index.html"
];

const staticCacheName = "emulatrix-v1";

self.addEventListener("install", event => {
	event.waitUntil(
		caches.open(staticCacheName)
		.then(cache => {
			return cache.addAll(filesToCache);
		})
	);
});

self.addEventListener("fetch", event => {
	event.respondWith(
		caches.match(event.request)
		.then(response => {
			if (response) {
				return response;
			}
			return fetch(event.request)
		}).catch(error => {
		})
	);
});