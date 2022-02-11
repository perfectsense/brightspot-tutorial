import React, { Fragment } from 'react'
import Head from 'next/head'
import {Demo4, useDemo4Query} from "../generated/graphql";

const Home = () => {

  const { data, loading, error } = useDemo4Query();
  const demo4 = data?.Demo4 as Demo4

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
      </Head>
      <h1>{demo4.foxtrot}</h1>
    </Fragment>
  )
}

export default Home
