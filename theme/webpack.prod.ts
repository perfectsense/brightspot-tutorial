import MiniCssExtractPlugin from 'mini-css-extract-plugin'
import path from 'path'
import merge from 'webpack-merge'
import commonConfig from './webpack.common'

export default merge(commonConfig, {
  mode: 'production',

  plugins: [
    new MiniCssExtractPlugin({
      filename: 'All.min.css',
    }),
  ],

  module: {
    rules: [
      {
        test: path.resolve(__dirname, './styleguide/All.less'),
        use: [MiniCssExtractPlugin.loader, 'css-loader', 'less-loader'],
      },
    ],
  },
})
