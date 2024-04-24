module.exports = {
  transpileDependencies: true,
  chainWebpack: (config) => {
    config.plugin('define').tap((definitions) => {
      Object.assign(definitions[0], {
        __VUE_OPTIONS_API__: 'true',
        __VUE_PROD_DEVTOOLS__: 'false',
        __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: 'false'
      })
      return definitions
    })
  },
  devServer: {
    /**
     * 连接真实使用的后端服务器地址
     */
    // proxy: 'http://10.135.40.221:8081'

    /**
     * 简易服务器测试用
     */
    proxy: 'http://172.17.55.197:8081'
  }
}