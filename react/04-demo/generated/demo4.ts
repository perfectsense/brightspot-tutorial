import {
  Demo4Query, Demo4QueryVariables, useDemo4Query as originalQuery
} from './graphql'
import * as Apollo from '@apollo/client'
import { ApolloQueryResult, QueryResult } from '@apollo/react-hooks'
import { generateUseQuery } from '../rest/query'

const useQuery = generateUseQuery('demo4', originalQuery)
export const useDemo4Query = (baseOptions?: Apollo.QueryHookOptions<Demo4Query, Demo4QueryVariables>):
  ApolloQueryResult<Demo4Query> | QueryResult<Demo4Query, Demo4QueryVariables> => {

  return useQuery(baseOptions)
}