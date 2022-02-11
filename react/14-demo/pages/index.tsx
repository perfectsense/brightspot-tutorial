import React, { Fragment, useEffect, useState } from 'react'
import Head from 'next/head'

import {Demo14, useDemo14ByIdQuery} from "../generated/graphql";
import Page from "../components/Page";
import AudienceSelector from "../components/AudienceSelector";

const Home = (props) => {

  const [audience, setAudience] = useState()

  useEffect(() => {
    //setAudience(localStorage.getItem('audience'))
    //console.log('component loaded...', localStorage.getItem('audience'))
  }, [audience])

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <Page audience={audience} />
      <AudienceSelector audience={audience} setAudience={setAudience} />
    </Fragment>
  )
}

export default Home
