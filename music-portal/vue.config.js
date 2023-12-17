const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // configureWebpack: {
  //   devtool: 'source-map',
  //   name: this.name,
  //   resolve: {
  //     alias: {
  //       // eslint-disable-next-line no-undef
  //       '@': resolve('src')
  //     }
  //   }
  // },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:9090',//这里填入你要请求的接口的前缀
        changeOrigin: true,//虚拟的站点需要更管origin
        secure: false,                   //是否https接口
        pathRewrite: {
          '^/api': ''//重写路径，把多余的api变为空字符
        }
      }
    }
  }
})



