import React, { Fragment } from 'react'
import { useQuery, gql } from '@apollo/client'
import Head from 'next/head'

const QUERY = gql`
  query Home {
    Text(id: "0000017e-744f-d0cd-a5ff-f47ff5100000") {
      name
      text
      statusCode
      _id
    }
  }
`

const Home = () => {
  const { data, loading, error } = useQuery(QUERY)

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
