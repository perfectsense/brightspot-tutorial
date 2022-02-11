import Handler from '../../rest/handler'

const handler = new Handler('./components/graphql/Demo6Search.graphql', ['arguments'])
export default async (req, res) => {
  await handler.handle(req, res)
}
