export interface MarkedText {
  text: string
  marks: Array<Mark | RichTextMark>
}

export interface Mark {
  __typename: string
  name: string
  start: number
  end: number
  descendants: number
  attributes: {
    entries: [{ key: string; value: string }]
  }
}

export interface RichTextMark {
  __typename: string
  start: number
  end: number
  descendants: number
  richTextElement: {
    __typename: string
    url: string
    maximumWidth: number | null
    maximumHeight: number | null
  }
}

export interface VisitMark {
  (
    mark: Mark | RichTextMark | null,
    children: Array<string>,
    index: number
  ): any
}
