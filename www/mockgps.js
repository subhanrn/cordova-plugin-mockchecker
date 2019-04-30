module.exports = {
  check: function (args1, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'MockChecker', 'check', [args1]);
  }
}