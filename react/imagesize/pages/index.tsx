import React, { Fragment } from 'react'
import Head from 'next/head'
import { useImageByIdQuery } from '../generated/graphql'

const Home = () => {
  const {data, loading, error} = useImageByIdQuery({
    variables: { 
      id: "0000017e-b0a8-dcfb-abff-fbfb6e320000",
      size: "80xauto"
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
