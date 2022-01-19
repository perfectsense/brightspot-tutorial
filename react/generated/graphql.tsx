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
  /** Represent the milliseconds since the standard base time known as "the epoch", namely January 1, 1970, 00:00:00 GMT */
  Date: any;
};

/** Represents a generic content. */
export type Content = {
  _id?: Maybe<Scalars['ID']>;
};

export type Content_ObjectModificationCmsContentField = {
  __typename?: 'Content_ObjectModificationCmsContentField';
  publishDate?: Maybe<Scalars['Date']>;
  updateDate?: Maybe<Scalars['Date']>;
};

export type Query = {
  __typename?: 'Query';
  Text?: Maybe<Text>;
  _Entry?: Maybe<_Entry>;
};


export type QueryTextArgs = {
  id?: Maybe<Scalars['ID']>;
  path?: Maybe<Scalars['String']>;
};


export type Query_EntryArgs = {
  id?: Maybe<Scalars['ID']>;
  path?: Maybe<Scalars['String']>;
};

/** Represents a generic record. */
export type Record = Record_Interface & {
  __typename?: 'Record';
  _id?: Maybe<Scalars['ID']>;
  cms_content?: Maybe<Content_ObjectModificationCmsContentField>;
};

/** Represents a generic record. */
export type Record_Interface = {
  _id?: Maybe<Scalars['ID']>;
};

export type Renderer = {
  _id?: Maybe<Scalars['ID']>;
};

export type Text = Content & Record_Interface & Renderer & {
  __typename?: 'Text';
  _id?: Maybe<Scalars['ID']>;
  cms_content?: Maybe<Content_ObjectModificationCmsContentField>;
  headers?: Maybe<Array<Maybe<Text_Header>>>;
  /** Returns the name. */
  name?: Maybe<Scalars['String']>;
  statusCode?: Maybe<Scalars['Int']>;
  /** Returns the text. */
  text?: Maybe<Scalars['String']>;
};

export type Text_Header = Record_Interface & {
  __typename?: 'Text_Header';
  _id?: Maybe<Scalars['ID']>;
  name?: Maybe<Scalars['String']>;
  value?: Maybe<Scalars['String']>;
};

export type _Entry = Text;

export type HomeQueryVariables = Exact<{ [key: string]: never; }>;


export type HomeQuery = { __typename?: 'Query', Text?: Maybe<{ __typename?: 'Text', name?: Maybe<string>, text?: Maybe<string>, statusCode?: Maybe<number>, _id?: Maybe<string> }> };


export const HomeDocument = gql`
    query Home {
  Text(id: "0000017e-744f-d0cd-a5ff-f47ff5100000") {
    name
    text
    statusCode
    _id
  }
}
    `;

/**
 * __useHomeQuery__
 *
 * To run a query within a React component, call `useHomeQuery` and pass it any options that fit your needs.
 * When your component renders, `useHomeQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useHomeQuery({
 *   variables: {
 *   },
 * });
 */
export function useHomeQuery(baseOptions?: Apollo.QueryHookOptions<HomeQuery, HomeQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<HomeQuery, HomeQueryVariables>(HomeDocument, options);
      }
export function useHomeLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<HomeQuery, HomeQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<HomeQuery, HomeQueryVariables>(HomeDocument, options);
        }
export type HomeQueryHookResult = ReturnType<typeof useHomeQuery>;
export type HomeLazyQueryHookResult = ReturnType<typeof useHomeLazyQuery>;
export type HomeQueryResult = Apollo.QueryResult<HomeQuery, HomeQueryVariables>;