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

/** This is class / type level documentation. */
export type Demo4 = {
  __typename?: 'Demo4';
  /**
   * This describes what Alpha is and what you can do with it.
   *
   */
  alpha?: Maybe<Scalars['String']>;
  /**
   * Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget.
   *
   */
  bravo?: Maybe<Scalars['Int']>;
  /**
   * Sometimes returns true, sometimes returns false, and in rare cases it returns nothing at all!
   *
   */
  charlie?: Maybe<Scalars['Boolean']>;
  /**
   * This is a collection field. While technically null is allowed, we promise it will never be null. At worst,
   *  it'll be empty.
   *
   */
  delta?: Maybe<Array<Maybe<Scalars['String']>>>;
  /**
   * This field returns a special type. Check out the docs on this one!
   *
   */
  foxtrot?: Maybe<Demo4Relation>;
};

/**
 *
 * Markdown FTW!
 *
 *
 *  # Heading 1
 *  ## Heading 2
 *  ### Heading 3
 *  #### Heading 4
 *
 *
 * ---
 *
 *
 *
 * Images!
 *
 *
 *
 *
 * ![Image](http://localhost/dims4/default/4f6815a/2147483647/strip/false/crop/2460x1976+0+0/resize/187x150!/quality/90/?url=http%3A%2F%2Fbrightspot-brightspot.s3.amazonaws.com%2Fbsp%2Fd2%2F10%2F35af456b475c80650232a724817a%2F2.2_gradient%20centered.png)
 *
 *
 *
 * ---
 *
 *
 *
 * Text styles!
 *
 *
 * **This sentence is bold.**
 *
 *
 * *This sentence is italic.*
 *
 *
 * `// This sentence is code.`
 *
 *
 * > But, please, don't quote me on that!
 * >
 *
 *
 *
 * ---
 *
 *
 *
 * More Code!
 *
 *
 *  ```
 *  public static void main(String[] args) {
 *      System.out.println("Brightspot is awesome!! 🎉 😎");
 *  }
 *  ```
 *
 * ---
 *
 *
 *
 * Lists!
 *
 *
 *
 * 1. **Agility** - With a headless implementation, back end and front end developers can be working on their parts of the project concurrently.
 * 1. **Flexibility** - Ability to mix and match front-end content offerings, meaning the best user experience can be delivered across every device, channel and touchpoint.
 * 1. **Resiliency** - Supports organizations in future-proofing their businesses by making it easy to continually evolve alongside new technologies.
 * 1. **Reusability** - The modular approach of a headless CMS likewise means you're far less likely to have code that's customized for a specific implementation.
 *
 */
export type Demo4Relation = {
  __typename?: 'Demo4Relation';
  /**
   * I think you already know everything there is to know about this type and its fields.
   *
   */
  value?: Maybe<Scalars['String']>;
};

export type Query = {
  __typename?: 'Query';
  Demo4?: Maybe<Demo4>;
};

export type Demo4QueryVariables = Exact<{ [key: string]: never; }>;


export type Demo4Query = { __typename?: 'Query', Demo4?: Maybe<{ __typename?: 'Demo4', alpha?: Maybe<string>, bravo?: Maybe<number>, charlie?: Maybe<boolean>, delta?: Maybe<Array<Maybe<string>>>, foxtrot?: Maybe<{ __typename?: 'Demo4Relation', value?: Maybe<string> }> }> };


export const Demo4Document = gql`
    query Demo4 {
  Demo4 {
    alpha
    bravo
    charlie
    delta
    foxtrot {
      value
    }
  }
}
    `;

/**
 * __useDemo4Query__
 *
 * To run a query within a React component, call `useDemo4Query` and pass it any options that fit your needs.
 * When your component renders, `useDemo4Query` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useDemo4Query({
 *   variables: {
 *   },
 * });
 */
export function useDemo4Query(baseOptions?: Apollo.QueryHookOptions<Demo4Query, Demo4QueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<Demo4Query, Demo4QueryVariables>(Demo4Document, options);
      }
export function useDemo4LazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<Demo4Query, Demo4QueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<Demo4Query, Demo4QueryVariables>(Demo4Document, options);
        }
export type Demo4QueryHookResult = ReturnType<typeof useDemo4Query>;
export type Demo4LazyQueryHookResult = ReturnType<typeof useDemo4LazyQuery>;
export type Demo4QueryResult = Apollo.QueryResult<Demo4Query, Demo4QueryVariables>;