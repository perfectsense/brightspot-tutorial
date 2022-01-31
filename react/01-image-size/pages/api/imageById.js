import Handler from '../../rest/handler'

const handler = new Handler('./components/graphql/image.graphql', ['id', 'size'])
export default async (req, res) => {
  await handler.handle(req, res)
}
