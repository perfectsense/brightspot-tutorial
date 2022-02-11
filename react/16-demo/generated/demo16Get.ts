import {
  Demo16GetQuery, Demo16GetQueryVariables, useDemo16GetQuery as originalQuery
} from './graphql'
import * as Apollo from '@apollo/client'
import { ApolloQueryResult, QueryResult } from '@apollo/react-hooks'
import { generateUseQuery } from '../rest/query'

const useQuery = generateUseQuery('demo16Get', originalQuery)
export const useDemo16GetQuery = (baseOptions?: Apollo.QueryHookOptions<Demo16GetQuery, Demo16GetQueryVariables>):
  ApolloQueryResult<Demo16GetQuery> | QueryResult<Demo16GetQuery, Demo16GetQueryVariables> => {

  return useQuery(baseOptions)
}