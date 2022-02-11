import React, { Fragment, useState } from 'react'
import Head from 'next/head'
import Search from "../components/search";
import Edit from "../components/edit";

const Home = () => {

  const [editId, setEditId] = useState('')

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <h1>Hello Brightspot!</h1>

      <div className="row" style={{display: 'flex', width: '50%'}}>
        <div className="column" style={{flex: "50%"}}>
          <Search setEditId={setEditId} />
        </div>
        <div className="column" style={{flex: "50%"}}>
          <Edit editId={editId} />
        </div>
      </div>

    </Fragment>
  )
}

export default Home
