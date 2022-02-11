import Handler from '../../rest/handler'

const handler = new Handler('./components/graphql/Demo4.graphql', [])
export default async (req, res) => {
  await handler.handle(req, res)
}
