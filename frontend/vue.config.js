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
    // proxy: 'http://10.133.224.37:8081'
    // proxy: 'http://172.17.55.197:8081'

    proxy: 'http://localhost:8081'
  }
}