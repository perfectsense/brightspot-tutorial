import Handler from '../../rest/handler'

const handler = new Handler('./components/graphql/Demo14ById.graphql', ['id'])
export default async (req, res) => {
  await handler.handle(req, res)
}
