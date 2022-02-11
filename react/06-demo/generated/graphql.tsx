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
  /** A custom scalar that handles identifiers for mutation diff fields */
  DiffId: any;
  /** Long type */
  Long: any;
  /** Convert ID to "Recordable" object */
  RefId: any;
  /** Convert ID, username, or email to "ToolUser" object */
  ToolUser: any;
};

export type Globals = {
  __typename?: 'Globals';
  com_psddev_analytics_PageViewsModification?: Maybe<Com_Psddev_Analytics_PageViewsModification>;
  com_psddev_cms_db_BulkUploadDraft?: Maybe<Com_Psddev_Cms_Db_BulkUploadDraft>;
  com_psddev_cms_db_ContentTemplateSource?: Maybe<Com_Psddev_Cms_Db_ContentTemplateSource>;
  com_psddev_cms_db_Content_ObjectModification?: Maybe<Com_Psddev_Cms_Db_Content_ObjectModification>;
  com_psddev_cms_db_Directory_Data?: Maybe<Com_Psddev_Cms_Db_Directory_Data>;
  com_psddev_cms_db_Directory_ObjectModification?: Maybe<Com_Psddev_Cms_Db_Directory_ObjectModification>;
  com_psddev_cms_db_Draft_NameData?: Maybe<Com_Psddev_Cms_Db_Draft_NameData>;
  com_psddev_cms_db_ExternalItemImported?: Maybe<Com_Psddev_Cms_Db_ExternalItemImported>;
  com_psddev_cms_db_SiteCopierObjectModification?: Maybe<Com_Psddev_Cms_Db_SiteCopierObjectModification>;
  com_psddev_cms_db_Site_ObjectModification?: Maybe<Com_Psddev_Cms_Db_Site_ObjectModification>;
  com_psddev_cms_db_Workflow_Data?: Maybe<Com_Psddev_Cms_Db_Workflow_Data>;
  com_psddev_dari_db_ColorDistribution_Data?: Maybe<Com_Psddev_Dari_Db_ColorDistribution_Data>;
  com_psddev_graphql_cma_ContentManagementApiMutationMetadata?: Maybe<Com_Psddev_Graphql_Cma_ContentManagementApiMutationMetadata>;
};

export type GlobalsInput = {
  com_psddev_analytics_PageViewsModificationInput?: Maybe<Com_Psddev_Analytics_PageViewsModificationInput>;
  com_psddev_cms_db_BulkUploadDraftInput?: Maybe<Com_Psddev_Cms_Db_BulkUploadDraftInput>;
  com_psddev_cms_db_ContentTemplateSourceInput?: Maybe<Com_Psddev_Cms_Db_ContentTemplateSourceInput>;
  com_psddev_cms_db_Content_ObjectModificationInput?: Maybe<Com_Psddev_Cms_Db_Content_ObjectModificationInput>;
  com_psddev_cms_db_Directory_DataInput?: Maybe<Com_Psddev_Cms_Db_Directory_DataInput>;
  com_psddev_cms_db_Directory_ObjectModificationInput?: Maybe<Com_Psddev_Cms_Db_Directory_ObjectModificationInput>;
  com_psddev_cms_db_Draft_NameDataInput?: Maybe<Com_Psddev_Cms_Db_Draft_NameDataInput>;
  com_psddev_cms_db_ExternalItemImportedInput?: Maybe<Com_Psddev_Cms_Db_ExternalItemImportedInput>;
  com_psddev_cms_db_SiteCopierObjectModificationInput?: Maybe<Com_Psddev_Cms_Db_SiteCopierObjectModificationInput>;
  com_psddev_cms_db_Site_ObjectModificationInput?: Maybe<Com_Psddev_Cms_Db_Site_ObjectModificationInput>;
  com_psddev_cms_db_Workflow_DataInput?: Maybe<Com_Psddev_Cms_Db_Workflow_DataInput>;
  com_psddev_dari_db_ColorDistribution_DataInput?: Maybe<Com_Psddev_Dari_Db_ColorDistribution_DataInput>;
  com_psddev_graphql_cma_ContentManagementApiMutationMetadataInput?: Maybe<Com_Psddev_Graphql_Cma_ContentManagementApiMutationMetadataInput>;
};

export type Mutation = {
  __typename?: 'Mutation';
  com_brightspot_tutorial_graphql_demo6_Demo6Delete?: Maybe<Com_Brightspot_Tutorial_Graphql_Demo6_Demo6>;
  com_brightspot_tutorial_graphql_demo6_Demo6Save?: Maybe<Com_Brightspot_Tutorial_Graphql_Demo6_Demo6>;
};


export type MutationCom_Brightspot_Tutorial_Graphql_Demo6_Demo6DeleteArgs = {
  id: Scalars['ID'];
  permanently?: Maybe<Scalars['Boolean']>;
};


export type MutationCom_Brightspot_Tutorial_Graphql_Demo6_Demo6SaveArgs = {
  diffs?: Maybe<Array<Maybe<Com_Brightspot_Tutorial_Graphql_Demo6_Demo6DiffInput>>>;
  id?: Maybe<Scalars['DiffId']>;
  toolUser?: Maybe<Scalars['ToolUser']>;
};

export type PageInfo = {
  __typename?: 'PageInfo';
  count?: Maybe<Scalars['Long']>;
  hasNext?: Maybe<Scalars['Boolean']>;
  limit?: Maybe<Scalars['Int']>;
  offset?: Maybe<Scalars['Long']>;
};

export type Query = {
  __typename?: 'Query';
  com_brightspot_tutorial_graphql_demo6_Demo6Query?: Maybe<Com_Brightspot_Tutorial_Graphql_Demo6_Demo6QueryResult>;
};


export type QueryCom_Brightspot_Tutorial_Graphql_Demo6_Demo6QueryArgs = {
  id?: Maybe<Scalars['ID']>;
  limit?: Maybe<Scalars['Int']>;
  offset?: Maybe<Scalars['Long']>;
  predicate?: Maybe<Scalars['String']>;
  sorts?: Maybe<Array<SortInput>>;
  where?: Maybe<WhereInput>;
};

export type Reference = {
  __typename?: 'Reference';
  _id?: Maybe<Scalars['ID']>;
  _type?: Maybe<Scalars['ID']>;
};

export type SortInput = {
  options: Array<Scalars['String']>;
  order: SortOrder;
};

export enum SortOrder {
  Ascending = 'ascending',
  Closest = 'closest',
  Descending = 'descending',
  Farthest = 'farthest',
  Newest = 'newest',
  Oldest = 'oldest',
  Relevant = 'relevant'
}

export type WhereInput = {
  arguments?: Maybe<Array<Maybe<Scalars['String']>>>;
  predicate: Scalars['String'];
};

/**
 * **Type Metadata**
 * - Publishable: `true`
 *
 */
export type Com_Brightspot_Tutorial_Graphql_Demo6_Demo6 = Com_Psddev_Cms_Db_Content & Com_Psddev_Dari_Db_Record & {
  __typename?: 'com_brightspot_tutorial_graphql_demo6_Demo6';
  _globals?: Maybe<Globals>;
  _id?: Maybe<Scalars['ID']>;
  _type?: Maybe<Scalars['ID']>;
  /**
   * **Field Metadata**
   * - Indexed
   */
  firstName?: Maybe<Scalars['String']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Full Name`
   */
  getFullName?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   */
  lastName?: Maybe<Scalars['String']>;
  title?: Maybe<Scalars['String']>;
};

export type Com_Brightspot_Tutorial_Graphql_Demo6_Demo6DiffInput = {
  com_brightspot_tutorial_graphql_demo6_Demo6Diff?: Maybe<Com_Brightspot_Tutorial_Graphql_Demo6_Demo6Input>;
  com_psddev_cms_db_WorkflowLogDiff?: Maybe<Com_Psddev_Cms_Db_WorkflowLogInput>;
  id?: Maybe<Scalars['DiffId']>;
};

/**
 * **Type Metadata**
 * - Publishable: `true`
 *
 */
export type Com_Brightspot_Tutorial_Graphql_Demo6_Demo6Input = {
  _globals?: Maybe<GlobalsInput>;
  /**
   * **Field Metadata**
   * - Indexed
   */
  firstName?: Maybe<Scalars['String']>;
  getFullName?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   */
  lastName?: Maybe<Scalars['String']>;
  title?: Maybe<Scalars['String']>;
};

export type Com_Brightspot_Tutorial_Graphql_Demo6_Demo6QueryResult = {
  __typename?: 'com_brightspot_tutorial_graphql_demo6_Demo6QueryResult';
  items: Array<Com_Brightspot_Tutorial_Graphql_Demo6_Demo6>;
  pageInfo?: Maybe<PageInfo>;
};

/**
 * Modification to index all metrics available via PageViewsSupplier and PageMetricsSupplier.
 *
 * **Type Metadata**
 * - Display Name: `Page Views`
 *
 */
export type Com_Psddev_Analytics_PageViewsModification = {
  __typename?: 'com_psddev_analytics_PageViewsModification';
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Average Time On Page`
   *
   * Internal name is "analytics.pageViews.getAverageTimeOnPage"
   */
  getAverageTimeOnPage?: Maybe<Scalars['Float']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Bounces`
   * - Minimum: `-9223372036854775808`
   * - Maximum: `9223372036854775807`
   * - Step: `1`
   *
   * Internal name is "analytics.pageViews.getBounces"
   */
  getBounces?: Maybe<Scalars['Float']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Entrances`
   * - Minimum: `-9223372036854775808`
   * - Maximum: `9223372036854775807`
   * - Step: `1`
   *
   * Internal name is "analytics.pageViews.getEntrances"
   */
  getEntrances?: Maybe<Scalars['Float']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Exit Rate`
   *
   * Internal name is "analytics.pageViews.getExitRate"
   */
  getExitRate?: Maybe<Scalars['Float']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Page Value`
   *
   * Internal name is "analytics.pageViews.getPageValue"
   */
  getPageValue?: Maybe<Scalars['Float']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Page Views`
   * - Minimum: `-9223372036854775808`
   * - Maximum: `9223372036854775807`
   * - Step: `1`
   *
   * Internal name is "analytics.pageViews.getPageViews"
   */
  getPageViews?: Maybe<Scalars['Float']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Unique Page Views`
   * - Minimum: `-9223372036854775808`
   * - Maximum: `9223372036854775807`
   * - Step: `1`
   *
   * Internal name is "analytics.pageViews.getUniquePageViews"
   */
  getUniquePageViews?: Maybe<Scalars['Float']>;
};

/**
 * Modification to index all metrics available via PageViewsSupplier and PageMetricsSupplier.
 *
 * **Type Metadata**
 * - Display Name: `Page Views`
 *
 */
export type Com_Psddev_Analytics_PageViewsModificationInput = {
  getAverageTimeOnPage?: Maybe<Scalars['Float']>;
  getBounces?: Maybe<Scalars['Float']>;
  getEntrances?: Maybe<Scalars['Float']>;
  getExitRate?: Maybe<Scalars['Float']>;
  getPageValue?: Maybe<Scalars['Float']>;
  getPageViews?: Maybe<Scalars['Float']>;
  getUniquePageViews?: Maybe<Scalars['Float']>;
};

export type Com_Psddev_Cms_Db_BulkUploadDraft = {
  __typename?: 'com_psddev_cms_db_BulkUploadDraft';
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.bulkUpload.containerId"
   */
  containerId?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.bulkUpload.uploadId"
   */
  uploadId?: Maybe<Scalars['ID']>;
};

export type Com_Psddev_Cms_Db_BulkUploadDraftInput = {
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.bulkUpload.containerId"
   */
  containerId?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.bulkUpload.uploadId"
   */
  uploadId?: Maybe<Scalars['ID']>;
};

/**
 * Represents a generic content.
 *
 * **Type Metadata**
 * - Publishable: `true`
 *
 */
export type Com_Psddev_Cms_Db_Content = {
  _globals?: Maybe<Globals>;
  _id?: Maybe<Scalars['ID']>;
  _type?: Maybe<Scalars['ID']>;
};

export type Com_Psddev_Cms_Db_ContentTemplateSource = {
  __typename?: 'com_psddev_cms_db_ContentTemplateSource';
  source?: Maybe<Reference>;
  sourceId?: Maybe<Scalars['ID']>;
};

export type Com_Psddev_Cms_Db_ContentTemplateSourceInput = {
  source?: Maybe<Scalars['RefId']>;
  sourceId?: Maybe<Scalars['ID']>;
};

/**
 * Modification that adds CMS content information.
 *
 * **Type Metadata**
 * - Display Name: `Object`
 *
 */
export type Com_Psddev_Cms_Db_Content_ObjectModification = {
  __typename?: 'com_psddev_cms_db_Content_ObjectModification';
  /**
   * Field getter method Javadoc:
   * Returns `true` if this content is a draft.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.draft"
   */
  draft?: Maybe<Scalars['Boolean']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Calendar Date`
   */
  getCalendarDate?: Maybe<Scalars['Date']>;
  /**
   * **Field Metadata**
   * - Display Name: `Overlaid?`
   */
  overlaid?: Maybe<Scalars['Boolean']>;
  /**
   * Field getter method Javadoc:
   * Returns the date when the given `object` was published.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.publishDate"
   */
  publishDate?: Maybe<Scalars['Date']>;
  /**
   * Field getter method Javadoc:
   * Returns the tool user that published the given `object`.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.publishUser"
   */
  publishUser?: Maybe<Reference>;
  scheduleDate?: Maybe<Scalars['Date']>;
  /**
   * **Field Metadata**
   * - Display Name: `Scheduled?`
   */
  scheduled?: Maybe<Scalars['Boolean']>;
  /**
   * Field getter method Javadoc:
   * Returns `true` if this content is a trash.
   *
   * **Field Metadata**
   * - Indexed
   * - Display Name: `Archived`
   *
   * Internal name is "cms.content.trashed"
   */
  trash?: Maybe<Scalars['Boolean']>;
  /**
   * Field getter method Javadoc:
   * Returns the date when the given `object` was last updated.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.updateDate"
   */
  updateDate?: Maybe<Scalars['Date']>;
  /**
   * Field getter method Javadoc:
   * Returns the tool user that last updated the given `object`.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.updateUser"
   */
  updateUser?: Maybe<Reference>;
};

/**
 * Modification that adds CMS content information.
 *
 * **Type Metadata**
 * - Display Name: `Object`
 *
 */
export type Com_Psddev_Cms_Db_Content_ObjectModificationInput = {
  /**
   * Field getter method Javadoc:
   * Sets whether this content is a draft.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.draft"
   */
  draft?: Maybe<Scalars['Boolean']>;
  getCalendarDate?: Maybe<Scalars['Date']>;
  /**
   * **Field Metadata**
   * - Display Name: `Overlaid?`
   */
  overlaid?: Maybe<Scalars['Boolean']>;
  /**
   * Field getter method Javadoc:
   * Sets the date when the given `object` was published.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.publishDate"
   */
  publishDate?: Maybe<Scalars['Date']>;
  /**
   * Field getter method Javadoc:
   * Sets the tool user that published the given `object`.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.publishUser"
   */
  publishUser?: Maybe<Scalars['RefId']>;
  scheduleDate?: Maybe<Scalars['Date']>;
  /**
   * **Field Metadata**
   * - Display Name: `Scheduled?`
   */
  scheduled?: Maybe<Scalars['Boolean']>;
  /**
   * Field getter method Javadoc:
   * Sets whether this content is a trash.
   *
   * **Field Metadata**
   * - Indexed
   * - Display Name: `Archived`
   *
   * Internal name is "cms.content.trashed"
   */
  trash?: Maybe<Scalars['Boolean']>;
  /**
   * Field getter method Javadoc:
   * Sets the date when the given `object` was last updated.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.updateDate"
   */
  updateDate?: Maybe<Scalars['Date']>;
  /**
   * Field getter method Javadoc:
   * Sets the tool user that last updated the given `object`.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.content.updateUser"
   */
  updateUser?: Maybe<Scalars['RefId']>;
};

/**
 * **Type Metadata**
 * - Display Name: `Directory Data`
 *
 */
export type Com_Psddev_Cms_Db_Directory_Data = {
  __typename?: 'com_psddev_cms_db_Directory_Data';
  automaticRawPaths?: Maybe<Array<Maybe<Scalars['String']>>>;
  /**
   * Method Javadoc:
   * Returns whether or not this object contains any paths.
   *
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Has Path?`
   *
   * Internal name is "cms.directory.hasPath"
   */
  hasPath?: Maybe<Scalars['Boolean']>;
};

/**
 * **Type Metadata**
 * - Display Name: `Directory Data`
 *
 */
export type Com_Psddev_Cms_Db_Directory_DataInput = {
  automaticRawPaths?: Maybe<Array<Maybe<Scalars['String']>>>;
  hasPath?: Maybe<Scalars['Boolean']>;
};

/**
 * Modification that adds directory information.
 *
 * **Type Metadata**
 * - Display Name: `Object`
 *
 */
export type Com_Psddev_Cms_Db_Directory_ObjectModification = {
  __typename?: 'com_psddev_cms_db_Directory_ObjectModification';
  /**
   * Field getter method Javadoc:
   * Returns the object name.
   */
  objectName?: Maybe<Scalars['String']>;
  /**
   * Field getter method Javadoc:
   * Returns all paths in the given Site.ObjectModification#getOwner owner site associated with this
   *  object.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.directory.paths"
   */
  paths?: Maybe<Array<Maybe<Scalars['String']>>>;
  /**
   * Field getter method Javadoc:
   * Returns the paths mode.
   */
  pathsMode?: Maybe<Com_Psddev_Cms_Db_Directory_PathsMode>;
};

/**
 * Modification that adds directory information.
 *
 * **Type Metadata**
 * - Display Name: `Object`
 *
 */
export type Com_Psddev_Cms_Db_Directory_ObjectModificationInput = {
  /**
   * Field getter method Javadoc:
   * Sets the object name.
   */
  objectName?: Maybe<Scalars['String']>;
  paths?: Maybe<Array<Maybe<Scalars['String']>>>;
  /**
   * Field getter method Javadoc:
   * Sets the paths mode.
   */
  pathsMode?: Maybe<Com_Psddev_Cms_Db_Directory_PathsMode>;
};

export enum Com_Psddev_Cms_Db_Directory_PathsMode {
  Automatic = 'AUTOMATIC',
  Manual = 'MANUAL'
}

export type Com_Psddev_Cms_Db_Draft_NameData = {
  __typename?: 'com_psddev_cms_db_Draft_NameData';
  /**
   * **Field Metadata**
   * - Minimum: `-2147483648`
   * - Maximum: `2147483647`
   * - Step: `1`
   */
  index?: Maybe<Scalars['Int']>;
};

export type Com_Psddev_Cms_Db_Draft_NameDataInput = {
  /**
   * **Field Metadata**
   * - Minimum: `-2147483648`
   * - Maximum: `2147483647`
   * - Step: `1`
   */
  index?: Maybe<Scalars['Int']>;
};

export type Com_Psddev_Cms_Db_ExternalItemImported = {
  __typename?: 'com_psddev_cms_db_ExternalItemImported';
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.externalItemImported.itemId"
   */
  itemId?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.externalItemImported.sourceType"
   */
  sourceType?: Maybe<Scalars['String']>;
};

export type Com_Psddev_Cms_Db_ExternalItemImportedInput = {
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.externalItemImported.itemId"
   */
  itemId?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.externalItemImported.sourceType"
   */
  sourceType?: Maybe<Scalars['String']>;
};

/** An interface indicating the type is capable of producing a revision that can be surfaced in the CMS com.psddev.cms.db.Revision. */
export type Com_Psddev_Cms_Db_Revision = {
  _globals?: Maybe<Globals>;
  _id?: Maybe<Scalars['ID']>;
  _type?: Maybe<Scalars['ID']>;
  com_psddev_cms_db_RevisionModification?: Maybe<Com_Psddev_Cms_Db_RevisionModification>;
};

/**
 * **Type Metadata**
 * - Display Name: `Revision`
 *
 */
export type Com_Psddev_Cms_Db_RevisionModification = {
  __typename?: 'com_psddev_cms_db_RevisionModification';
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Revision Sort Date`
   *
   * Internal name is "cms.revision.getRevisionSortDate"
   */
  getRevisionSortDate?: Maybe<Scalars['String']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Revision Sort Name`
   *
   * Internal name is "cms.revision.getRevisionSortName"
   */
  getRevisionSortName?: Maybe<Scalars['String']>;
  /**
   * **Method Metadata**
   * - Indexed
   * - Display Name: `Revision Sort Object ID`
   *
   * Internal name is "cms.revision.getRevisionSortObjectId"
   */
  getRevisionSortObjectId?: Maybe<Scalars['ID']>;
};

/**
 * **Type Metadata**
 * - Display Name: `Revision`
 *
 */
export type Com_Psddev_Cms_Db_RevisionModificationInput = {
  getRevisionSortDate?: Maybe<Scalars['String']>;
  getRevisionSortName?: Maybe<Scalars['String']>;
  getRevisionSortObjectId?: Maybe<Scalars['ID']>;
};

/**
 * **Type Metadata**
 * - Display Name: `Site Copier Object`
 *
 */
export type Com_Psddev_Cms_Db_SiteCopierObjectModification = {
  __typename?: 'com_psddev_cms_db_SiteCopierObjectModification';
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.siteCopier.copySourceId"
   */
  copySourceId?: Maybe<Scalars['ID']>;
  /**
   * **Method Metadata**
   * - Indexed
   *
   * Internal name is "cms.siteCopier.isFromSiteCopier"
   */
  isFromSiteCopier?: Maybe<Scalars['Boolean']>;
};

/**
 * **Type Metadata**
 * - Display Name: `Site Copier Object`
 *
 */
export type Com_Psddev_Cms_Db_SiteCopierObjectModificationInput = {
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.siteCopier.copySourceId"
   */
  copySourceId?: Maybe<Scalars['ID']>;
  isFromSiteCopier?: Maybe<Scalars['Boolean']>;
};

/**
 * Modification that adds object accessibility information per site.
 *
 * **Type Metadata**
 * - Display Name: `Object`
 *
 */
export type Com_Psddev_Cms_Db_Site_ObjectModification = {
  __typename?: 'com_psddev_cms_db_Site_ObjectModification';
  /**
   * Field getter method Javadoc:
   * Returns the set of blacklisted sites that aren't allowed to access this object.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.site.blacklist"
   */
  blacklist?: Maybe<Array<Maybe<Reference>>>;
  /**
   * Field getter method Javadoc:
   * Returns the set of consumer sites that are allowed to access the object.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.site.consumers"
   */
  consumers?: Maybe<Array<Maybe<Reference>>>;
  isGlobal?: Maybe<Scalars['Boolean']>;
  /**
   * Field getter method Javadoc:
   * Returns the owner that controls this object.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.site.owner"
   */
  owner?: Maybe<Reference>;
};

/**
 * Modification that adds object accessibility information per site.
 *
 * **Type Metadata**
 * - Display Name: `Object`
 *
 */
export type Com_Psddev_Cms_Db_Site_ObjectModificationInput = {
  /**
   * Field getter method Javadoc:
   * Sets the set of blacklisted sites that aren't allowed to access this object.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.site.blacklist"
   */
  blacklist?: Maybe<Array<Maybe<Scalars['RefId']>>>;
  /**
   * Field getter method Javadoc:
   * Sets the set of consumer sites that are allowed to access this object.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.site.consumers"
   */
  consumers?: Maybe<Array<Maybe<Scalars['RefId']>>>;
  isGlobal?: Maybe<Scalars['Boolean']>;
  /**
   * Field getter method Javadoc:
   * Sets the owner that controls this object.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.site.owner"
   */
  owner?: Maybe<Scalars['RefId']>;
};

export type Com_Psddev_Cms_Db_WorkflowLog = Com_Psddev_Cms_Db_Revision & Com_Psddev_Dari_Db_Record & {
  __typename?: 'com_psddev_cms_db_WorkflowLog';
  _globals?: Maybe<Globals>;
  _id?: Maybe<Scalars['ID']>;
  _type?: Maybe<Scalars['ID']>;
  com_psddev_cms_db_RevisionModification?: Maybe<Com_Psddev_Cms_Db_RevisionModification>;
  comment?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   */
  date?: Maybe<Scalars['Date']>;
  mainObjectId?: Maybe<Scalars['ID']>;
  mainObjectLabel?: Maybe<Scalars['String']>;
  newWorkflowState?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   */
  objectId?: Maybe<Scalars['ID']>;
  objectLabel?: Maybe<Scalars['String']>;
  objectTypeId?: Maybe<Scalars['ID']>;
  oldWorkflowState?: Maybe<Scalars['String']>;
  transition?: Maybe<Scalars['String']>;
  userId?: Maybe<Scalars['String']>;
};

export type Com_Psddev_Cms_Db_WorkflowLogInput = {
  _globals?: Maybe<GlobalsInput>;
  com_psddev_cms_db_RevisionModificationInput?: Maybe<Com_Psddev_Cms_Db_RevisionModificationInput>;
  comment?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   */
  date?: Maybe<Scalars['Date']>;
  mainObjectId?: Maybe<Scalars['ID']>;
  mainObjectLabel?: Maybe<Scalars['String']>;
  newWorkflowState?: Maybe<Scalars['String']>;
  /**
   * **Field Metadata**
   * - Indexed
   */
  objectId?: Maybe<Scalars['ID']>;
  objectLabel?: Maybe<Scalars['String']>;
  objectTypeId?: Maybe<Scalars['ID']>;
  oldWorkflowState?: Maybe<Scalars['String']>;
  transition?: Maybe<Scalars['String']>;
  userId?: Maybe<Scalars['String']>;
};

/**
 * **Type Metadata**
 * - Display Name: `Workflow Data`
 *
 */
export type Com_Psddev_Cms_Db_Workflow_Data = {
  __typename?: 'com_psddev_cms_db_Workflow_Data';
  /**
   * **Field Metadata**
   * - Embedded
   */
  currentLog?: Maybe<Com_Psddev_Cms_Db_WorkflowLog>;
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "cms.workflow.currentState"
   */
  currentState?: Maybe<Scalars['String']>;
};

/**
 * **Type Metadata**
 * - Display Name: `Workflow Data`
 *
 */
export type Com_Psddev_Cms_Db_Workflow_DataInput = {
  /**
   * **Field Metadata**
   * - Embedded
   */
  currentLog?: Maybe<Scalars['DiffId']>;
  currentState?: Maybe<Scalars['String']>;
};

/**
 * Stores information about how much of each color is in an image.
 *
 * **Type Metadata**
 * - Embedded
 *
 */
export type Com_Psddev_Dari_Db_ColorDistribution = Com_Psddev_Dari_Db_Record & {
  __typename?: 'com_psddev_dari_db_ColorDistribution';
  _globals?: Maybe<Globals>;
  _id?: Maybe<Scalars['ID']>;
  _type?: Maybe<Scalars['ID']>;
  n_0_0_0?: Maybe<Scalars['Float']>;
  n_0_0_20?: Maybe<Scalars['Float']>;
  n_0_0_40?: Maybe<Scalars['Float']>;
  n_0_0_60?: Maybe<Scalars['Float']>;
  n_0_0_80?: Maybe<Scalars['Float']>;
  n_0_0_100?: Maybe<Scalars['Float']>;
  n_0_40_20?: Maybe<Scalars['Float']>;
  n_0_40_40?: Maybe<Scalars['Float']>;
  n_0_40_60?: Maybe<Scalars['Float']>;
  n_0_40_80?: Maybe<Scalars['Float']>;
  n_0_60_20?: Maybe<Scalars['Float']>;
  n_0_60_40?: Maybe<Scalars['Float']>;
  n_0_60_60?: Maybe<Scalars['Float']>;
  n_0_60_80?: Maybe<Scalars['Float']>;
  n_0_80_20?: Maybe<Scalars['Float']>;
  n_0_80_40?: Maybe<Scalars['Float']>;
  n_0_80_60?: Maybe<Scalars['Float']>;
  n_0_80_80?: Maybe<Scalars['Float']>;
  n_0_100_20?: Maybe<Scalars['Float']>;
  n_0_100_40?: Maybe<Scalars['Float']>;
  n_0_100_60?: Maybe<Scalars['Float']>;
  n_0_100_80?: Maybe<Scalars['Float']>;
  n_24_40_20?: Maybe<Scalars['Float']>;
  n_24_40_40?: Maybe<Scalars['Float']>;
  n_24_40_60?: Maybe<Scalars['Float']>;
  n_24_40_80?: Maybe<Scalars['Float']>;
  n_24_60_20?: Maybe<Scalars['Float']>;
  n_24_60_40?: Maybe<Scalars['Float']>;
  n_24_60_60?: Maybe<Scalars['Float']>;
  n_24_60_80?: Maybe<Scalars['Float']>;
  n_24_80_20?: Maybe<Scalars['Float']>;
  n_24_80_40?: Maybe<Scalars['Float']>;
  n_24_80_60?: Maybe<Scalars['Float']>;
  n_24_80_80?: Maybe<Scalars['Float']>;
  n_24_100_20?: Maybe<Scalars['Float']>;
  n_24_100_40?: Maybe<Scalars['Float']>;
  n_24_100_60?: Maybe<Scalars['Float']>;
  n_24_100_80?: Maybe<Scalars['Float']>;
  n_48_40_20?: Maybe<Scalars['Float']>;
  n_48_40_40?: Maybe<Scalars['Float']>;
  n_48_40_60?: Maybe<Scalars['Float']>;
  n_48_40_80?: Maybe<Scalars['Float']>;
  n_48_60_20?: Maybe<Scalars['Float']>;
  n_48_60_40?: Maybe<Scalars['Float']>;
  n_48_60_60?: Maybe<Scalars['Float']>;
  n_48_60_80?: Maybe<Scalars['Float']>;
  n_48_80_20?: Maybe<Scalars['Float']>;
  n_48_80_40?: Maybe<Scalars['Float']>;
  n_48_80_60?: Maybe<Scalars['Float']>;
  n_48_80_80?: Maybe<Scalars['Float']>;
  n_48_100_20?: Maybe<Scalars['Float']>;
  n_48_100_40?: Maybe<Scalars['Float']>;
  n_48_100_60?: Maybe<Scalars['Float']>;
  n_48_100_80?: Maybe<Scalars['Float']>;
  n_72_40_20?: Maybe<Scalars['Float']>;
  n_72_40_40?: Maybe<Scalars['Float']>;
  n_72_40_60?: Maybe<Scalars['Float']>;
  n_72_40_80?: Maybe<Scalars['Float']>;
  n_72_60_20?: Maybe<Scalars['Float']>;
  n_72_60_40?: Maybe<Scalars['Float']>;
  n_72_60_60?: Maybe<Scalars['Float']>;
  n_72_60_80?: Maybe<Scalars['Float']>;
  n_72_80_20?: Maybe<Scalars['Float']>;
  n_72_80_40?: Maybe<Scalars['Float']>;
  n_72_80_60?: Maybe<Scalars['Float']>;
  n_72_80_80?: Maybe<Scalars['Float']>;
  n_72_100_20?: Maybe<Scalars['Float']>;
  n_72_100_40?: Maybe<Scalars['Float']>;
  n_72_100_60?: Maybe<Scalars['Float']>;
  n_72_100_80?: Maybe<Scalars['Float']>;
  n_96_40_20?: Maybe<Scalars['Float']>;
  n_96_40_40?: Maybe<Scalars['Float']>;
  n_96_40_60?: Maybe<Scalars['Float']>;
  n_96_40_80?: Maybe<Scalars['Float']>;
  n_96_60_20?: Maybe<Scalars['Float']>;
  n_96_60_40?: Maybe<Scalars['Float']>;
  n_96_60_60?: Maybe<Scalars['Float']>;
  n_96_60_80?: Maybe<Scalars['Float']>;
  n_96_80_20?: Maybe<Scalars['Float']>;
  n_96_80_40?: Maybe<Scalars['Float']>;
  n_96_80_60?: Maybe<Scalars['Float']>;
  n_96_80_80?: Maybe<Scalars['Float']>;
  n_96_100_20?: Maybe<Scalars['Float']>;
  n_96_100_40?: Maybe<Scalars['Float']>;
  n_96_100_60?: Maybe<Scalars['Float']>;
  n_96_100_80?: Maybe<Scalars['Float']>;
  n_120_40_20?: Maybe<Scalars['Float']>;
  n_120_40_40?: Maybe<Scalars['Float']>;
  n_120_40_60?: Maybe<Scalars['Float']>;
  n_120_40_80?: Maybe<Scalars['Float']>;
  n_120_60_20?: Maybe<Scalars['Float']>;
  n_120_60_40?: Maybe<Scalars['Float']>;
  n_120_60_60?: Maybe<Scalars['Float']>;
  n_120_60_80?: Maybe<Scalars['Float']>;
  n_120_80_20?: Maybe<Scalars['Float']>;
  n_120_80_40?: Maybe<Scalars['Float']>;
  n_120_80_60?: Maybe<Scalars['Float']>;
  n_120_80_80?: Maybe<Scalars['Float']>;
  n_120_100_20?: Maybe<Scalars['Float']>;
  n_120_100_40?: Maybe<Scalars['Float']>;
  n_120_100_60?: Maybe<Scalars['Float']>;
  n_120_100_80?: Maybe<Scalars['Float']>;
  n_144_40_20?: Maybe<Scalars['Float']>;
  n_144_40_40?: Maybe<Scalars['Float']>;
  n_144_40_60?: Maybe<Scalars['Float']>;
  n_144_40_80?: Maybe<Scalars['Float']>;
  n_144_60_20?: Maybe<Scalars['Float']>;
  n_144_60_40?: Maybe<Scalars['Float']>;
  n_144_60_60?: Maybe<Scalars['Float']>;
  n_144_60_80?: Maybe<Scalars['Float']>;
  n_144_80_20?: Maybe<Scalars['Float']>;
  n_144_80_40?: Maybe<Scalars['Float']>;
  n_144_80_60?: Maybe<Scalars['Float']>;
  n_144_80_80?: Maybe<Scalars['Float']>;
  n_144_100_20?: Maybe<Scalars['Float']>;
  n_144_100_40?: Maybe<Scalars['Float']>;
  n_144_100_60?: Maybe<Scalars['Float']>;
  n_144_100_80?: Maybe<Scalars['Float']>;
  n_168_40_20?: Maybe<Scalars['Float']>;
  n_168_40_40?: Maybe<Scalars['Float']>;
  n_168_40_60?: Maybe<Scalars['Float']>;
  n_168_40_80?: Maybe<Scalars['Float']>;
  n_168_60_20?: Maybe<Scalars['Float']>;
  n_168_60_40?: Maybe<Scalars['Float']>;
  n_168_60_60?: Maybe<Scalars['Float']>;
  n_168_60_80?: Maybe<Scalars['Float']>;
  n_168_80_20?: Maybe<Scalars['Float']>;
  n_168_80_40?: Maybe<Scalars['Float']>;
  n_168_80_60?: Maybe<Scalars['Float']>;
  n_168_80_80?: Maybe<Scalars['Float']>;
  n_168_100_20?: Maybe<Scalars['Float']>;
  n_168_100_40?: Maybe<Scalars['Float']>;
  n_168_100_60?: Maybe<Scalars['Float']>;
  n_168_100_80?: Maybe<Scalars['Float']>;
  n_192_40_20?: Maybe<Scalars['Float']>;
  n_192_40_40?: Maybe<Scalars['Float']>;
  n_192_40_60?: Maybe<Scalars['Float']>;
  n_192_40_80?: Maybe<Scalars['Float']>;
  n_192_60_20?: Maybe<Scalars['Float']>;
  n_192_60_40?: Maybe<Scalars['Float']>;
  n_192_60_60?: Maybe<Scalars['Float']>;
  n_192_60_80?: Maybe<Scalars['Float']>;
  n_192_80_20?: Maybe<Scalars['Float']>;
  n_192_80_40?: Maybe<Scalars['Float']>;
  n_192_80_60?: Maybe<Scalars['Float']>;
  n_192_80_80?: Maybe<Scalars['Float']>;
  n_192_100_20?: Maybe<Scalars['Float']>;
  n_192_100_40?: Maybe<Scalars['Float']>;
  n_192_100_60?: Maybe<Scalars['Float']>;
  n_192_100_80?: Maybe<Scalars['Float']>;
  n_216_40_20?: Maybe<Scalars['Float']>;
  n_216_40_40?: Maybe<Scalars['Float']>;
  n_216_40_60?: Maybe<Scalars['Float']>;
  n_216_40_80?: Maybe<Scalars['Float']>;
  n_216_60_20?: Maybe<Scalars['Float']>;
  n_216_60_40?: Maybe<Scalars['Float']>;
  n_216_60_60?: Maybe<Scalars['Float']>;
  n_216_60_80?: Maybe<Scalars['Float']>;
  n_216_80_20?: Maybe<Scalars['Float']>;
  n_216_80_40?: Maybe<Scalars['Float']>;
  n_216_80_60?: Maybe<Scalars['Float']>;
  n_216_80_80?: Maybe<Scalars['Float']>;
  n_216_100_20?: Maybe<Scalars['Float']>;
  n_216_100_40?: Maybe<Scalars['Float']>;
  n_216_100_60?: Maybe<Scalars['Float']>;
  n_216_100_80?: Maybe<Scalars['Float']>;
  n_240_40_20?: Maybe<Scalars['Float']>;
  n_240_40_40?: Maybe<Scalars['Float']>;
  n_240_40_60?: Maybe<Scalars['Float']>;
  n_240_40_80?: Maybe<Scalars['Float']>;
  n_240_60_20?: Maybe<Scalars['Float']>;
  n_240_60_40?: Maybe<Scalars['Float']>;
  n_240_60_60?: Maybe<Scalars['Float']>;
  n_240_60_80?: Maybe<Scalars['Float']>;
  n_240_80_20?: Maybe<Scalars['Float']>;
  n_240_80_40?: Maybe<Scalars['Float']>;
  n_240_80_60?: Maybe<Scalars['Float']>;
  n_240_80_80?: Maybe<Scalars['Float']>;
  n_240_100_20?: Maybe<Scalars['Float']>;
  n_240_100_40?: Maybe<Scalars['Float']>;
  n_240_100_60?: Maybe<Scalars['Float']>;
  n_240_100_80?: Maybe<Scalars['Float']>;
  n_264_40_20?: Maybe<Scalars['Float']>;
  n_264_40_40?: Maybe<Scalars['Float']>;
  n_264_40_60?: Maybe<Scalars['Float']>;
  n_264_40_80?: Maybe<Scalars['Float']>;
  n_264_60_20?: Maybe<Scalars['Float']>;
  n_264_60_40?: Maybe<Scalars['Float']>;
  n_264_60_60?: Maybe<Scalars['Float']>;
  n_264_60_80?: Maybe<Scalars['Float']>;
  n_264_80_20?: Maybe<Scalars['Float']>;
  n_264_80_40?: Maybe<Scalars['Float']>;
  n_264_80_60?: Maybe<Scalars['Float']>;
  n_264_80_80?: Maybe<Scalars['Float']>;
  n_264_100_20?: Maybe<Scalars['Float']>;
  n_264_100_40?: Maybe<Scalars['Float']>;
  n_264_100_60?: Maybe<Scalars['Float']>;
  n_264_100_80?: Maybe<Scalars['Float']>;
  n_288_40_20?: Maybe<Scalars['Float']>;
  n_288_40_40?: Maybe<Scalars['Float']>;
  n_288_40_60?: Maybe<Scalars['Float']>;
  n_288_40_80?: Maybe<Scalars['Float']>;
  n_288_60_20?: Maybe<Scalars['Float']>;
  n_288_60_40?: Maybe<Scalars['Float']>;
  n_288_60_60?: Maybe<Scalars['Float']>;
  n_288_60_80?: Maybe<Scalars['Float']>;
  n_288_80_20?: Maybe<Scalars['Float']>;
  n_288_80_40?: Maybe<Scalars['Float']>;
  n_288_80_60?: Maybe<Scalars['Float']>;
  n_288_80_80?: Maybe<Scalars['Float']>;
  n_288_100_20?: Maybe<Scalars['Float']>;
  n_288_100_40?: Maybe<Scalars['Float']>;
  n_288_100_60?: Maybe<Scalars['Float']>;
  n_288_100_80?: Maybe<Scalars['Float']>;
  n_312_40_20?: Maybe<Scalars['Float']>;
  n_312_40_40?: Maybe<Scalars['Float']>;
  n_312_40_60?: Maybe<Scalars['Float']>;
  n_312_40_80?: Maybe<Scalars['Float']>;
  n_312_60_20?: Maybe<Scalars['Float']>;
  n_312_60_40?: Maybe<Scalars['Float']>;
  n_312_60_60?: Maybe<Scalars['Float']>;
  n_312_60_80?: Maybe<Scalars['Float']>;
  n_312_80_20?: Maybe<Scalars['Float']>;
  n_312_80_40?: Maybe<Scalars['Float']>;
  n_312_80_60?: Maybe<Scalars['Float']>;
  n_312_80_80?: Maybe<Scalars['Float']>;
  n_312_100_20?: Maybe<Scalars['Float']>;
  n_312_100_40?: Maybe<Scalars['Float']>;
  n_312_100_60?: Maybe<Scalars['Float']>;
  n_312_100_80?: Maybe<Scalars['Float']>;
  n_336_40_20?: Maybe<Scalars['Float']>;
  n_336_40_40?: Maybe<Scalars['Float']>;
  n_336_40_60?: Maybe<Scalars['Float']>;
  n_336_40_80?: Maybe<Scalars['Float']>;
  n_336_60_20?: Maybe<Scalars['Float']>;
  n_336_60_40?: Maybe<Scalars['Float']>;
  n_336_60_60?: Maybe<Scalars['Float']>;
  n_336_60_80?: Maybe<Scalars['Float']>;
  n_336_80_20?: Maybe<Scalars['Float']>;
  n_336_80_40?: Maybe<Scalars['Float']>;
  n_336_80_60?: Maybe<Scalars['Float']>;
  n_336_80_80?: Maybe<Scalars['Float']>;
  n_336_100_20?: Maybe<Scalars['Float']>;
  n_336_100_40?: Maybe<Scalars['Float']>;
  n_336_100_60?: Maybe<Scalars['Float']>;
  n_336_100_80?: Maybe<Scalars['Float']>;
};

/**
 * Global modification for associating a ColorDistribution instance to an object.
 *
 * **Type Metadata**
 * - Display Name: `Color Distribution Data`
 *
 */
export type Com_Psddev_Dari_Db_ColorDistribution_Data = {
  __typename?: 'com_psddev_dari_db_ColorDistribution_Data';
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "color.distribution"
   */
  distribution?: Maybe<Com_Psddev_Dari_Db_ColorDistribution>;
};

/**
 * Global modification for associating a ColorDistribution instance to an object.
 *
 * **Type Metadata**
 * - Display Name: `Color Distribution Data`
 *
 */
export type Com_Psddev_Dari_Db_ColorDistribution_DataInput = {
  /**
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "color.distribution"
   */
  distribution?: Maybe<Scalars['DiffId']>;
};

/** Represents a generic record. */
export type Com_Psddev_Dari_Db_Record = {
  _globals?: Maybe<Globals>;
  _id?: Maybe<Scalars['ID']>;
  _type?: Maybe<Scalars['ID']>;
};

/** Represents a generic record. */
export type Com_Psddev_Dari_Db_RecordInput = {
  _globals?: Maybe<GlobalsInput>;
};

/** Represents a generic record. */
export type Com_Psddev_Dari_Db_Record_Type = Com_Psddev_Dari_Db_Record & {
  __typename?: 'com_psddev_dari_db_Record_Type';
  _globals?: Maybe<Globals>;
  _id?: Maybe<Scalars['ID']>;
  _type?: Maybe<Scalars['ID']>;
};

export type Com_Psddev_Dari_Db_VisibilityValues = {
  _globals?: Maybe<Globals>;
  _id?: Maybe<Scalars['ID']>;
  _type?: Maybe<Scalars['ID']>;
};

export type Com_Psddev_Graphql_Cma_ContentManagementApiMutationMetadata = {
  __typename?: 'com_psddev_graphql_cma_ContentManagementApiMutationMetadata';
  /**
   * Field getter method Javadoc:
   * Return the most recent client that modified the original object.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "graphql.cma.lastMutation.client"
   */
  client?: Maybe<Reference>;
  /**
   * Field getter method Javadoc:
   * Return the date of when the original object was last updated by a CMA mutation.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "graphql.cma.lastMutation.date"
   */
  date?: Maybe<Scalars['Date']>;
  /**
   * Field getter method Javadoc:
   * Return the most recent endpoint that modified the original object.
   *
   * **Field Metadata**
   * - Indexed
   *
   * Internal name is "graphql.cma.lastMutation.endpoint"
   */
  endpoint?: Maybe<Reference>;
};

export type Com_Psddev_Graphql_Cma_ContentManagementApiMutationMetadataInput = {
  client?: Maybe<Scalars['RefId']>;
  date?: Maybe<Scalars['Date']>;
  endpoint?: Maybe<Scalars['RefId']>;
};

export type Demo6ByIdQueryVariables = Exact<{
  id?: Maybe<Scalars['ID']>;
}>;


export type Demo6ByIdQuery = { __typename?: 'Query', com_brightspot_tutorial_graphql_demo6_Demo6Query?: Maybe<{ __typename?: 'com_brightspot_tutorial_graphql_demo6_Demo6QueryResult', items: Array<{ __typename?: 'com_brightspot_tutorial_graphql_demo6_Demo6', _id?: Maybe<string>, _type?: Maybe<string>, firstName?: Maybe<string>, lastName?: Maybe<string>, title?: Maybe<string>, getFullName?: Maybe<string> }> }> };

export type Demo6SaveMutationVariables = Exact<{
  id?: Maybe<Scalars['DiffId']>;
  firstName?: Maybe<Scalars['String']>;
  lastName?: Maybe<Scalars['String']>;
  title?: Maybe<Scalars['String']>;
  toolUser?: Maybe<Scalars['ToolUser']>;
}>;


export type Demo6SaveMutation = { __typename?: 'Mutation', com_brightspot_tutorial_graphql_demo6_Demo6Save?: Maybe<{ __typename?: 'com_brightspot_tutorial_graphql_demo6_Demo6', _id?: Maybe<string>, _type?: Maybe<string>, firstName?: Maybe<string>, lastName?: Maybe<string>, title?: Maybe<string>, getFullName?: Maybe<string> }> };

export type Demo6SearchQueryVariables = Exact<{
  arguments?: Maybe<Array<Maybe<Scalars['String']>> | Maybe<Scalars['String']>>;
}>;


export type Demo6SearchQuery = { __typename?: 'Query', com_brightspot_tutorial_graphql_demo6_Demo6Query?: Maybe<{ __typename?: 'com_brightspot_tutorial_graphql_demo6_Demo6QueryResult', pageInfo?: Maybe<{ __typename?: 'PageInfo', count?: Maybe<any> }>, items: Array<{ __typename?: 'com_brightspot_tutorial_graphql_demo6_Demo6', _id?: Maybe<string>, _type?: Maybe<string>, firstName?: Maybe<string>, lastName?: Maybe<string>, title?: Maybe<string>, getFullName?: Maybe<string> }> }> };


export const Demo6ByIdDocument = gql`
    query Demo6ById($id: ID) {
  com_brightspot_tutorial_graphql_demo6_Demo6Query(id: $id) {
    items {
      _id
      _type
      firstName
      lastName
      title
      getFullName
    }
  }
}
    `;

/**
 * __useDemo6ByIdQuery__
 *
 * To run a query within a React component, call `useDemo6ByIdQuery` and pass it any options that fit your needs.
 * When your component renders, `useDemo6ByIdQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useDemo6ByIdQuery({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useDemo6ByIdQuery(baseOptions?: Apollo.QueryHookOptions<Demo6ByIdQuery, Demo6ByIdQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<Demo6ByIdQuery, Demo6ByIdQueryVariables>(Demo6ByIdDocument, options);
      }
export function useDemo6ByIdLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<Demo6ByIdQuery, Demo6ByIdQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<Demo6ByIdQuery, Demo6ByIdQueryVariables>(Demo6ByIdDocument, options);
        }
export type Demo6ByIdQueryHookResult = ReturnType<typeof useDemo6ByIdQuery>;
export type Demo6ByIdLazyQueryHookResult = ReturnType<typeof useDemo6ByIdLazyQuery>;
export type Demo6ByIdQueryResult = Apollo.QueryResult<Demo6ByIdQuery, Demo6ByIdQueryVariables>;
export const Demo6SaveDocument = gql`
    mutation Demo6Save($id: DiffId, $firstName: String, $lastName: String, $title: String, $toolUser: ToolUser) {
  com_brightspot_tutorial_graphql_demo6_Demo6Save(
    id: $id
    toolUser: $toolUser
    diffs: {com_brightspot_tutorial_graphql_demo6_Demo6Diff: {firstName: $firstName, lastName: $lastName, title: $title}}
  ) {
    _id
    _type
    firstName
    lastName
    title
    getFullName
  }
}
    `;
export type Demo6SaveMutationFn = Apollo.MutationFunction<Demo6SaveMutation, Demo6SaveMutationVariables>;

/**
 * __useDemo6SaveMutation__
 *
 * To run a mutation, you first call `useDemo6SaveMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useDemo6SaveMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [demo6SaveMutation, { data, loading, error }] = useDemo6SaveMutation({
 *   variables: {
 *      id: // value for 'id'
 *      firstName: // value for 'firstName'
 *      lastName: // value for 'lastName'
 *      title: // value for 'title'
 *      toolUser: // value for 'toolUser'
 *   },
 * });
 */
export function useDemo6SaveMutation(baseOptions?: Apollo.MutationHookOptions<Demo6SaveMutation, Demo6SaveMutationVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useMutation<Demo6SaveMutation, Demo6SaveMutationVariables>(Demo6SaveDocument, options);
      }
export type Demo6SaveMutationHookResult = ReturnType<typeof useDemo6SaveMutation>;
export type Demo6SaveMutationResult = Apollo.MutationResult<Demo6SaveMutation>;
export type Demo6SaveMutationOptions = Apollo.BaseMutationOptions<Demo6SaveMutation, Demo6SaveMutationVariables>;
export const Demo6SearchDocument = gql`
    query Demo6Search($arguments: [String]) {
  com_brightspot_tutorial_graphql_demo6_Demo6Query(
    where: {predicate: "getFullName matches ?", arguments: $arguments}
    sorts: {order: ascending, options: "getFullName"}
  ) {
    pageInfo {
      count
    }
    items {
      _id
      _type
      firstName
      lastName
      title
      getFullName
    }
  }
}
    `;

/**
 * __useDemo6SearchQuery__
 *
 * To run a query within a React component, call `useDemo6SearchQuery` and pass it any options that fit your needs.
 * When your component renders, `useDemo6SearchQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useDemo6SearchQuery({
 *   variables: {
 *      arguments: // value for 'arguments'
 *   },
 * });
 */
export function useDemo6SearchQuery(baseOptions?: Apollo.QueryHookOptions<Demo6SearchQuery, Demo6SearchQueryVariables>) {
        const options = {...defaultOptions, ...baseOptions}
        return Apollo.useQuery<Demo6SearchQuery, Demo6SearchQueryVariables>(Demo6SearchDocument, options);
      }
export function useDemo6SearchLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<Demo6SearchQuery, Demo6SearchQueryVariables>) {
          const options = {...defaultOptions, ...baseOptions}
          return Apollo.useLazyQuery<Demo6SearchQuery, Demo6SearchQueryVariables>(Demo6SearchDocument, options);
        }
export type Demo6SearchQueryHookResult = ReturnType<typeof useDemo6SearchQuery>;
export type Demo6SearchLazyQueryHookResult = ReturnType<typeof useDemo6SearchLazyQuery>;
export type Demo6SearchQueryResult = Apollo.QueryResult<Demo6SearchQuery, Demo6SearchQueryVariables>;