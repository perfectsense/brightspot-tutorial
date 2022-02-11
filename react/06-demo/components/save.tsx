import React, {Fragment, useState, useEffect} from 'react'
import {useDemo6SaveMutation} from "../generated/graphql";

const Save = (props) => {

  const [demo6SaveMutation, { data, loading, error }] = useDemo6SaveMutation({
    variables: {
      id: props.id,
      firstName: props.firstName,
      lastName: props.lastName,
      title: props.title,
      toolUser: 'demo6'
    },
  });

  useEffect(() => {
    console.log('okokokok got here...')
    //demo6SaveMutation()
  });

  return null;
}

export default Save
