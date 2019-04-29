module.exports = {
  check: function (params, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'MockChecker', 'check', [params]);
  }
}