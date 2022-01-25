import React, { Fragment } from 'react'
import Head from 'next/head'
import { useRteQuery } from '../generated/graphql'

const Home = () => {
  const { data, loading, error } = useRteQuery({})

  if (loading) {
    return <h2>Loading...</h2>
  }

  if (error) {
    console.error(error)
    return null
  }

  type MapData = {
    json: string
    entries: [MapEntry]
  }

  type MapEntry = {
    key: string
    value: string
  }

  interface Mark {
    start: number
    end: number
    selfClosing: boolean
    isBlock: boolean
  }

  interface HtmlMark extends Mark {
    name: string
    attributes: MapData
  }

  type Element = {
    open: string
    close: string
    closed: string
  }

  const transformHtmlMarkToHtml = (htmlMark: HtmlMark): Element => {
    const attributes: string = htmlMark.attributes?.entries
      ?.map((e) => `${e.key}="${e.value}"`)
      .join(' ')
      .trim()
    return {
      open: `<${htmlMark.name}${attributes ? ' ' + attributes : ''}>`,
      close: `</${htmlMark.name}>`,
      closed: `<${htmlMark.name} />`,
    }
  }

  const isHtmlMark = (mark: Mark): mark is HtmlMark =>
    (mark as HtmlMark).name !== undefined

  const findHtmlMarkStart = (htmlMark: HtmlMark) => {
    return htmlMark.start
  }

  const findHtmlMarkEnd = (htmlMark: HtmlMark) => {
    return htmlMark.end
  }

  const isSelfClosing = (htmlMark: HtmlMark) => {
    return htmlMark.selfClosing
  }

  const transformRichText = (richText) => {
    let htmlRichText = richText.text

    const charactersAdded = [
      {
        key: 0,
        length: 0,
      },
    ]

    let key = 0
    let charactersAddedBefore = 0

    const replaceText = (mark, type) => {
      if (type === 'close') {
        key = findHtmlMarkEnd(mark)
      } else {
        key = findHtmlMarkStart(mark)
      }

      charactersAddedBefore = 0

      charactersAdded.forEach((character) => {
        if (type === 'close') {
          if (character.key < key) {
            charactersAddedBefore += character.length
          }
        } else {
          if (character.key <= key) {
            charactersAddedBefore += character.length
          }
        }
      })

      htmlRichText =
        htmlRichText.slice(0, key + charactersAddedBefore) +
        transformHtmlMarkToHtml(mark)[type] +
        htmlRichText.slice(key + charactersAddedBefore)

      charactersAdded.push({
        key: key,
        length: transformHtmlMarkToHtml(mark)[type].length,
      })
    }

    richText.marks.forEach((mark) => {
      if (isHtmlMark(mark)) {
        if (mark.selfClosing) {
          replaceText(mark, 'closed')
        } else {
          replaceText(mark, 'open')
          replaceText(mark, 'close')
        }
      }
    })

    return htmlRichText
  }

  return (
    <Fragment>
      <Head>
        <title>Hello Brightspot!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <h1>Hello Brightspot!</h1>
      <div
        dangerouslySetInnerHTML={{
          __html: transformRichText(data.Test.richText),
        }}
      />
    </Fragment>
  )
}

export default Home
