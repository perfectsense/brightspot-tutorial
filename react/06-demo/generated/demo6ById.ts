import {
  Demo6ByIdQuery, Demo6ByIdQueryVariables, useDemo6ByIdQuery as originalQuery
} from './graphql'
import * as Apollo from '@apollo/client'
import { ApolloQueryResult, QueryResult } from '@apollo/react-hooks'
import { generateUseQuery } from '../rest/query'

const useQuery = generateUseQuery('demo6ById', originalQuery)
export const useDemo6ByIdQuery = (baseOptions?: Apollo.QueryHookOptions<Demo6ByIdQuery, Demo6ByIdQueryVariables>):
  ApolloQueryResult<Demo6ByIdQuery> | QueryResult<Demo6ByIdQuery, Demo6ByIdQueryVariables> => {

  return useQuery(baseOptions)
}