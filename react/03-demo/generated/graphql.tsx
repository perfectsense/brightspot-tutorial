import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
export type Maybe<T> = T | null;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
const defaultOptions =  {}
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
};

export type Demo3 = {
  __typename?: 'Demo3';
  automaticPersistedQueries?: Maybe<Scalars['Boolean']>;
  byline?: Maybe<Scalars['String']>;
  message?: Maybe<Scalars['String']>;
  randomNumber?: Maybe<Scalars['Int']>;
  staticPersistedQueries?: Maybe<Scalars['Boolean']>;
};

export type Query = {
  __typename?: 'Query';
  Demo3?: Maybe<Demo3>;
};


export type QueryDemo3Args = {
  id?: Maybe<Scalars['ID']>;
};

export type Demo3QueryVariables = Exact<{
  id?: Maybe<Scalars['ID']>;
}>;


export type Demo3Query = { __typename?: 'Query', Demo3?: Maybe<{ __typename?: 'Demo3', message?: Maybe<string>, byline?: Maybe<string>, randomNumber?: Maybe<number>, automaticPersistedQueries?: Maybe<boolean> }> };


export const Demo3Document = gql`
    query Demo3($id: ID) {
  Demo3(id: $id) {
    message
    byline
    randomNumber
    automaticPersistedQueries
  }
}
    `;

/**
 * __useDemo3Query__
 *
 * To run a query within a React component, call `useDemo3Query` and pass it any options that fit your needs.
 * When your component renders, `useDemo3Query` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useDemo3Query({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useDemo3Query(baseOptions?: Apollo.QueryHookOptions<Demo3Query, Demo3QueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<Demo3Query, Demo3QueryVariables>(Demo3Document, options);
      }
export function useDemo3LazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<Demo3Query, Demo3QueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<Demo3Query, Demo3QueryVariables>(Demo3Document, options);
        }
export type Demo3QueryHookResult = ReturnType<typeof useDemo3Query>;
export type Demo3LazyQueryHookResult = ReturnType<typeof useDemo3LazyQuery>;
export type Demo3QueryResult = Apollo.QueryResult<Demo3Query, Demo3QueryVariables>;