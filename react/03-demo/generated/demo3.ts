import {
  Demo3Query, Demo3QueryVariables, useDemo3Query as originalQuery
} from './graphql'
import * as Apollo from '@apollo/client'
import { ApolloQueryResult, QueryResult } from '@apollo/react-hooks'
import { generateUseQuery } from '../rest/query'

const useQuery = generateUseQuery('demo3', originalQuery)
export const useDemo3Query = (baseOptions?: Apollo.QueryHookOptions<Demo3Query, Demo3QueryVariables>):
  ApolloQueryResult<Demo3Query> | QueryResult<Demo3Query, Demo3QueryVariables> => {

  return useQuery(baseOptions)
}