import React, {Fragment, useState} from 'react'

const SearchResultItem = (props) => {

  function handleEditLinkClick(event) {
    console.log(event.target.dataset.id)
    props.setEditId(event.target.dataset.id)
  }

  return (
    <Fragment>
      <li>{props.getFullName} - {props.title}
        <span data-id={props._id}
              onClick={handleEditLinkClick}
              style={{fontSize: "24px", cursor: "pointer"}}> | ✎</span>
      </li>
    </Fragment>
  )
}

export default SearchResultItem
