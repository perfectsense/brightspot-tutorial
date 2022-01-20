import React, { Fragment } from 'react'
import Head from 'next/head'
import { useRteQuery } from '../generated/graphql'

const Home = () => {
  const { data, loading, error } = useRteQuery({})

  if (loading) {
    return <h2>Loading...</h2>
  }

  if (error) {
    console.error(error)
    return null
  }

  console.log(data.Test.richText.marks)

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <h1>Hello Brightspot!</h1>
      {data.Test.richText.text}
    </Fragment>
  )
}

export default Home
