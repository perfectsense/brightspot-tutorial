import { gql } from '@apollo/client'
import fs from 'fs-extra'
import glob from 'glob'
import camelCase from 'camelcase'

const apiRouteTemplate = fs.readFileSync('./rest/apiRouteTemplate.js.txt', 'utf8')
const queryTemplate = fs.readFileSync('./rest/queryTemplate.ts.txt', 'utf8')

glob("./components/**/*.graphql", {}, (error, files) => {

  files.forEach(file => {

    const query = fs.readFileSync(file, 'utf8')
    const ast = gql`${query}`

    ast.definitions.forEach(d => {
      if (d.kind === 'OperationDefinition' && d.operation === 'query') {

        const queryName = d.name.value
        const filename = camelCase(queryName)
        const variableNames = d.variableDefinitions.map(vd => `'${vd.variable.name.value}'`).join(", ")

        fs.outputFileSync(`./pages/api/${filename}.js`,
          apiRouteTemplate.replaceAll('${queryFile}', file)
            .replaceAll('${variableNames}', variableNames))

        const queryTypeName = `${queryName}Query`
        const queryVariablesTypeName = `${queryTypeName}Variables`
        const useQueryHookName = `use${queryTypeName}`

        fs.outputFileSync(`./generated/${filename}.ts`,
          queryTemplate.replaceAll('${queryTypeName}', queryTypeName)
            .replaceAll('${queryVariablesTypeName}', queryVariablesTypeName)
            .replaceAll('${useQueryHookName}', useQueryHookName)
            .replaceAll('${restApiPath}', filename))
      }
    })
  })
})
