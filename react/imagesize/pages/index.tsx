import React, { Fragment } from 'react'
import Head from 'next/head'
import { useImageQueryQuery } from '../generated/graphql'

const Home = () => {
  const {data, loading, error} = useImageQueryQuery({
    variables: { 
      id: "0000017e-96a7-ddda-af7e-b6ff8cb90000", 
      size: "96x96"
    }
  })

  if (loading) {
    return <h2>Loading...</h2>
  }
  
  if (error) {
    return <h2>Error</h2>
  }

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <div>
        <h1>{data?.Image?.title}</h1>
        <img src={'http://localhost' + data?.Image?.image?.size?.src}/>
        <h4>Width: {data?.Image?.image.width} Height: {data?.Image?.image.height}</h4>
      </div>
    </Fragment>
  )
}

export default Home
