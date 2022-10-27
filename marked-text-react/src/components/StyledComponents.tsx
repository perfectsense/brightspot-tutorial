interface TagElProps {
  typename: string
  tag: string
  children: Array<string | React.ReactElement>
  attributes: { entries: [{ key: string; value: string }] }
}

interface ConvertedElementProps {
  element: string | React.ReactElement
}

interface RichTextMarkProps {
  typename: string
  children: Array<string | React.ReactElement>
  richTextElement: {
    __typename: string
    url: string
  }
  size: { maxWidth: number | null; maxHeight: number | null }
}

const TagComponent = ({ tag, typename, children, attributes }: TagElProps) => {
  if (tag === 'br') return <LineBreakComponent />
  if (tag === 'iframe')
    return (
      <IframeComponent
        tag={tag}
        typename={typename}
        children={children}
        attributes={attributes}
      />
    )
  const Tag = `${tag}` as React.ElementType
  return (
    <Tag
      className={attributes.entries.map((entry) =>
        entry.key === 'class' ? entry.value : null
      )}
    >
      {children.map((child, index) => (
        <ConvertedElement key={index} element={child} />
      ))}
    </Tag>
  )
}

const LineBreakComponent = () => {
  return <br />
}

const IframeComponent = ({ children, attributes }: TagElProps) => {
  const SRC = attributes.entries.filter((entry) => entry.key === 'src')[0].value
  const WIDTH = attributes.entries.filter((entry) => entry.key === 'width')[0]
    .value
  const HEIGHT = attributes.entries.filter((entry) => entry.key === 'height')[0]
    .value
  const ALLOW = attributes.entries.filter((entry) => entry.key === 'allow')[0]
    .value
  return (
    <iframe title={SRC} src={SRC} width={WIDTH} height={HEIGHT} allow={ALLOW}>
      {children.map((child, index) => (
        <ConvertedElement key={index} element={child} />
      ))}
    </iframe>
  )
}

const ConvertedElement = ({ element }: ConvertedElementProps) => {
  return <>{element}</>
}

const ExternalContent = ({ richTextElement, size }: RichTextMarkProps) => {
  let URL
  //YouTube links that are not embed links
  richTextElement.url.includes('youtube.com/watch?v=')
    ? (URL =
        richTextElement.url.replace('watch?v=', 'embed/') + '?feature=oembed')
    : (URL = richTextElement.url)

  // Twitter links that are not embed links
  if (richTextElement.url.includes('/status')) {
    const tweetId = richTextElement.url.match(/\d*\d{10}/g)
    URL = richTextElement.url.replace(/\?\w*\D*\d*\D*\w*\D*\w*/g, '')
    URL = URL.replace('twitter.com', 'platform.twitter.com')
    URL =
      URL.replace(/(?<![https://])\/\w*\//, '/embed/Tweet.html?&embedId=') +
      '&id=' +
      tweetId
    URL.replace('status/', '')
  }

  return (
    <div className="embed-container">
      <iframe
        title={richTextElement.url}
        src={URL}
        width={size.maxWidth ? size.maxWidth : 600}
        height={size.maxHeight ? size.maxHeight : 400}
        aria-label="external embedded item"
      ></iframe>
    </div>
  )
}

export { TagComponent, ExternalContent }
