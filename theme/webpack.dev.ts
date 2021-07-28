import styleguide from '@brightspot/styleguide'
import webpack from 'webpack'
import merge from 'webpack-merge'
import commonConfig from './webpack.common'

export default merge(
  commonConfig,
  styleguide.webpack('./styleguide', webpack, {
    mode: 'development',
    devtool: 'inline-source-map',

    module: {
      rules: [
        {
          test: /\.less$/,
          use: ['style-loader', 'css-loader', 'less-loader'],
        },
      ],
    },
  }) as any // eslint-disable-line @typescript-eslint/no-explicit-any
)
