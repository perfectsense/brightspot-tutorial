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

export type Image = {
  __typename?: 'Image';
  image?: Maybe<ImageAttributes>;
  title?: Maybe<Scalars['String']>;
};


export type ImageImageArgs = {
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

export type PageEntry = Image;

export type Query = {
  __typename?: 'Query';
  Image?: Maybe<Image>;
};


export type QueryImageArgs = {
  id?: Maybe<Scalars['ID']>;
};

export type ImageByIdQueryVariables = Exact<{
  id?: Maybe<Scalars['ID']>;
  size?: Maybe<Scalars['String']>;
}>;


export type ImageByIdQuery = { __typename?: 'Query', Image?: Maybe<{ __typename?: 'Image', title?: Maybe<string>, image?: Maybe<{ __typename?: 'ImageAttributes', height?: Maybe<number>, width?: Maybe<number>, size?: Maybe<{ __typename?: 'ImageSize', src?: Maybe<string> }> }> }> };


export const ImageByIdDocument = gql`
    query ImageById($id: ID, $size: String) {
  Image(id: $id) {
    title
    image(size: $size) {
      size(name: $size) {
        src
      }
      height
      width
    }
  }
}
    `;

/**
 * __useImageByIdQuery__
 *
 * To run a query within a React component, call `useImageByIdQuery` and pass it any options that fit your needs.
 * When your component renders, `useImageByIdQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useImageByIdQuery({
 *   variables: {
 *      id: // value for 'id'
 *      size: // value for 'size'
 *   },
 * });
 */
export function useImageByIdQuery(baseOptions?: Apollo.QueryHookOptions<ImageByIdQuery, ImageByIdQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<ImageByIdQuery, ImageByIdQueryVariables>(ImageByIdDocument, options);
      }
export function useImageByIdLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<ImageByIdQuery, ImageByIdQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<ImageByIdQuery, ImageByIdQueryVariables>(ImageByIdDocument, options);
        }
export type ImageByIdQueryHookResult = ReturnType<typeof useImageByIdQuery>;
export type ImageByIdLazyQueryHookResult = ReturnType<typeof useImageByIdLazyQuery>;
export type ImageByIdQueryResult = Apollo.QueryResult<ImageByIdQuery, ImageByIdQueryVariables>;