import React, { Fragment } from 'react'
import Head from 'next/head'
import { useRouter } from 'next/router'

import {Demo16, useDemo16GetQuery} from "../generated/graphql";

const Home = () => {
  const { query } = useRouter();
  const id = query.previewId as string || null
  const path = id ? null : '/' + (query.index as string[] || []).join('/')

  const { data, loading, error } = useDemo16GetQuery({
    variables: {
      id: id,
      path: path
    },
  });

  const demo16 = data?.Demo16 as Demo16

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <body style={{backgroundColor:demo16?._theme?.backgroundColor, height:"100%"}}>
        <h1 style={{color:demo16?._theme?.primaryColor, fontFamily:demo16?._theme?.primaryFont}}>{demo16?.title}</h1>
        <h2 style={{color:demo16?._theme?.secondaryColor}}>{demo16?.description}</h2>
        {demo16?.image?.publicUrl &&
          <img src={demo16?.image?.sizes?.find(element => element.name === 'landscape-1600x900')?.src} width="50%" alt="Image" />
        }
      </body>
    </Fragment>
  )
}

export default Home
