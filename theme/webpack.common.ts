import CopyPlugin from 'copy-webpack-plugin'
import ESLintPlugin from 'eslint-webpack-plugin'
import path from 'path'
import webpack from 'webpack'

const commonConfig: webpack.Configuration = {
  entry: {
    'All.min.js': './styleguide/All.ts',
  },

  output: {
    path: path.resolve(__dirname, './build'),
    filename: '[name]',
    publicPath: '/',
  },

  plugins: [
    new CopyPlugin({
      patterns: [{ from: 'styleguide/assets/**', noErrorOnMissing: true }],
    }),
    new ESLintPlugin(),
  ],

  module: {
    rules: [
      // Split out large binary files into separate chunks.
      {
        test: /\.(png|jpg|gif|svg|eot|ttf|woff|woff2)$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
        },
      },

      // Transpile JS.
      {
        test: /\.[jt]s$/,
        exclude: /node_modules/,
        use: [
          {
            loader: 'babel-loader',
            options: {
              cacheDirectory: true,
            },
          },
        ],
      },
    ],
  },

  resolve: {
    extensions: ['.js', '.ts'],
  },
}

export default commonConfig
