const autoprefixer = require('autoprefixer')
const Styleguide = require('brightspot-styleguide')
const gulp = require('gulp')
const plugins = require('gulp-load-plugins')()
const Builder = require('systemjs-builder')

const styleguide = new Styleguide(gulp)

gulp.task(styleguide.task.less(), () => {
  return gulp.src('styleguide/All.less', { base: '.' })
    .pipe(plugins.sourcemaps.init())
    .pipe(plugins.less())
    .pipe(plugins.postcss([ autoprefixer('last 2 versions') ]))
    .pipe(plugins.cleanCss())
    .pipe(plugins.rename({ extname: '.min.css' }))
    .pipe(plugins.sourcemaps.write('.'))
    .pipe(gulp.dest(styleguide.path.build()))
})

gulp.task(styleguide.task.js(), (done) => {
  let builder = new Builder()

  builder.config({
    defaultJSExtensions: true,
    map: {
//      'bsp-utils': 'node_modules/bsp-utils/bsp-utils.js'
    }
  })

  let buildOptions = {
    minify: false
  }

  builder.buildStatic('styleguide/All.js', buildOptions).then((output) => {
    gulp.src([ ])
      .pipe(plugins.file('styleguide/All.js', output.source))
      .pipe(gulp.dest(styleguide.path.build()))
      .pipe(plugins.sourcemaps.init())
      .pipe(plugins.uglify())
      .pipe(plugins.rename({ extname: '.min.js' }))
      .pipe(plugins.sourcemaps.write('.'))
      .pipe(gulp.dest(styleguide.path.build()))
      .on('end', done)
  })
})

// Copies static assets (images, fonts, etc.) into the styleguide build
gulp.task(styleguide.task.extra('assets'), () => {
  return gulp.src('styleguide/assets/**', { base: '.' })
      .pipe(gulp.dest(styleguide.path.build()))
})

gulp.task(styleguide.task.extra('TASKNAME_GOES_HERE'), () => {
  // create a custom build task here
})
