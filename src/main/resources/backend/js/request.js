(function (win) {
  axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

  const service = axios.create({
    baseURL: '/',
    timeout: 1000000
  })
  // request interceptor
  service.interceptors.request.use(config => {
    // get request config params
    if (config.method === 'get' && config.params) {
      let url = config.url + '?';
      for (const propName of Object.keys(config.params)) {
        const value = config.params[propName];
        const part = encodeURIComponent(propName) + "=";
        if (value !== null && typeof(value) !== "undefined") {
          if (typeof value === 'object') {
            for (const key of Object.keys(value)) {
              let params = propName + '[' + key + ']';
              const subPart = encodeURIComponent(params) + "=";
              url += subPart + encodeURIComponent(value[key]) + "&";
            }
          } else {
            url += part + encodeURIComponent(value) + "&";
          }
        }
      }
      url = url.slice(0, -1);
      config.params = {};
      config.url = url;
    }
    return config
  }, error => {
      console.log(error)
      Promise.reject(error)
  })

  // response interceptor
  service.interceptors.response.use(res => {
      if (res.data.code === 0 && res.data.msg === 'NOTLOGIN') {
        console.log('---/backend/page/login/login.html---')
        localStorage.removeItem('userInfo')
        window.top.location.href = '/backend/page/login/login.html'
      } else {
        return res.data
      }
    },
    error => {
      console.log('err' + error)
      let { message } = error;
      if (message === "Network Error") {
        message = "Connection exception";
      }
      else if (message.includes("timeout")) {
        message = "System timeout";
      }
      else if (message.includes("Request failed with status code")) {
        message = "API" + message.substr(message.length - 3) + "exception";
      }
      window.ELEMENT.Message({
        message: message,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(error)
    }
  )
  win.$axios = service
})(window);
