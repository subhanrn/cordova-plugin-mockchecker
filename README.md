# cordova-plugin-mockchecker

forked from https://gitlab.com/arham.anwar/cordova-plugin-mockchecker

This is a cordova plugin to avoid mock locations

This plugin get mock location in Android api <= 22 AND api > 22

## Supported Platforms

- Android API all versions

## Installation

```bash
cordova plugin add cordova-plugin-mockchecker
```

## Usage in javascript

```js
document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
  mockchecker.check(successCallback, errorCallback);
}

function successCallback(mockStatus) {
  console.log(mockStatus);
}

function errorCallback(error) {
  console.log(error);
}
```

## mockStatus

Contains mock status :

### properties

- isMock : (boolean) true if device mock, false if no mock behavior detected.
- messages : (string) this properties exists if isMock properties values is true. 

