export const ArticleMarkQuery = `
query ArticleMarkQuery($path: String) {
  Article(model: {path: $path}) {
    headline
    subheadline
    body {
      text
      marks {
        ... on HtmlMark {
          __typename
          name
          start
          end
          descendants
          attributes {
            entries {
              key
              value
            }
          }
        }
        ... on RichTextMark {
          __typename
          start
          end
          descendants
          richTextElement {
            ... on ExternalContentRichTextElement {
              __typename
              url
              maximumWidth
              maximumHeight
            }
          }
        }
      }
    }
  }
}
`
