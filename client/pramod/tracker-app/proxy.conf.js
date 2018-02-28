const PROXY_CONFIG = [
  {
    context: [
      "/vehicles",
      "/vehicles/:{id}",
      "/alerts"
    ],
    target: "http://localhost:8080/cartracker",
    secure: false
  }
]
module.exports = PROXY_CONFIG;
