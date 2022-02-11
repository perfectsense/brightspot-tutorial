import React, { Fragment, useEffect, useState } from 'react'
import Head from 'next/head'

const AudienceSelector = (props) => {

  function handleAudienceToggle(event) {
    console.log('got here...', event.target.value)
    props.setAudience(event.target.value)
    localStorage.setItem('audience', event.target.value);
  }

  useEffect(() => {
    props.setAudience(localStorage.getItem('audience'))
    console.log('component loaded...', localStorage.getItem('audience'))
  }, [props.audience])

  return (
    <Fragment>
      <div style={{position:'fixed',right:0, top:0,padding:'4px',margin:'10px',border:'1px solid #000'}}>
        Audience:<br /><hr />

        <input type="radio" id="default" name="audience" value="" checked={props.audience === ''} onChange={handleAudienceToggle} />
        <label htmlFor="default">Default</label><br />

        <input type="radio" id="biz" name="audience" value="biz" checked={props.audience === 'biz'} onChange={handleAudienceToggle} />
        <label htmlFor="biz">Business</label><br />

        <input type="radio" id="dev" name="audience" value="dev" checked={props.audience === 'dev'} onChange={handleAudienceToggle} />
        <label htmlFor="dev">Developer</label>
      </div>
    </Fragment>
  )
}

export default AudienceSelector
