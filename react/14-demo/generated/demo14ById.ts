import {
  Demo14ByIdQuery, Demo14ByIdQueryVariables, useDemo14ByIdQuery as originalQuery
} from './graphql'
import * as Apollo from '@apollo/client'
import { ApolloQueryResult, QueryResult } from '@apollo/react-hooks'
import { generateUseQuery } from '../rest/query'

const useQuery = generateUseQuery('demo14ById', originalQuery)
export const useDemo14ByIdQuery = (baseOptions?: Apollo.QueryHookOptions<Demo14ByIdQuery, Demo14ByIdQueryVariables>):
  ApolloQueryResult<Demo14ByIdQuery> | QueryResult<Demo14ByIdQuery, Demo14ByIdQueryVariables> => {

  return useQuery(baseOptions)
}