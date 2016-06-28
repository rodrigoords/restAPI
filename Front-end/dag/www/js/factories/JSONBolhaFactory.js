factories.factory('JSONBolhaFactory', function($http){
  var API_PATH = 'http://localhost:8080/rest/bolha/range';
  return {
    getBolhasRange : function(_lat, _longi) {
      return $http.get(API_PATH,{
        'params' : {
          lat : _lat,
          longi : _longi
        }
      });
    }
  }
})
