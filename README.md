# cordova-plugin-mockchecker

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

function successCallback(result) {
  console.log(result); // true - enabled, false - disabled
}

function errorCallback(error) {
  console.log(error);
}
```