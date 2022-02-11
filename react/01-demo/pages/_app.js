import React from 'react'
import App from 'next/app'
import { ApolloProvider } from '@apollo/react-hooks'
import { getDataFromTree } from '@apollo/client/react/ssr'
import withApollo from 'next-with-apollo'
import { ApolloClient } from 'apollo-client'
import { InMemoryCache } from 'apollo-cache-inmemory'
import { IntrospectionFragmentMatcher } from 'apollo-cache-inmemory'
import introspectionQueryResultData from '../generated/fragmentTypes.json'
import { createHttpLink } from 'apollo-link-http'
import fetch from 'isomorphic-unfetch'
import { createPersistedQueryLink } from '@apollo/client/link/persisted-queries'
import { sha256 } from 'crypto-hash'

const persistedQueriesLink = createPersistedQueryLink({
  sha256,
  useGETForHashedQueries: true,
})

class MyApp extends App {
  render() {
    const { Component, pageProps, apollo } = this.props
    return (
      <ApolloProvider client={apollo}>
        <Component {...pageProps} />
      </ApolloProvider>
    )
  }
}

export default withApollo(({ initialState }) => {
  const fragmentMatcher = new IntrospectionFragmentMatcher({
    introspectionQueryResultData,
  })

  const linkOptions = {
    fetch, // Switches between unfetch & node-fetch for client & server.
    uri: process.env.GRAPHQL_URL || process.env.NEXT_PUBLIC_GRAPHQL_URL,
  }

  const fetchPolicy = { fetchPolicy: 'no-cache' }

  const defaultOptions = {}
  if (typeof window === 'undefined') {
    linkOptions.headers = {
      'X-Client-ID': process.env.GRAPHQL_CLIENT_ID,
      'X-Client-Secret': process.env.GRAPHQL_CLIENT_SECRET,
    }
  } else {
    defaultOptions.query = fetchPolicy
    defaultOptions.watchQuery = fetchPolicy
  }

  return new ApolloClient({
    defaultOptions,
    link: persistedQueriesLink.concat(createHttpLink(linkOptions)),
    cache: new InMemoryCache({ fragmentMatcher })
      // rehydrate the cache using the initial data passed from the server:
      .restore(initialState || {}),
  })
})(MyApp, { getDataFromTree })
