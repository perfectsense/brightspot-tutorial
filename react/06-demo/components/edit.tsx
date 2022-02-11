import React, {Fragment, useState, useEffect} from 'react'
import {useDemo6ByIdQuery} from "../generated/demo6ById";
import {Com_Brightspot_Tutorial_Graphql_Demo6_Demo6, useDemo6SaveMutation} from "../generated/graphql";
import Save from "./save";

const Edit = (props) => {

  const [editId, setEditId] = useState(props.editId)

  const byIdQueryResult = useDemo6ByIdQuery({
    variables: {
      id: props.editId
    },
  })

  const demo6 = byIdQueryResult.data?.com_brightspot_tutorial_graphql_demo6_Demo6Query?.items[0] as Com_Brightspot_Tutorial_Graphql_Demo6_Demo6

  console.log('demo6', demo6);

  const [firstNameValue, setFirstNameValue] = useState(demo6?.firstName)
  const [lastNameValue, setLastNameValue] = useState(demo6?.lastName)
  const [titleValue, setTitleValue] = useState(demo6?.title)

  const [doSave, setDoSave] = useState(false)

  let firstName = demo6?.firstName
  let lastName = demo6?.lastName
  let title = demo6?.title

  console.log('ok....', props.editId, firstName, lastName, title)

  let firstNameInput = null
  let lastNameInput = null
  let titleInput = null

  useEffect(() => {
    //setEditId(props.editId)
    setFirstNameValue(firstName)
    //setLastNameValue(lastName)
    //setTitleValue(title)
  }, [props.editId])

  function handleFormSubmit(event) {
    event.preventDefault()

    console.log(firstNameInput?.value)
    console.log(lastNameInput?.value)
    console.log(titleInput?.value)

    setFirstNameValue(firstNameInput?.value)
    setLastNameValue(lastNameInput?.value)
    setTitleValue(titleInput?.value)

    setDoSave(true)

    return false
  }

  function handleChange(event) {
    console.log('update...' + event.target.value);
    setFirstNameValue(event.target.value)
  }

  return (
    <Fragment>

      <form>
        <label htmlFor="firstName">First name:</label><br/>
        <input id="firstName"
               type="text"
               name="firstName"
               value={firstNameValue || firstName}
               onChange={handleChange}
               //ref={node => (firstNameInput = node)}
        />

        <br/><br/>

        <label htmlFor="lastName">Last name:</label><br/>
        <input type="text"
               name="lastName"
               defaultValue={lastName || ''}
               //ref={node => (lastNameInput = node)}
        />

        <br/><br/>

        <label htmlFor="title">Title:</label><br/>
        <input type="text"
               name="title"
               defaultValue={title || ''}
               //ref={node => (titleInput = node)}
        />

        <br/><br/>

        <button onClick={handleFormSubmit}>Save</button>
      </form>

      {doSave ? <Save /> : ''}

    </Fragment>
  )
}

export default Edit
