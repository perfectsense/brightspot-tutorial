import {
  ImageByIdQuery, ImageByIdQueryVariables, useImageByIdQuery as originalQuery
} from './graphql'
import * as Apollo from '@apollo/client'
import { ApolloQueryResult, QueryResult } from '@apollo/react-hooks'
import { generateUseQuery } from '../rest/query'

const useQuery = generateUseQuery('imageById', originalQuery)
export const useImageByIdQuery = (baseOptions?: Apollo.QueryHookOptions<ImageByIdQuery, ImageByIdQueryVariables>):
  ApolloQueryResult<ImageByIdQuery> | QueryResult<ImageByIdQuery, ImageByIdQueryVariables> => {

  return useQuery(baseOptions)
}