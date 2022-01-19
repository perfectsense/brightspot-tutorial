import React, { Fragment } from 'react'
import Head from 'next/head'
import { useHomeQuery } from '../generated/graphql'

const Home = () => {
  const { data, loading, error } = useHomeQuery({})

  if (loading) {
    return <h2>Loading...</h2>
  }

  if (error) {
    console.error(error)
    return null
  }

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <h1>Hello Brightspot!</h1>
      {data.Text.text}
    </Fragment>
  )
}

export default Home
