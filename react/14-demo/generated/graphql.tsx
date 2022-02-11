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

export type Demo14 = {
  __typename?: 'Demo14';
  description?: Maybe<Scalars['String']>;
  title?: Maybe<Scalars['String']>;
};

export type PageEntry = Demo14;

export type Query = {
  __typename?: 'Query';
  Demo14?: Maybe<Demo14>;
};


export type QueryDemo14Args = {
  id?: Maybe<Scalars['ID']>;
  path?: Maybe<Scalars['String']>;
};

export type Demo14ByIdQueryVariables = Exact<{
  id?: Maybe<Scalars['ID']>;
}>;


export type Demo14ByIdQuery = { __typename?: 'Query', Demo14?: Maybe<{ __typename?: 'Demo14', title?: Maybe<string>, description?: Maybe<string> }> };


export const Demo14ByIdDocument = gql`
    query Demo14ById($id: ID) {
  Demo14(id: $id) {
    title
    description
  }
}
    `;

/**
 * __useDemo14ByIdQuery__
 *
 * To run a query within a React component, call `useDemo14ByIdQuery` and pass it any options that fit your needs.
 * When your component renders, `useDemo14ByIdQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useDemo14ByIdQuery({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useDemo14ByIdQuery(baseOptions?: Apollo.QueryHookOptions<Demo14ByIdQuery, Demo14ByIdQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<Demo14ByIdQuery, Demo14ByIdQueryVariables>(Demo14ByIdDocument, options);
      }
export function useDemo14ByIdLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<Demo14ByIdQuery, Demo14ByIdQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<Demo14ByIdQuery, Demo14ByIdQueryVariables>(Demo14ByIdDocument, options);
        }
export type Demo14ByIdQueryHookResult = ReturnType<typeof useDemo14ByIdQuery>;
export type Demo14ByIdLazyQueryHookResult = ReturnType<typeof useDemo14ByIdLazyQuery>;
export type Demo14ByIdQueryResult = Apollo.QueryResult<Demo14ByIdQuery, Demo14ByIdQueryVariables>;