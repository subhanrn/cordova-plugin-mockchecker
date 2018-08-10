module.exports = {
  check: function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'MockChecker', 'check', []);
  }
}