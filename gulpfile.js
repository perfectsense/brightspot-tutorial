const gulp = require('gulp')
const plugins = require('gulp-load-plugins')()
const autoprefixer = require('autoprefixer')
const Styleguide = require('brightspot-styleguide')
const Builder = require('systemjs-builder')

const styleguide = new Styleguide(gulp)

gulp.task('css', [ styleguide.task.lint.less() ], () => {
  return gulp.src('styleguide/All.less', { base: '.' })
    .pipe(plugins.sourcemaps.init())
    .pipe(plugins.less())
    .pipe(plugins.postcss([ autoprefixer('last 2 versions') ]))
    .pipe(plugins.cleanCss())
    .pipe(plugins.rename({ extname: '.min.css' }))
    .pipe(plugins.sourcemaps.write('.'))
    .pipe(gulp.dest(styleguide.path.build()))
    .pipe(styleguide.notify('Finished'))
})

gulp.task('js', [ styleguide.task.lint.js() ], (done) => {
  let builder = new Builder()

  builder.config({
    map: {
      'bsp-utils': 'node_modules/bsp-utils/bsp-utils.js'
    }
  })

  let buildOptions = {
    minify: false
  }

  return builder.buildStatic('styleguide/All.js', buildOptions).then((output) => {
    gulp.src([ ])
      .pipe(plugins.file('styleguide/All.js', output.source))
      .pipe(gulp.dest(styleguide.path.build()))
      .pipe(plugins.sourcemaps.init())
      .pipe(plugins.uglify())
      .pipe(plugins.rename({ extname: '.min.js' }))
      .pipe(plugins.sourcemaps.write('.'))
      .pipe(gulp.dest(styleguide.path.build()))
      .pipe(styleguide.notify('Finished'))
      .on('end', done)
  })
})

gulp.task('styleguide', ['default'], () => {
  styleguide.watch()
  styleguide.serve()
})

gulp.task('default', [ 'css', 'js', styleguide.task.copy.templates() ])
