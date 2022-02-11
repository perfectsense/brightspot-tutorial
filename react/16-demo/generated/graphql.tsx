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

export type Demo16 = {
  __typename?: 'Demo16';
  _theme?: Maybe<ThemeFields>;
  description?: Maybe<Scalars['String']>;
  image?: Maybe<ImageAttributes>;
  title?: Maybe<Scalars['String']>;
};


export type Demo16ImageArgs = {
  size?: Maybe<Scalars['String']>;
};

export type ImageAltFormat = {
  __typename?: 'ImageAltFormat';
  name?: Maybe<Scalars['String']>;
  src?: Maybe<Scalars['String']>;
  srcSets: Array<ImageSrcSet>;
  srcSetsRaw?: Maybe<Scalars['String']>;
};

export type ImageAttributes = {
  __typename?: 'ImageAttributes';
  /** @deprecated Use 'size -> altFormats' or 'sizes -> altFormats' */
  altFormats: Array<ImageAltFormat>;
  contentType?: Maybe<Scalars['String']>;
  crops: Array<ImageCrop>;
  editorSettings?: Maybe<ImageEditorSettings>;
  edits?: Maybe<ImageEdits>;
  filename?: Maybe<Scalars['String']>;
  focus?: Maybe<ImageFocus>;
  height?: Maybe<Scalars['Int']>;
  publicUrl?: Maybe<Scalars['String']>;
  size?: Maybe<ImageSize>;
  sizes: Array<ImageSize>;
  /** @deprecated Use 'size -> src' or 'sizes -> src' */
  src?: Maybe<Scalars['String']>;
  /** @deprecated Use 'size -> srcSets' or 'sizes -> srcSets' */
  srcSets: Array<ImageSrcSet>;
  /** @deprecated Use 'size -> srcSetsRaw' or 'sizes -> srcSetsRaw' */
  srcSetsRaw?: Maybe<Scalars['String']>;
  width?: Maybe<Scalars['Int']>;
};


export type ImageAttributesSizeArgs = {
  name?: Maybe<Scalars['String']>;
};


export type ImageAttributesSizesArgs = {
  names?: Maybe<Array<Scalars['String']>>;
};

export type ImageCrop = {
  __typename?: 'ImageCrop';
  height?: Maybe<Scalars['Float']>;
  name?: Maybe<Scalars['String']>;
  width?: Maybe<Scalars['Float']>;
  x?: Maybe<Scalars['Float']>;
  y?: Maybe<Scalars['Float']>;
};

export type ImageEditorSettings = {
  __typename?: 'ImageEditorSettings';
  baseUrl?: Maybe<Scalars['String']>;
  sharedSecret?: Maybe<Scalars['String']>;
};

export type ImageEdits = {
  __typename?: 'ImageEdits';
  brightness?: Maybe<Scalars['Float']>;
  contrast?: Maybe<Scalars['Float']>;
  flipH?: Maybe<Scalars['Boolean']>;
  flipV?: Maybe<Scalars['Boolean']>;
  grayscale?: Maybe<Scalars['Boolean']>;
  invert?: Maybe<Scalars['Boolean']>;
  rotate?: Maybe<Scalars['Int']>;
  sepia?: Maybe<Scalars['Boolean']>;
  sharpen?: Maybe<Scalars['Int']>;
};

export type ImageFocus = {
  __typename?: 'ImageFocus';
  x?: Maybe<Scalars['Float']>;
  y?: Maybe<Scalars['Float']>;
};

export type ImageSize = {
  __typename?: 'ImageSize';
  altFormats: Array<ImageAltFormat>;
  height?: Maybe<Scalars['Int']>;
  name?: Maybe<Scalars['String']>;
  src?: Maybe<Scalars['String']>;
  srcSets: Array<ImageSrcSet>;
  srcSetsRaw?: Maybe<Scalars['String']>;
  width?: Maybe<Scalars['Int']>;
};

export type ImageSrcSet = {
  __typename?: 'ImageSrcSet';
  size?: Maybe<Scalars['String']>;
  src?: Maybe<Scalars['String']>;
};

export type PageEntry = Demo16;

export type Query = {
  __typename?: 'Query';
  Demo16?: Maybe<Demo16>;
};


export type QueryDemo16Args = {
  id?: Maybe<Scalars['ID']>;
  path?: Maybe<Scalars['String']>;
};

export type ThemeFields = {
  __typename?: 'ThemeFields';
  backgroundColor?: Maybe<Scalars['String']>;
  primaryColor?: Maybe<Scalars['String']>;
  primaryFont?: Maybe<Scalars['String']>;
  secondaryColor?: Maybe<Scalars['String']>;
};

export type Demo16GetQueryVariables = Exact<{
  id?: Maybe<Scalars['ID']>;
  path?: Maybe<Scalars['String']>;
}>;


export type Demo16GetQuery = { __typename?: 'Query', Demo16?: Maybe<{ __typename?: 'Demo16', title?: Maybe<string>, description?: Maybe<string>, image?: Maybe<{ __typename?: 'ImageAttributes', publicUrl?: Maybe<string>, width?: Maybe<number>, height?: Maybe<number>, sizes: Array<{ __typename?: 'ImageSize', name?: Maybe<string>, src?: Maybe<string>, width?: Maybe<number>, height?: Maybe<number>, srcSets: Array<{ __typename?: 'ImageSrcSet', src?: Maybe<string>, size?: Maybe<string> }>, altFormats: Array<{ __typename?: 'ImageAltFormat', name?: Maybe<string>, src?: Maybe<string>, srcSets: Array<{ __typename?: 'ImageSrcSet', src?: Maybe<string>, size?: Maybe<string> }> }> }> }>, _theme?: Maybe<{ __typename?: 'ThemeFields', primaryColor?: Maybe<string>, secondaryColor?: Maybe<string>, backgroundColor?: Maybe<string>, primaryFont?: Maybe<string> }> }> };


export const Demo16GetDocument = gql`
    query Demo16Get($id: ID, $path: String) {
  Demo16(id: $id, path: $path) {
    title
    description
    image {
      publicUrl
      width
      height
      sizes {
        name
        src
        width
        height
        srcSets {
          src
          size
        }
        altFormats {
          name
          src
          srcSets {
            src
            size
          }
        }
      }
    }
    _theme {
      primaryColor
      secondaryColor
      backgroundColor
      primaryFont
    }
  }
}
    `;

/**
 * __useDemo16GetQuery__
 *
 * To run a query within a React component, call `useDemo16GetQuery` and pass it any options that fit your needs.
 * When your component renders, `useDemo16GetQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useDemo16GetQuery({
 *   variables: {
 *      id: // value for 'id'
 *      path: // value for 'path'
 *   },
 * });
 */
export function useDemo16GetQuery(baseOptions?: Apollo.QueryHookOptions<Demo16GetQuery, Demo16GetQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<Demo16GetQuery, Demo16GetQueryVariables>(Demo16GetDocument, options);
      }
export function useDemo16GetLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<Demo16GetQuery, Demo16GetQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<Demo16GetQuery, Demo16GetQueryVariables>(Demo16GetDocument, options);
        }
export type Demo16GetQueryHookResult = ReturnType<typeof useDemo16GetQuery>;
export type Demo16GetLazyQueryHookResult = ReturnType<typeof useDemo16GetLazyQuery>;
export type Demo16GetQueryResult = Apollo.QueryResult<Demo16GetQuery, Demo16GetQueryVariables>;