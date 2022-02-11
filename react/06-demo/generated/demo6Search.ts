import {
  Demo6SearchQuery, Demo6SearchQueryVariables, useDemo6SearchQuery as originalQuery
} from './graphql'
import * as Apollo from '@apollo/client'
import { ApolloQueryResult, QueryResult } from '@apollo/react-hooks'
import { generateUseQuery } from '../rest/query'

const useQuery = generateUseQuery('demo6Search', originalQuery)
export const useDemo6SearchQuery = (baseOptions?: Apollo.QueryHookOptions<Demo6SearchQuery, Demo6SearchQueryVariables>):
  ApolloQueryResult<Demo6SearchQuery> | QueryResult<Demo6SearchQuery, Demo6SearchQueryVariables> => {

  return useQuery(baseOptions)
}