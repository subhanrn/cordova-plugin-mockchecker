module.exports = {
  check: function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'MockGpsChecker', 'check', []);
  }
}