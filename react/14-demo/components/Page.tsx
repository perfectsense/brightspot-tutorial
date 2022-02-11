import React, { Fragment, useEffect, useState } from 'react'
import Head from 'next/head'

import {Demo14, useDemo14ByIdQuery} from "../generated/graphql";

const Page = (props) => {

  console.log('loading Home', props.audience)

  let { data, loading, error } = useDemo14ByIdQuery({
    variables: {
      id: '/demo-14-demo-segmentation',
      // @ts-ignore
      foo: props.audience
    },
    context: {
      headers: {
        'X-Client-ID': process.env.NEXT_PUBLIC_GRAPHQL_CLIENT_ID,
        'X-Client-Secret': process.env.NEXT_PUBLIC_GRAPHQL_CLIENT_SECRET,
        'X-Audience': props.audience
      }}
  });

  let demo14 = data?.Demo14 as Demo14

  useEffect(() => {
    //console.log('component loaded...', localStorage.getItem('audience'))
  }, [props.audience])

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <h1>{demo14?.title}</h1>
      <h2>{demo14?.description}</h2>
    </Fragment>
  )
}

export default Page
