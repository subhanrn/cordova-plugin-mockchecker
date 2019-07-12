# cordova-plugin-mockchecker

forked from https://gitlab.com/arham.anwar/cordova-plugin-mockchecker

This is a cordova plugin to avoid mock locations

This plugin get mock location in Android api <= 22 AND api > 22

## Supported Platforms

- Android API all versions

## Installation

```bash
cordova plugin add https://github.com/subhanrn/cordova-plugin-mockchecker
```

## Usage in javascript

```js
document.addEventListener("deviceready", onDeviceReady, false);

var whiteList = ["com.yy.hiyo"]; // use empty array to get all indicated mock location apps 

function onDeviceReady() {
  mockchecker.check(whiteList, successCallback, errorCallback);
}

function successCallback(mockStatus) {
  console.log(mockStatus);
}

function errorCallback(error) {
  console.log(error);
}
```
## Usage in typescript

```ts
declare var mockchecker: any; // add this line after import


 checkMock(whiteList): Promise<{ isMock: boolean, messages?: string, indicated?: Array<String> }> {
    return new Promise<{ isMock: boolean, messages?: string, indicated?: Array<String> }>(res => {
      mockchecker.check(whiteList, success => res(success), failed => res(failed))
    })
  }
  
const whiteList = ["com.yy.hiyo"]; // use empty array to get all indicated mock location apps 
const result = await this.checkMock(whiteList)
```

## mockStatus

Contains mock status :

### properties

- isMock : (boolean) true if device mock, false if no mock behavior detected.
- messages : (string) this properties exists if isMock properties values is true. 
- indicated : (array) this properties exists if isMock properties values is true and contains list of indicated apps which can be used to mock location

