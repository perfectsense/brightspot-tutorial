import React, {Fragment, useState} from 'react'
import {useDemo6SearchQuery} from "../generated/demo6Search";
import {Com_Brightspot_Tutorial_Graphql_Demo6_Demo6QueryResult, Demo6SearchQuery} from "../generated/graphql";
import SearchResultItem from "./searchResultItem";

const Search = (props) => {

  const [nameQuery, setNameQuery] = useState('')

  const { data, loading, error } = useDemo6SearchQuery({
    variables: {
      arguments: [nameQuery === '' ? '*' : nameQuery]
    },
  })

  const demo6Query = data?.com_brightspot_tutorial_graphql_demo6_Demo6Query as Com_Brightspot_Tutorial_Graphql_Demo6_Demo6QueryResult

  function handleNameQueryInput(event) {
    const { value } = event.target
    setNameQuery(value)
  }

  return (
    <Fragment>

      <input type="text"
             placeholder="Search names..."
             name="nameQuery"
             style={{width: "250px"}}
             value={nameQuery || ''}
             onChange={handleNameQueryInput}
      />

      <div className="searchResults">
        <div className="resultCount">Num Results: {demo6Query?.pageInfo?.count}</div>
        <div className="resultItems">
          <ol>
            {demo6Query?.items?.map(item => (
              <SearchResultItem key={item._id} {...item} setEditId={props.setEditId} />
            ))}
          </ol>
        </div>
      </div>

    </Fragment>
  )
}

export default Search
