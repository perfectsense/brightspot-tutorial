import React, { Fragment, useState } from 'react'
import Head from 'next/head'
import { useDemo3Query } from "../generated/graphql";

const Home = () => {

  const [objectIdValue, setObjectIdValue] = useState('')

  const { data, loading, error } = useDemo3Query({
      variables: {
          id: objectIdValue
      },
  });

  console.log('data', data)

  const message = data?.Demo3?.message
  const byline = data?.Demo3?.byline
  const randomNumber = data?.Demo3?.randomNumber

  function handleObjectIdInput(event) {
    const { value } = event.target
    setObjectIdValue(value)
  }

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <input type="text"
             placeholder="Enter a UUID..."
             name="objectId"
             style={{width: "250px"}}
             value={objectIdValue || ''}
             onChange={handleObjectIdInput}
      />

      <h2>{message}</h2>
      <h3>{byline}</h3>
      <p>{randomNumber}</p>
    </Fragment>
  )
}

export default Home
