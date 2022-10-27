import { useState, useEffect, useCallback } from 'react'
import { ArticleMarkQuery } from './ArticleMarkQuery'
import { Mark, MarkedText, RichTextMark } from '../brightspot-marked-text/types'
import { markedText } from '../brightspot-marked-text/marked-text'
import { TagComponent, ExternalContent } from './StyledComponents'

interface ArticleData {
  headline: string
  subheadline: string
  body: MarkedText
}

interface ArticleResponse {
  articleData?: ArticleData
  errors?: Array<string>
}

const ArticleContainer = () => {
  const [article, setArticle] = useState<ArticleResponse>()

  const GRAPHQL_URL = process.env.REACT_APP_GRAPHQL_URL ?? ''

  const handleResponse = (res: any) => {
    let articleData: ArticleData | undefined
    let errors: string[] = []
    if (res?.data?.Article) {
      articleData = {
        headline: res.data.Article.headline,
        subheadline: res.data.Article.subheadline,
        body: res.data.Article.body,
      }
    }
    if (res.errors)
      res.errors.forEach((error: any) => errors.push(error.message))

    setArticle({
      articleData,
      errors: errors,
    })
  }

  const handleError = (error: Error) => {
    setArticle({
      errors: [error.message],
    })
  }

  const fetchArticleData = useCallback(async () => {
    const dataRequestParams = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        query: ArticleMarkQuery,
        variables: {
          path: 'marked-text',
        },
      }),
    }

    await fetch(GRAPHQL_URL, dataRequestParams)
      .then((res) => res.json())
      .then((res) => handleResponse(res))
      .catch((error: Error) => handleError(error))
  }, [GRAPHQL_URL])

  useEffect(() => {
    fetchArticleData()
  }, [fetchArticleData])

  const componentHandler = (
    mark: Mark | RichTextMark | null,
    children: Array<string | React.ReactElement>,
    index: number
  ) => {
    if (mark === null) return ''
    if (mark.__typename === 'HtmlMark') {
      const { name, __typename, attributes } = mark as Mark
      return (
        <TagComponent
          key={index}
          typename={__typename}
          tag={name}
          children={children}
          attributes={attributes}
        />
      )
    } else {
      const { __typename, richTextElement } = mark as RichTextMark

      let maxWidth
      let maxHeight
      richTextElement?.maximumWidth
        ? (maxWidth = richTextElement.maximumWidth)
        : (maxWidth = null)
      richTextElement?.maximumHeight
        ? (maxHeight = richTextElement.maximumHeight)
        : (maxHeight = null)
      return (
        <ExternalContent
          key={index}
          typename={__typename}
          children={children}
          richTextElement={richTextElement}
          size={{ maxWidth, maxHeight }}
        />
      )
    }
  }

  return (
    <div>
      <h1>{article?.articleData?.headline}</h1>
      <h2>{article?.articleData?.subheadline}</h2>
      {article?.articleData &&
        markedText(article?.articleData?.body, componentHandler, 'POST').map(
          (Component: React.ReactElement, index: number) => {
            return Component
          }
        )}
    </div>
  )
}

export default ArticleContainer
